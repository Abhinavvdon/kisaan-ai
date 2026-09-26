package com.kisaan.model;

import java.util.List;

public class WeatherData {
    private String condition;
    private String icon;
    private double temperature;
    private double feelsLike;
    private int humidity;
    private double windSpeed;
    private int rainChance;
    private String forecastBrief;
    private List<ForecastDay> forecast;

    public static class ForecastDay {
        private String day;
        private double tempMax;
        private double tempMin;
        private String condition;
        private int rainChance;

        public ForecastDay() {}
        public ForecastDay(String day, double tempMax, double tempMin, String condition, int rainChance) {
            this.day = day;
            this.tempMax = tempMax;
            this.tempMin = tempMin;
            this.condition = condition;
            this.rainChance = rainChance;
        }

        public String getDay() { return day; }
        public void setDay(String day) { this.day = day; }
        public double getTempMax() { return tempMax; }
        public void setTempMax(double tempMax) { this.tempMax = tempMax; }
        public double getTempMin() { return tempMin; }
        public void setTempMin(double tempMin) { this.tempMin = tempMin; }
        public String getCondition() { return condition; }
        public void setCondition(String condition) { this.condition = condition; }
        public int getRainChance() { return rainChance; }
        public void setRainChance(int rainChance) { this.rainChance = rainChance; }
    }

    public WeatherData() {}

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    public double getFeelsLike() { return feelsLike; }
    public void setFeelsLike(double feelsLike) { this.feelsLike = feelsLike; }
    public int getHumidity() { return humidity; }
    public void setHumidity(int humidity) { this.humidity = humidity; }
    public double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }
    public int getRainChance() { return rainChance; }
    public void setRainChance(int rainChance) { this.rainChance = rainChance; }
    public String getForecastBrief() { return forecastBrief; }
    public void setForecastBrief(String forecastBrief) { this.forecastBrief = forecastBrief; }
    public List<ForecastDay> getForecast() { return forecast; }
    public void setForecast(List<ForecastDay> forecast) { this.forecast = forecast; }
}
