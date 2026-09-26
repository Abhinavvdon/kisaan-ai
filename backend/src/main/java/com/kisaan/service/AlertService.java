package com.kisaan.service;

import com.kisaan.model.CropAlert;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertService {

    private final List<CropAlert> allAlerts = new ArrayList<>();

    public AlertService() {
        initAlerts();
    }

    private void initAlerts() {
        // 1. Nashik, Maharashtra
        allAlerts.add(new CropAlert(
                "ALT-001", "Nashik", "Maharashtra", "Grape & Onion", "Downy Mildew & Twister Disease",
                "HIGH",
                "Morning fog and relative humidity above 80% create favorable conditions for Downy Mildew sporulation in vineyards.",
                "सुबह के कोहरे और 80% से अधिक आर्द्रता के कारण अंगूर के बगीचों में डाउनी मिल्ड्यू का भारी खतरा है।",
                "Apply prophylactic spray of Bordeaux mixture (1%) or Potassium phosphonate at 3g/L.",
                "1% बोर्डो मिश्रण या पोटेशियम फॉस्फोनेट 3 ग्राम प्रति लीटर पानी में मिलाकर छिड़काव करें।"
        ));

        // 2. Pune, Maharashtra
        allAlerts.add(new CropAlert(
                "ALT-002", "Pune", "Maharashtra", "Sugarcane", "Woolly Aphid (Ceratovacuna lanigera)",
                "MEDIUM",
                "Colonies of white woolly aphids observed on lower surfaces of sugarcane foliage.",
                "गन्ने की पत्तियों की निचली सतह पर सफेद ऊनी माहू (एफिड) का प्रकोप देखा गया है।",
                "Release biological predators like Dipha aphidivora or spray Azadirachtin 10,000 ppm at 2 ml/L.",
                "डिफा एफिडिवोरा परभक्षी छोड़ें या नीम का तेल (अजाडिराक्टिन 10000 पीपीएम) 2 मिली/लीटर का छिड़काव करें।"
        ));

        // 3. Guntur, Andhra Pradesh
        allAlerts.add(new CropAlert(
                "ALT-003", "Guntur", "Andhra Pradesh", "Chilli", "Black Thrips (Thrips parvispinus)",
                "HIGH",
                "Heavy flower drop and upward leaf curling reported across irrigated chilli parcels.",
                "मिर्च के खेतों में फूल गिरने और पत्तियों के ऊपर की ओर मुड़ने (ब्लैक थ्रिप्स) की समस्या।",
                "Install blue sticky traps (25/acre) and spray Spinetoram 11.7 SC at 1 ml/L or Neem oil.",
                "नीले चिपचिपे कार्ड (25 प्रति एकड़) लगाएं और स्पिनटोरम 11.7 एससी (1 मिली/लीटर) का छिड़काव करें।"
        ));

        // 4. Ludhiana, Punjab
        allAlerts.add(new CropAlert(
                "ALT-004", "Ludhiana", "Punjab", "Wheat", "Yellow Rust (Puccinia striiformis)",
                "HIGH",
                "Cool morning temperatures and lingering dew conducive for stripe rust yellow pustules.",
                "सुबह की ठंडक और ओस की वजह से गेहूं में पीला रतुआ (येलो रस्ट) के लक्षण दिखाई देने की संभावना।",
                "Scout fields early morning. Spray Propiconazole 25 EC at 1 ml/L immediately if stripes appear.",
                "खेतों का सुबह निरीक्षण करें। पीले धारियां दिखने पर प्रोपिकोनाजोल 25 ईसी 1 मिली/लीटर का छिड़काव करें।"
        ));

        // 5. Karnal, Haryana
        allAlerts.add(new CropAlert(
                "ALT-005", "Karnal", "Haryana", "Basmati Rice", "Bacterial Leaf Blight (Xanthomonas)",
                "MEDIUM",
                "Water-soaked lesions turning yellow-white with wavy margins on leaf tips.",
                "धान की पत्तियों के किनारों पर पीले-सफेद धारियां और बैक्टीरिया झुलसा रोग का प्रकोप।",
                "Avoid excess nitrogen fertilizer. Spray Copper Oxychloride (2.5 g/L) + Streptocycline (1 g/10 L).",
                "यूरिया का अत्यधिक प्रयोग न करें। कॉपर ऑक्सीक्लोराइड 2.5 ग्राम + स्ट्रेप्टोसाइक्लिन 1 ग्राम/10 लीटर छिड़कें।"
        ));

        // 6. Indore, Madhya Pradesh
        allAlerts.add(new CropAlert(
                "ALT-006", "Indore", "Madhya Pradesh", "Soybean", "Stem Fly & Girdle Beetle",
                "MEDIUM",
                "Larval feeding causing ring cuts on shoots leading to premature plant wilting.",
                "तने में गिर्डल बीटल और स्टेम फ्लाई की सुंडी द्वारा रिंग कट और पौधे के सूखने का जोखिम।",
                "Spray Chlorantraniliprole 18.5 SC at 0.3 ml/L or Thiamethoxam 12.6% + Lambda cyhalothrin.",
                "क्लोरएंट्रानिलिप्रोल 18.5 एससी (0.3 मिली/लीटर) का छिड़काव करें।"
        ));

        // 7. Rajkot, Gujarat
        allAlerts.add(new CropAlert(
                "ALT-007", "Rajkot", "Gujarat", "Groundnut", "Tikka Leaf Spot (Cercospora)",
                "MEDIUM",
                "Dark circular spots with chlorotic halos spotted on lower older leaves.",
                "मूंगफली की निचली पत्तियों पर भूरे-काले टिक्का रोग के धब्बे।",
                "Spray Mancozeb 75 WP at 2 g/L or Carbendazim 50 WP at 1 g/L at early onset.",
                "मैनकोजेब 75 डब्ल्यूपी 2 ग्राम/लीटर या कार्बेन्डाजिम 1 ग्राम/लीटर का छिड़काव करें।"
        ));

        // 8. Varanasi, Uttar Pradesh
        allAlerts.add(new CropAlert(
                "ALT-008", "Varanasi", "Uttar Pradesh", "Tomato & Brinjal", "Early Blight & Fruit Borer",
                "MEDIUM",
                "Concentric target-board spots on leaves and borer holes near calyx in solanaceous crops.",
                "टमाटर की पत्तियों पर संकेंद्रित छल्लों वाले धब्बे और फल छेदक कीट की सक्रियता।",
                "Apply Trichoderma viride enriched compost to root zone and pheromone traps in fields.",
                "ट्राइकोडर्मा विरिडी युक्त कम्पोस्ट जड़ में डालें और फेरोमोन ट्रैप लगाएं।"
        ));

        // 9. Shimla, Himachal Pradesh
        allAlerts.add(new CropAlert(
                "ALT-009", "Shimla", "Himachal Pradesh", "Apple", "Apple Scab (Venturia inaequalis)",
                "HIGH",
                "Extended leaf wetness following light precipitation elevates ascospore discharge.",
                "हल्की बारिश के बाद पत्तियों में नमी बने रहने से सेब में स्कैब रोग का तीव्र फैलाव।",
                "Spray Difenoconazole 25 EC at 0.3 ml/L during pink bud or petal fall stage.",
                "पिंक बड अवस्था में डाइफेनोकोनाजोल 25 ईसी 0.3 मिली/लीटर का छिड़काव करें।"
        ));

        // 10. Mandya, Karnataka
        allAlerts.add(new CropAlert(
                "ALT-010", "Mandya", "Karnataka", "Paddy", "Blast Disease (Pyricularia oryzae)",
                "HIGH",
                "Spindle-shaped lesions with ash centers expanding on leaf blades under overcast skies.",
                "बादल छाए रहने के कारण धान की पत्तियों पर आंख के आकार वाले झुलसा (ब्लास्ट) रोग के धब्बे।",
                "Maintain optimal field flooding and apply Tricyclazole 75 WP at 0.6 g/L.",
                "खेत में पानी का उचित स्तर बनाए रखें और ट्राइसाइक्लाजोल 75 डब्ल्यूपी 0.6 ग्राम/लीटर छिड़कें।"
        ));

        // 11. Coimbatore, Tamil Nadu
        allAlerts.add(new CropAlert(
                "ALT-011", "Coimbatore", "Tamil Nadu", "Cotton", "Pink Bollworm (Pectinophora gossypiella)",
                "HIGH",
                "Rosetted flowers and exit holes observed in developing squares and green bolls.",
                "कपास में गुलाबी सुंडी के कारण फूलों का बंद होना और गूलरों में छेद होना।",
                "Install Pheromone traps @ 5/acre for monitoring and release Trichogramma egg parasitoids.",
                "5 फेरोमोन ट्रैप प्रति एकड़ लगाएं और ट्राइकोग्रामा परजीवी छोड़ें।"
        ));

        // 12. Bikaner, Rajasthan
        allAlerts.add(new CropAlert(
                "ALT-012", "Bikaner", "Rajasthan", "Mustard", "White Rust (Albugo candida)",
                "MEDIUM",
                "White chalky pustules developing on lower surface of leaves and floral malformation.",
                "सरसों की पत्तियों की निचली सतह पर सफेद फफोले और सफेद रतुआ रोग।",
                "Spray Metalaxyl 8% + Mancozeb 64% WP at 2 g/L during vegetative growth.",
                "मेटालेक्सिल + मैनकोजेब (2 ग्राम/लीटर) का छिड़काव करें।"
        ));

        // 13. Burdwan, West Bengal
        allAlerts.add(new CropAlert(
                "ALT-013", "Burdwan", "West Bengal", "Rice", "Brown Plant Hopper (BPH)",
                "MEDIUM",
                "Hopper burn patches visible near plant base in densely planted paddy fields.",
                "घने धान के खेतों में तने के पास भूरा फुदका (बीपीएच) और पत्तियां झुलसने की समस्या।",
                "Drain standing water for 3-4 days to expose soil. Spray Triflumezopyrim 10 SC at 0.5 ml/L.",
                "खेत का पानी 3-4 दिन निकाल दें। ट्राइफ्लूमेज़ोपाइरिम 10 एससी (0.5 मिली/लीटर) का छिड़काव करें।"
        ));

        // 14. Patna, Bihar
        allAlerts.add(new CropAlert(
                "ALT-014", "Patna", "Bihar", "Maize", "Fall Armyworm (FAW)",
                "HIGH",
                "Ragged shot-holes and sawdust-like frass found in central whorl of young maize plants.",
                "मक्का के पत्तों में सुंडी द्वारा छेद और तने में भूसी जैसा चूरा (फॉल आर्मीवर्म)।",
                "Apply Bacillus thuringiensis (Bt) formulation or whorl application of sand + neem cake.",
                "गोभ में बालू + नीम खली का मिश्रण डालें या बीटी (Bt) घोल का छिड़काव करें।"
        ));

        // 15. Wayanad, Kerala
        allAlerts.add(new CropAlert(
                "ALT-015", "Wayanad", "Kerala", "Black Pepper", "Quick Wilt / Foot Rot (Phytophthora)",
                "HIGH",
                "Sudden defoliation and root decay triggered by continuous monsoon root waterlogging.",
                "लगातार नमी और जलभराव के कारण काली मिर्च की जड़ों में सड़ांध और द्रुत मुरझान रोग।",
                "Drench soil basin around vine with 1% Bordeaux mixture or Trichoderma harzianum culture.",
                "पौधे की जड़ के पास 1% बोर्डो मिश्रण या ट्राइकोडर्मा घोल से ड्रेंचिंग करें।"
        ));
    }

    public List<CropAlert> getAlertsForLocation(String district, String state) {
        List<CropAlert> matched = new ArrayList<>();

        if (district != null && !district.trim().isEmpty()) {
            matched = allAlerts.stream()
                    .filter(a -> a.getDistrict().equalsIgnoreCase(district.trim()))
                    .collect(Collectors.toList());
        }

        if (matched.isEmpty() && state != null && !state.trim().isEmpty()) {
            matched = allAlerts.stream()
                    .filter(a -> a.getState().equalsIgnoreCase(state.trim()))
                    .collect(Collectors.toList());
        }

        // Always guarantee 1-3 alerts (as required by prompt)
        if (matched.isEmpty()) {
            // Default to top 2 alerts (e.g. Nashik and Pune)
            matched.add(allAlerts.get(0));
            matched.add(allAlerts.get(1));
        }

        return matched.stream().limit(3).collect(Collectors.toList());
    }

    public List<CropAlert> getAllAlerts() {
        return allAlerts;
    }
}
