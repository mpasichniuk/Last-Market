import java.util.Arrays;
import java.util.HashSet;

import org.h2.engine.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerNewUser(User user) throws UserAlreadyExistsException {
        if (userRepository.findUserByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("User already exists with username: " + user.getUsername());
        }
        user.setRoles(new HashSet<>(Arrays.asList(Role.USER)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }


    public void save(User user) {
    }


}
