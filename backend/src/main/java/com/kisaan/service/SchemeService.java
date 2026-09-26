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
        // --- 1. INCOME SUPPORT & CREDIT ---
        addScheme("SCH-001",
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
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-002",
                "Kisan Credit Card (KCC) & Modified Interest Subvention",
                "किसान क्रेडिट कार्ड (KCC) व ब्याज छूट योजना",
                "Credit & Loans",
                "Institutional crop loans up to ₹3 Lakhs at 4% effective interest upon prompt annual repayment.",
                "समय पर अदायगी करने पर मात्र 4% रियायती ब्याज दर पर ₹3 लाख तक का कृषि फसल ऋण।",
                "All farmers, tenant farmers, dairy farmers, fishers, and SHGs.",
                "सभी भूमिधारक, बटाईदार किसान, पशुपालक और मत्स्य पालक।",
                "4% effective annual interest (7% base minus 3% prompt repayment incentive).",
                "प्रभावी 4% वार्षिक ब्याज दर।",
                "https://www.myscheme.gov.in/schemes/kcc",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-003",
                "PMFBY (Pradhan Mantri Fasal Bima Yojana)",
                "प्रधानमंत्री फसल बीमा योजना",
                "Crop Insurance",
                "Comprehensive crop insurance covering non-preventable natural risks from sowing to post-harvest.",
                "बुआई से लेकर कटाई के बाद तक बेमौसम बारिश, सूखा व कीट प्रकोप से होने वाले नुकसान पर फसल बीमा।",
                "All farmers growing notified crops in notified areas.",
                "अधिसूचित क्षेत्रों में अधिसूचित फसलें उगाने वाले सभी किसान।",
                "Maximum premium: 2% for Kharif, 1.5% for Rabi, 5% for commercial/horticultural crops.",
                "खरीफ 2%, रबी 1.5%, बागवानी 5% अधिकतम प्रीमियम; शेष सरकार देती है।",
                "https://pmfby.gov.in",
                Arrays.asList("All India", "National"), true);

        // --- 2. RECENT HIGH-IMPACT INITIATIVES (2020-2025) ---
        addScheme("SCH-004",
                "Digital Agriculture Mission (2024 Approval)",
                "डिजिटल कृषि मिशन (2024)",
                "Technology & Drones",
                "₹2,817 Cr Union Cabinet approved digital infrastructure creating AgriStack (Digital Farmer ID), unified crop registries, and automated drought/weather crop assessments.",
                "₹2,817 करोड़ की डिजिटल कृषि योजना: किसान डिजिटल आईडी (AgriStack), डिजिटल गिरदावरी और त्वरित फसल नुकसान आकलन।",
                "All farmers across Indian states integrating with State Digital Land Records.",
                "राज्य डिजिटल भू-अभिलेखों से जुड़े सभी भारतीय किसान।",
                "Universal Digital Farmer ID for single-window access to PM-KISAN, crop insurance, and subsidized inputs.",
                "एकल किसान आईडी से सभी सरकारी योजनाओं व क्रेडिट का सीधा लाभ।",
                "https://agricoop.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-005",
                "Clean Plant Programme (CPP - 2024)",
                "क्लीन प्लांट प्रोग्राम (2024)",
                "Horticulture",
                "₹1,765 Cr mission establishing 9 state-of-the-art Clean Plant Centers across India to supply certified disease-free & virus-free planting material for fruit orchards.",
                "फलों (सेब, संतरा, अंगूर, आम) के बगीचों के लिए शत-प्रतिशत रोगमुक्त व वायरस-मुक्त उन्नत पौधे उपलब्ध कराने की योजना।",
                "Fruit orchard growers, horticulture farmers, and certified nursery owners.",
                "बागवानी किसान और फल उत्पादक।",
                "Up to 50% subsidy on certified clean planting material and tissue culture rootstocks.",
                "प्रमाणित रोगमुक्त पौधों और ग्राफ्टिंग पर 50% तक सरकारी अनुदान।",
                "https://nhb.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-006",
                "Kisan Drone Scheme & SMAM Drone Subsidy",
                "किसान ड्रोन योजना व उपकरण अनुदान",
                "Technology & Drones",
                "Subsidy for precision aerial spraying of liquid fertilizers and bio-pesticides using agricultural drones.",
                "खेतों में कीटनाशक और नैनो यूरिया के सटीक हवाई छिड़काव के लिए किसान ड्रोन खरीद पर बंपर छूट।",
                "FPOs, rural youth entrepreneurs, custom hiring centers, and individual farmers.",
                "किसान उत्पादक संगठन (FPO), ग्रामीण युवा और व्यक्तिगत किसान।",
                "100% grant (up to ₹10 Lakhs) for ICAR/KVKs, 75% for FPOs, 50% (up to ₹5 Lakhs) for SC/ST/Women/Small farmers.",
                "FPO को 75% और छोटे/महिला किसानों को 50% (₹5 लाख तक) अनुदान।",
                "https://agrimachinery.nic.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-007",
                "PM-PRANAM (Alternative Fertilizers Promotion)",
                "पीएम-प्रणाम योजना (वैकल्पिक उर्वरक प्रोत्साहन)",
                "Natural & Bio Farming",
                "Incentivizes states and farmers to adopt bio-fertilizers, organic manure, and nano-urea while reducing chemical fertilizer dependency.",
                "रासायनिक खादों की जगह नैनो यूरिया, जैविक खाद और जैव उर्वरकों के प्रयोग पर प्रोत्साहन।",
                "All progressive farmers and Gram Panchayats adopting balanced nutrient management.",
                "संतुलित खाद और जैविक पद्धति अपनाने वाले किसान व ग्राम पंचायतें।",
                "50% of fertilizer subsidy savings returned as grant for local organic asset creation.",
                "उर्वरक सब्सिडी बचत का 50% हिस्सा ग्रामीण जैविक विकास पर खर्च।",
                "https://fert.nic.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-008",
                "GOBARdhan Scheme (Bio-Agro Resources Dhan)",
                "गोबर-धन योजना (बायो-सीएनजी व जैविक खाद)",
                "Renewable Energy",
                "Converts cattle dung and agricultural residue into bio-CNG/biogas and enriched fermented organic manure (FOM) for soil rejuvenation.",
                "गोबर और कृषि अवशेषों से बायोगैस व उच्च गुणवत्तायुक्त जैविक खाद तैयार करने हेतु अनुदान।",
                "Dairy farmers, Gaushalas, farmer cooperatives, and village communities.",
                "पशुपालक किसान, गौशालाएं और ग्रामीण क्लस्टर।",
                "Up to ₹50 Lakhs capital assistance for commercial CBG/Bio-gas plants; free organic manure for participating farmers.",
                "व्यावसायिक बायोगैस संयंत्र पर ₹50 लाख तक की वित्तीय सहायता।",
                "https://gobardhan.co.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-009",
                "PM-KUSUM (Solar Agricultural Pumps)",
                "पीएम कुसुम सौर पंप योजना",
                "Renewable Energy",
                "Solarization of diesel and grid-connected farm irrigation pumps, plus grid power feed-in tariffs.",
                "डीजल पंपों को सौर ऊर्जा में बदलना और खेतों में सोलर पंप लगाने पर 60% सरकारी अनुदान।",
                "Individual farmers, water user associations, and cooperatives.",
                "व्यक्तिगत किसान और जल उपयोगकर्ता संघ।",
                "60% total subsidy (30% Central + 30% State); farmer pays only 10% upfront.",
                "लागत का 60% अनुदान; किसान को मात्र 10% अग्रिम देना होता है।",
                "https://pmkusum.mnre.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-010",
                "PM Surya Ghar Muft Bijli Yojana (Rural Agri Solar - 2024)",
                "पीएम सूर्य घर मुफ्त बिजली योजना (2024)",
                "Renewable Energy",
                "Massive rooftop solar scheme providing up to 300 units of free solar electricity per month for farm households and rural agro-sheds.",
                "ग्रामीण घरों और फार्म शेड पर सोलर पैनल लगाकर हर महीने 300 यूनिट तक मुफ्त बिजली।",
                "Rural households, farmer dwellings, and agricultural homesteads.",
                "ग्रामीण आवासीय किसान और कृषि परिवार।",
                "Direct cash subsidy of ₹30,000 for 1 kW, ₹60,000 for 2 kW, and ₹78,000 for 3 kW+ installations.",
                "3 kW तक की सोलर प्रणाली पर ₹78,000 तक की सीधी बैंक सब्सिडी।",
                "https://pmsuryaghar.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-011",
                "National Mission on Natural Farming (NMNF - 2023)",
                "राष्ट्रीय प्राकृतिक खेती मिशन (NMNF)",
                "Natural & Bio Farming",
                "Dedicated national mission to promote chemical-free, climate-resilient natural farming on 7.5 lakh hectares.",
                "रसायन मुक्त प्राकृतिक खेती (जीवामृत, बीजामृत) को बढ़ावा देने के लिए समर्पित मिशन।",
                "Farmers willing to transition cultivable land to natural chemical-free farming.",
                "प्राकृतिक खेती अपनाने वाले किसान।",
                "Financial assistance of ₹15,000 per hectare for 3 years + bio-input resource centers.",
                "3 वर्षों में ₹15,000 प्रति हेक्टेयर की सहायता व निशुल्क परीक्षण किट।",
                "https://naturalfarming.dac.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-012",
                "National Mission on Edible Oils - Oil Palm (NMEO-OP)",
                "राष्ट्रीय खाद्य तेल मिशन - पाम ऑयल",
                "Commercial Crops",
                "₹11,040 Cr mission to increase domestic edible oil production with guaranteed viability price protection.",
                "खाद्य तेलों में आत्मनिर्भरता हेतु पाम ऑयल की खेती पर रोपण व मूल्य संरक्षण सहायता।",
                "Farmers in coastal, southern, and north-eastern notified agro-climatic zones.",
                "पाम ऑयल की खेती के लिए उपयुक्त अधिसूचित क्षेत्रों के किसान।",
                "₹29,000/ha for planting material, ₹10,000/ha for maintenance + formula price assurance.",
                "पौध सामग्री पर ₹29,000 प्रति हेक्टेयर और न्यूनतम सुनिश्चित मूल्य की गारंटी।",
                "https://nmeo.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-013",
                "Agri-SURE Startup Capital Fund (2024)",
                "एग्री-श्योर स्टार्टअप फंड (2024)",
                "Technology & Drones",
                "₹750 Cr blended capital fund by MoA&FW and NABARD supporting tech-driven startups in precision farming, IoT sensors, and supply chain automation.",
                "नाबार्ड व कृषि मंत्रालय द्वारा कृषि तकनीक, IoT सेंसर और ड्रोन स्टार्टअप्स के लिए ₹750 करोड़ का कोष।",
                "Agri-tech startups, rural youth innovators, and FPO tech incubators.",
                "एग्रीटेक स्टार्टअप और ग्रामीण तकनीकी उद्यमी।",
                "Equity investment and debt support up to ₹25 Crore per innovative agri-enterprise.",
                "कृषि नवाचार और स्टार्टअप्स के लिए ₹25 करोड़ तक का निवेश व ऋण।",
                "https://nabard.org",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-014",
                "PMFME (Micro Food Processing Enterprises)",
                "पीएम सूक्ष्म खाद्य उद्योग उन्नयन योजना (PMFME)",
                "Post-Harvest & Processing",
                "Credit-linked capital subsidy for setting up small agro-processing units like flour mills, spice grinding, pickle making, and oil expellers.",
                "आटा चक्की, मसाला पिसाई, तेल एक्सपेलर और फल प्रसंस्करण इकाइयां लगाने पर 35% पूंजीगत सब्सिडी।",
                "Individual farmers, Self-Help Groups (SHGs), producer cooperatives.",
                "व्यक्तिगत किसान, महिला स्वयं सहायता समूह और ग्रामीण उद्यमी।",
                "35% credit-linked capital subsidy up to maximum of ₹10 Lakhs per enterprise.",
                "परियोजना लागत का 35% (अधिकतम ₹10 लाख) का बैंक अनुदान।",
                "https://pmfme.mofpi.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-015",
                "Operation Greens (TOP to TOTAL Expanded)",
                "ऑपरेशन ग्रीन्स (टमाटर, प्याज, आलू व फल-सब्जियां)",
                "Post-Harvest & Processing",
                "Supply chain stabilization scheme providing 50% subsidy on transportation and cold storage for 22 perishable crops.",
                "टमाटर, प्याज, आलू सहित 22 फल व सब्जियों के परिवहन और कोल्ड स्टोरेज पर 50% भाड़ा सब्सिडी।",
                "Farmers, FPOs, food processors, and agricultural logistics providers.",
                "किसान, एफपीओ और कृषि उपज परिवहनकर्ता।",
                "50% subsidy on freight charges via Kisan Rail and cold storage rents during glut periods.",
                "किसान रेल भाड़े और शीतगृह भंडारण किराए पर 50% सरकारी छूट।",
                "https://mofpi.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-016",
                "National Beekeeping & Honey Mission (NBHM)",
                "राष्ट्रीय मधुमक्खी पालन एवं शहद मिशन",
                "Allied Agriculture",
                "Sweet Revolution scheme promoting scientific bee-keeping for additional farmer income and boosted crop cross-pollination yields.",
                "अतिरिक्त आय और परागण से फसल पैदावार बढ़ाने के लिए आधुनिक मधुमक्खी पालन पर अनुदान।",
                "Small and marginal farmers, rural youth, and beekeeping cooperatives.",
                "छोटे व सीमांत किसान और ग्रामीण युवा।",
                "Up to 80% subsidy on bee boxes, colonies, honey extractors, and custom honey testing labs.",
                "मधुमक्खी के डिब्बे, कॉलोनी और शहद निष्कासन यंत्रों पर 80% तक सहायता।",
                "https://nbhm.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-017",
                "PMKSY (Per Drop More Crop - Micro Irrigation)",
                "पीएम कृषि सिंचाई योजना (प्रति बूंद अधिक फसल)",
                "Irrigation Subsidy",
                "Capital subsidy for installation of precision drip and sprinkler micro-irrigation systems to maximize water use efficiency.",
                "ड्रिप व स्प्रिंकलर सिंचाई प्रणाली लगाने पर छोटे किसानों को 55% तक सरकारी सब्सिडी।",
                "Farmers with assured water source and land title documents (7/12 or Jamabandi).",
                "जल स्रोत और वैध भूमि अभिलेख वाले सभी किसान।",
                "Up to 55% subsidy for small/marginal farmers, 45% for other farmers.",
                "छोटे व सीमांत किसानों को 55% और अन्य किसानों को 45% तक उपकरण सब्सिडी।",
                "https://pmksy.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-018",
                "Soil Health Card Scheme",
                "मृदा स्वास्थ्य कार्ड योजना",
                "Soil Nutrition",
                "Provides periodic soil testing report with crop-wise nutrient recommendations for Nitrogen, Phosphorus, Potassium, and micronutrients.",
                "खेत की मिट्टी की निशुल्क जांच कर एनपीके और सूक्ष्म पोषक तत्वों के संतुलित प्रयोग की रिपोर्ट।",
                "All farmers across rural districts with agricultural land.",
                "कृषि योग्य भूमि वाले सभी ग्रामीण किसान।",
                "Free testing of 12 soil health parameters every 2 years with customized fertilizer dosage.",
                "हर 2 साल में 12 मृदा मापदंडों की निशुल्क जांच व संतुलित खाद की सलाह।",
                "https://soilhealth.dac.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-019",
                "PKVY (Paramparagat Krishi Vikas Yojana)",
                "परंपरागत कृषि विकास योजना",
                "Natural & Bio Farming",
                "Promotes cluster-based certified chemical-free organic farming with PGS-India organic certification support.",
                "समूह आधारित जैविक खेती को बढ़ावा और पीजीएस-इंडिया जैविक प्रमाणीकरण।",
                "Farmer groups forming clusters of minimum 20 hectares (50 acres).",
                "कम से कम 20 हेक्टेयर का क्लस्टर बनाने वाले किसान समूह।",
                "₹50,000 per hectare for 3 years (₹31,000 directly for organic inputs/bio-fertilizers).",
                "3 साल में ₹50,000 प्रति हेक्टेयर की वित्तीय सहायता।",
                "https://pgsindia-ncof.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-020",
                "e-NAM (National Agriculture Market)",
                "ई-राष्ट्रीय कृषि बाजार (ई-नाम)",
                "Market Linkage",
                "Online electronic trading platform linking APMC mandis nationwide for transparent price discovery and direct online bidding.",
                "देश भर की कृषि उपज मंडियों को ऑनलाइन जोड़कर पारदर्शी बोली और बेहतर भाव की सुविधा।",
                "Any registered farmer bringing produce to an e-NAM integrated APMC yard.",
                "ई-नाम से जुड़ी किसी भी मंडी में उपज लाने वाले पंजीकृत किसान।",
                "Zero commission fee on inter-mandi trade; direct electronic settlement to farmer bank account.",
                "बिना बिचौलियों के देशव्यापी व्यापारियों से उच्चतम बोली और सीधा बैंक भुगतान।",
                "https://enam.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-021",
                "Agriculture Infrastructure Fund (AIF)",
                "कृषि अवसंरचना कोष",
                "Post-Harvest & Processing",
                "Medium-long term debt financing for post-harvest management infrastructure like warehouses, silos, cold storage, and onion chawls.",
                "गोदाम, कोल्ड स्टोरेज, ग्रेडिंग यूनिट और प्याज भंडारण शेड बनाने के लिए सस्ता ऋण।",
                "Farmers, FPOs, Agri-entrepreneurs, and Primary Agricultural Credit Societies (PACS).",
                "किसान, एफपीओ और कृषि उद्यमी।",
                "3% annual interest subvention on loans up to ₹2 Crore for up to 7 years with credit guarantee coverage.",
                "₹2 करोड़ तक के ऋण पर 7 वर्षों के लिए 3% वार्षिक ब्याज छूट।",
                "https://agriinfra.dac.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-022",
                "PMMSY (Fisheries & Aquaculture)",
                "प्रधानमंत्री मत्स्य संपदा योजना",
                "Allied Agriculture",
                "Financial assistance for farm ponds, biofloc aquaculture units, fish seed hatcheries, and aerators.",
                "खेत में तालाब, बायोफ्लॉक मछली पालन और गुणवत्तापूर्ण मत्स्य बीज उत्पादन के लिए सहायता।",
                "Fish farmers, fishers, rural youth, and self-help groups.",
                "मत्स्य पालक किसान और ग्रामीण युवा।",
                "40% government subsidy for general category, 60% for SC/ST/Women beneficiaries.",
                "सामान्य वर्ग को 40% और महिला/अनुसूचित जाति/जनजाति को 60% तक सहायता।",
                "https://pmmsy.dof.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-023",
                "Sub-Mission on Agricultural Mechanization (SMAM)",
                "कृषि यंत्रीकरण उप-अभियान (मशीनरी सब्सिडी)",
                "Technology & Drones",
                "Financial assistance for purchasing modern tractors, rotavators, power tillers, and laser land levelers.",
                "ट्रैक्टर, रोटावेटर, पावर टिलर और बुआई मशीनों की खरीद पर अनुदान।",
                "Individual farmers, custom hiring centers, and Farmer Producer Organizations (FPOs).",
                "व्यक्तिगत किसान और कस्टम हायरिंग सेंटर।",
                "40% to 50% subsidy on procurement cost of agricultural implements.",
                "कृषि उपकरणों की खरीद लागत पर 40% से 50% तक सब्सिडी।",
                "https://agrimachinery.nic.in",
                Arrays.asList("All India", "National"), true);

        // --- 3. MAJOR STATE-SPECIFIC TOP-UP SCHEMES ---
        addScheme("SCH-024",
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
                Arrays.asList("Maharashtra"), false);

        addScheme("SCH-025",
                "Rythu Bharosa / Rythu Bandhu (Andhra Pradesh & Telangana)",
                "रायथु भरोसा / रायथु बंधु इनपुट सहायता",
                "State Top-Up",
                "Agricultural input support providing ₹13,500 to ₹15,000 per year per farmer family prior to crop sowing seasons.",
                "बुआई से पहले खाद, बीज व जुताई खर्च के लिए ₹13,500 से ₹15,000 प्रति वर्ष की प्रत्यक्ष सहायता।",
                "Landholder farmer families and tenant farmers belonging to SC, ST, BC, and minority groups.",
                "सभी भूमिधारक और पट्टेदार किसान।",
                "Direct cash transfer credited into farmer bank accounts ahead of Kharif and Rabi sowings.",
                "खरीफ और रबी बुआई से पहले सीधे बैंक खाते में भुगतान।",
                "https://ysrrythubharosa.ap.gov.in",
                Arrays.asList("Andhra Pradesh", "Telangana"), false);

        addScheme("SCH-026",
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
                Arrays.asList("Madhya Pradesh"), false);

        addScheme("SCH-027",
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
                Arrays.asList("Himachal Pradesh"), false);

        addScheme("SCH-028",
                "Krishi Bhagya Scheme (Karnataka)",
                "कृषि भाग्य योजना (कर्नाटक)",
                "Irrigation Subsidy",
                "Construction of polythene-lined farm ponds (Krishi Honda) and subsidized diesel/solar pump sets in rainfed dry zones.",
                "सूखे क्षेत्रों में वर्षा जल संचयन हेतु पॉलीथिन लाइनिंग वाले कृषि तालाब (कृषि होंडा) पर 80% सब्सिडी।",
                "Rainfed agricultural farmers across Karnataka districts.",
                "कर्नाटक के वर्षा आधारित क्षेत्रों के किसान।",
                "80% to 90% subsidy on farm pond excavation, polythene tarpaulin lining, and lift pumps.",
                "खेत तालाब निर्माण और पंप सेट पर 80% से 90% तक सरकारी सब्सिडी।",
                "https://raitamitra.karnataka.gov.in",
                Arrays.asList("Karnataka"), false);

        addScheme("SCH-029",
                "Subsidized Crop Residue Management (Punjab & Haryana)",
                "फसल अवशेष प्रबंधन मशीनरी सब्सिडी (पंजाब व हरियाणा)",
                "Technology & Drones",
                "50% to 80% subsidy on Super Seeder, Smart Seeder, Paddy Straw Chopper, and Balers to curb stubble burning.",
                "पराली प्रबंधन हेतु सुपर सीडर, हैप्पी सीडर और बेलर मशीनों की खरीद पर 50% से 80% अनुदान।",
                "Individual farmers and Custom Hiring Centers in Punjab and Haryana.",
                "पंजाब और हरियाणा के व्यक्तिगत किसान व कस्टम हायरिंग सेंटर।",
                "50% subsidy for individual farmers and 80% for cooperatives/panchayats on CRM machinery.",
                "व्यक्तिगत किसान को 50% और किसान समितियों को 80% अनुदान।",
                "https://agrimachinerypb.com",
                Arrays.asList("Punjab", "Haryana"), false);

        addScheme("SCH-030",
                "UP Kisan Uday Yojana (Uttar Pradesh)",
                "यूपी किसान उदय योजना (उत्तर प्रदेश)",
                "Irrigation Subsidy",
                "Free distribution and installation of energy-efficient 5 HP and 7.5 HP submersible solar and electric pump sets.",
                "किसानों को मुफ्त ऊर्जा-दक्ष 5 और 7.5 एचपी के सोलर व इलेक्ट्रिक सबमर्सिबल पंप सेट उपलब्ध कराना।",
                "Small and marginal farmers holding agricultural land in Uttar Pradesh.",
                "उत्तर प्रदेश के छोटे व सीमांत किसान।",
                "100% free distribution and 5-year free maintenance of high-efficiency irrigation pump sets.",
                "सिंचाई पंप सेटों का पूर्णतः निशुल्क वितरण व 5 साल की फ्री मेंटेनेंस।",
                "https://upagriculture.com",
                Arrays.asList("Uttar Pradesh"), false);
    }

    private void addScheme(String id, String name, String nameHi, String category,
                           String desc, String descHi, String elig, String eligHi,
                           String ben, String benHi, String url, List<String> states, boolean isNational) {
        allSchemes.add(new Scheme(id, name, nameHi, category, desc, descHi, elig, eligHi, ben, benHi, url, states, isNational));
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
