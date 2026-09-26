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
        // Maharashtra
        addAlert("ALT-001", "Nashik", "Maharashtra", "Grape & Onion", "Downy Mildew & Twister Disease", "HIGH",
                "Morning fog and relative humidity above 80% create favorable conditions for Downy Mildew sporulation in vineyards.",
                "सुबह के कोहरे और 80% से अधिक आर्द्रता के कारण अंगूर के बगीचों में डाउनी मिल्ड्यू का भारी खतरा है।",
                "Apply prophylactic spray of Bordeaux mixture (1%) or Potassium phosphonate at 3g/L.",
                "1% बोर्डो मिश्रण या पोटेशियम फॉस्फोनेट 3 ग्राम प्रति लीटर पानी में मिलाकर छिड़काव करें।");

        addAlert("ALT-002", "Pune", "Maharashtra", "Sugarcane", "Woolly Aphid (Ceratovacuna lanigera)", "MEDIUM",
                "Colonies of white woolly aphids observed on lower surfaces of sugarcane foliage.",
                "गन्ने की पत्तियों की निचली सतह पर सफेद ऊनी माहू (एफिड) का प्रकोप देखा गया है।",
                "Release biological predators like Dipha aphidivora or spray Azadirachtin 10,000 ppm at 2 ml/L.",
                "डिफा एफिडिवोरा परभक्षी छोड़ें या नीम का तेल (अजाडिराक्टिन 10000 पीपीएम) 2 मिली/लीटर का छिड़काव करें।");

        addAlert("ALT-003", "Nagpur", "Maharashtra", "Orange & Mandarin", "Citrus Canker (Xanthomonas citri)", "HIGH",
                "Raised corky lesions on fruit rinds and leaves exacerbated by recent intermittent rain showers.",
                "संतरे के फलों और पत्तियों पर उभरे हुए भूरे खुरदरे धब्बे (सिट्रस कैंकर) का फैलाव।",
                "Spray Copper Oxychloride 50 WP (2.5 g/L) + Streptocycline (1 g/10 L) at flushing stage.",
                "कॉपर ऑक्सीक्लोराइड 50 WP (2.5 ग्राम/लीटर) + स्ट्रेप्टोसाइक्लिन 1 ग्राम प्रति 10 लीटर छिड़कें।");

        addAlert("ALT-004", "Solapur", "Maharashtra", "Pomegranate", "Bacterial Blight / Telya (Xanthomonas)", "HIGH",
                "Dark water-soaked angular spots on leaves and oily cracks on ripening pomegranates.",
                "अनार की पत्तियों और फलों पर तेलिया रोग (काले तैलीय दरारें व धब्बे) की चेतावनी।",
                "Prune infected twigs, apply 1% Bordeaux paste on cuts, spray 2-bromo-2-nitropropane-1,3-diol (0.5 g/L).",
                "संक्रमित टहनियां काटें, बोर्डो पेस्ट लगाएं और ब्रोनोपोल 0.5 ग्राम/लीटर का छिड़काव करें।");

        addAlert("ALT-005", "Jalgaon", "Maharashtra", "Banana", "Sigatoka Leaf Spot (Mycosphaerella)", "MEDIUM",
                "Spindle-shaped pale spots maturing into dark brown necrotic streaks with gray center.",
                "केले की पत्तियों पर सिगाटोका पर्ण दाग (भूरे-काले लंबे सूखे धब्बे) का खतरा।",
                "Remove severely spotted lower leaves. Spray Propiconazole 25 EC (1 ml/L) with mineral oil (10 ml/L).",
                "सूखी निचली पत्तियां काटें। प्रोपिकोनाजोल 1 मिली/लीटर खनिज तेल के साथ मिलाकर छिड़कें।");

        // Andhra Pradesh
        addAlert("ALT-006", "Guntur", "Andhra Pradesh", "Chilli", "Black Thrips (Thrips parvispinus)", "HIGH",
                "Heavy flower drop and upward leaf curling reported across irrigated chilli parcels.",
                "मिर्च के खेतों में फूल गिरने और पत्तियों के ऊपर की ओर मुड़ने (ब्लैक थ्रिप्स) की समस्या।",
                "Install blue sticky traps (25/acre) and spray Spinetoram 11.7 SC at 1 ml/L or Neem oil.",
                "नीले चिपचिपे कार्ड (25 प्रति एकड़) लगाएं और स्पिनटोरम 11.7 एससी (1 मिली/लीटर) का छिड़काव करें।");

        addAlert("ALT-007", "Kurnool", "Andhra Pradesh", "Chickpea (Bengal Gram)", "Fusarium Wilt (Fusarium oxysporum)", "MEDIUM",
                "Drooping of petioles and dark xylem vascular discoloration in collar region.",
                "चने की फसल में उकठा/विल्ट रोग (पौधों का अचानक मुरझाना और सूखना)।",
                "Avoid excessive flooding; drench root zones with Trichoderma viride bio-agent @ 5 g/L.",
                "खेत में पानी जमा न होने दें; ट्राइकोडर्मा विरिडी 5 ग्राम/लीटर से जड़ के पास ड्रेंचिंग करें।");

        // Punjab
        addAlert("ALT-008", "Ludhiana", "Punjab", "Wheat", "Yellow Rust (Puccinia striiformis)", "HIGH",
                "Cool morning temperatures and lingering dew conducive for stripe rust yellow pustules.",
                "सुबह की ठंडक और ओस की वजह से गेहूं में पीला रतुआ (येलो रस्ट) के लक्षण दिखाई देने की संभावना।",
                "Scout fields early morning. Spray Propiconazole 25 EC at 1 ml/L immediately if stripes appear.",
                "खेतों का सुबह निरीक्षण करें। पीले धारियां दिखने पर प्रोपिकोनाजोल 25 ईसी 1 मिली/लीटर का छिड़काव करें।");

        addAlert("ALT-009", "Bathinda", "Punjab", "Cotton", "Whitefly Infestation (Bemisia tabaci)", "HIGH",
                "Sucking pest density exceeding ETL on underside of leaves; honeydew leading to sooty mold.",
                "कपास की निचली पत्तियों पर सफेद मक्खी की अत्यधिक संख्या व चिपचिपा काला फफूंद।",
                "Spray Pyriproxyfen 10 EC @ 2 ml/L or Diafenthiuron 50 WP @ 1.2 g/L.",
                "पायरीप्रॉक्सीफेन 10 ईसी 2 मिली/लीटर या डायफेंथियूरोन 1.2 ग्राम/लीटर का छिड़काव करें।");

        // Haryana
        addAlert("ALT-010", "Karnal", "Haryana", "Basmati Rice", "Bacterial Leaf Blight (Xanthomonas)", "MEDIUM",
                "Water-soaked lesions turning yellow-white with wavy margins on leaf tips.",
                "धान की पत्तियों के किनारों पर पीले-सफेद धारियां और बैक्टीरिया झुलसा रोग का प्रकोप।",
                "Avoid excess nitrogen fertilizer. Spray Copper Oxychloride (2.5 g/L) + Streptocycline (1 g/10 L).",
                "यूरिया का अत्यधिक प्रयोग न करें। कॉपर ऑक्सीक्लोराइड 2.5 ग्राम + स्ट्रेप्टोसाइक्लिन 1 ग्राम/10 लीटर छिड़कें।");

        addAlert("ALT-011", "Hisar", "Haryana", "Mustard", "Sclerotinia Stem Rot (Sclerotinia sclerotiorum)", "MEDIUM",
                "Elongated grayish-white lesions on stems with hard black sclerotial bodies inside hollow stems.",
                "सरसों के तने पर सफेद-धूसर धब्बे और तने का खोखला होकर टूटना (तना गलन रोग)।",
                "Spray Carbendazim 50 WP @ 1 g/L or Thiophanate Methyl 70 WP @ 1 g/L at early flowering.",
                "कार्बेन्डाजिम 1 ग्राम/लीटर या थायोफिनेट मिथाइल 1 ग्राम/लीटर का छिड़काव करें।");

        // Madhya Pradesh
        addAlert("ALT-012", "Indore", "Madhya Pradesh", "Soybean", "Stem Fly & Girdle Beetle", "MEDIUM",
                "Larval feeding causing ring cuts on shoots leading to premature plant wilting.",
                "तने में गिर्डल बीटल और स्टेम फ्लाई की सुंडी द्वारा रिंग कट और पौधे के सूखने का जोखिम।",
                "Spray Chlorantraniliprole 18.5 SC at 0.3 ml/L or Thiamethoxam 12.6% + Lambda cyhalothrin.",
                "क्लोरएंट्रानिलिप्रोल 18.5 एससी (0.3 मिली/लीटर) का छिड़काव करें।");

        addAlert("ALT-013", "Ujjain", "Madhya Pradesh", "Soybean & Garlic", "Purple Blotch & Thrips in Garlic", "MEDIUM",
                "Sunken purple lesions on garlic foliage and thrips lacerating young shoots.",
                "लहसुन की पत्तियों पर बैंगनी धब्बे और थ्रिप्स कीट की सक्रियता।",
                "Spray Mancozeb @ 2.5 g/L + Fipronil 5 SC @ 1.5 ml/L.",
                "मैनकोजेब 2.5 ग्राम/लीटर + फिप्रोनिल 5 एससी 1.5 मिली/लीटर का छिड़काव करें।");

        // Gujarat
        addAlert("ALT-014", "Rajkot", "Gujarat", "Groundnut", "Tikka Leaf Spot (Cercospora)", "MEDIUM",
                "Dark circular spots with chlorotic halos spotted on lower older leaves.",
                "मूंगफली की निचली पत्तियों पर भूरे-काले टिक्का रोग के धब्बे।",
                "Spray Mancozeb 75 WP at 2 g/L or Carbendazim 50 WP at 1 g/L at early onset.",
                "मैनकोजेब 75 डब्ल्यूपी 2 ग्राम/लीटर या कार्बेन्डाजिम 1 ग्राम/लीटर का छिड़काव करें।");

        addAlert("ALT-015", "Surat", "Gujarat", "Sugarcane", "Red Rot (Colletotrichum falcatum)", "HIGH",
                "Third and fourth leaves yellowing; split stalks show red tissue with transverse white patches.",
                "गन्ने के तने में लाल सड़ांध (रेड रॉट) के लक्षण व पत्तियों का सूखना।",
                "Uproot and burn diseased clumps; avoid ratooning infected crop; rotate with legumes.",
                "रोगी गन्नों को उखाड़कर नष्ट करें और जल निकासी दुरुस्त रखें।");

        // Uttar Pradesh
        addAlert("ALT-016", "Varanasi", "Uttar Pradesh", "Tomato & Brinjal", "Early Blight & Fruit Borer", "MEDIUM",
                "Concentric target-board spots on leaves and borer holes near calyx in solanaceous crops.",
                "टमाटर की पत्तियों पर संकेंद्रित छल्लों वाले धब्बे और फल छेदक कीट की सक्रियता।",
                "Apply Trichoderma viride enriched compost to root zone and pheromone traps in fields.",
                "ट्राइकोडर्मा विरिडी युक्त कम्पोस्ट जड़ में डालें और फेरोमोन ट्रैप लगाएं।");

        addAlert("ALT-017", "Lucknow", "Uttar Pradesh", "Mango", "Mango Malformation & Powdery Mildew", "HIGH",
                "Compact bunchy vegetatives and white powdery fungal coating on emerging inflorescence.",
                "आम के बौर पर सफेद चूर्ण (पाउडरी मिल्ड्यू) और गुच्छा रोग (मालफॉर्मेशन) का खतरा।",
                "Prune malformed panicles; spray wettable sulfur 80 WP @ 2 g/L or Hexaconazole 5 EC @ 1 ml/L.",
                "घने बौर पर घुलनशील गंधक (2 ग्राम/लीटर) या हेक्साकोनाजोल 1 मिली/लीटर का छिड़काव करें।");

        // Himachal Pradesh
        addAlert("ALT-018", "Shimla", "Himachal Pradesh", "Apple", "Apple Scab (Venturia inaequalis)", "HIGH",
                "Extended leaf wetness following light precipitation elevates ascospore discharge.",
                "हल्की बारिश के बाद पत्तियों में नमी बने रहने से सेब में स्कैब रोग का तीव्र फैलाव।",
                "Spray Difenoconazole 25 EC at 0.3 ml/L during pink bud or petal fall stage.",
                "पिंक बड अवस्था में डाइफेनोकोनाजोल 25 ईसी 0.3 मिली/लीटर का छिड़काव करें।");

        // Karnataka
        addAlert("ALT-019", "Mandya", "Karnataka", "Paddy", "Blast Disease (Pyricularia oryzae)", "HIGH",
                "Spindle-shaped lesions with ash centers expanding on leaf blades under overcast skies.",
                "बादल छाए रहने के कारण धान की पत्तियों पर आंख के आकार वाले झुलसा (ब्लास्ट) रोग के धब्बे।",
                "Maintain optimal field flooding and apply Tricyclazole 75 WP at 0.6 g/L.",
                "खेत में पानी का उचित स्तर बनाए रखें और ट्राइसाइक्लाजोल 75 डब्ल्यूपी 0.6 ग्राम/लीटर छिड़कें।");

        addAlert("ALT-020", "Belagavi", "Karnataka", "Sugarcane & Maize", "Fall Armyworm (Spodoptera frugiperda)", "HIGH",
                "Whorl damage with pinholes and ragged edges in maize and sweet corn.",
                "मक्के और गन्ने में फॉल आर्मीवर्म द्वारा पत्तों को खाने व तने में छेद करने का संकट।",
                "Apply Emamectin benzoate 5 SG @ 0.4 g/L directly into central leaf whorls.",
                "इमामेक्टिन बेंजोएट 5 एसजी (0.4 ग्राम/लीटर) का घोल गोभ में डालें।");

        // Tamil Nadu
        addAlert("ALT-021", "Coimbatore", "Tamil Nadu", "Cotton", "Pink Bollworm (Pectinophora gossypiella)", "HIGH",
                "Rosetted flowers and exit holes observed in developing squares and green bolls.",
                "कपास में गुलाबी सुंडी के कारण फूलों का बंद होना और गूलरों में छेद होना।",
                "Install Pheromone traps @ 5/acre for monitoring and release Trichogramma egg parasitoids.",
                "5 फेरोमोन ट्रैप प्रति एकड़ लगाएं और ट्राइकोग्रामा परजीवी छोड़ें।");

        addAlert("ALT-022", "Thanjavur", "Tamil Nadu", "Paddy (Kuruvai)", "Brown Plant Hopper (BPH)", "HIGH",
                "Base of tillers drying rapidly; circular patches of yellowing hopper burn.",
                "धान की फसल में बीपीएच का प्रकोप; पौधों की जड़ के पास पत्तियां सूखना।",
                "Alternate wetting and drying; spray Pymetrozine 50 WG @ 0.6 g/L to base of plants.",
                "खेत में लगातार पानी न भरें; पाइमेट्रोज़िन 50 डब्लूजी 0.6 ग्राम/लीटर तनों के पास छिड़कें।");

        // Rajasthan
        addAlert("ALT-023", "Bikaner", "Rajasthan", "Mustard", "White Rust (Albugo candida)", "MEDIUM",
                "White chalky pustules developing on lower surface of leaves and floral malformation.",
                "सरसों की पत्तियों की निचली सतह पर सफेद फफोले और सफेद रतुआ रोग।",
                "Spray Metalaxyl 8% + Mancozeb 64% WP at 2 g/L during vegetative growth.",
                "मेटालेक्सिल + मैनकोजेब (2 ग्राम/लीटर) का छिड़काव करें।");

        addAlert("ALT-024", "Jaipur", "Rajasthan", "Pearl Millet (Bajra)", "Downy Mildew / Green Ear (Sclerospora)", "MEDIUM",
                "Chlorotic stripes on leaves and transformation of floral earhead into leafy structure.",
                "बाजरे में जोगिया/ग्रीन ईयर रोग (बालियों का हरी पत्तियों जैसा विकृत होना)।",
                "Rogue out and destroy infected green-ear plants; spray Ridomil MZ 72 @ 2 g/L.",
                "रोगी पौधों को तुरंत उखाड़कर नष्ट करें; रिडोमिल एमजेड 2 ग्राम/लीटर छिड़कें।");

        // Bihar & Bengal & Kerala
        addAlert("ALT-025", "Patna", "Bihar", "Maize & Potato", "Late Blight of Potato", "HIGH",
                "Water-soaked dark lesions spreading rapidly from leaf tips under fog and damp cold.",
                "आलू की पत्तियों पर काले जलसिक्त धब्बे व निचली सतह पर सफेद फफूंद (पछेती झुलसा)।",
                "Spray Dimethomorph 50 WP @ 1 g/L + Mancozeb @ 2 g/L; avoid night sprinkler irrigation.",
                "डाइमेथोमॉर्फ 1 ग्राम/लीटर + मैनकोजेब 2 ग्राम/लीटर का सुरक्षात्मक छिड़काव करें।");

        addAlert("ALT-026", "Burdwan", "West Bengal", "Rice", "Sheath Blight (Rhizoctonia solani)", "MEDIUM",
                "Snake-skin like greenish-gray spots with brown borders on leaf sheaths near water line.",
                "धान की तना आवरण पर चितकबरे सांप की त्वचा जैसे धब्बे (शीथ ब्लाइट रोग)।",
                "Apply Validamycin 3L @ 2 ml/L or Azoxystrobin @ 1 ml/L targeted at water surface.",
                "वैलिडामाइसिन 2 मिली/लीटर या एजोक्सिस्ट्रोबिन 1 मिली/लीटर का छिड़काव करें।");

        addAlert("ALT-027", "Wayanad", "Kerala", "Black Pepper", "Quick Wilt / Foot Rot (Phytophthora)", "HIGH",
                "Sudden defoliation and root decay triggered by continuous monsoon root waterlogging.",
                "लगातार नमी और जलभराव के कारण काली मिर्च की जड़ों में सड़ांध और द्रुत मुरझान रोग।",
                "Drench soil basin around vine with 1% Bordeaux mixture or Trichoderma harzianum culture.",
                "पौधे की जड़ के पास 1% बोर्डो मिश्रण या ट्राइकोडर्मा घोल से ड्रेंचिंग करें।");
    }

    private void addAlert(String id, String district, String state, String crop, String pest, String severity,
                          String msgEn, String msgHi, String actionEn, String actionHi) {
        allAlerts.add(new CropAlert(id, district, state, crop, pest, severity, msgEn, msgHi, actionEn, actionHi));
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

        if (matched.isEmpty()) {
            matched.add(allAlerts.get(0));
            matched.add(allAlerts.get(1));
        }

        return matched.stream().limit(3).collect(Collectors.toList());
    }

    public List<CropAlert> getAllAlerts() {
        return allAlerts;
    }
}
