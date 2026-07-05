package com.example.englishlearning.service.impl;

import com.example.englishlearning.entity.User;
import com.example.englishlearning.reposistory.UserRepository;
import com.example.englishlearning.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User save(User user) {
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getRole() == null) {
            user.setRole("USER");
        }
        if (user.getStatus() == null) {
            user.setStatus(1);
        }
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(LocalDateTime.now());
        }
        return userRepository.save(user);
    }

    @Override
    public User update(Integer id, User user) {
        User oldUser = getById(id);

        oldUser.setFullName(user.getFullName());
        oldUser.setEmail(user.getEmail());
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            if (!user.getPassword().startsWith("$2a$")) {
                oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
            } else {
                oldUser.setPassword(user.getPassword());
            }
        }
        oldUser.setAvatar(user.getAvatar());
        oldUser.setRole(user.getRole());
        oldUser.setStatus(user.getStatus());
        oldUser.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(oldUser);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email không tồn tại"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Sai mật khẩu");
        }

        return user;
    }
}

