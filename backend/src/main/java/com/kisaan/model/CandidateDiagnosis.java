package com.kisaan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CandidateDiagnosis {
    @JsonProperty("disease_name")
    private String diseaseName;

    @JsonProperty("confidence")
    private int confidence;

    @JsonProperty("severity")
    private String severity; // mild, moderate, severe

    @JsonProperty("affected_area_description")
    private String affectedAreaDescription;

    @JsonProperty("is_healthy")
    private Boolean isHealthy;

    public CandidateDiagnosis() {}

    public CandidateDiagnosis(String diseaseName, int confidence, String severity, String affectedAreaDescription) {
        this.diseaseName = diseaseName;
        this.confidence = confidence;
        this.severity = severity;
        this.affectedAreaDescription = affectedAreaDescription;
    }

    public String getDiseaseName() { return diseaseName; }
    public void setDiseaseName(String diseaseName) { this.diseaseName = diseaseName; }

    public int getConfidence() { return confidence; }
    public void setConfidence(int confidence) { this.confidence = confidence; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getAffectedAreaDescription() { return affectedAreaDescription; }
    public void setAffectedAreaDescription(String affectedAreaDescription) { this.affectedAreaDescription = affectedAreaDescription; }

    public Boolean getIsHealthy() { return isHealthy; }
    public void setIsHealthy(Boolean isHealthy) { this.isHealthy = isHealthy; }
}
