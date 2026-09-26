package com.kisaan.service;

import com.kisaan.model.SoilData;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SoilMoistureService {

    public SoilData getSoilMoisture(double lat, double lon) {
        // Deterministic pseudo-randomness seeded by geographical coordinates
        long seed = Double.doubleToLongBits(lat * 1000.0) ^ Double.doubleToLongBits(lon * 1000.0);
        Random random = new Random(seed);

        // Range 20.5% to 32.5%
        double moisture = 20.5 + (random.nextDouble() * 12.0);
        moisture = Math.round(moisture * 10.0) / 10.0;

        double soilTemp = 22.0 + (random.nextDouble() * 5.0);
        soilTemp = Math.round(soilTemp * 10.0) / 10.0;

        double ec = 0.6 + (random.nextDouble() * 0.5);
        ec = Math.round(ec * 100.0) / 100.0;

        String status;
        String advisoryEn;
        String advisoryHi;

        if (moisture < 23.0) {
            status = "LOW";
            advisoryEn = "Root zone moisture is below target. Light drip irrigation recommended within 24 hours.";
            advisoryHi = "जड़ क्षेत्र में नमी कम है। अगले 24 घंटों में हल्की ड्रिप सिंचाई की सलाह दी जाती है।";
        } else if (moisture > 30.0) {
            status = "HIGH";
            advisoryEn = "Soil is near saturation. Hold off irrigation and ensure furrows have clear drainage.";
            advisoryHi = "मिट्टी में पानी की मात्रा अधिक है। सिंचाई रोकें और जल निकासी नालियां साफ रखें।";
        } else {
            status = "OPTIMAL";
            advisoryEn = "Soil moisture is in the optimal band (23-30%) for nutrient uptake. No immediate watering required.";
            advisoryHi = "मिट्टी की नमी पोषक तत्व अवशोषण के लिए उत्तम स्तर (23-30%) पर है। तुरंत सिंचाई की जरूरत नहीं है।";
        }

        return new SoilData(moisture, status, soilTemp, ec, advisoryEn, advisoryHi);
    }
}
