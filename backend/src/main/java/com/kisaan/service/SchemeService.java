package com.kisaan.service;

import com.kisaan.model.Scheme;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchemeService {

    private final List<Scheme> allSchemes = new ArrayList<>();

    public SchemeService() {
        initSchemes();
    }

    private void initSchemes() {
        // 1. PM-KISAN
        allSchemes.add(new Scheme(
                "SCH-001",
                "PM-KISAN (Pradhan Mantri Kisan Samman Nidhi)",
                "पीएम-किसान सम्मान निधि योजना",
                "Income Support",
                "Direct income support of ₹6,000 per year transferred in three equal installments of ₹2,000 directly into bank accounts.",
                "किसानों के बैंक खातों में ₹6,000 प्रति वर्ष तीन समान किस्तों (₹2,000 प्रत्येक) में सीधे अंतरण।",
                "All landholding farmer families with cultivable land in their names.",
                "सभी भूमिधारक किसान परिवार जिनके नाम कृषि योग्य भूमि दर्ज है।",
                "₹6,000 annually via Direct Benefit Transfer (DBT)",
                "वार्षिक ₹6,000 सीधे बैंक खाते में (डीबीटी)",
                "https://pmkisan.gov.in",
                Arrays.asList("All India", "National"),
                true
        ));

        // 2. PMFBY (Crop Insurance)
        allSchemes.add(new Scheme(
                "SCH-002",
                "PMFBY (Pradhan Mantri Fasal Bima Yojana)",
                "प्रधानमंत्री फसल बीमा योजना",
                "Crop Insurance",
                "Comprehensive crop insurance covering sowing to post-harvest yield losses against unseasonal rains, drought, and pests.",
                "बुआई से लेकर कटाई के बाद तक बेमौसम बारिश, सूखा व कीट प्रकोप से होने वाले नुकसान पर व्यापक फसल बीमा।",
                "All farmers including sharecroppers and tenant farmers growing notified crops in notified areas.",
                "अधिसूचित क्षेत्रों में अधिसूचित फसलें उगाने वाले सभी भू-स्वामी व बटाईदार किसान।",
                "Maximum premium: 2% for Kharif, 1.5% for Rabi, 5% for horticultural crops; balance paid by Govt.",
                "खरीफ के लिए 2%, रबी के लिए 1.5% प्रीमियम; शेष प्रीमियम सरकार वहन करती है।",
                "https://pmfby.gov.in",
                Arrays.asList("All India", "National"),
                true
        ));

        // 3. Soil Health Card Scheme
        allSchemes.add(new Scheme(
                "SCH-003",
                "Soil Health Card Scheme",
                "मृदा स्वास्थ्य कार्ड योजना",
                "Soil Nutrition",
                "Provides periodic soil testing report with crop-wise nutrient recommendations for Nitrogen, Phosphorus, Potassium, and micronutrients.",
                "खेत की मिट्टी की जांच कर एनपीके और सूक्ष्म पोषक तत्वों के उचित प्रयोग की रिपोर्ट व सिफारिश।",
                "All farmers across rural districts with agricultural land.",
                "कृषि योग्य भूमि वाले सभी ग्रामीण किसान।",
                "Free testing of 12 soil health parameters every 2 years with customized fertilizer dosage.",
                "हर 2 साल में 12 मृदा मापदंडों की निशुल्क जांच व संतुलित खाद की सलाह।",
                "https://soilhealth.dac.gov.in",
                Arrays.asList("All India", "National"),
                true
        ));

        // 4. PMKSY (Per Drop More Crop)
        allSchemes.add(new Scheme(
                "SCH-004",
                "PMKSY (Per Drop More Crop - Micro Irrigation)",
                "पीएम कृषि सिंचाई योजना (प्रति बूंद अधिक फसल)",
                "Irrigation Subsidy",
                "Capital subsidy for installation of precision drip and sprinkler micro-irrigation systems to maximize water use efficiency.",
                "पानी की बचत और उत्पादकता बढ़ाने के लिए ड्रिप व स्प्रिंकलर सिंचाई प्रणाली पर भारी सरकारी सब्सिडी।",
                "Farmers with assured water source and land title documents (7/12 or Jamabandi).",
                "जल स्रोत और वैध भूमि अभिलेख वाले सभी किसान।",
                "Up to 55% subsidy for small/marginal farmers, 45% for other farmers on micro-irrigation equipment.",
                "छोटे व सीमांत किसानों को 55% और अन्य किसानों को 45% तक उपकरण सब्सिडी।",
                "https://pmksy.gov.in",
                Arrays.asList("All India", "National"),
                true
        ));

        // 5. PKVY (Paramparagat Krishi Vikas Yojana)
        allSchemes.add(new Scheme(
                "SCH-005",
                "PKVY (Paramparagat Krishi Vikas Yojana)",
                "परंपरागत कृषि विकास योजना",
                "Organic Farming",
                "Promotes cluster-based certified chemical-free organic farming with PGS-India organic certification support.",
                "समूह आधारित जैविक व प्राकृतिक खेती को बढ़ावा और पीजीएस-इंडिया जैविक प्रमाणीकरण।",
                "Farmer groups forming clusters of minimum 20 hectares (50 acres).",
                "कम से कम 20 हेक्टेयर का क्लस्टर बनाने वाले किसान समूह।",
                "₹50,000 per hectare for 3 years (₹31,000 directly for organic inputs/bio-fertilizers).",
                "3 साल में ₹50,000 प्रति हेक्टेयर की वित्तीय सहायता (जैविक खाद व प्रमाणीकरण हेतु)।",
                "https://pgsindia-ncof.gov.in",
                Arrays.asList("All India", "National"),
                true
        ));

        // 6. Kisan Credit Card (KCC)
        allSchemes.add(new Scheme(
                "SCH-006",
                "Kisan Credit Card (KCC) Scheme",
                "किसान क्रेडिट कार्ड योजना",
                "Credit & Loans",
                "Institutional crop loans at highly subsidized interest rates with revolving credit limit up to ₹3 Lakhs.",
                "रियायती ब्याज दर पर ₹3 लाख तक का कृषि ऋण और आसान कार्यशील पूंजी।",
                "All farmers, dairy farmers, fishers, and self-help group members.",
                "सभी किसान, पशुपालक और मत्स्य पालक।",
                "Effective 4% annual interest rate upon prompt loan repayment (7% base minus 3% prompt repayment incentive).",
                "समय पर भुगतान करने पर मात्र 4% वार्षिक प्रभावी ब्याज दर।",
                "https://www.myscheme.gov.in/schemes/kcc",
                Arrays.asList("All India", "National"),
                true
        ));

        // 7. Sub-Mission on Agricultural Mechanization (SMAM)
        allSchemes.add(new Scheme(
                "SCH-007",
                "SMAM (Farm Machinery Subsidy)",
                "कृषि यंत्रीकरण उप-अभियान (मशीनरी सब्सिडी)",
                "Mechanization",
                "Financial assistance for purchasing modern tractors, rotavators, power tillers, and laser land levelers.",
                "ट्रैक्टर, रोटावेटर, पावर टिलर और बुआई मशीनों की खरीद पर अनुदान।",
                "Individual farmers, custom hiring centers, and Farmer Producer Organizations (FPOs).",
                "व्यक्तिगत किसान और कस्टम हायरिंग सेंटर।",
                "40% to 50% subsidy on procurement cost of agricultural implements.",
                "कृषि उपकरणों की खरीद लागत पर 40% से 50% तक सब्सिडी।",
                "https://agrimachinery.nic.in",
                Arrays.asList("All India", "National"),
                true
        ));

        // 8. PM-KUSUM (Solar Agri Pumps)
        allSchemes.add(new Scheme(
                "SCH-008",
                "PM-KUSUM (Solar Agricultural Pumps)",
                "पीएम कुसुम सौर पंप योजना",
                "Renewable Energy",
                "Solarization of diesel and grid-connected farm irrigation pumps, plus grid power feed-in tariffs.",
                "डीजल पंपों को सौर ऊर्जा में बदलना और खेतों में सोलर पंप लगाने पर बंपर छूट।",
                "Individual farmers, water user associations, and cooperatives.",
                "व्यक्तिगत किसान और जल उपयोगकर्ता संघ।",
                "Up to 60% total subsidy (30% Central + 30% State); farmer pays only 10% upfront (balance via bank loan).",
                "लागत का 60% सरकारी अनुदान; किसान को मात्र 10% अग्रिम देना होता है।",
                "https://pmkusum.mnre.gov.in",
                Arrays.asList("All India", "National"),
                true
        ));

        // 9. e-NAM (National Agriculture Market)
        allSchemes.add(new Scheme(
                "SCH-009",
                "e-NAM (Electronic National Agriculture Market)",
                "ई-राष्ट्रीय कृषि बाजार (ई-नाम)",
                "Market Linkage",
                "Online electronic trading platform linking APMC mandis nationwide for transparent price discovery and direct online bidding.",
                "देश भर की कृषि उपज मंडियों को ऑनलाइन जोड़कर पारदर्शी बोली और बेहतर भाव की सुविधा।",
                "Any registered farmer bringing produce to an e-NAM integrated APMC yard.",
                "ई-नाम से जुड़ी किसी भी मंडी में उपज लाने वाले पंजीकृत किसान।",
                "Zero commission fee on inter-mandi trade; direct electronic settlement to farmer bank account.",
                "बिना बिचौलियों के देशव्यापी व्यापारियों से उच्चतम बोली और सीधा बैंक भुगतान।",
                "https://enam.gov.in",
                Arrays.asList("All India", "National"),
                true
        ));

        // 10. Agriculture Infrastructure Fund (AIF)
        allSchemes.add(new Scheme(
                "SCH-010",
                "Agriculture Infrastructure Fund (AIF)",
                "कृषि अवसंरचना कोष",
                "Post-Harvest Infra",
                "Medium-long term debt financing for post-harvest management infrastructure like warehouses, silos, cold storage, and onion chawls.",
                "गोदाम, कोल्ड स्टोरेज, ग्रेडिंग यूनिट और प्याज भंडारण शेड बनाने के लिए सस्ता दीर्घकालिक ऋण।",
                "Farmers, FPOs, Agri-entrepreneurs, and Primary Agricultural Credit Societies (PACS).",
                "किसान, एफपीओ और कृषि उद्यमी।",
                "3% annual interest subvention on loans up to ₹2 Crore for up to 7 years with credit guarantee coverage.",
                "₹2 करोड़ तक के ऋण पर 7 वर्षों के लिए 3% वार्षिक ब्याज छूट।",
                "https://agriinfra.dac.gov.in",
                Arrays.asList("All India", "National"),
                true
        ));

        // 11. PMMSY (Fisheries & Aquaculture)
        allSchemes.add(new Scheme(
                "SCH-011",
                "PM Matsya Sampada Yojana (PMMSY)",
                "प्रधानमंत्री मत्स्य संपदा योजना",
                "Allied Agriculture",
                "Financial assistance for farm ponds, biofloc aquaculture units, fish seed hatcheries, and aerators.",
                "खेत में तालाब, बायोफ्लॉक मछली पालन और गुणवत्तापूर्ण मत्स्य बीज उत्पादन के लिए वित्तीय सहायता।",
                "Fish farmers, fishers, rural youth, and self-help groups.",
                "मत्स्य पालक किसान और ग्रामीण युवा।",
                "40% government subsidy for general category, 60% for SC/ST/Women beneficiaries.",
                "सामान्य वर्ग को 40% और महिला/अनुसूचित जाति/जनजाति को 60% तक सहायता।",
                "https://pmmsy.dof.gov.in",
                Arrays.asList("All India", "National"),
                true
        ));

        // 12. Rashtriya Gokul Mission
        allSchemes.add(new Scheme(
                "SCH-012",
                "Rashtriya Gokul Mission",
                "राष्ट्रीय गोकुल मिशन",
                "Dairy & Livestock",
                "Development and conservation of indigenous bovine breeds, artificial insemination, and sex-sorted semen subsidy.",
                "स्वदेशी नस्लों (गीर, साहीवाल, थारपारकर) के संरक्षण और उन्नत नस्ल सुधार के लिए अनुदान।",
                "Livestock farmers and milk cooperative members.",
                "पशुपालक किसान और दुग्ध उत्पादक।",
                "Free doorstep artificial insemination (AI) and up to 50% capital subsidy on cattle breeding farms.",
                "घर-द्वार पर निशुल्क कृत्रिम गर्भाधान और ब्रीडिंग फार्म पर 50% तक अनुदान।",
                "https://dahd.nic.in/schemes/programmes/rashtriya-gokul-mission",
                Arrays.asList("All India", "National"),
                true
        ));

        // 13. State: Maharashtra — Namo Shetkari Mahasanman Nidhi
        allSchemes.add(new Scheme(
                "SCH-013",
                "Namo Shetkari Mahasanman Nidhi (Maharashtra)",
                "नमो शेतकरी महासन्मान निधी योजना (महाराष्ट्र)",
                "State Top-Up",
                "State Government cash benefit of ₹6,000 per year given in addition to Central PM-KISAN, granting ₹12,000 total.",
                "महाराष्ट्र सरकार द्वारा केंद्र की पीएम-किसान योजना के ऊपर अतिरिक्त ₹6,000 प्रति वर्ष (कुल ₹12,000)।",
                "All active PM-KISAN registered landholding farmers in Maharashtra state.",
                "महाराष्ट्र के सभी सक्रिय पीएम-किसान पंजीकृत किसान।",
                "₹6,000 per year transferred in 3 installments into Aadhaar-linked bank accounts.",
                "सालाना ₹6,000 अतिरिक्त बैंक खाते में।",
                "https://mahadbt.maharashtra.gov.in",
                Arrays.asList("Maharashtra"),
                false
        ));

        // 14. State: Andhra Pradesh — YSR Rythu Bharosa / PM-KISAN
        allSchemes.add(new Scheme(
                "SCH-014",
                "Rythu Bharosa Input Assistance (Andhra Pradesh)",
                "वायएसआर रायथु भरोसा (आंध्र प्रदेश)",
                "State Top-Up",
                "Agricultural input support providing ₹13,500 per year per farmer family prior to crop sowing seasons.",
                "बुआई से पहले खाद, बीज व जुताई खर्च के लिए ₹13,500 प्रति वर्ष की प्रत्यक्ष सहायता।",
                "Landholder farmer families and tenant farmers belonging to SC, ST, BC, and minority groups in AP.",
                "आंध्र प्रदेश के सभी भूमिधारक और पट्टेदार किसान।",
                "₹13,500 annually in 3 installments (₹7,500 before Kharif, ₹4,000 before Rabi, ₹2,000 at harvest).",
                "वार्षिक ₹13,500 तीन किस्तों में (खरीफ से पूर्व ₹7,500)।",
                "https://ysrrythubharosa.ap.gov.in",
                Arrays.asList("Andhra Pradesh"),
                false
        ));

        // 15. State: Madhya Pradesh — Mukhyamantri Kisan Kalyan Yojana
        allSchemes.add(new Scheme(
                "SCH-015",
                "Mukhyamantri Kisan Kalyan Yojana (Madhya Pradesh)",
                "मुख्यमंत्री किसान कल्याण योजना (मध्य प्रदेश)",
                "State Top-Up",
                "State financial assistance of ₹6,000 annually complementing PM-KISAN to empower rural farmers.",
                "मध्य प्रदेश सरकार द्वारा किसानों को वार्षिक ₹6,000 की पूरक आर्थिक सहायता।",
                "PM-KISAN beneficiaries residing and holding agricultural land in Madhya Pradesh.",
                "मध्य प्रदेश के सभी पीएम-किसान लाभार्थी।",
                "₹6,000 per year credited directly in two installments of ₹3,000.",
                "वार्षिक ₹6,000 की अतिरिक्त सहायता (₹3,000 की 2 किस्तों में)।",
                "https://saara.mp.gov.in",
                Arrays.asList("Madhya Pradesh"),
                false
        ));

        // 16. State: Himachal Pradesh — Mukhya Mantri Khet Sanrakshan Yojana
        allSchemes.add(new Scheme(
                "SCH-016",
                "Mukhya Mantri Khet Sanrakshan Yojana (Himachal Pradesh)",
                "मुख्यमंत्री खेत संरक्षण योजना (हिमाचल प्रदेश)",
                "Crop Protection",
                "Subsidy on solar-powered fencing and barbed wire netting to safeguard fruit and vegetable crops from wild animal attacks.",
                "जंगली जानवरों और बंदरों से फसलों को बचाने के लिए सौर ऊर्जा बाड़बंदी (सोलर फेंसिंग) पर भारी अनुदान।",
                "Individual farmers or farming groups in Himachal Pradesh with agricultural holdings.",
                "हिमाचल प्रदेश के सभी किसान व फल उत्पादक।",
                "Up to 80% capital subsidy for group fencing, 70% for individual farmer installations.",
                "समूह में फेंसिंग पर 80% और व्यक्तिगत स्तर पर 70% तक सरकारी अनुदान।",
                "https://himachal.nic.in/agri",
                Arrays.asList("Himachal Pradesh"),
                false
        ));
    }

    public List<Scheme> getSchemesForState(String state) {
        if (state == null || state.trim().isEmpty() || state.equalsIgnoreCase("All India") || state.equalsIgnoreCase("national")) {
            return allSchemes;
        }

        String target = state.trim().toLowerCase();
        return allSchemes.stream()
                .filter(s -> s.isNational() || s.getApplicableStates().stream().anyMatch(st -> st.equalsIgnoreCase(target)))
                .collect(Collectors.toList());
    }

    public List<Scheme> getAllSchemes() {
        return allSchemes;
    }
}
