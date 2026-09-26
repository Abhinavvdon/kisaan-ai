package com.kisaan.controller;

import com.kisaan.model.ScanResponse;
import com.kisaan.service.GeminiScannerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ScanController {

    private final GeminiScannerService scannerService;

    public ScanController(GeminiScannerService scannerService) {
        this.scannerService = scannerService;
    }

    @PostMapping(value = "/scan", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ScanResponse> scanCrop(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "lat", defaultValue = "19.9975") double lat,
            @RequestParam(value = "lon", defaultValue = "73.7898") double lon,
            @RequestParam(value = "language", defaultValue = "English") String language,
            @RequestParam(value = "cropHint", required = false) String cropHint,
            @RequestParam(value = "apiKey", required = false) String apiKey,
            @RequestHeader(value = "X-Gemini-Key", required = false) String headerApiKey
    ) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        String effectiveKey = (apiKey != null && !apiKey.trim().isEmpty()) ? apiKey : headerApiKey;
        ScanResponse response = scannerService.analyzeCrop(file, lat, lon, language, cropHint, effectiveKey);
        return ResponseEntity.ok(response);
    }
}
