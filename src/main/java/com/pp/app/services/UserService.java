package com.pp.app.services;

import com.pp.app.enums.Roles;
import com.pp.app.models.User;
import com.pp.app.repositories.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean createUser(User user){
        String email = user.getEmail();
        if (userRepo.findByEmail(email) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Roles.USER);
        userRepo.save(user);
        return true;
    }

    public void banUser(Long id) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()){
                user.setActive(false);
            } else {
                user.setActive(true);
            }
        }
        userRepo.save(user);
    }

    public List<User> list() {
        return userRepo.findAll();
    }

    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Roles.values())
                .map(Roles::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Roles.valueOf(key));
            }
        }
        userRepo.save(user);
    }

}
