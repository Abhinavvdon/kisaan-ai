package com.kisaan.service;

import com.kisaan.model.ScanRecord;
import com.kisaan.model.User;
import com.kisaan.repository.ScanRecordRepository;
import com.kisaan.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final ScanRecordRepository scanRecordRepository;

    // Simple in-memory session token mapping: token -> User
    private final Map<String, Long> activeTokens = new HashMap<>();

    public AuthService(UserRepository userRepository, ScanRecordRepository scanRecordRepository) {
        this.userRepository = userRepository;
        this.scanRecordRepository = scanRecordRepository;
    }

    @PostConstruct
    public void initDemoUsers() {
        if (!userRepository.existsByEmail("ramesh@kisaan.ai")) {
            User ramesh = new User(
                    "Ramesh Patil",
                    "ramesh@kisaan.ai",
                    "+91 98220 12345",
                    hashPassword("kisaan123"),
                    "Nashik",
                    "Maharashtra",
                    4.5,
                    "Onion, Tomato, Grapes"
            );
            ramesh = userRepository.save(ramesh);

            // Pre-seed some scan history for demo
            scanRecordRepository.save(new ScanRecord(
                    ramesh.getId(),
                    "Tomato",
                    "Early Blight (Alternaria solani)",
                    88,
                    "moderate",
                    "Apply Mancozeb 75 WP @ 2.5 g/L",
                    "High humidity (74%) increases fungal spore spread risk."
            ));
            scanRecordRepository.save(new ScanRecord(
                    ramesh.getId(),
                    "Grapes",
                    "Downy Mildew (Plasmopara viticola)",
                    92,
                    "severe",
                    "Spray 1% Bordeaux mixture immediately",
                    "Morning dew and high humidity accelerate vine sporulation."
            ));
        }

        if (!userRepository.existsByEmail("harpreet@kisaan.ai")) {
            User harpreet = new User(
                    "Harpreet Singh",
                    "harpreet@kisaan.ai",
                    "+91 98140 54321",
                    hashPassword("kisaan123"),
                    "Ludhiana",
                    "Punjab",
                    8.0,
                    "Wheat, Basmati Rice, Mustard"
            );
            harpreet = userRepository.save(harpreet);

            scanRecordRepository.save(new ScanRecord(
                    harpreet.getId(),
                    "Wheat",
                    "Yellow Rust (Puccinia striiformis)",
                    86,
                    "moderate",
                    "Spray Propiconazole 25 EC @ 1 ml/L",
                    "Cool morning conditions favor stripe rust expansion."
            ));
        }
    }

    @Transactional
    public Map<String, Object> register(String fullName, String email, String phone,
                                       String password, String district, String state,
                                       Double landSize, String crops) {
        if (userRepository.existsByEmail(email.toLowerCase().trim())) {
            throw new IllegalArgumentException("An account with this email already exists.");
        }

        User user = new User(
                fullName.trim(),
                email.toLowerCase().trim(),
                phone != null ? phone.trim() : "",
                hashPassword(password),
                district != null ? district.trim() : "Nashik",
                state != null ? state.trim() : "Maharashtra",
                landSize != null ? landSize : 2.0,
                crops != null ? crops.trim() : "General Crops"
        );

        user = userRepository.save(user);
        String token = generateToken(user.getId());

        Map<String, Object> res = new HashMap<>();
        res.put("token", token);
        res.put("user", user);
        return res;
    }

    public Map<String, Object> login(String loginId, String password) {
        String cleanId = loginId.trim().toLowerCase();
        Optional<User> userOpt = userRepository.findByEmail(cleanId);
        if (userOpt.isEmpty()) {
            userOpt = userRepository.findByPhoneNumber(loginId.trim());
        }

        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("No farmer account found with this email or phone.");
        }

        User user = userOpt.get();
        if (!user.getPasswordHash().equals(hashPassword(password))) {
            throw new IllegalArgumentException("Incorrect password. Please try again.");
        }

        String token = generateToken(user.getId());
        Map<String, Object> res = new HashMap<>();
        res.put("token", token);
        res.put("user", user);
        return res;
    }

    public Optional<User> getUserByToken(String token) {
        Long userId = activeTokens.get(token);
        if (userId == null) return Optional.empty();
        return userRepository.findById(userId);
    }

    @Transactional
    public User updateProfile(Long userId, String fullName, String phone, String district,
                              String state, Double landSize, String crops) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (fullName != null && !fullName.trim().isEmpty()) user.setFullName(fullName.trim());
        if (phone != null) user.setPhoneNumber(phone.trim());
        if (district != null && !district.trim().isEmpty()) user.setDistrict(district.trim());
        if (state != null && !state.trim().isEmpty()) user.setState(state.trim());
        if (landSize != null) user.setLandSizeAcres(landSize);
        if (crops != null) user.setPrimaryCrops(crops.trim());

        return userRepository.save(user);
    }

    @Transactional
    public ScanRecord recordScan(Long userId, String crop, String disease, int confidence,
                                 String severity, String action, String risk) {
        ScanRecord record = new ScanRecord(userId, crop, disease, confidence, severity, action, risk);
        return scanRecordRepository.save(record);
    }

    public List<ScanRecord> getUserScanHistory(Long userId) {
        return scanRecordRepository.findByUserIdOrderByScannedAtDesc(userId);
    }

    private String generateToken(Long userId) {
        String token = "KSN-" + UUID.randomUUID().toString();
        activeTokens.put(token, userId);
        return token;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            return String.valueOf(password.hashCode());
        }
    }
}
