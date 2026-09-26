package com.kisaan.model;

public class CropAlert {
    private String id;
    private String district;
    private String state;
    private String crop;
    private String pestOrDisease;
    private String severity; // HIGH, MEDIUM, LOW
    private String messageEn;
    private String messageHi;
    private String preventativeActionEn;
    private String preventativeActionHi;

    public CropAlert() {}

    public CropAlert(String id, String district, String state, String crop, String pestOrDisease,
                     String severity, String messageEn, String messageHi,
                     String preventativeActionEn, String preventativeActionHi) {
        this.id = id;
        this.district = district;
        this.state = state;
        this.crop = crop;
        this.pestOrDisease = pestOrDisease;
        this.severity = severity;
        this.messageEn = messageEn;
        this.messageHi = messageHi;
        this.preventativeActionEn = preventativeActionEn;
        this.preventativeActionHi = preventativeActionHi;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getCrop() { return crop; }
    public void setCrop(String crop) { this.crop = crop; }
    public String getPestOrDisease() { return pestOrDisease; }
    public void setPestOrDisease(String pestOrDisease) { this.pestOrDisease = pestOrDisease; }
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    public String getMessageEn() { return messageEn; }
    public void setMessageEn(String messageEn) { this.messageEn = messageEn; }
    public String getMessageHi() { return messageHi; }
    public void setMessageHi(String messageHi) { this.messageHi = messageHi; }
    public String getPreventativeActionEn() { return preventativeActionEn; }
    public void setPreventativeActionEn(String preventativeActionEn) { this.preventativeActionEn = preventativeActionEn; }
    public String getPreventativeActionHi() { return preventativeActionHi; }
    public void setPreventativeActionHi(String preventativeActionHi) { this.preventativeActionHi = preventativeActionHi; }
}
