package libm.backend.lib.controller;

import libm.backend.lib.dto.UserDto;
import libm.backend.lib.entity.User;
import libm.backend.lib.service.UserService;
import libm.backend.lib.config.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // ===== LOGIN =====
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {

        System.out.println("LOGIN HIT → email = " + userDto.getEmail());
        System.out.println("Password received = " + userDto.getPassword());

        try {
            // Authenticate user
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDto.getEmail(),
                            userDto.getPassword()
                    )
            );
            System.out.println("Authentication success");

            // Load user from DB
            Optional<User> optionalUser = userService.getUserByEmail(userDto.getEmail());
            if (optionalUser.isEmpty()) {
                return ResponseEntity.status(404).body("User not found");
            }
            User user = optionalUser.get();
            System.out.println("User loaded → " + user.getEmail());

            // Generate JWT token
            final String token = jwtUtil.generateToken(user.getEmail());
            System.out.println("JWT token generated → " + token);

            return ResponseEntity.ok(new AuthResponse(token));

        } catch (BadCredentialsException e) {
            System.out.println("Bad credentials");
            return ResponseEntity.status(401).body("Invalid email or password");
        } catch (Exception e) {
            System.out.println("Login error → " + e.getMessage());
            return ResponseEntity.status(500).body("Internal server error");
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

    // ===== AuthResponse Class =====
    public static class AuthResponse {
        private String token;

        public AuthResponse(String token) {
            this.token = token;
        }

        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
    }
}





