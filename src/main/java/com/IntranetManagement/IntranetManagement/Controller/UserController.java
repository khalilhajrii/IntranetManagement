package com.IntranetManagement.IntranetManagement.Controller;
import com.IntranetManagement.IntranetManagement.Entities.User;
import com.IntranetManagement.IntranetManagement.Services.AuthenticationService;
import com.IntranetManagement.IntranetManagement.Services.JwtService;
import com.IntranetManagement.IntranetManagement.Services.UserService;
import com.IntranetManagement.IntranetManagement.dtos.RegisterUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
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

}
