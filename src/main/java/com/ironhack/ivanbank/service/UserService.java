package com.ironhack.ivanbank.service;

import com.ironhack.ivanbank.dto.UserDTO;
import com.ironhack.ivanbank.exception.UserNotFoundException;
import com.ironhack.ivanbank.model.User;
import com.ironhack.ivanbank.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User changeUserPasswordByAdmin(Long id, UserDTO userDTO) {
        var user = userRepository.findById(id);
        user.orElseThrow(() -> new UserNotFoundException("User with id '" + id + "' not found"));
        user.get().setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user.get());
    }

    public User changeUserPasswordByUser(UserDTO userDTO) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userDetails = (UserDetails) authentication.getPrincipal();
        var username = userDetails.getUsername();
        var user = userRepository.findByUsername(username).get();
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }
}
