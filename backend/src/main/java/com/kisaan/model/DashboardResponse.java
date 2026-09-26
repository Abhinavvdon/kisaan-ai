package com.kisaan.model;

import java.util.List;

public class DashboardResponse {
    private String district;
    private String state;
    private double lat;
    private double lon;
    private WeatherData weather;
    private SoilData soilMoisture;
    private List<CropAlert> activeAlerts;

    public DashboardResponse() {}

    public DashboardResponse(String district, String state, double lat, double lon,
                             WeatherData weather, SoilData soilMoisture, List<CropAlert> activeAlerts) {
        this.district = district;
        this.state = state;
        this.lat = lat;
        this.lon = lon;
        this.weather = weather;
        this.soilMoisture = soilMoisture;
        this.activeAlerts = activeAlerts;
    }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public double getLat() { return lat; }
    public void setLat(double lat) { this.lat = lat; }

    public double getLon() { return lon; }
    public void setLon(double lon) { this.lon = lon; }

    public WeatherData getWeather() { return weather; }
    public void setWeather(WeatherData weather) { this.weather = weather; }

    public SoilData getSoilMoisture() { return soilMoisture; }
    public void setSoilMoisture(SoilData soilMoisture) { this.soilMoisture = soilMoisture; }

    public List<CropAlert> getActiveAlerts() { return activeAlerts; }
    public void setActiveAlerts(List<CropAlert> activeAlerts) { this.activeAlerts = activeAlerts; }
}
