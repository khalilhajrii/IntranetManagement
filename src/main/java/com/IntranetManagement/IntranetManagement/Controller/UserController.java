package com.IntranetManagement.IntranetManagement.Controller;
import com.IntranetManagement.IntranetManagement.Entities.User;
import com.IntranetManagement.IntranetManagement.Services.UserService;
import com.IntranetManagement.IntranetManagement.dtos.RegisterUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService UserService;

    public UserController(UserService userService) {
        this.UserService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = new User();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

            String currentUsername = authentication.getName();
            System.out.println("Authenticated user: " + currentUsername);

            registeredUser = UserService.saveUser(registerUserDto);
        } catch (Exception ex) {
            System.out.println("Error during user registration: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return error response
        }
        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/GetAllUsers")
    public ResponseEntity<List<User>> GetAllUsers() {
        List<User> Users = new ArrayList<>();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
            Users = UserService.GetAllUsers();
        } catch (Exception ex) {
            System.out.println("Error during user Getting Users: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return error response
        }
        return ResponseEntity.ok(Users);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long userId) {
        Optional<User> Users = Optional.of(new User());
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
            Users = UserService.getuserById(userId);
        } catch (Exception ex) {
            System.out.println("Error during user Getting Users: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return error response
        }
        return ResponseEntity.ok(Users);
    }


    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<Void> DeleteUser(@RequestParam Long userId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            };

            UserService.deleteUser(userId);
        } catch (Exception ex) {
            System.out.println("Error during user registration: " + ex.getMessage());
        }
        return null;
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody RegisterUserDto registerUserDto
    ) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
            String currentUsername = authentication.getName();
            System.out.println("Authenticated user: " + currentUsername);
            User updatedUser = UserService.updateUser(id, registerUserDto);
            return ResponseEntity.ok(updatedUser);

        } catch (Exception ex) {
            System.out.println("Error during user update: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return error response
        }
    }



}
