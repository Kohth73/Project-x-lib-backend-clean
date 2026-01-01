package libm.backend.lib;

import libm.backend.lib.config.JwtUtil;

public class JwtCliTest {
    public static void main(String[] args) {
        JwtUtil jwtUtil = new JwtUtil();

        // --- Inject secret using reflection ---
        String secret = "bGlibS1iYWNrZW5kLWp3dC1zZWNyZXQta2V5LTIwMjUtc2VjdXJlLTI1NmJpdA==";
        try {
            java.lang.reflect.Field field = JwtUtil.class.getDeclaredField("secretKey");
            field.setAccessible(true);
            field.set(jwtUtil, secret);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to set JWT secret.");
            return;
        }

        // --- Generate and validate token ---
        String username = "monitor@example.com";
        String token = jwtUtil.generateToken(username);
        boolean valid = jwtUtil.validateToken(token, username);

        // --- Print results ---
        System.out.println("Token: " + token);
        System.out.println("Valid? " + valid);
    }
}


