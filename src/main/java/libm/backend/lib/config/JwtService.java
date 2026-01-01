package libm.backend.lib.config;

import libm.backend.lib.entity.User;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    public String generateToken(User user) {
        // Implement JWT generation logic
        return "dummy-token";
    }

    public boolean validateToken(String token, User user) {
        // Implement token validation
        return true;
    }
}
