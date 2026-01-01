package libm.backend.lib.service;

import libm.backend.lib.dto.UserDto;
import libm.backend.lib.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(UserDto userDto);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserById(Long id);

    List<User> getAllUsers();

    User saveUser(User user);

    void deleteUser(Long id);

    String encodePassword(String rawPassword);

    UserDto convertToDto(User user);
}








