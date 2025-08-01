package volunteer_Management.volunteer_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import volunteer_Management.volunteer_backend.entity.User;
import volunteer_Management.volunteer_backend.jwt.JwtUtil;
import volunteer_Management.volunteer_backend.repository.UserRepository;
import volunteer_Management.volunteer_backend.dto.LoginRequest;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("❌ Email already registered");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }

        userRepository.save(user);
        return ResponseEntity.ok("✅ Signup successful");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginData) {
        if (loginData.getEmail() == null || loginData.getPassword() == null) {
            return ResponseEntity.badRequest().body("❌ Email and password must not be null");
        }

        Optional<User> userOptional = userRepository.findByEmail(loginData.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (passwordEncoder.matches(loginData.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user.getEmail());

                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("role", user.getRole());

                return ResponseEntity.ok(response);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("❌ Invalid email or password");
    }


}
