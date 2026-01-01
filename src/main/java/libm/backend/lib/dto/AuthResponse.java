package libm.backend.lib.dto;

import java.util.Objects;

public class AuthResponse {

    private String token;
    private UserDto user;

    public AuthResponse() { }

    public AuthResponse(String token, UserDto user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public UserDto getUser() { return user; }
    public void setUser(UserDto user) { this.user = user; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthResponse that)) return false;
        return Objects.equals(getToken(), that.getToken()) &&
               Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken(), getUser());
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "token='" + token + '\'' +
                ", user=" + user +
                '}';
    }
}
