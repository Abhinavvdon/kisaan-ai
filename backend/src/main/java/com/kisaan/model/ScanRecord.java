package com.kisaan.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "scan_records")
public class ScanRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String cropName;

    private String diseaseName;

    private int confidence;

    private String severity;

    @Column(columnDefinition = "TEXT")
    private String recommendedAction;

    @Column(columnDefinition = "TEXT")
    private String weatherRisk;

    private LocalDateTime scannedAt;

    public ScanRecord() {}

    public ScanRecord(Long userId, String cropName, String diseaseName, int confidence,
                      String severity, String recommendedAction, String weatherRisk) {
        this.userId = userId;
        this.cropName = cropName;
        this.diseaseName = diseaseName;
        this.confidence = confidence;
        this.severity = severity;
        this.recommendedAction = recommendedAction;
        this.weatherRisk = weatherRisk;
        this.scannedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getCropName() { return cropName; }
    public void setCropName(String cropName) { this.cropName = cropName; }

    public String getDiseaseName() { return diseaseName; }
    public void setDiseaseName(String diseaseName) { this.diseaseName = diseaseName; }

    public int getConfidence() { return confidence; }
    public void setConfidence(int confidence) { this.confidence = confidence; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getRecommendedAction() { return recommendedAction; }
    public void setRecommendedAction(String recommendedAction) { this.recommendedAction = recommendedAction; }

    public String getWeatherRisk() { return weatherRisk; }
    public void setWeatherRisk(String weatherRisk) { this.weatherRisk = weatherRisk; }

    public LocalDateTime getScannedAt() { return scannedAt; }
    public void setScannedAt(LocalDateTime scannedAt) { this.scannedAt = scannedAt; }
}
