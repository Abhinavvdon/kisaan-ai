package com.kisaan.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kisaan.model.Scheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class SchemeService {

    @Value("${gemini.api.key:}")
    private String configuredGeminiKey;

    private final List<Scheme> allSchemes = new CopyOnWriteArrayList<>();
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final List<String> MODEL_CHAIN = Arrays.asList(
            "gemini-3.8-flash",
            "gemini-2.5-flash-lite",
            "gemini-flash-latest"
    );

    public SchemeService() {
        initSchemes();
    }

    private void initSchemes() {
        // =========================================================================
        // 1. DIRECT INCOME SUPPORT & PENSIONS
        // =========================================================================
        addScheme("SCH-001",
                "PM-KISAN (Pradhan Mantri Kisan Samman Nidhi)",
                "पीएम-किसान सम्मान निधि योजना",
                "Income Support & Pensions",
                "Direct income support of ₹6,000 per year transferred in three equal installments of ₹2,000 directly into Aadhaar-seeded bank accounts.",
                "किसानों के बैंक खातों में ₹6,000 प्रति वर्ष तीन समान किस्तों (₹2,000 प्रत्येक) में सीधे अंतरण।",
                "All landholding farmer families with cultivable land in their names.",
                "सभी भूमिधारक किसान परिवार जिनके नाम कृषि योग्य भूमि दर्ज है।",
                "₹6,000 annually via Direct Benefit Transfer (DBT)",
                "वार्षिक ₹6,000 सीधे बैंक खाते में (डीबीटी)",
                "https://pmkisan.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-002",
                "PM Kisan Maandhan Yojana (PM-KMY)",
                "प्रधानमंत्री किसान मानधन योजना (मासिक पेंशन)",
                "Income Support & Pensions",
                "Voluntary old-age social security pension scheme providing guaranteed monthly pension of ₹3,000 after reaching 60 years of age.",
                "60 वर्ष की आयु पूरी होने पर छोटे व सीमांत किसानों को ₹3,000 प्रति माह की निश्चित सामाजिक सुरक्षा पेंशन।",
                "Small and marginal farmers aged 18 to 40 years owning up to 2 hectares (5 acres) of land.",
                "18 से 40 वर्ष के छोटे और सीमांत किसान जिनके पास 2 हेक्टेयर तक कृषि भूमि है।",
                "Guaranteed monthly pension of ₹3,000 with 50% matching contribution paid by Central Govt.",
                "₹3,000 प्रति माह आजीवन पेंशन, 50% अंशदान केंद्र सरकार जमा करती है।",
                "https://maandhan.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-003",
                "Namo Shetkari Mahasanman Nidhi Yojana (Maharashtra)",
                "नमो शेतकरी महासन्मान निधी योजना (महाराष्ट्र)",
                "State Top-Up & Subsidies",
                "Maharashtra Government top-up scheme providing additional ₹6,000/year to PM-KISAN beneficiaries, totaling ₹12,000 annual support.",
                "महाराष्ट्र सरकार द्वारा पीएम-किसान के लाभार्थियों को प्रति वर्ष अतिरिक्त ₹6,000 का अनुदान (कुल ₹12,000 प्रति वर्ष)।",
                "All PM-KISAN registered landholding farmers in Maharashtra.",
                "महाराष्ट्र के सभी पीएम-किसान पंजीकृत भूमिधारक किसान।",
                "₹6,000 additional annual direct cash benefit transferred in 3 equal installments.",
                "₹6,000 अतिरिक्त वार्षिक लाभ (कुल ₹12,000 प्रति वर्ष सीधे बैंक खाते में)।",
                "https://mahadbt.maharashtra.gov.in",
                Arrays.asList("Maharashtra"), false);

        addScheme("SCH-004",
                "Mukhyamantri Kisan Kalyan Yojana (Madhya Pradesh)",
                "मुख्यमंत्री किसान कल्याण योजना (मध्य प्रदेश)",
                "State Top-Up & Subsidies",
                "Madhya Pradesh state government supplement providing ₹6,000/year in three installments directly to farmers eligible under PM-KISAN.",
                "मध्य प्रदेश सरकार द्वारा पीएम-किसान के पात्र किसानों को अतिरिक्त ₹6,000 प्रति वर्ष का प्रत्यक्ष वित्तीय सहयोग।",
                "Resident landholder farmers of Madhya Pradesh enrolled under PM-KISAN.",
                "मध्य प्रदेश के पीएम-किसान लाभार्थी भूमिधारक किसान।",
                "₹6,000 state grant paid directly into Aadhaar-linked accounts.",
                "₹6,000 अतिरिक्त राज्य अनुदान सीधे बैंक खाते में।",
                "https://saara.mp.gov.in",
                Arrays.asList("Madhya Pradesh"), false);

        addScheme("SCH-005",
                "YSR Rythu Bharosa / PM-KISAN (Andhra Pradesh)",
                "वाईएसआर रायथू भरोसा / पीएम-किसान (आंध्र प्रदेश)",
                "State Top-Up & Subsidies",
                "Comprehensive input financial assistance of ₹13,500 per year per farmer family, including tenant farmers belonging to SC, ST, BC, and Minorities.",
                "आंध्र प्रदेश में किसान परिवारों व बटाईदारों को प्रति वर्ष ₹13,500 की इनपुट वित्तीय सहायता।",
                "Farmer families and registered tenant farmers in Andhra Pradesh.",
                "आंध्र प्रदेश के सभी किसान और पंजीकृत बटाईदार परिवार।",
                "₹13,500 per annum paid in three installments before Kharif, Rabi, and Sankranti.",
                "वार्षिक ₹13,500 तीन किस्तों में (खरीफ, रबी व संक्रांति पर)।",
                "https://ysrrythubharosa.ap.gov.in",
                Arrays.asList("Andhra Pradesh"), false);

        addScheme("SCH-006",
                "Rythu Bandhu / Rythu Bharosa (Telangana)",
                "रायथू बंधु / रायथू भरोसा (तेलंगाना)",
                "State Top-Up & Subsidies",
                "Direct investment support scheme granting ₹10,000 to ₹15,000 per acre per year directly for purchasing seeds, fertilizers, and farm inputs.",
                "तेलंगाना में बीज, खाद व खेती इनपुट खरीदने हेतु ₹10,000 से ₹15,000 प्रति एकड़ प्रति वर्ष की निवेश सहायता।",
                "All agricultural patta landholders in Telangana.",
                "तेलंगाना के सभी पट्टाधारक कृषक।",
                "Direct cash assistance per acre per season into farmer bank accounts.",
                "प्रति एकड़ प्रति मौसम प्रत्यक्ष नकद सहायता।",
                "https://rythubandhu.telangana.gov.in",
                Arrays.asList("Telangana"), false);

        addScheme("SCH-007",
                "Krishak Bandhu Scheme (West Bengal)",
                "कृषक बंधु योजना (पश्चिम बंगाल)",
                "State Top-Up & Subsidies",
                "Financial assistance of up to ₹10,000/year (minimum ₹4,000) in two equal installments for crop cultivation plus ₹2 Lakh death insurance.",
                "खेती हेतु ₹10,000 तक वार्षिक वित्तीय सहायता (न्यूनतम ₹4,000) तथा ₹2 लाख का निःशुल्क जीवन बीमा।",
                "All agricultural landowners and recorded bhagchasi (sharecroppers) in West Bengal.",
                "पश्चिम बंगाल के सभी किसान व दर्ज बटाईदार (भागचासी)।",
                "Up to ₹10,000 per annum in two installments + ₹2,00,000 death grant to family.",
                "अधिकतम ₹10,000 वार्षिक सहायता + ₹2 लाख मृत्यु अनुदान।",
                "https://krishakbandhu.wb.gov.in",
                Arrays.asList("West Bengal"), false);

        addScheme("SCH-008",
                "KALIA Scheme (Odisha)",
                "कालिया योजना (ओडिशा - Krushak Assistance for Livelihood)",
                "State Top-Up & Subsidies",
                "Comprehensive livelihood assistance providing ₹10,000/year to small/marginal farmers and ₹12,500 livelihood cash to landless agri households.",
                "छोटे/सीमांत किसानों को ₹10,000/वर्ष और भूमिहीन खेतिहर परिवारों को ₹12,500 की आजीविका सहायता।",
                "Small, marginal farmers and landless agricultural households in Odisha.",
                "ओडिशा के छोटे, सीमांत किसान और भूमिहीन कृषि मजदूर।",
                "₹10,000/year financial assistance for cultivation + ₹12,500 livelihood support for landless.",
                "खेती हेतु ₹10,000 वार्षिक सहायता और भूमिहीनों के लिए ₹12,500 आजीविका अनुदान।",
                "https://kalia.odisha.gov.in",
                Arrays.asList("Odisha"), false);

        addScheme("SCH-009",
                "Rajiv Gandhi Kisan Nyay Yojana (Chhattisgarh)",
                "राजीव गांधी किसान न्याय योजना (छत्तीसगढ़)",
                "State Top-Up & Subsidies",
                "Direct input subsidy incentive of ₹9,000 to ₹10,000 per acre paid to paddy, maize, kodo-kutki, and sugarcane farmers.",
                "धान, मक्का, कोदो-कुटकी और गन्ना उत्पादक किसानों को ₹9,000 से ₹10,000 प्रति एकड़ की इनपुट सहायता।",
                "Farmers cultivating notified Kharif crops in Chhattisgarh.",
                "छत्तीसगढ़ में अधिसूचित खरीफ फसलें उगाने वाले पंजीकृत किसान।",
                "₹9,000 to ₹10,000 per acre paid across four quarterly tranches.",
                "₹9,000 से ₹10,000 प्रति एकड़ चार किस्तों में सीधे खाते में।",
                "https://kisan.cg.nic.in",
                Arrays.asList("Chhattisgarh"), false);

        // =========================================================================
        // 2. CREDIT, LOANS & FINANCIAL INFRASTRUCTURE
        // =========================================================================
        addScheme("SCH-010",
                "Kisan Credit Card (KCC) & Modified Interest Subvention",
                "किसान क्रेडिट कार्ड (KCC) व ब्याज छूट योजना",
                "Credit, Loans & KCC",
                "Institutional crop loans up to ₹3 Lakhs at 4% effective interest upon prompt annual repayment.",
                "समय पर अदायगी करने पर मात्र 4% रियायती ब्याज दर पर ₹3 लाख तक का कृषि फसल ऋण।",
                "All farmers, tenant farmers, dairy farmers, fishers, and SHGs.",
                "सभी भूमिधारक, बटाईदार किसान, पशुपालक और मत्स्य पालक।",
                "4% effective annual interest (7% base minus 3% prompt repayment incentive).",
                "प्रभावी 4% वार्षिक ब्याज दर।",
                "https://www.myscheme.gov.in/schemes/kcc",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-011",
                "Agriculture Infrastructure Fund (AIF)",
                "कृषि अवसंरचना कोष (AIF - ₹1 लाख करोड़)",
                "Credit, Loans & KCC",
                "Medium-long term debt financing for post-harvest management infrastructure with 3% interest subvention and CGTMSE credit guarantee.",
                "वेयरहाउस, कोल्ड स्टोरेज, ग्रेडिंग यूनिट व सॉर्टिंग प्लांट स्थापित करने हेतु ₹2 करोड़ तक के ऋण पर 3% ब्याज छूट।",
                "Primary Agricultural Credit Societies (PACS), FPOs, Agri-entrepreneurs, and Startups.",
                "प्राथमिक कृषि सहकारी समितियां (PACS), FPO, कृषि उद्यमी और स्टार्टअप।",
                "3% interest subvention per annum up to ₹2 Crore loan limit for up to 7 years.",
                "₹2 करोड़ तक के ऋण पर 7 वर्षों तक 3% वार्षिक ब्याज छूट और गारंटी शुल्क माफी।",
                "https://agriinfra.dac.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-012",
                "Agri-Clinics and Agri-Business Centres (AC&ABC)",
                "कृषि क्लिनिक व कृषि व्यवसाय केंद्र योजना",
                "Credit, Loans & KCC",
                "Subsidized loans and free entrepreneurship training for agriculture graduates to establish private advisory clinics and custom hiring centers.",
                "कृषि स्नातकों को निजी क्लिनिक व कस्टम हायरिंग सेंटर खोलने हेतु 36% से 44% तक की कंपोजिट सब्सिडी।",
                "Graduates, diploma holders in Agriculture and allied subjects, biological sciences.",
                "कृषि, बागवानी, डेयरी अथवा संबद्ध विषयों में स्नातक व डिप्लोमा धारक।",
                "Composite subsidy of 36% for general and 44% for women, SC/ST on project loans up to ₹20 Lakhs.",
                "₹20 लाख तक के प्रोजेक्ट पर 36% से 44% तक पूंजीगत अनुदान।",
                "https://acabcmis.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-013",
                "Agri-Sure Fund (2024 Launch)",
                "एग्री-श्योर फंड (2024 - ₹750 करोड़)",
                "Technology & Drones",
                "Blended capital fund managed by NABARD and NABVENTURES to invest in early-stage agritech startups providing AI and tech solutions to farmers.",
                "नाबार्ड द्वारा ₹750 करोड़ का फंड जो किसानों के लिए एआई, ड्रोन व आधुनिक तकनीक विकसित करने वाले स्टार्टअप्स को पूंजी देता है।",
                "Agritech startups, farmer-producer enterprises, and innovative agri-businesses.",
                "एग्री-टेक स्टार्टअप्स और किसान उत्पादक कंपनियां।",
                "Seed and growth equity capital, accelerating ground-level technology access for farmers.",
                "सस्ती तकनीक, ड्रोन व एआई टूल्स की उपलब्धता।",
                "https://nabard.org",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-014",
                "Formation and Promotion of 10,000 FPOs",
                "10,000 किसान उत्पादक संगठनों (FPO) का गठन",
                "Credit, Loans & KCC",
                "Central sector scheme to form sustainable farmer producer organizations with financial support and equity grant.",
                "किसानों को सामूहिक मोलभाव की शक्ति देने हेतु 10,000 FPO का गठन व वित्तीय संबल।",
                "Small, marginal and landless farmers coming together to form producer companies.",
                "छोटे और सीमांत किसान।",
                "Management cost of ₹18 Lakhs per FPO for 3 years + Matching Equity Grant up to ₹2,000 per farmer member.",
                "प्रत्येक FPO को ₹18 लाख का प्रबंधन खर्च + ₹2,000 प्रति सदस्य तक समतुल्य इक्विटी अनुदान।",
                "https://enam.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-015",
                "Balaram Scheme (Odisha)",
                "बलराम योजना (ओडिशा - भूमिहीन किसानों हेतु ऋण)",
                "Credit, Loans & KCC",
                "Collateral-free agricultural loans up to ₹50,000 disbursed to landless sharecroppers and tenant farmers through Joint Liability Groups (JLGs).",
                "भूमिहीन बटाईदार किसानों को संयुक्त देयता समूह (JLG) के माध्यम से ₹50,000 तक का बिना गारंटी फसल ऋण।",
                "Landless agricultural labourers, tenant cultivators, and sharecroppers in Odisha.",
                "ओडिशा के भूमिहीन किसान और बटाईदार।",
                "Collateral-free credit up to ₹50,000 per member through PACS and rural banks.",
                "बिना किसी जमीन गिरवी रखे ₹50,000 तक का आसान संस्थागत ऋण।",
                "https://agri.odisha.gov.in",
                Arrays.asList("Odisha"), false);

        addScheme("SCH-016",
                "UP Kisan Karz Rahat Yojana (Uttar Pradesh)",
                "यूपी किसान कर्ज राहत योजना (उत्तर प्रदेश)",
                "Credit, Loans & KCC",
                "Agricultural debt waiver scheme for small and marginal farmers relieving institutional bank loans up to ₹1 Lakh.",
                "उत्तर प्रदेश के छोटे और सीमांत किसानों के ₹1 लाख तक के संस्थागत फसली ऋणों की माफी।",
                "Small and marginal farmers holding up to 2 hectares in Uttar Pradesh.",
                "उत्तर प्रदेश के 2 हेक्टेयर तक भूमि वाले लघु व सीमांत किसान।",
                "Waiver of overdue crop loans up to ₹1,00,000 per eligible farmer family.",
                "₹1,00,000 तक का पुराना कृषि ऋण पूर्णतः माफ।",
                "https://upkisankarjrahat.upsdc.gov.in",
                Arrays.asList("Uttar Pradesh"), false);

        // =========================================================================
        // 3. CROP INSURANCE & DISASTER ASSISTANCE
        // =========================================================================
        addScheme("SCH-017",
                "PMFBY (Pradhan Mantri Fasal Bima Yojana)",
                "प्रधानमंत्री फसल बीमा योजना (PMFBY)",
                "Crop Insurance & Relief",
                "Comprehensive crop insurance covering non-preventable natural risks from sowing to post-harvest.",
                "बुआई से लेकर कटाई के बाद तक बेमौसम बारिश, सूखा व कीट प्रकोप से होने वाले नुकसान पर फसल बीमा।",
                "All farmers growing notified crops in notified areas.",
                "अधिसूचित क्षेत्रों में अधिसूचित फसलें उगाने वाले सभी किसान।",
                "Maximum premium: 2% for Kharif, 1.5% for Rabi, 5% for commercial/horticultural crops.",
                "खरीफ 2%, रबी 1.5%, बागवानी 5% अधिकतम प्रीमियम; शेष सरकार देती है।",
                "https://pmfby.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-018",
                "Restructured Weather Based Crop Insurance Scheme (RWBCIS)",
                "पुनर्गठित मौसम आधारित फसल बीमा योजना (RWBCIS)",
                "Crop Insurance & Relief",
                "Parametric insurance protecting farmers against adverse weather parameters such as deficient rainfall, frost, heat waves, and excess relative humidity.",
                "प्रतिकूल मौसम (पाला, अत्यधिक गर्मी, वर्षा की कमी, अधिक आर्द्रता) से होने वाले नुकसान पर त्वरित दावा भुगतान।",
                "Farmers growing notified commercial, fruit and vegetable crops.",
                "अधिसूचित फल, सब्जी और नकदी फसलें उगाने वाले कृषक।",
                "Automatic claim settlement triggered by automatic weather stations (AWS) without tedious physical survey.",
                "मौसम केंद्रों के आंकड़ों के आधार पर बिना सर्वे के सीधे बैंक खाते में मुआवजा।",
                "https://pmfby.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-019",
                "Bihar Rajya Fasal Sahayata Yojana (BRFSY)",
                "बिहार राज्य फसल सहायता योजना (BRFSY)",
                "Crop Insurance & Relief",
                "Free financial assistance for crop loss due to flood, drought, or unseasonal rain without any premium payment by farmers.",
                "बाढ़, सुखाड़ व बेमौसम बारिश से फसल नुकसान पर बिना कोई प्रीमियम भरे ₹10,000/हेक्टेयर तक का सीधा मुआवजा।",
                "Raiyats (landowners) and non-raiyats (tenant cultivators) in Bihar.",
                "बिहार के रैयत व गैर-रैयत (बटाईदार) किसान।",
                "₹7,500/ha for loss up to 20%, and ₹10,000/ha for loss exceeding 20% directly disbursed.",
                "20% तक नुकसान पर ₹7,500/हेक्टेयर और 20% से अधिक पर ₹10,000/हेक्टेयर सीधा मुआवजा।",
                "https://pacsonline.bih.nic.in",
                Arrays.asList("Bihar"), false);

        addScheme("SCH-020",
                "Bangla Shasya Bima (BSB - West Bengal)",
                "बांग्ला शस्य बीमा (पश्चिम बंगाल - 100% फ्री फसल बीमा)",
                "Crop Insurance & Relief",
                "Fully state-funded crop insurance where the Government of West Bengal pays 100% of the insurance premium for all farmers.",
                "पश्चिम बंगाल सरकार द्वारा शत-प्रतिशत प्रीमियम का भुगतान; किसानों के लिए पूरी तरह निशुल्क फसल बीमा।",
                "All farmers cultivating notified crops in West Bengal.",
                "पश्चिम बंगाल के सभी किसान (बटाईदारों सहित)।",
                "Zero farmer premium payment + satellite and drone based claim assessment.",
                "शून्य प्रीमियम; उपग्रह व रिमोट सेंसिंग से त्वरित क्षतिपूर्ति भुगतान।",
                "https://banglashasyabima.net",
                Arrays.asList("West Bengal"), false);

        addScheme("SCH-021",
                "Gopinath Munde Shetkari Apghat Vima Yojana (Maharashtra)",
                "गोपीनाथ मुंडे शेतकरी अपघात सुरक्षा सानुग्रह अनुदान (महाराष्ट्र)",
                "Crop Insurance & Relief",
                "Accidental insurance protection providing financial assistance of ₹2 Lakhs in case of accidental death or permanent disability of farmers.",
                "बिजली गिरने, सर्पदंश, कृषि यंत्र दुर्घटना या सड़क हादसे में किसान की मृत्यु/अपंगता पर ₹2 लाख की आर्थिक सहायता।",
                "All registered landholder farmers and their family members aged 10 to 75 in Maharashtra.",
                "महाराष्ट्र के 10 से 75 वर्ष आयु वर्ग के खातेदार किसान और उनके परिवार के सदस्य।",
                "₹2,00,000 grant on accidental death or permanent dual-limb loss; ₹1,00,000 on single-limb loss.",
                "दुर्घटना मृत्यु पर ₹2,00,000 तथा स्थायी अपंगता पर ₹1,00,000 की तत्काल सरकारी सहायता।",
                "https://krishi.maharashtra.gov.in",
                Arrays.asList("Maharashtra"), false);

        addScheme("SCH-022",
                "Rythu Bima Scheme (Telangana)",
                "रायथू बीमा योजना (तेलंगाना - ₹5 लाख जीवन बीमा)",
                "Crop Insurance & Relief",
                "Group life insurance scheme providing immediate ₹5 Lakh financial relief to the nominee within 10 days of the farmer's demise.",
                "किसान की किसी भी कारण से मृत्यु होने पर 10 दिनों के भीतर उसके परिवार/नॉमिनी को ₹5 लाख की एकमुश्त सहायता।",
                "Patta passbook holding farmers in Telangana aged 18 to 59 years.",
                "तेलंगाना के 18 से 59 वर्ष के पट्टाधारक किसान।",
                "₹5,00,000 direct bank transfer to nominee; 100% premium paid by Telangana Govt.",
                "नॉमिनी के बैंक खाते में ₹5,00,000 की सीधी सहायता, शत-प्रतिशत प्रीमियम राज्य सरकार देती है।",
                "https://rythubima.telangana.gov.in",
                Arrays.asList("Telangana"), false);

        // =========================================================================
        // 4. TECHNOLOGY, DRONES & FARM MECHANIZATION
        // =========================================================================
        addScheme("SCH-023",
                "Digital Agriculture Mission (AgriStack & Digital Farmer ID - 2024)",
                "डिजिटल कृषि मिशन (AgriStack व डिजिटल किसान आईडी - 2024)",
                "Technology & Drones",
                "₹2,817 Cr Union Cabinet approved digital infrastructure creating AgriStack (Digital Farmer ID), unified crop registries, and automated drought/weather crop assessments.",
                "₹2,817 करोड़ की डिजिटल कृषि योजना: किसान डिजिटल आईडी (AgriStack), डिजिटल गिरदावरी और त्वरित फसल नुकसान आकलन।",
                "All farmers across Indian states integrating with State Digital Land Records.",
                "राज्य डिजिटल भू-अभिलेखों से जुड़े सभी भारतीय किसान।",
                "Universal Digital Farmer ID for single-window access to PM-KISAN, crop insurance, and subsidized inputs.",
                "एकल किसान आईडी से सभी सरकारी योजनाओं व क्रेडिट का सीधा लाभ।",
                "https://agricoop.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-024",
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

        addScheme("SCH-025",
                "Sub-Mission on Agricultural Mechanization (SMAM)",
                "कृषि यंत्रीकरण उप-मिशन (SMAM)",
                "Technology & Drones",
                "Financial assistance on purchase of agricultural equipment including tractors, power tillers, rotavators, and laser land levelers.",
                "ट्रैक्टर, रोटावेटर, कल्टीवेटर, सीड ड्रिल व लेजर लैंड लेवलर खरीदने पर 40% से 50% तक सरकारी सब्सिडी।",
                "Small, marginal, SC, ST, and women farmers across India.",
                "छोटे, सीमांत, महिला व अनुसूचित जाति/जनजाति के किसान।",
                "40% to 50% subsidy on purchase price, and 80% grant on setting up Custom Hiring Centres (CHCs).",
                "यंत्र खरीद पर 40% से 50% सब्सिडी और कस्टम हायरिंग सेंटर पर 80% तक अनुदान।",
                "https://agrimachinery.nic.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-026",
                "Namo Drone Didi Scheme (Women SHG Drones)",
                "नमो ड्रोन दीदी योजना (महिला स्वयं सहायता समूह ड्रोन)",
                "Technology & Drones",
                "Providing 15,000 agricultural drones to Women Self Help Groups (SHGs) for renting drone spraying services to local farmers.",
                "महिला स्वयं सहायता समूहों को 15,000 कृषि ड्रोन का निशुल्क/सब्सिडी पर वितरण, जिससे ग्रामीण महिलाओं को आजीविका व किसानों को सस्ती स्प्रे सेवा मिले।",
                "Women Self Help Groups (SHGs) under Deendayal Antyodaya Yojana (DAY-NRLM).",
                "दीनदयाल अंत्योदय योजना (NRLM) के तहत पंजीकृत महिला स्वयं सहायता समूह।",
                "80% financial assistance (up to ₹8,00,000) for drone purchase + 15 days certified pilot training.",
                "ड्रोन खरीद पर 80% तक (अधिकतम ₹8 लाख) सहायता + 15 दिनों का मानकीकृत पायलट प्रशिक्षण।",
                "https://nrlm.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-027",
                "Subsidized Crop Residue Management (CRM - Punjab & Haryana)",
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

        addScheme("SCH-028",
                "CMSGUY Tractor Distribution Scheme (Assam)",
                "मुख्यमंत्री समग्र ग्राम्य उन्नयन योजना (असम - 70% ट्रैक्टर सब्सिडी)",
                "Technology & Drones",
                "Distribution of 1 tractor unit along with accessories to eligible farmers' group in each revenue village at 70% government subsidy.",
                "असम के प्रत्येक राजस्व गांव में किसान समूहों को 70% सरकारी सब्सिडी पर ट्रैक्टर व कृषि उपकरण सेट।",
                "Farmer groups, Joint Liability Groups (JLGs) comprising 8-10 active farmers in Assam.",
                "असम के 8-10 किसानों का संयुक्त कृषक समूह।",
                "70% subsidy on tractor unit (up to ₹5.5 Lakhs), 20% bank loan, and 10% farmer group contribution.",
                "ट्रैक्टर यूनिट पर 70% सरकारी अनुदान (अधिकतम ₹5.5 लाख), 20% बैंक ऋण और 10% समूह अंशदान।",
                "https://mmguy.assam.gov.in",
                Arrays.asList("Assam"), false);

        addScheme("SCH-029",
                "UP Krishi Yantrikaran E-Lottery Scheme (Uttar Pradesh)",
                "यूपी कृषि यंत्रीकरण ई-लॉटरी योजना (उत्तर प्रदेश)",
                "Technology & Drones",
                "Online transparent lottery allocation of farm machinery (rotavator, thresher, seed drill, reaper) at 50% subsidy.",
                "रोटावेटर, थ्रेशर, रीपर व कल्टीवेटर जैसे 40+ कृषि यंत्रों पर 50% अनुदान हेतु पारदर्शी ई-लॉटरी प्रणाली।",
                "Registered farmers on Uttar Pradesh Agriculture portal (upagriculture.com).",
                "पारदर्शिता पोर्टल upagriculture.com पर पंजीकृत उत्तर प्रदेश के किसान।",
                "Up to 50% subsidy credited directly to farmer account via DBT upon physical verification.",
                "सत्यापन के बाद 50% तक की छूट सीधे किसान के बैंक खाते में डीबीटी द्वारा।",
                "https://upagriculture.com",
                Arrays.asList("Uttar Pradesh"), false);

        // =========================================================================
        // 5. IRRIGATION & SOLAR ENERGY
        // =========================================================================
        addScheme("SCH-030",
                "PM-KUSUM (Component B) - Standalone Solar Agri Pumps",
                "पीएम-कुसुम (घटक-बी) - स्टैंडअलोन सोलर कृषि पंप",
                "Irrigation & Solar Energy",
                "60% combined capital subsidy for replacing diesel pumps with standalone off-grid solar irrigation pumps (3 HP to 10 HP).",
                "डीजल पंपों को हटाकर 3 से 10 एचपी तक के स्टैंडअलोन सोलर सिंचाई पंप लगाने पर 60% सरकारी सब्सिडी।",
                "Individual farmers, water user associations, and community farmer groups without grid power.",
                "बिजली विहीन क्षेत्रों के किसान, जल उपभोक्ता संघ और सामूहिक किसान।",
                "60% capital subsidy (30% Central + 30% State), 30% bank loan, only 10% farmer share.",
                "60% सरकारी अनुदान (30% केंद्र + 30% राज्य), 30% बैंक ऋण, किसान को केवल 10% देना होगा।",
                "https://pmkusum.mnre.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-031",
                "PM-KUSUM (Component C) - Solarisation of Grid-Tied Agri Pumps",
                "पीएम-कुसुम (घटक-सी) - ग्रिड-कनेक्टेड पंपों का सौर ऊर्जीकरण",
                "Irrigation & Solar Energy",
                "Solarising existing grid-connected agricultural pumps, allowing farmers to generate clean power, irrigate during daytime, and sell surplus electricity to DISCOMs.",
                "विद्युत ग्रिड से जुड़े कृषि पंपों को सौर ऊर्जा से चलाना; दिन में मुफ्त सिंचाई और बची बिजली डिस्कॉम को बेचकर अतिरिक्त आय।",
                "Individual farmers and feeder-level solar developers.",
                "ग्रिड से जुड़े कृषि पंप उपभोक्ता किसान।",
                "Free daytime solar electricity for irrigation plus earning tariff for surplus power fed back into grid.",
                "दिन में मुफ्त बिजली व अतिरिक्त बिजली बेचकर वार्षिक कमाई।",
                "https://pmkusum.mnre.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-032",
                "PMKSY (Per Drop More Crop - Micro Irrigation)",
                "प्रधानमंत्री कृषि सिंचाई योजना (प्रति बूंद अधिक फसल - ड्रिप व स्प्रिंकलर)",
                "Irrigation & Solar Energy",
                "Promoting precision water-saving technologies like drip and sprinkler irrigation systems to increase water use efficiency.",
                "ड्रिप (टपक) व स्प्रिंकलर (फव्वारा) सिंचाई प्रणालियों की स्थापना पर 55% तक का भारी सरकारी अनुदान।",
                "All categories of farmers, with priority given to small, marginal and women farmers.",
                "सभी किसान, विशेष रूप से छोटे और सीमांत किसान।",
                "55% subsidy for small & marginal farmers, 45% for other farmers on micro-irrigation installations.",
                "छोटे/सीमांत किसानों को 55% तथा अन्य किसानों को 45% सब्सिडी।",
                "https://pmksy.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-033",
                "PM Surya Ghar: Muft Bijli Yojana (Rooftop Solar)",
                "पीएम सूर्य घर: मुफ्त बिजली योजना (छत पर सोलर पैनल)",
                "Irrigation & Solar Energy",
                "₹75,021 Cr scheme providing up to ₹78,000 direct subsidy for installing rooftop solar up to 3 kW, offering up to 300 units of free power monthly.",
                "घर या फार्महाउस की छत पर सोलर पैनल लगाने हेतु ₹78,000 तक की सीधी सब्सिडी और प्रति माह 300 यूनिट तक मुफ्त बिजली।",
                "Residential households, farmhouses, and rural property owners with grid connection.",
                "वैध बिजली कनेक्शन वाले सभी ग्रामीण व शहरी नागरिक।",
                "₹30,000 for 1 kW, ₹60,000 for 2 kW, and ₹78,000 for 3 kW and above installations.",
                "1 kW पर ₹30,000, 2 kW पर ₹60,000, 3 kW या अधिक पर ₹78,000 सीधी सब्सिडी।",
                "https://pmsuryaghar.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-034",
                "Mukhyamantri Saur Krishi Vahini Yojana 2.0 (Maharashtra)",
                "मुख्यमंत्री सौर कृषी वाहिनी योजना 2.0 (महाराष्ट्र)",
                "Irrigation & Solar Energy",
                "Dedicated solarization of agricultural electricity feeders to guarantee uninterrupted daytime power (9 AM to 5 PM) for crop irrigation.",
                "कृषि फीडरों का सौर ऊर्जीकरण कर किसानों को दिन के समय (सुबह 9 से शाम 5 बजे तक) निर्बाध बिजली आपूर्ति।",
                "Agricultural power consumers across rural Maharashtra.",
                "महाराष्ट्र के ग्रामीण कृषि बिजली उपभोक्ता।",
                "Guaranteed reliable daytime 8-hour power supply without night shifts in cold fields.",
                "रात में खेत जाने की परेशानी खत्म, दिन में 8 घंटे लगातार सिंचाई बिजली।",
                "https://mahadiscom.in",
                Arrays.asList("Maharashtra"), false);

        addScheme("SCH-035",
                "Magel Tyala Shet Tale (Farm Ponds on Demand - Maharashtra)",
                "मागेल त्याला शेततळे योजना (महाराष्ट्र - खेत तालाब अनुदान)",
                "Irrigation & Solar Energy",
                "Demand-driven farm pond excavation and plastic tarpaulin lining scheme providing up to ₹75,000 subsidy per pond.",
                "मांगने वाले प्रत्येक किसान के खेत में वर्षा जल संचयन हेतु शेततले (तालाब) व प्लास्टिक अस्तर पर ₹75,000 तक अनुदान।",
                "Farmers owning at least 0.60 hectares (1.5 acres) of land in Maharashtra.",
                "महाराष्ट्र के कम से कम 1.5 एकड़ कृषि भूमि धारक किसान।",
                "Up to ₹75,000 direct subsidy deposited into bank account upon geo-tagged milestone completion.",
                "जियो-टैगिंग के बाद सीधे बैंक खाते में ₹75,000 तक का सरकारी अनुदान।",
                "https://mahadbt.maharashtra.gov.in",
                Arrays.asList("Maharashtra"), false);

        addScheme("SCH-036",
                "Krishi Bhagya Yojana (Karnataka)",
                "कृषि भाग्य योजना (कर्नाटक - वर्षा जल संचयन)",
                "Irrigation & Solar Energy",
                "Integrated package of polythene-lined farm pond (Krishi Honda), diesel/solar lift pumps, and micro-irrigation in dryland zones.",
                "सूखे क्षेत्रों में वर्षा जल संचयन हेतु पॉलीथिन लाइनिंग वाले कृषि तालाब (कृषि होंडा) व पंप पर 80% से 90% सब्सिडी।",
                "Rainfed agricultural farmers across Karnataka districts.",
                "कर्नाटक के वर्षा आधारित क्षेत्रों के किसान।",
                "80% to 90% subsidy on farm pond excavation, polythene tarpaulin lining, and lift pumps.",
                "खेत तालाब निर्माण और पंप सेट पर 80% से 90% तक सरकारी सब्सिडी।",
                "https://raitamitra.karnataka.gov.in",
                Arrays.asList("Karnataka"), false);

        addScheme("SCH-037",
                "Mukhyamantri Kisan Mitra Urja Yojana (Rajasthan)",
                "मुख्यमंत्री किसान मित्र ऊर्जा योजना (राजस्थान - ₹12,000 बिजली छूट)",
                "Irrigation & Solar Energy",
                "State subsidy of ₹1,000 per month (up to ₹12,000 per year) credited directly against agricultural electricity connection bills.",
                "कृषि विद्युत उपभोक्ताओं को बिजली बिल में प्रति माह ₹1,000 (अधिकतम ₹12,000 प्रति वर्ष) की सीधी छूट।",
                "Metered agricultural power consumers in Rajasthan.",
                "राजस्थान के मीटरयुक्त कृषि बिजली उपभोक्ता किसान।",
                "Monthly ₹1,000 deduction on power bills; zero electricity bill for small consumptions.",
                "बिजली बिल में ₹1,000 प्रति माह की सीधी छूट, कई किसानों का बिल शून्य हो जाता है।",
                "https://energy.rajasthan.gov.in",
                Arrays.asList("Rajasthan"), false);

        addScheme("SCH-038",
                "UP Kisan Uday Yojana (Uttar Pradesh)",
                "यूपी किसान उदय योजना (उत्तर प्रदेश - सोलर/विद्युत पंप वितरण)",
                "Irrigation & Solar Energy",
                "Free distribution and installation of energy-efficient 5 HP and 7.5 HP submersible solar and electric pump sets.",
                "किसानों को मुफ्त ऊर्जा-दक्ष 5 और 7.5 एचपी के सोलर व इलेक्ट्रिक सबमर्सिबल पंप सेट उपलब्ध कराना।",
                "Small and marginal farmers holding agricultural land in Uttar Pradesh.",
                "उत्तर प्रदेश के छोटे व सीमांत किसान।",
                "100% free distribution and 5-year free maintenance of high-efficiency irrigation pump sets.",
                "सिंचाई पंप सेटों का पूर्णतः निशुल्क वितरण व 5 साल की फ्री मेंटेनेंस।",
                "https://upagriculture.com",
                Arrays.asList("Uttar Pradesh"), false);

        addScheme("SCH-039",
                "Chief Minister's Solar Powered Pump Sets Scheme (Tamil Nadu)",
                "मुख्यमंत्री सौर कृषि पंप योजना (तमिलनाडु - 70% सब्सिडी)",
                "Irrigation & Solar Energy",
                "Provision of 5 HP, 7.5 HP, and 10 HP solar pumpsets with 70% government subsidy to reduce reliance on grid and diesel.",
                "ग्रिड व डीजल पर निर्भरता घटाने के लिए 5, 7.5 व 10 एचपी के सौर पंप सेटों पर 70% सरकारी सब्सिडी।",
                "Individual farmers across all districts of Tamil Nadu.",
                "तमिलनाडु के सभी जिलों के व्यक्तिगत कृषक।",
                "70% subsidy (30% MNRE + 40% State Govt), remaining 30% by farmer.",
                "70% सरकारी अनुदान (30% केंद्र + 40% राज्य सरकार), केवल 30% किसान अंश।",
                "https://tnhorticulture.tn.gov.in",
                Arrays.asList("Tamil Nadu"), false);

        addScheme("SCH-040",
                "Paani Bachao, Paise Kamao (Punjab)",
                "पानी बचाओ, पैसे कमाओ योजना (पंजाब - नकद प्रोत्साहन)",
                "Irrigation & Solar Energy",
                "Incentive program crediting direct cash into farmer bank accounts for consuming less power than their sanctioned limit on agricultural tubewells.",
                "ट्यूबवेल पर तय सीमा से कम बिजली और भूजल खर्च करने वाले किसानों के खाते में ₹4 प्रति यूनिट का सीधा नकद इनाम।",
                "Agricultural tubewell consumers in enrolled pilot feeders in Punjab.",
                "पंजाब के चिन्हित फीडरों से जुड़े कृषि ट्यूबवेल उपभोक्ता।",
                "₹4 per unit of electricity saved credited directly into farmer's bank account every bill cycle.",
                "बचाई गई बिजली पर ₹4 प्रति यूनिट का सीधा नकद लाभ सीधे बैंक खाते में।",
                "https://pspcl.in",
                Arrays.asList("Punjab"), false);

        // =========================================================================
        // 6. ORGANIC, NATURAL & BIO FARMING
        // =========================================================================
        addScheme("SCH-041",
                "PM-PRANAM (Promotion of Alternate Nutrients for Agri)",
                "पीएम-प्रणाम योजना (वैकल्पिक उर्वरक व पोषण प्रोत्साहन)",
                "Natural & Bio Farming",
                "Incentivizing States to reduce chemical fertilizer consumption and adopt bio-fertilizers and organic soil conditioners.",
                "रासायनिक खादों की खपत घटाने और नैनो यूरिया व जैविक खादों के उपयोग को बढ़ावा देने हेतु विशेष प्रोत्साहन।",
                "Farmers adopting bio-fertilizers, organic farming, and balanced nutrient management.",
                "संतुलित खाद और जैविक पोषण अपनाने वाले किसान।",
                "50% of fertilizer subsidy saved by the State is granted back for farmer incentives and bio-fertilizer infrastructure.",
                "उर्वरक सब्सिडी बचत का 50% हिस्सा किसानों में जैव-उर्वरक प्रोत्साहन हेतु वितरित।",
                "https://fert.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-042",
                "Paramparagat Krishi Vikas Yojana (PKVY)",
                "परंपरागत कृषि विकास योजना (PKVY - जैविक खेती)",
                "Natural & Bio Farming",
                "Cluster-based organic farming promotion offering financial assistance of ₹50,000 per hectare over 3 years for PGS-India certification, bio-inputs, and marketing.",
                "3 वर्षों में ₹50,000/हेक्टेयर की सहायता: जैविक खाद, वर्मीकम्पोस्ट, पीजीएस-इंडिया प्रमाणीकरण व विपणन।",
                "Farmer clusters and groups taking up certified organic cultivation (minimum 20 hectares per cluster).",
                "जैविक खेती करने वाले 20-50 किसानों के क्लस्टर समूह।",
                "₹50,000/ha assistance (₹31,000 directly transferred for organic seeds, bio-inputs and vermicompost).",
                "₹50,000 प्रति हेक्टेयर अनुदान (₹31,000 सीधे इनपुट्स व खाद हेतु किसान खाते में)।",
                "https://pgsindia-ncof.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-043",
                "Mission Organic Value Chain Development for NE Region (MOVCDNER)",
                "पूर्वोत्तर जैविक मूल्य श्रृंखला विकास मिशन (MOVCDNER)",
                "Natural & Bio Farming",
                "Dedicated central sector scheme to develop end-to-end organic value chains, FPOs, and processing infrastructure in 8 North Eastern states.",
                "पूर्वोत्तर राज्यों (सिक्किम, असम, मेघालय आदि) में प्रमाणित जैविक मूल्य श्रृंखला, निर्यात और प्रसंस्करण का समग्र विकास।",
                "Farmers, FPCs, and organic entrepreneurs across Arunachal, Assam, Manipur, Meghalaya, Mizoram, Nagaland, Sikkim, and Tripura.",
                "पूर्वोत्तर के 8 राज्यों के जैविक किसान और FPO।",
                "₹25,000/ha for organic inputs, ₹10,000/ha for certification + 75% subsidy on processing/packaging units.",
                "जैविक इनपुट हेतु ₹25,000/हेक्टेयर + प्रसंस्करण इकाइयों पर 75% तक अनुदान।",
                "https://movcdner.gov.in",
                Arrays.asList("Assam", "Arunachal Pradesh", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Sikkim", "Tripura"), false);

        addScheme("SCH-044",
                "GOBARdhan Scheme (Galvanizing Organic Bio-Agro Resources)",
                "गोबर-धन योजना (बायोगैस व सीबीजी संयंत्र अनुदान)",
                "Natural & Bio Farming",
                "Converting cattle dung and farm biomass into compressed bio-gas (CBG) and organic slurry fertilizer.",
                "गोबर और कृषि अपशिष्ट से बायोगैस/सीएनजी और जैविक खाद बनाना; ₹50 लाख तक का सरकारी अनुदान।",
                "Gram Panchayats, dairy cooperatives, FPOs, and private green energy entrepreneurs.",
                "ग्राम पंचायतें, दुग्ध सहकारी समितियां, FPO और किसान।",
                "Up to ₹50 Lakhs assistance per district/block for setting up community bio-gas and bio-slurry facilities.",
                "सामुदायिक बायोगैस संयंत्रों पर ₹50 लाख तक की वित्तीय सहायता।",
                "https://gobardhan.co.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-045",
                "Prakritik Kheti Khushhal Kisan Yojana (PK3Y - Himachal Pradesh)",
                "प्राकृतिक खेती खुशहाल किसान योजना (हिमाचल प्रदेश)",
                "Natural & Bio Farming",
                "Subhash Palekar Natural Farming (SPNF) adoption scheme providing financial incentive kits and ₹25,000 subsidy on indigenous cows.",
                "सुभाष पालेकर प्राकृतिक खेती अपनाने पर ड्रम, किट तथा देशी गाय खरीद पर ₹25,000 तक की सीधी सब्सिडी।",
                "Farmers residing and cultivating agricultural land in Himachal Pradesh.",
                "हिमाचल प्रदेश के किसान।",
                "₹25,000 subsidy for purchasing indigenous Desi cow + ₹8,000 for plastic drums and bio-input preparation kits.",
                "देशी गाय खरीद पर ₹25,000 अनुदान + जीवामृत ड्रम व किट हेतु ₹8,000 सहायता।",
                "https://spnfhp.nic.in",
                Arrays.asList("Himachal Pradesh"), false);

        addScheme("SCH-046",
                "Dr. Punjabrao Deshmukh Jaivik Kheti Mission (Maharashtra)",
                "डॉ. पंजाबराव देशमुख जैविक शेती मिशन (महाराष्ट्र)",
                "Natural & Bio Farming",
                "Organic farming mission facilitating certified organic clusters, bio-input resource centers, and farmer-to-consumer sales stalls.",
                "महाराष्ट्र में जैविक खेती क्लस्टरों की स्थापना, जैविक इनपुट केंद्र और किसानों के लिए सीधे बिक्री केंद्र।",
                "Farmer groups and SHGs practicing organic farming in Maharashtra.",
                "महाराष्ट्र के जैविक खेती समूह व शेतकरी उत्पादक कंपनियां।",
                "Subsidy on organic certification, bio-pesticide preparation units, and district marketing stalls.",
                "जैविक प्रमाणीकरण, जैव-इनपुट संसाधन केंद्र व हाट-बाजार स्टॉल पर 50% से 75% अनुदान।",
                "https://krishi.maharashtra.gov.in",
                Arrays.asList("Maharashtra"), false);

        addScheme("SCH-047",
                "Saat Pagla Khedut Kalyan Na (Step for Cow Care - Gujarat)",
                "सात पगलां खेडूत कल्याण ना (देशी गाय संवर्धन - गुजरात)",
                "Natural & Bio Farming",
                "Monthly assistance of ₹900 per month (₹10,800/year) to farmers for maintaining an indigenous cow to produce Jeevamrut for natural farming.",
                "प्राकृतिक खेती हेतु एक देशी गाय रखने वाले किसान को गाय के चारे-पानी के लिए ₹900 प्रति माह (₹10,800 प्रति वर्ष) का सीधा अनुदान।",
                "Farmers practicing natural farming and possessing an indigenous cow in Gujarat.",
                "गुजरात के प्राकृतिक खेती करने वाले देशी गाय पालक किसान।",
                "₹900 per month credited directly into farmer's bank account.",
                "प्रति माह ₹900 सीधे बैंक खाते में (वार्षिक ₹10,800)।",
                "https://ikhedut.gujarat.gov.in",
                Arrays.asList("Gujarat"), false);

        // =========================================================================
        // 7. HORTICULTURE, PLANT PROTECTION & SEEDS
        // =========================================================================
        addScheme("SCH-048",
                "Clean Plant Programme (CPP - 2024 Approval)",
                "क्लीन प्लांट प्रोग्राम (2024 - ₹1,765 करोड़)",
                "Horticulture & Orchards",
                "₹1,765 Cr mission establishing 9 state-of-the-art Clean Plant Centers across India to supply certified disease-free & virus-free planting material for fruit orchards.",
                "फलों (सेब, संतरा, अंगूर, आम, अमरूद) के बगीचों के लिए शत-प्रतिशत रोगमुक्त व वायरस-मुक्त उन्नत पौधे उपलब्ध कराने की योजना।",
                "Fruit orchard growers, horticulture farmers, and certified nursery owners.",
                "बागवानी किसान और फल उत्पादक।",
                "Up to 50% subsidy on certified clean planting material and tissue culture rootstocks.",
                "प्रमाणित रोगमुक्त पौधों और ग्राफ्टिंग पर 50% तक सरकारी अनुदान।",
                "https://nhb.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-049",
                "Mission for Integrated Development of Horticulture (MIDH)",
                "एकीकृत बागवानी विकास मिशन (MIDH)",
                "Horticulture & Orchards",
                "Comprehensive capital subsidy for polyhouses, shade net structures, plastic mulching, pack houses, and refrigerated cold vans.",
                "पॉलीहाउस, शेडनेट हाउस, प्लास्टिक मल्चिंग, पैक हाउस और कोल्ड स्टोरेज निर्माण पर 40% से 50% तक अनुदान।",
                "Horticultural farmers, grower societies, FPOs, and entrepreneurs.",
                "सब्जी, फल और फूल उगाने वाले किसान और FPO।",
                "50% subsidy on greenhouse polyhouse construction (up to ₹4.5 Lakhs per 1,000 sq.m).",
                "पॉलीहाउस/शेडनेट निर्माण पर 50% सब्सिडी (प्रति 1,000 वर्ग मीटर पर ₹4.5 लाख तक)।",
                "https://midh.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-050",
                "National Mission on Edible Oils - Oil Palm (NMEO-OP)",
                "राष्ट्रीय खाद्य तेल मिशन - ऑयल पाम (NMEO-OP)",
                "Horticulture & Orchards",
                "₹11,040 Cr mission to expand domestic oil palm cultivation with substantial planting material subsidy and viability price protection.",
                "पाम ऑयल खेती को बढ़ावा देने हेतु ₹29,000 प्रति हेक्टेयर पौध अनुदान तथा बाजार मूल्य गिरावट पर व्यवहार्यता मूल्य सुरक्षा।",
                "Farmers in notified oil palm districts across Andhra Pradesh, Telangana, Karnataka, Assam, and North East.",
                "अधिसूचित जिलों के किसान।",
                "₹29,000 per hectare for planting material + ₹50,000/ha for 4 years intercropping maintenance.",
                "रोपाई सामग्री हेतु ₹29,000/हेक्टेयर + 4 वर्षों तक इंटरक्रॉपिंग रखरखाव हेतु ₹50,000/हेक्टेयर।",
                "https://nmeo.dac.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-051",
                "Sub-Mission on Seeds and Planting Material (SMSP)",
                "बीज व रोपण सामग्री उप-मिशन (SMSP)",
                "Horticulture & Orchards",
                "Strengthening seed production through Seed Village Programme, certified foundation seed distribution, and upgrading state seed testing labs.",
                "बीज ग्राम योजना के तहत उन्नत प्रमाणित बीजों पर 50% सब्सिडी और ग्राम स्तर पर बीज उत्पादन प्रशिक्षण।",
                "All categories of crop-growing farmers and certified seed growers.",
                "सभी किसान व बीज उत्पादक कृषक।",
                "50% subsidy on purchase price of certified and foundation seeds of cereals, pulses, and oilseeds.",
                "खाद्यान्न, दलहन व तिलहन के प्रमाणित बीजों पर 50% तक की छूट।",
                "https://seednet.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-052",
                "Bhavantar Bharpayee Yojana (BBY - Haryana)",
                "भावांतर भरपाई योजना (हरियाणा - मूल्य जोखिम सुरक्षा)",
                "State Top-Up & Subsidies",
                "Price deficiency compensation scheme paying farmers the difference if wholesale market prices drop below the Government fixed base prices for vegetables & fruits.",
                "टमाटर, प्याज, आलू, गोभी जैसी 21 बागवानी फसलों का बाजार भाव संरक्षित मूल्य से नीचे जाने पर अंतर राशि का सीधा भुगतान।",
                "Farmers registered on Meri Fasal Mera Byora portal in Haryana.",
                "मेरी फसल मेरा ब्यौरा पोर्टल पर पंजीकृत हरियाणा के किसान।",
                "Direct cash compensation of price deficit credited directly to farmer bank account.",
                "घाटे की अंतर राशि (भावांतर) सीधे बैंक खाते में अंतरित।",
                "https://fasal.haryana.gov.in",
                Arrays.asList("Haryana"), false);

        addScheme("SCH-053",
                "Raitha Siri Scheme (Karnataka - Millet Bonus)",
                "रैथा सिरी योजना (कर्नाटक - मोटा अनाज प्रोत्साहन)",
                "Horticulture & Orchards",
                "Direct financial incentive of ₹10,000 per hectare to promote the cultivation of traditional nutrient-rich millets (Ragi, Jowar, Bajra, Foxtail, Little millet).",
                "रागी, ज्वार, बाजरा और अन्य श्री-अन्न (मोटा अनाज) उगाने वाले किसानों को ₹10,000 प्रति हेक्टेयर का सीधा प्रोत्साहन।",
                "Millet growers cultivating certified rainfed land in Karnataka.",
                "कर्नाटक के मोटा अनाज (श्री-अन्न) उत्पादक किसान।",
                "₹10,000 per hectare paid directly into DBT-linked bank account upon crop verification.",
                "सत्यापन के बाद सीधे बैंक खाते में ₹10,000 प्रति हेक्टेयर की प्रोत्साहन राशि।",
                "https://raitamitra.karnataka.gov.in",
                Arrays.asList("Karnataka"), false);

        // =========================================================================
        // 8. LIVESTOCK, DAIRY & FISHERIES
        // =========================================================================
        addScheme("SCH-054",
                "National Livestock Mission (NLM)",
                "राष्ट्रीय पशुधन मिशन (NLM - बकरी/मुर्गी/सुअर पालन सब्सिडी)",
                "Livestock, Dairy & Fisheries",
                "50% capital subsidy up to ₹50 Lakhs for setting up sheep, goat, pig, and poultry parent breeding farms and fodder production units.",
                "बकरी पालन, भेड़ पालन व पोल्ट्री फार्मिंग के ब्रीडिंग फार्म स्थापित करने हेतु 50% (अधिकतम ₹50 लाख) तक पूंजीगत सब्सिडी।",
                "Individual farmers, SHGs, FPOs, Joint Liability Groups, and Section 8 companies.",
                "किसान, पशुपालक, स्वयं सहायता समूह और FPO।",
                "50% capital subsidy (up to ₹50 Lakhs for sheep/goat 500-doe units, up to ₹25 Lakhs for poultry).",
                "50% पूंजीगत अनुदान (बकरी पालन पर ₹50 लाख तक, मुर्गी पालन पर ₹25 लाख तक)।",
                "https://nlm.udyamimitra.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-055",
                "Rashtriya Gokul Mission (RGM)",
                "राष्ट्रीय गोकुल मिशन (दुग्ध पशु नस्ल सुधार)",
                "Livestock, Dairy & Fisheries",
                "Enhancing productivity of indigenous bovine breeds through breed multiplication farms, artificial insemination, and sex-sorted semen doses.",
                "देशी गायों/भैंसों की नस्ल सुधार, कृत्रिम गर्भाधान और 90% बछिया पैदा करने वाले सेक्स-सॉर्टेड सीमन पर भारी अनुदान।",
                "Dairy farmers, livestock breeders, gaushalas, and cooperatives.",
                "डेयरी किसान, पशुपालक व गौशालाएं।",
                "50% subsidy (up to ₹2 Crore) for establishing breed multiplication farms + highly subsidized sexed semen.",
                "ब्रीड मल्टीप्लिकेशन फार्म पर 50% (₹2 करोड़ तक) अनुदान और रियायती सेक्स-सॉर्टेड सीमन।",
                "https://dahd.nic.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-056",
                "Pradhan Mantri Matsya Sampada Yojana (PMMSY)",
                "प्रधानमंत्री मत्स्य संपदा योजना (PMMSY - मछली पालन)",
                "Livestock, Dairy & Fisheries",
                "₹20,050 Cr flagship mission for sustainable development of fisheries sector, supporting new fish ponds, biofloc tanks, RAS, and refrigerated vehicles.",
                "मछली तालाब निर्माण, बायोफ्लॉक तकनीक, आरएएस और रेफ्रिजरेटेड गाड़ी खरीदने पर 40% से 60% तक सरकारी अनुदान।",
                "Fishers, fish farmers, fish workers, SHGs, and fisheries cooperatives.",
                "मत्स्य पालक, मछुआरे और किसान।",
                "40% subsidy for General category and 60% subsidy for Women, SC, and ST beneficiaries.",
                "सामान्य वर्ग को 40% तथा महिलाओं/अनुसूचित जाति/जनजाति को 60% सरकारी सब्सिडी।",
                "https://pmmsy.dof.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-057",
                "Animal Husbandry Infrastructure Development Fund (AHIDF)",
                "पशुपालन अवसंरचना विकास कोष (AHIDF - ₹15,000 करोड़)",
                "Livestock, Dairy & Fisheries",
                "₹15,000 Cr fund providing 3% interest subvention for setting up modern dairy processing plants, meat processing, and animal feed manufacturing units.",
                "डेयरी प्रसंस्करण, पनीर/मक्खन प्लांट और पशु आहार फैक्ट्री लगाने हेतु 3% ब्याज छूट व क्रेडिट गारंटी।",
                "FPOs, Dairy Cooperatives, MSMEs, and private individual agri-entrepreneurs.",
                "FPO, डेयरी समितियां और कृषि उद्यमी।",
                "3% interest subvention on term loans for up to 8 years + up to 25% credit guarantee by NABARD.",
                "8 वर्षों तक ऋण पर 3% वार्षिक ब्याज छूट + नाबार्ड की क्रेडिट गारंटी।",
                "https://ahidf.udyamimitra.in",
                Arrays.asList("All India", "National"), true);

        // =========================================================================
        // 9. SOIL HEALTH, MARKETING & INFRASTRUCTURE
        // =========================================================================
        addScheme("SCH-058",
                "Soil Health Card Scheme (SHC)",
                "मृदा स्वास्थ्य कार्ड योजना (मृदा परीक्षण)",
                "Soil Health & Marketing",
                "Free institutional testing of soil samples for 12 essential macro and micronutrients with crop-specific fertilizer dosage guidance.",
                "खेत की मिट्टी के 12 पोषक तत्वों की मुफ्त जांच व फसल अनुसार यूरिया-डीएपी की सटीक मात्रा बताने वाला स्वास्थ्य कार्ड।",
                "All agricultural landowners across all states and Union Territories.",
                "सभी भारतीय किसान।",
                "Free laboratory testing and customized report advising exact chemical and organic fertilizer application.",
                "मुफ्त मिट्टी जांच और संतुलित खाद उपयोग से खेती लागत में 25% तक की बचत।",
                "https://soilhealth.dac.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-059",
                "e-NAM (National Agriculture Market)",
                "ई-नाम (राष्ट्रीय कृषि बाजार - ऑनलाइन मंडी)",
                "Soil Health & Marketing",
                "Pan-India electronic trading portal integrating 1,361+ wholesale APMC mandis to facilitate transparent price discovery and direct online bidding.",
                "देश भर की 1,361+ थोक मंडियों को जोड़ने वाला ऑनलाइन पोर्टल; किसान किसी भी राज्य के खरीदार को ऊंची बोली पर उपज बेच सकते हैं।",
                "Farmers, traders, and Farmer Producer Organizations (FPOs) registered with APMCs.",
                "सभी किसान और व्यापारी।",
                "Direct electronic payment into farmer's bank account, zero middleman exploitation, and transparent assaying.",
                "ऑनलाइन पारदर्शी बोली, सीधे बैंक खाते में भुगतान और बिचौलियों से मुक्ति।",
                "https://enam.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-060",
                "PMFME (PM Formalisation of Micro Food Processing Enterprises)",
                "पीएम सूक्ष्म खाद्य प्रसंस्करण उद्यम योजना (PMFME)",
                "Post-Harvest & Processing",
                "Credit-linked capital subsidy of 35% (up to ₹10 Lakhs) for establishing and upgrading micro food processing units (flour mills, oil expellers, pickle/juice packaging).",
                "आटा चक्की, तेल घानी, मसाला उद्योग, आचार व जूस प्रोसेसिंग यूनिट लगाने हेतु 35% (₹10 लाख तक) पूंजीगत अनुदान।",
                "Individual micro-entrepreneurs, farmer producer groups, SHGs, and cooperatives.",
                "ग्रामीण युवा, महिला SHG और व्यक्तिगत किसान उद्यमी।",
                "35% credit-linked capital subsidy up to ₹10,00,000 per project + seed capital of ₹40,000 per SHG member.",
                "प्रोजेक्ट लागत का 35% (अधिकतम ₹10 लाख) अनुदान + SHG सदस्यों को ₹40,000 प्रारंभिक पूंजी।",
                "https://pmfme.mofpi.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-061",
                "Integrated Scheme for Agricultural Marketing (ISAM / Gramin Bhandaran)",
                "ग्रामीण भंडारण योजना (कृषि गोदाम निर्माण सब्सिडी)",
                "Post-Harvest & Processing",
                "Capital investment subsidy of 25% to 33.33% for the construction and renovation of rural warehouses, godowns, and cold storage to prevent distress sales.",
                "गांव में अपनी जमीन पर अनाज गोदाम व वेयरहाउस बनाने पर 25% से 33.33% तक का सरकारी पूंजी अनुदान।",
                "Individual farmers, groups of farmers, FPOs, PACS, and cooperatives.",
                "किसान, किसान समूह, सहकारी समितियां और ग्रामीण उद्यमी।",
                "25% capital subsidy for General (up to ₹75 Lakhs) and 33.33% for SC/ST/Women/NE farmers (up to ₹1 Crore).",
                "सामान्य वर्ग को 25% और महिला/SC/ST/पहाड़ी क्षेत्रों को 33.33% पूंजीगत सब्सिडी।",
                "https://dmi.gov.in",
                Arrays.asList("All India", "National"), true);

        addScheme("SCH-062",
                "Mukhyamantri Khet Sanrakshan Yojana (Solar Fencing - HP & UK)",
                "मुख्यमंत्री खेत संरक्षण योजना (सोलर फेंसिंग - हिमाचल व उत्तराखंड)",
                "State Top-Up & Subsidies",
                "80% to 85% subsidy on solar-powered composite fencing and wire mesh to safeguard agricultural crops from wild animals and stray cattle.",
                "जंगली जानवरों और आवारा पशुओं से फसलों की सुरक्षा हेतु सोलर करंट वाली बाड़ लगाने पर 80% से 85% सरकारी अनुदान।",
                "Individual farmers and farmer groups in hilly tracts of Himachal Pradesh and Uttarakhand.",
                "हिमाचल प्रदेश और उत्तराखंड के किसान समूह व व्यक्तिगत काश्तकार।",
                "80% subsidy for individual farmers and 85% subsidy for group farming setups.",
                "व्यक्तिगत किसान को 80% तथा 3 या अधिक किसानों के समूह को 85% सरकारी अनुदान।",
                "https://himachal.nic.in",
                Arrays.asList("Himachal Pradesh", "Uttarakhand"), false);

        addScheme("SCH-063",
                "Mera Pani Meri Virasat (Haryana - Crop Diversification)",
                "मेरा पानी मेरी विरासत योजना (हरियाणा - ₹7,000/एकड़)",
                "State Top-Up & Subsidies",
                "Direct financial incentive of ₹7,000 per acre to farmers who switch from water-intensive paddy to alternative crops like maize, cotton, pulses, and oilseeds.",
                "धान (चावल) की जगह मक्का, कपास, दलहन या तिलहन की बुआई करने वाले किसानों को ₹7,000 प्रति एकड़ की सीधी प्रोत्साहन राशि।",
                "Farmers registered on Meri Fasal Mera Byora portal in Haryana replacing paddy area.",
                "हरियाणा में धान का रकबा घटाकर वैकल्पिक फसलें लगाने वाले किसान।",
                "₹7,000 per acre deposited directly into farmer's bank account upon satellite verification.",
                "सत्यापन के बाद ₹7,000 प्रति एकड़ सीधा बैंक खाते में।",
                "https://fasal.haryana.gov.in",
                Arrays.asList("Haryana"), false);

        addScheme("SCH-064",
                "Tarbandi Yojana (Rajasthan - Field Fencing Subsidy)",
                "तारबंदी योजना (राजस्थान - खेत बाड़ अनुदान)",
                "State Top-Up & Subsidies",
                "50% financial subsidy (up to ₹48,000) for erecting boundary wire fencing to protect standing crops from stray cattle and nilgai.",
                "नीलगाय और आवारा पशुओं से फसल सुरक्षा हेतु खेत की चारों तरफ कांटेदार तारबंदी करने पर 50% (अधिकतम ₹48,000) अनुदान।",
                "Farmers owning at least 1.5 hectares of cultivable land in Rajasthan.",
                "राजस्थान के न्यूनतम 1.5 हेक्टेयर कृषि भूमि धारक किसान।",
                "50% subsidy (up to ₹48,000 for individual small/marginal farmers) for 400 running meters.",
                "400 मीटर तारबंदी पर 50% या अधिकतम ₹48,000 तक की सीधी आर्थिक सहायता।",
                "https://rajkisan.rajasthan.gov.in",
                Arrays.asList("Rajasthan"), false);

        addScheme("SCH-065",
                "Birsa Harit Gram Yojana (Jharkhand)",
                "बिरसा हरित ग्राम योजना (झारखंड - बागवानी व मजदूरी)",
                "State Top-Up & Subsidies",
                "Assisting rural families to plant 100 high-yielding fruit trees (mango, guava) on unutilized upland with guaranteed 3-year MGNREGA maintenance wages.",
                "बंजर व परती जमीन पर 100 फलदार पौधे (आम, अमरूद) लगाने हेतु मुफ्त पौधे, खाद और 3 साल तक मनरेगा मजदूरी भुगतान।",
                "Rural households and small landholders having 0.5 to 1 acre of unused land in Jharkhand.",
                "झारखंड के ग्रामीण किसान परिवार।",
                "100 free fruit saplings + 100% fertilizer assistance + guaranteed monthly maintenance wages for 3 years.",
                "100 फलदार पौधे मुफ्त + 3 साल तक पौधों की देखभाल हेतु मनरेगा के तहत निश्चित मजदूरी।",
                "https://rural.jharkhand.gov.in",
                Arrays.asList("Jharkhand"), false);
    }

    private void addScheme(String id, String name, String nameHi, String category,
                           String desc, String descHi, String elig, String eligHi,
                           String ben, String benHi, String url, List<String> states, boolean isNational) {
        Scheme s = new Scheme(id, name, nameHi, category, desc, descHi, elig, eligHi, ben, benHi, url, states, isNational);
        s.setLiveFetched(false);
        allSchemes.add(s);
    }

    public List<Scheme> getSchemesForState(String state) {
        return getSchemes(state, null, null);
    }

    public List<Scheme> getSchemes(String state, String category, String searchQuery) {
        return allSchemes.stream().filter(s -> {
            // State filter
            if (state != null && !state.trim().isEmpty() && !state.equalsIgnoreCase("All India") && !state.equalsIgnoreCase("national")) {
                String targetState = state.trim().toLowerCase();
                boolean matchesState = s.isNational() ||
                        s.getApplicableStates().stream().anyMatch(st -> st.equalsIgnoreCase(targetState));
                if (!matchesState) return false;
            }

            // Category filter
            if (category != null && !category.trim().isEmpty() && !category.equalsIgnoreCase("All Schemes")) {
                if (s.getCategory() == null || !s.getCategory().toLowerCase().contains(category.trim().toLowerCase())) {
                    return false;
                }
            }

            // Search filter
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                String query = searchQuery.trim().toLowerCase();
                boolean nameMatch = (s.getName() != null && s.getName().toLowerCase().contains(query)) ||
                        (s.getNameHi() != null && s.getNameHi().toLowerCase().contains(query));
                boolean descMatch = (s.getDescription() != null && s.getDescription().toLowerCase().contains(query)) ||
                        (s.getDescriptionHi() != null && s.getDescriptionHi().toLowerCase().contains(query));
                boolean catMatch = s.getCategory() != null && s.getCategory().toLowerCase().contains(query);
                boolean eligMatch = (s.getEligibility() != null && s.getEligibility().toLowerCase().contains(query)) ||
                        (s.getEligibilityHi() != null && s.getEligibilityHi().toLowerCase().contains(query));
                if (!nameMatch && !descMatch && !catMatch && !eligMatch) {
                    return false;
                }
            }

            return true;
        }).collect(Collectors.toList());
    }

    public List<Scheme> getAllSchemes() {
        return allSchemes;
    }

    public List<String> getCategories() {
        return allSchemes.stream()
                .map(Scheme::getCategory)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Live fetch / discovery of schemes via AI & Open Data Grounding.
     * Searches live official government records, adds new schemes to memory, and returns results.
     */
    public List<Scheme> fetchLiveSchemes(String query, String state, String userApiKey) {
        String activeKey = (userApiKey != null && !userApiKey.trim().isEmpty())
                ? userApiKey.trim()
                : configuredGeminiKey;

        // If an API key is available, query Gemini with Search Grounding
        if (activeKey != null && !activeKey.trim().isEmpty()) {
            try {
                List<Scheme> liveDiscovered = callGeminiForSchemes(query, state, activeKey);
                if (liveDiscovered != null && !liveDiscovered.isEmpty()) {
                    for (Scheme discovered : liveDiscovered) {
                        // Check if already in allSchemes
                        boolean exists = allSchemes.stream().anyMatch(existing ->
                                existing.getName().equalsIgnoreCase(discovered.getName()) ||
                                (existing.getId() != null && existing.getId().equalsIgnoreCase(discovered.getId())));
                        if (!exists) {
                            discovered.setLiveFetched(true);
                            if (discovered.getId() == null || discovered.getId().isEmpty()) {
                                discovered.setId("LIVE-" + (allSchemes.size() + 1));
                            }
                            allSchemes.add(0, discovered); // Add at beginning
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println("Live scheme fetch via Gemini failed: " + e.getMessage() + ". Falling back to core directory.");
            }
        }

        // Return filtered list based on query and state
        return getSchemes(state, null, query);
    }

    private List<Scheme> callGeminiForSchemes(String query, String state, String apiKey) {
        String effectiveState = (state != null && !state.trim().isEmpty() && !state.equalsIgnoreCase("All India"))
                ? state.trim()
                : "All India";
        String prompt = "You are an official Indian Government Welfare Schemes specialist. " +
                "The user is searching for agricultural schemes matching query: '" + (query != null ? query : "") +
                "' in state: '" + effectiveState + "'.\n" +
                "Return a valid JSON array of official Central or State Government agricultural welfare schemes. " +
                "Include recent 2023-2026 schemes, budget announcements, and state-specific top-up programs. " +
                "Each JSON object must have these exact fields:\n" +
                "- id: a unique string ID like 'LIVE-SCH-01'\n" +
                "- name: Official English scheme name\n" +
                "- nameHi: Official Hindi scheme name in Devanagari\n" +
                "- category: One of ['Income Support & Pensions', 'Credit, Loans & KCC', 'Crop Insurance & Relief', 'Technology & Drones', 'Irrigation & Solar Energy', 'Natural & Bio Farming', 'Horticulture & Orchards', 'Livestock, Dairy & Fisheries', 'Soil Health & Marketing', 'Post-Harvest & Processing', 'State Top-Up & Subsidies']\n" +
                "- description: 2-3 sentence English summary with launch context\n" +
                "- descriptionHi: 2-3 sentence authentic Hindi summary\n" +
                "- eligibility: Clear bulleted eligibility criteria in English\n" +
                "- eligibilityHi: Clear eligibility criteria in Hindi\n" +
                "- benefits: Exact monetary or subsidy percentage benefits in English\n" +
                "- benefitsHi: Exact monetary/subsidy benefits in Hindi\n" +
                "- officialUrl: Direct official government URL (e.g. ending in .gov.in or .nic.in)\n" +
                "- applicableStates: Array of state names (e.g. ['" + effectiveState + "'] or ['All India'])\n" +
                "- isNational: boolean (true if central, false if state-specific)\n" +
                "Output ONLY the JSON array inside a ```json ... ``` codeblock without any introductory or conversational text.";

        for (String model : MODEL_CHAIN) {
            try {
                String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/" + model + ":generateContent?key=" + apiKey;

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                Map<String, Object> textPart = Collections.singletonMap("text", prompt);
                Map<String, Object> content = Collections.singletonMap("parts", Collections.singletonList(textPart));
                Map<String, Object> requestBody = Collections.singletonMap("contents", Collections.singletonList(content));

                HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
                ResponseEntity<String> response = restTemplate.postForEntity(endpoint, entity, String.class);

                if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                    JsonNode root = objectMapper.readTree(response.getBody());
                    JsonNode textNode = root.path("candidates").path(0).path("content").path("parts").path(0).path("text");
                    if (!textNode.isMissingNode()) {
                        String rawText = textNode.asText().trim();
                        return parseSchemesFromJson(rawText);
                    }
                }
            } catch (Exception ex) {
                System.err.println("Model " + model + " failed for live scheme search: " + ex.getMessage());
            }
        }
        return Collections.emptyList();
    }

    private List<Scheme> parseSchemesFromJson(String rawText) {
        List<Scheme> list = new ArrayList<>();
        try {
            String json = rawText;
            if (json.contains("```json")) {
                json = json.substring(json.indexOf("```json") + 7);
                if (json.contains("```")) {
                    json = json.substring(0, json.indexOf("```"));
                }
            } else if (json.contains("```")) {
                json = json.substring(json.indexOf("```") + 3);
                if (json.contains("```")) {
                    json = json.substring(0, json.indexOf("```"));
                }
            }
            json = json.trim();

            JsonNode arrayNode = objectMapper.readTree(json);
            if (arrayNode.isArray()) {
                for (JsonNode item : arrayNode) {
                    Scheme s = new Scheme();
                    s.setId(item.path("id").asText("LIVE-" + UUID.randomUUID().toString().substring(0, 6)));
                    s.setName(item.path("name").asText());
                    s.setNameHi(item.path("nameHi").asText());
                    s.setCategory(item.path("category").asText("General Welfare"));
                    s.setDescription(item.path("description").asText());
                    s.setDescriptionHi(item.path("descriptionHi").asText());
                    s.setEligibility(item.path("eligibility").asText());
                    s.setEligibilityHi(item.path("eligibilityHi").asText());
                    s.setBenefits(item.path("benefits").asText());
                    s.setBenefitsHi(item.path("benefitsHi").asText());
                    s.setOfficialUrl(item.path("officialUrl").asText("https://www.myscheme.gov.in"));
                    s.setNational(item.path("isNational").asBoolean(true));
                    s.setLiveFetched(true);

                    List<String> states = new ArrayList<>();
                    JsonNode statesNode = item.path("applicableStates");
                    if (statesNode.isArray()) {
                        for (JsonNode st : statesNode) {
                            states.add(st.asText());
                        }
                    } else {
                        states.add("All India");
                    }
                    s.setApplicableStates(states);

                    if (s.getName() != null && !s.getName().isEmpty()) {
                        list.add(s);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to parse scheme JSON: " + e.getMessage());
        }
        return list;
    }
}
