package com.IntranetManagement.IntranetManagement.Controller;

import com.IntranetManagement.IntranetManagement.Entities.User;
import com.IntranetManagement.IntranetManagement.Services.AuthenticationService;
import com.IntranetManagement.IntranetManagement.Services.JwtService;
import com.IntranetManagement.IntranetManagement.dtos.LoginResponse;
import com.IntranetManagement.IntranetManagement.dtos.LoginUserDto;
import com.IntranetManagement.IntranetManagement.dtos.RegisterUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = new User();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

            String currentUsername = authentication.getName(); // Get the username (or email)
            System.out.println("Authenticated user: " + currentUsername);

            registeredUser = authenticationService.signup(registerUserDto);
        } catch (Exception ex) {
            System.out.println("Error during user registration: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return error response
        }
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
