package libm.backend.lib.controller;

import libm.backend.lib.config.JwtUtil;
import libm.backend.lib.dto.LoginRequest;
import libm.backend.lib.dto.LoginResponse;
import libm.backend.lib.dto.UserDto;
import libm.backend.lib.entity.User;
import libm.backend.lib.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // ===== LOGIN =====
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        System.out.println("LOGIN HIT → email = " + loginRequest.getEmail());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String token = jwtUtil.generateToken(userDetails.getUsername());

            System.out.println("Authentication success → JWT issued for " + userDetails.getUsername());

            return ResponseEntity.ok(
                    new LoginResponse(userDetails.getUsername(), token)
            );

        } catch (BadCredentialsException ex) {
            System.out.println("Bad credentials for email: " + loginRequest.getEmail());
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    // ===== REGISTER =====
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {

        if (userService.getUserByEmail(userDto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        User user = userService.createUser(userDto);
        System.out.println("User registered → " + user.getEmail());

        return ResponseEntity.ok(user);
    }
}

