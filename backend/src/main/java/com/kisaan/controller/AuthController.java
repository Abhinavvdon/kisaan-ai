package com.kisaan.controller;

import com.kisaan.model.ScanRecord;
import com.kisaan.model.User;
import com.kisaan.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, Object> payload) {
        try {
            String fullName = (String) payload.get("fullName");
            String email = (String) payload.get("email");
            String phone = (String) payload.get("phoneNumber");
            String password = (String) payload.get("password");
            String district = (String) payload.getOrDefault("district", "Nashik");
            String state = (String) payload.getOrDefault("state", "Maharashtra");
            Double landSize = payload.get("landSizeAcres") != null ?
                    Double.valueOf(payload.get("landSizeAcres").toString()) : 2.0;
            String crops = (String) payload.getOrDefault("primaryCrops", "Vegetables");

            Map<String, Object> result = authService.register(
                    fullName, email, phone, password, district, state, landSize, crops
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        try {
            String loginId = payload.get("loginId");
            String password = payload.get("password");
            Map<String, Object> result = authService.login(loginId, password);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader(value = "Authorization", required = false) String authHeader,
                                            @RequestParam(value = "token", required = false) String tokenParam) {
        String token = tokenParam;
        if (token == null && authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "No token provided"));
        }

        return authService.getUserByToken(token)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestParam Long userId, @RequestBody Map<String, Object> payload) {
        try {
            String fullName = (String) payload.get("fullName");
            String phone = (String) payload.get("phoneNumber");
            String district = (String) payload.get("district");
            String state = (String) payload.get("state");
            Double landSize = payload.get("landSizeAcres") != null ?
                    Double.valueOf(payload.get("landSizeAcres").toString()) : null;
            String crops = (String) payload.get("primaryCrops");

            User updated = authService.updateProfile(userId, fullName, phone, district, state, landSize, crops);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PostMapping("/scan-history")
    public ResponseEntity<ScanRecord> recordScan(@RequestBody Map<String, Object> payload) {
        Long userId = payload.get("userId") != null ? Long.valueOf(payload.get("userId").toString()) : 1L;
        String crop = (String) payload.getOrDefault("cropName", "Crop");
        String disease = (String) payload.getOrDefault("diseaseName", "Diagnosed Pathology");
        int confidence = payload.get("confidence") != null ? Integer.parseInt(payload.get("confidence").toString()) : 85;
        String severity = (String) payload.getOrDefault("severity", "moderate");
        String action = (String) payload.getOrDefault("recommendedAction", "");
        String risk = (String) payload.getOrDefault("weatherRisk", "");

        ScanRecord saved = authService.recordScan(userId, crop, disease, confidence, severity, action, risk);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/scan-history")
    public ResponseEntity<List<ScanRecord>> getScanHistory(@RequestParam Long userId) {
        return ResponseEntity.ok(authService.getUserScanHistory(userId));
    }
}
