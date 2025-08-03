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
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("✅ Volunteer Backend is running!");
    }

    @GetMapping("/cleanup-duplicates")
    public ResponseEntity<String> cleanupDuplicates() {
        try {
            // This is a temporary endpoint to help with database cleanup
            // In production, you should handle this properly
            return ResponseEntity.ok("✅ Duplicate cleanup endpoint available");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("❌ Cleanup failed: " + e.getMessage());
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("✅ Auth controller is working!");
    }

    @PostMapping("/test-signup")
    public ResponseEntity<?> testSignup(@RequestBody User user) {
        try {
            return ResponseEntity.ok("✅ Test signup received: " + user.getEmail());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("❌ Test failed: " + e.getMessage());
        }
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            // Validate required fields
            if (user.getEmail() == null || user.getEmail().isEmpty()) {
                return ResponseEntity.badRequest().body("❌ Email is required");
            }
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body("❌ Password is required");
            }

            // Check for existing users with the same email
            List<User> existingUsers = userRepository.findAllByEmail(user.getEmail());
            if (!existingUsers.isEmpty()) {
                return ResponseEntity.badRequest().body("❌ Email already registered");
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            if (user.getRole() == null || user.getRole().isEmpty()) {
                user.setRole("USER");
            }

            User savedUser = userRepository.save(user);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "✅ Signup successful");
            response.put("userId", savedUser.getId());
            response.put("email", savedUser.getEmail());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Signup error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Signup failed: " + e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginData) {
        if (loginData.getEmail() == null || loginData.getPassword() == null) {
            return ResponseEntity.badRequest().body("❌ Email and password must not be null");
        }

        // Check for users with the same email
        List<User> usersWithEmail = userRepository.findAllByEmail(loginData.getEmail());
        
        if (!usersWithEmail.isEmpty()) {
            // Use the first user found (you might want to clean up duplicates later)
            User user = usersWithEmail.get(0);

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
