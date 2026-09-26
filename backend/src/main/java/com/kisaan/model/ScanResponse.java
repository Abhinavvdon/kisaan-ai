package com.kisaan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ScanResponse {
    @JsonProperty("candidates")
    private List<CandidateDiagnosis> candidates;

    @JsonProperty("recommended_action")
    private String recommendedAction;

    @JsonProperty("organic_alternative")
    private String organicAlternative;

    @JsonProperty("is_healthy")
    private boolean isHealthy;

    @JsonProperty("weather_risk_note")
    private String weatherRiskNote;

    @JsonProperty("model_used")
    private String modelUsed;

    public ScanResponse() {}

    public List<CandidateDiagnosis> getCandidates() { return candidates; }
    public void setCandidates(List<CandidateDiagnosis> candidates) { this.candidates = candidates; }

    public String getRecommendedAction() { return recommendedAction; }
    public void setRecommendedAction(String recommendedAction) { this.recommendedAction = recommendedAction; }

    public String getOrganicAlternative() { return organicAlternative; }
    public void setOrganicAlternative(String organicAlternative) { this.organicAlternative = organicAlternative; }

    public boolean isHealthy() { return isHealthy; }
    public void setHealthy(boolean healthy) { isHealthy = healthy; }

    public String getWeatherRiskNote() { return weatherRiskNote; }
    public void setWeatherRiskNote(String weatherRiskNote) { this.weatherRiskNote = weatherRiskNote; }

    public String getModelUsed() { return modelUsed; }
    public void setModelUsed(String modelUsed) { this.modelUsed = modelUsed; }
}
