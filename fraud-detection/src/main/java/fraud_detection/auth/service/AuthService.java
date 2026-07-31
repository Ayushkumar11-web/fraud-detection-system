package fraud_detection.auth.service;

import fraud_detection.auth.dto.LoginRequest;
import fraud_detection.auth.dto.LoginResponse;
import fraud_detection.auth.dto.RegisterRequest;
import fraud_detection.auth.entity.Admin;
import fraud_detection.auth.repository.AdminRepository;
import fraud_detection.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ===========================
    // Register Admin
    // ===========================
    public String register(RegisterRequest request) {

        if (adminRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        Admin admin = new Admin();

        admin.setName(request.getName());
        admin.setEmail(request.getEmail());

        // Encrypt Password
        admin.setPassword(passwordEncoder.encode(request.getPassword()));

        adminRepository.save(admin);

        return "Admin Registered Successfully";
    }

    // ===========================
    // Login Admin
    // ===========================
    public LoginResponse login(LoginRequest request) {

        Admin admin = adminRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid Email"));

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtService.generateToken(admin.getEmail());

        return new LoginResponse(token);
    }
}