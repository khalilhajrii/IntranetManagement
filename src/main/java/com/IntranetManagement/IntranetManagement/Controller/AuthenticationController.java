package com.IntranetManagement.IntranetManagement.Controller;

import com.IntranetManagement.IntranetManagement.Entities.User;
import com.IntranetManagement.IntranetManagement.Services.AuthenticationService;
import com.IntranetManagement.IntranetManagement.Services.JwtService;
import com.IntranetManagement.IntranetManagement.dtos.LoginResponse;
import com.IntranetManagement.IntranetManagement.dtos.LoginUserRequestDto;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserRequestDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
