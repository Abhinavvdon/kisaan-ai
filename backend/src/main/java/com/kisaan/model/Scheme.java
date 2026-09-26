package com.kisaan.model;

import java.util.List;

public class Scheme {
    private String id;
    private String name;
    private String nameHi;
    private String category;
    private String description;
    private String descriptionHi;
    private String eligibility;
    private String eligibilityHi;
    private String benefits;
    private String benefitsHi;
    private String officialUrl;
    private List<String> applicableStates; // "All India", "Maharashtra", "Punjab", "Gujarat", etc.
    private boolean isNational;

    public Scheme() {}

    public Scheme(String id, String name, String nameHi, String category,
                  String description, String descriptionHi,
                  String eligibility, String eligibilityHi,
                  String benefits, String benefitsHi,
                  String officialUrl, List<String> applicableStates, boolean isNational) {
        this.id = id;
        this.name = name;
        this.nameHi = nameHi;
        this.category = category;
        this.description = description;
        this.descriptionHi = descriptionHi;
        this.eligibility = eligibility;
        this.eligibilityHi = eligibilityHi;
        this.benefits = benefits;
        this.benefitsHi = benefitsHi;
        this.officialUrl = officialUrl;
        this.applicableStates = applicableStates;
        this.isNational = isNational;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getNameHi() { return nameHi; }
    public void setNameHi(String nameHi) { this.nameHi = nameHi; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDescriptionHi() { return descriptionHi; }
    public void setDescriptionHi(String descriptionHi) { this.descriptionHi = descriptionHi; }
    public String getEligibility() { return eligibility; }
    public void setEligibility(String eligibility) { this.eligibility = eligibility; }
    public String getEligibilityHi() { return eligibilityHi; }
    public void setEligibilityHi(String eligibilityHi) { this.eligibilityHi = eligibilityHi; }
    public String getBenefits() { return benefits; }
    public void setBenefits(String benefits) { this.benefits = benefits; }
    public String getBenefitsHi() { return benefitsHi; }
    public void setBenefitsHi(String benefitsHi) { this.benefitsHi = benefitsHi; }
    public String getOfficialUrl() { return officialUrl; }
    public void setOfficialUrl(String officialUrl) { this.officialUrl = officialUrl; }
    public List<String> getApplicableStates() { return applicableStates; }
    public void setApplicableStates(List<String> applicableStates) { this.applicableStates = applicableStates; }
    public boolean isNational() { return isNational; }
    public void setNational(boolean national) { isNational = national; }
}
