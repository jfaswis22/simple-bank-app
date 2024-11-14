package com.simplebank.simplebankapp.presentation.controller;

import com.simplebank.simplebankapp.persistence.entity.User;
import com.simplebank.simplebankapp.presentation.dto.UserDTO;
import com.simplebank.simplebankapp.service.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<User> findByUser(Authentication authentication) {
        return new ResponseEntity<>(userService.findByUser(authentication), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAllUser() {
        return new ResponseEntity<>(userService.findAllUser(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody @Valid UserDTO userDTO, Authentication authentication) {
        return new ResponseEntity<>(userService.updateUser(userDTO, authentication), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(Authentication authentication) {
        userService.deleteUser(authentication);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
