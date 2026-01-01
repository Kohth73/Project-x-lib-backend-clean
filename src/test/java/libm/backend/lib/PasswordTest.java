package libm.backend.lib;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "monitor123";
        String hash = "$2a$10$GXrXirB5kXBUttMMJ4Y6s.jc1odvRA9kS7Dv4mqTEqwGZ2mj5nYHO";

        boolean matches = encoder.matches(rawPassword, hash);
        System.out.println("Password matches? " + matches);
    }
}
