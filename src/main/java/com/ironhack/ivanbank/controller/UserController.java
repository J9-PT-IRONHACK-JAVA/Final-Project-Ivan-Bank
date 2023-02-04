package com.ironhack.ivanbank.controller;

import com.ironhack.ivanbank.dto.UserDTO;
import com.ironhack.ivanbank.model.User;
import com.ironhack.ivanbank.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping
    @RequestMapping("/{id}")
    public User changeUserPasswordByAdmin(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) {
        return userService.changeUserPasswordByAdmin(id, userDTO);
    }

    @PatchMapping
    @RequestMapping("/password")
    public User changeUserPasswordByUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.changeUserPasswordByUser(userDTO);
    }
}
