package com.kisaan.controller;

import com.kisaan.model.CropAlert;
import com.kisaan.model.DashboardResponse;
import com.kisaan.model.SoilData;
import com.kisaan.model.WeatherData;
import com.kisaan.service.AlertService;
import com.kisaan.service.SoilMoistureService;
import com.kisaan.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DashboardController {

    private final WeatherService weatherService;
    private final SoilMoistureService soilMoistureService;
    private final AlertService alertService;

    public DashboardController(WeatherService weatherService,
                               SoilMoistureService soilMoistureService,
                               AlertService alertService) {
        this.weatherService = weatherService;
        this.soilMoistureService = soilMoistureService;
        this.alertService = alertService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardResponse> getDashboard(
            @RequestParam(defaultValue = "19.9975") double lat,
            @RequestParam(defaultValue = "73.7898") double lon,
            @RequestParam(defaultValue = "Nashik") String district,
            @RequestParam(defaultValue = "Maharashtra") String state
    ) {
        WeatherData weather = weatherService.getWeather(lat, lon);
        SoilData soil = soilMoistureService.getSoilMoisture(lat, lon);
        List<CropAlert> alerts = alertService.getAlertsForLocation(district, state);

        DashboardResponse response = new DashboardResponse(
                district, state, lat, lon, weather, soil, alerts
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/alerts")
    public ResponseEntity<List<CropAlert>> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }
}
