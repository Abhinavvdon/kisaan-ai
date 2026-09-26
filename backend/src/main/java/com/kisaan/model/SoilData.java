package com.kisaan.model;

public class SoilData {
    private double moisturePercentage;
    private String status; // OPTIMAL, LOW, SOUR / SATURATED
    private double soilTemperature;
    private double electricalConductivity; // dS/m
    private String advisoryEn;
    private String advisoryHi;

    public SoilData() {}

    public SoilData(double moisturePercentage, String status, double soilTemperature,
                    double electricalConductivity, String advisoryEn, String advisoryHi) {
        this.moisturePercentage = moisturePercentage;
        this.status = status;
        this.soilTemperature = soilTemperature;
        this.electricalConductivity = electricalConductivity;
        this.advisoryEn = advisoryEn;
        this.advisoryHi = advisoryHi;
    }

    public double getMoisturePercentage() { return moisturePercentage; }
    public void setMoisturePercentage(double moisturePercentage) { this.moisturePercentage = moisturePercentage; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getSoilTemperature() { return soilTemperature; }
    public void setSoilTemperature(double soilTemperature) { this.soilTemperature = soilTemperature; }

    public double getElectricalConductivity() { return electricalConductivity; }
    public void setElectricalConductivity(double electricalConductivity) { this.electricalConductivity = electricalConductivity; }

    public String getAdvisoryEn() { return advisoryEn; }
    public void setAdvisoryEn(String advisoryEn) { this.advisoryEn = advisoryEn; }

    public String getAdvisoryHi() { return advisoryHi; }
    public void setAdvisoryHi(String advisoryHi) { this.advisoryHi = advisoryHi; }
}
