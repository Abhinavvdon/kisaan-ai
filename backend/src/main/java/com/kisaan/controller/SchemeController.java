package com.kisaan.controller;

import com.kisaan.model.Scheme;
import com.kisaan.service.SchemeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/schemes")
public class SchemeController {

    private final SchemeService schemeService;

    public SchemeController(SchemeService schemeService) {
        this.schemeService = schemeService;
    }

    @GetMapping
    public ResponseEntity<List<Scheme>> getSchemes(
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(schemeService.getSchemes(state, category, search));
    }

    @PostMapping("/live-fetch")
    public ResponseEntity<List<Scheme>> fetchLiveSchemes(@RequestBody Map<String, String> payload) {
        String query = payload.getOrDefault("query", "");
        String state = payload.getOrDefault("state", "All India");
        String apiKey = payload.getOrDefault("apiKey", "");
        return ResponseEntity.ok(schemeService.fetchLiveSchemes(query, state, apiKey));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getCategories() {
        return ResponseEntity.ok(schemeService.getCategories());
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        List<Scheme> all = schemeService.getAllSchemes();
        long nationalCount = all.stream().filter(Scheme::isNational).count();
        long stateCount = all.size() - nationalCount;

        Map<String, Object> stats = new HashMap<>();
        stats.put("totalSchemes", all.size());
        stats.put("nationalSchemes", nationalCount);
        stats.put("stateSchemes", stateCount);
        stats.put("categoriesCount", schemeService.getCategories().size());
        return ResponseEntity.ok(stats);
    }
}
