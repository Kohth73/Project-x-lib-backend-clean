package libm.backend.lib.controller;

import libm.backend.lib.dto.UserDto;
import libm.backend.lib.entity.User;
import libm.backend.lib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('PROFILE_VIEW')")
    public ResponseEntity<UserDto> getProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOpt = userService.getUserByEmail(email);
        return userOpt.map(user -> ResponseEntity.ok(userService.convertToDto(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('PROFILE_UPDATE_SELF')")
    public ResponseEntity<UserDto> updateOwnProfile(@RequestBody UserDto updatedDto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> existingOpt = userService.getUserByEmail(email);

        if (!existingOpt.isPresent()) return ResponseEntity.notFound().build();

        User existing = existingOpt.get();
        if (updatedDto.getName() != null) existing.setName(updatedDto.getName());
        if (updatedDto.getPassword() != null)
            existing.setPassword(userService.encodePassword(updatedDto.getPassword()));

        User saved = userService.saveUser(existing);
        return ResponseEntity.ok(userService.convertToDto(saved));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('PROFILE_UPDATE_ANY')")
    public ResponseEntity<UserDto> updateAnyProfile(@PathVariable Long id, @RequestBody UserDto updatedDto) {
        Optional<User> existingOpt = userService.getUserById(id);
        if (!existingOpt.isPresent()) return ResponseEntity.notFound().build();

        User existing = existingOpt.get();
        if (updatedDto.getName() != null) existing.setName(updatedDto.getName());
        if (updatedDto.getPassword() != null)
            existing.setPassword(userService.encodePassword(updatedDto.getPassword()));

        User saved = userService.saveUser(existing);
        return ResponseEntity.ok(userService.convertToDto(saved));
    }
}




