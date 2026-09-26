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

        // Telangana
        addAlert("ALT-028", "Adilabad", "Telangana", "Cotton & Soya", "Pink Bollworm & Spodoptera", "HIGH",
                "High boll damage and larval penetration observed in mid-stage Bt cotton fields.",
                "कपास में गुलाबी सुंडी और स्पोडोप्टेरा का प्रकोप; गूलर में छेद और फूल गिरने का खतरा।",
                "Install delta traps @ 8/acre; spray Profenofos 50 EC @ 2 ml/L if rosette flowers exceed 5%.",
                "डेल्टा ट्रैप 8 प्रति एकड़ लगाएं; 5% से अधिक नुकसान दिखने पर प्रोफेनोफॉस 2 मिली/लीटर का छिड़काव करें।");

        addAlert("ALT-029", "Warangal", "Telangana", "Chilli", "Thrips & Geminivirus Leaf Curl", "HIGH",
                "Severe crinkling and boat-shaped upward curling of tender leaves with stunted shoots.",
                "मिर्च की ऊपरी पत्तियों का नाव की तरह मुड़ना व थ्रिप्स कीट का भारी प्रकोप।",
                "Spray Fipronil 5 SC @ 2 ml/L or Spinosad 45 SC @ 0.3 ml/L; spray neem oil weekly.",
                "फिप्रोनिल 5 एससी 2 मिली/लीटर या स्पिनोसाड 0.3 मिली/लीटर और नीम तेल का नियमित छिड़काव करें।");

        // Odisha
        addAlert("ALT-030", "Cuttack", "Odisha", "Rice (Paddy)", "Yellow Stem Borer (Scirpophaga incertulas)", "HIGH",
                "Dead hearts in vegetative stage and white ear heads in reproductive stage in coastal alluvial tracts.",
                "धान में तना छेदक के कारण 'डेड हार्ट' और बालियों का सफेद पड़ना (सफेद बाली)।",
                "Apply Cartap hydrochloride 4G @ 10 kg/acre or spray Chlorantraniliprole 18.5 SC @ 0.3 ml/L.",
                "कार्टाप हाइड्रोक्लोराइड 4G 10 किग्रा/एकड़ डालें या क्लोरएंट्रानिलिप्रोल 0.3 मिली/लीटर छिड़कें।");

        // Chhattisgarh
        addAlert("ALT-031", "Raipur", "Chhattisgarh", "Rice", "Paddy Gall Midge (Orseolia oryzae)", "MEDIUM",
                "Silver shoots / onion leaf gall structures replacing normal tillers in moist cloudy conditions.",
                "धान में गाद मक्खी (गॉल मिज) के कारण कल्ले प्याज की पत्ती या सिल्वर शूट जैसे बनना।",
                "Apply Fipronil 0.3G granules @ 7 kg/acre in standing water or spray Chlorpyriphos 20 EC.",
                "खेत में फिप्रोनिल 0.3G दानेदार 7 किग्रा/एकड़ डालें या क्लोरपायरीफॉस 2 मिली/लीटर छिड़कें।");

        // Jharkhand
        addAlert("ALT-032", "Ranchi", "Jharkhand", "Tomato & Brinjal", "Bacterial Wilt (Ralstonia solanacearum)", "HIGH",
                "Sudden total wilting of green plants without prior yellowing during sunny hours.",
                "टमाटर और बैंगन के हरे पौधों का अचानक बिना पीला पड़े धूप में मुरझाना (बैक्टीरियल विल्ट)।",
                "Drench soil with Streptocycline (1 g/10 L) + Copper Oxychloride (2.5 g/L); use bio-antagonists.",
                "स्ट्रेप्टोसाइक्लिन 1 ग्राम/10 ली + कॉपर ऑक्सीक्लोराइड 2.5 ग्राम/ली से पौधों की जड़ों में ड्रेंचिंग करें।");

        // Assam
        addAlert("ALT-033", "Kamrup", "Assam", "Tea & Rice", "Tea Mosquito Bug (Helopeltis) & Rice Hispa", "HIGH",
                "Dark circular necrotic spots on tea flushes and white scratch-streaks on paddy leaves.",
                "चाय की कोमल पत्तियों पर धब्बे और धान में हिसपा कीट द्वारा पत्तियों का सफेद खुरचना।",
                "Spray Thiamethoxam 25 WG @ 0.25 g/L on tea shoots; apply Quinalphos 25 EC on paddy.",
                "चाय में थायमेथोक्सम 0.25 ग्राम/ली या धान में क्विनालफॉस 2 मिली/ली का छिड़काव करें।");

        // Uttarakhand
        addAlert("ALT-034", "Dehradun", "Uttarakhand", "Basmati & Apple", "Sheath Rot & Apple Powdery Mildew", "MEDIUM",
                "Oblong gray lesions enclosing boot leaf in rice and white powdery coating on hill apples.",
                "बासमती धान में शीथ रॉट और सेब की नई शाखाओं पर सफेद चूर्ण (पाउडरी मिल्ड्यू)।",
                "Apply Hexaconazole 5 EC @ 2 ml/L or Carbendazim @ 1 g/L with thorough coverage.",
                "हेक्साकोनाजोल 5 ईसी 2 मिली/लीटर या कार्बेन्डाजिम 1 ग्राम/लीटर का छिड़काव करें।");

        // Jammu and Kashmir
        addAlert("ALT-035", "Srinagar", "Jammu and Kashmir", "Apple & Walnut", "Venturia Scab & San Jose Scale", "HIGH",
                "Olive-green velvety spots on apple leaves and crusted scaly insects on apple wood bark.",
                "सेब की पत्तियों व फलों पर मखमली जैतूनिया धब्बे (स्कैब) और तने पर स्केल कीट का जमाव।",
                "Apply Dodine 65 WP @ 0.75 g/L or Captan 50 WP @ 2.5 g/L; use horticultural mineral oil in dormancy.",
                "डोडीन 0.75 ग्राम/लीटर या कैप्टन 2.5 ग्राम/लीटर का छिड़काव करें; बागों की सफाई रखें।");

        // Ladakh
        addAlert("ALT-036", "Leh", "Ladakh", "Apricot & Alfalfa", "Apricot Fruit Borer & Leaf Spot", "MEDIUM",
                "Borer larvae tunneling into ripening halman apricots and fungal leaf spotting under dry sunshine.",
                "खुबानी के फलों में सुंडी का प्रवेश और अल्फाल्फा की पत्तियों पर सूखे धब्बे।",
                "Install pheromone monitoring traps and apply organic Bacillus thuringiensis (Bt) @ 2 g/L.",
                "फेरोमोन ट्रैप लगाएं और जैविक बेसिलस थुरिंजिएंसिस (बीटी) 2 ग्राम/लीटर का छिड़काव करें।");

        // Delhi
        addAlert("ALT-037", "New Delhi", "Delhi", "Vegetables & Greens", "Whitefly & Leaf Miner Infestation", "MEDIUM",
                "Serpentine silvery trails in cucurbit and spinach leaves with swarms of whiteflies.",
                "सब्जियों और पालक में लीफ माइनर की सफेद टेढ़ी-मेढ़ी लकीरें और सफेद मक्खी का प्रकोप।",
                "Install yellow sticky traps (15/acre); spray Azadirachtin 3000 ppm @ 3 ml/L.",
                "पीले चिपचिपे कार्ड लगाएं और नीम तेल (अजाडिराक्टिन 3000 पीपीएम) 3 मिली/लीटर का छिड़काव करें।");

        // Goa
        addAlert("ALT-038", "North Goa", "Goa", "Cashew & Arecanut", "Tea Mosquito Bug (TMB) & Koleroga", "HIGH",
                "Black necrotic lesion exudation on tender cashew shoots and fruit rot in arecanut bunches.",
                "काजू की नई कोपलों पर टी मॉस्किटो बग के डंक के काले धब्बे और सुपारी में फफूंद गलन।",
                "Spray Lambda-cyhalothrin 5 EC @ 0.6 ml/L at flushing and flowering; apply 1% Bordeaux mixture.",
                "लैम्ब्डा-साइहलोथ्रिन 0.6 मिली/लीटर या 1% बोर्डो मिश्रण का छिड़काव करें।");

        // Sikkim
        addAlert("ALT-039", "Gangtok", "Sikkim", "Large Cardamom & Mandarin", "Chirke Virus & Colletotrichum Blight", "MEDIUM",
                "Mosaic striping and dwarfing in cardamom clumps; organic mitigation needed.",
                "बड़ी इलायची में चिरके विषाणु और पत्तियों का पीला पड़ना (जैविक नियंत्रण अनिवार्य)।",
                "Rogue out and compost virus-infected stools; apply certified bio-pesticide Beauveria bassiana.",
                "संक्रमित पौधों को उखाड़कर नष्ट करें; प्रमाणित ब्यूवेरिया बेसियाना जैव-कीटनाशक का प्रयोग करें।");

        // Tripura
        addAlert("ALT-040", "West Tripura", "Tripura", "Rubber & Pineapple", "Abnormal Leaf Fall & Mealybug", "MEDIUM",
                "Premature leaf drop in rubber plantations under cloudy drizzle and mealybugs on pineapples.",
                "रबर के बागानों में पत्तियों का असमय गिरना और अनानास पर मिलीबग का जमावड़ा।",
                "Dust with sulfur or spray Copper Oxychloride 0.2%; release Cryptolaemus montrouzieri beetles.",
                "कॉपर ऑक्सीक्लोराइड 0.2% का छिड़काव करें और मित्र कीट क्रिप्टोलेमस छोड़ें।");

        // Meghalaya
        addAlert("ALT-041", "East Khasi Hills", "Meghalaya", "Ginger & Turmeric", "Rhizome Soft Rot (Pythium)", "HIGH",
                "Water-soaking at collar region and foul-smelling collapse of ginger pseudostems on hill terraces.",
                "अदरक की गांठों में सड़न (राइजोम सॉफ्ट रॉट) और तने का नीचे से गलकर गिरना।",
                "Drench soil beds with Metalaxyl-Mancozeb @ 2.5 g/L; improve terrace trench drainage.",
                "मेटालेक्सिल-मैनकोजेब 2.5 ग्राम/लीटर से क्यारियों में ड्रेंचिंग करें और जल निकासी नालियां बनाएं।");

        // Manipur
        addAlert("ALT-042", "Imphal West", "Manipur", "Rice & King Chilli", "Blast & Anthracnose Dieback", "HIGH",
                "Spindle leaf blast lesions in valley paddy and circular sunken lesions on unripe chillies.",
                "घाटी के धान में झुलसा रोग और राजा मिर्च में एंथ्राक्नोज फफूंद से टहनियों का सूखना।",
                "Spray Tricyclazole 75 WP @ 0.6 g/L or Azoxystrobin + Difenoconazole @ 1 ml/L.",
                "ट्राइसाइक्लाजोल 0.6 ग्राम/लीटर या एजोक्सिस्ट्रोबिन 1 मिली/लीटर का छिड़काव करें।");

        // Mizoram
        addAlert("ALT-043", "Aizawl", "Mizoram", "Arecanut & Chilli", "Fruit Rot & Colletotrichum Anthracnose", "MEDIUM",
                "Premature nut fall in hill slopes and necrotic spots on chilli pods.",
                "ढलान वाले खेतों में सुपारी का समय पूर्व गिरना और मिर्च पर काले सूखे धब्बे।",
                "Spray 1% Bordeaux mixture on arecanut bunches; apply Mancozeb 75 WP @ 2.5 g/L on chillies.",
                "सुपारी के गुच्छों पर 1% बोर्डो मिश्रण और मिर्च पर मैनकोजेब 2.5 ग्राम/लीटर छिड़कें।");

        // Nagaland
        addAlert("ALT-044", "Kohima", "Nagaland", "Naga King Chilli", "Aphids & Broad Mite Damage", "MEDIUM",
                "Downward curling and brittle dark leaves with stunted apical growth in Naga King Chilli.",
                "नागा राजा मिर्च में एफिड्स और माइट्स के कारण पत्तियों का नीचे की ओर मुड़ना व कड़ा होना।",
                "Apply Neem oil (5 ml/L) + Wettable Sulfur @ 2 g/L or Spiromesifen 22.9 SC @ 1 ml/L.",
                "नीम का तेल (5 मिली/ली) + घुलनशील गंधक (2 ग्राम/ली) या स्पाइरोमेसिफेन का छिड़काव करें।");

        // Arunachal Pradesh
        addAlert("ALT-045", "Papum Pare", "Arunachal Pradesh", "Mandarin Orange & Maize", "Citrus Trunk Borer & Fall Armyworm", "HIGH",
                "Frass ejection holes in orange trunks and extensive leaf shredding in terrace maize.",
                "संतरे के तने में छेद और बुरादा निकलना व मक्के में फॉल आर्मीवर्म द्वारा पत्तियां काटना।",
                "Inject Dichlorvos (0.1%) into borer tunnels and plug with wet mud; apply Bt spray on maize whorls.",
                "तने के छिद्रों में दवा डालकर गीली मिट्टी से बंद करें; मक्के की गोभ में बीटी का छिड़काव करें।");

        // Andaman and Nicobar
        addAlert("ALT-046", "South Andaman", "Andaman and Nicobar", "Coconut & Arecanut", "Rhinoceros Beetle (Oryctes rhinoceros)", "HIGH",
                "V-shaped cuts on fronds and bore holes at crown base of coconut palms.",
                "नारियल की पत्तियों पर 'V' आकार के कट और पेड़ के ऊपरी हिस्से में छेद।",
                "Hook out beetles using iron wire; fill crown axils with mixture of Sevidol/sand and neem cake.",
                "लोहे के तार से भृंग निकालें; पत्तियों के आधार पर बालू और नीम की खली का मिश्रण भरें।");

        // Lakshadweep
        addAlert("ALT-047", "Kavaratti", "Lakshadweep", "Coconut", "Eriophyid Mite (Aceria guerreronis)", "HIGH",
                "Triangular pale yellow patches near perianth maturing into brown warty fissures on nuts.",
                "नारियल के बटन पर हल्के पीले धब्बे व भूरी खुरदरी दरारें (माइट्स का प्रकोप)।",
                "Root feeding with Azadirachtin 5% (10 ml in 10 ml water) or spray wettable sulfur 4 g/L.",
                "नीम तेल आधारित अजाडिराक्टिन से रूट-फीडिंग करें या घुलनशील सल्फर का छिड़काव करें।");

        // Puducherry
        addAlert("ALT-048", "Puducherry", "Puducherry", "Paddy & Jasmine", "Leaf Folder & Jasmine Budworm", "MEDIUM",
                "Leaves folded longitudinally with scraped white transparent patches inside.",
                "धान की मुड़ी हुई पत्तियां और चमेली की कलियों में सूंडी द्वारा छेद।",
                "Release Trichogramma chilonis @ 2 cc/acre; spray Cartap hydrochloride 50 SP @ 1 g/L.",
                "ट्राइकोग्रामा कार्ड 2 सीसी/एकड़ लगाएं और कार्टाप हाइड्रोक्लोराइड 1 ग्राम/लीटर छिड़कें।");

        // Chandigarh
        addAlert("ALT-049", "Chandigarh", "Chandigarh", "Wheat & Ornamental Flora", "Aphid Colonization & Powdery Mildew", "LOW",
                "Colonies of green aphids on ears of wheat and powdery mildew on ornamental flora.",
                "गेहूं की बालियों पर हरे चेपा (माहू) का जमावड़ा और सफेद फफूंद के लक्षण।",
                "Conserve ladybird beetles; spray systemic Thiamethoxam 25 WG @ 0.2 g/L if threshold exceeded.",
                "मित्र कीट लेडीबर्ड भृंग का संरक्षण करें; आवश्यकता पड़ने पर थायमेथोक्सम 0.2 ग्राम/ली छिड़कें।");

        // Dadra and Nagar Haveli and Daman and Diu
        addAlert("ALT-050", "Daman", "Dadra and Nagar Haveli and Daman and Diu", "Mango & Paddy", "Mango Hopper & Leaf Blast", "MEDIUM",
                "Hopper nymph swarming on tender panicles causing blossom drop and sticky sooty honey.",
                "आम के बौर पर फुदका कीट (हॉपर) का हमला जिससे फूल झड़ना व काला फफूंद लगना।",
                "Spray Imidacloprid 17.8 SL @ 0.3 ml/L at panicle emergence; avoid spraying during peak bloom.",
                "बौर निकलते समय इमिडाक्लोप्रिड 0.3 मिली/लीटर का छिड़काव करें।");
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
