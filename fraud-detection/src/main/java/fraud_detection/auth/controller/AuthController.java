package fraud_detection.auth.controller;

import fraud_detection.auth.dto.LoginRequest;
import fraud_detection.auth.dto.LoginResponse;
import fraud_detection.auth.dto.RegisterRequest;
import fraud_detection.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5174")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}