package kz.edu.astanait.ajp2_final_project.services;

import kz.edu.astanait.ajp2_final_project.models.User;
import kz.edu.astanait.ajp2_final_project.repositories.RoleRepository;
import kz.edu.astanait.ajp2_final_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public void register(User user) {
        if(userRepository.existsByUsername(user.getUsername())){
            return;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findByName("USER"));

        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
