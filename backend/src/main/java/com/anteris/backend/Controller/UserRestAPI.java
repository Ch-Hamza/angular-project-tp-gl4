package com.anteris.backend.Controller;

import com.anteris.backend.Message.request.SignUpForm;
import com.anteris.backend.Message.request.UserInfo;
import com.anteris.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserRestAPI {

    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserInfo>> allUsers() {
        return userService.getAll();
    }

    @GetMapping("/role/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserInfo>> UsersByRole(@PathVariable("role") String roleR) {
        return userService.findByRole(roleR);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserInfo> getUserById(@PathVariable("id") long id) {
        return userService.findById(id);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> newUser(@RequestBody SignUpForm signUpRequest) {
        return userService.newUser(signUpRequest);
    }

    @PutMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@RequestBody UserInfo user) {
        return userService.updateUser(user);
    }

    @PutMapping("/change-password")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable("email") String email) {
        return userService.updateUserPassword(email);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> banUserById(@PathVariable("id") Long id) {
        return userService.banUserById(id);
    }

    @DeleteMapping("/unban/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> unbanUserById(@PathVariable("id") Long id) {
        return userService.unbanUserById(id);
    }
}
