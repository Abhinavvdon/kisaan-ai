package com.kisaan.service;

import com.kisaan.model.Comment;
import com.kisaan.model.Post;
import com.kisaan.repository.CommentRepository;
import com.kisaan.repository.PostRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class SaathiService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public SaathiService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @PostConstruct
    public void seedInitialCommunityPosts() {
        if (postRepository.count() == 0) {
            seedFreshPosts();
        } else {
            // Ensure all existing posts and comments have Hindi translations
            List<Post> existingPosts = postRepository.findAll();
            for (Post p : existingPosts) {
                boolean modified = false;
                if (p.getTitleHi() == null || p.getTitleHi().trim().isEmpty()) {
                    p.setTitleHi(translateAgriText(p.getTitle(), "hi"));
                    modified = true;
                }
                if (p.getDescriptionHi() == null || p.getDescriptionHi().trim().isEmpty()) {
                    p.setDescriptionHi(translateAgriText(p.getDescription(), "hi"));
                    modified = true;
                }
                if (p.getComments() != null) {
                    for (Comment c : p.getComments()) {
                        if (c.getTextHi() == null || c.getTextHi().trim().isEmpty()) {
                            c.setTextHi(translateAgriText(c.getText(), "hi"));
                            modified = true;
                        }
                    }
                }
                if (modified) {
                    postRepository.save(p);
                }
            }
        }
    }

    private void seedFreshPosts() {
        // Post 1: Nashik Onion
        Post p1 = new Post(
                "Nashik Onion Black Mold & Storage Rot — Prevention steps?",
                "Due to sudden unseasonal rains last fortnight, we are noticing early bulb softening and black Aspergillus mold in our chawl storage. Has anyone had success with sulfur dusting or ambient ventilation fans? Looking for low-cost preservation advice.",
                "नासिक प्याज काला फफूंद व भंडारण सड़न — रोकथाम के उपाय?",
                "पिछले पखवाड़े बेमौसम बारिश के कारण चाली (भंडार) में प्याज नरम पड़ रही है और काला एस्परगिलस फफूंद लग रहा है। क्या किसी किसान भाई ने सल्फर डस्टिंग या वेंटिलेशन पंखों से सफलता पाई है? कम लागत वाले सुझाव साझा करें।",
                null, "Nashik", "Maharashtra", "Kailash Jadhav", "Onion"
        );
        p1.setCreatedAt(LocalDateTime.now().minusHours(3));
        p1.addComment(new Comment(
                "Kailash ji, ensure your storage has 40% open slatted wooden flooring. We dusted Carbendazim @ 2g/kg on outer skins and improved airflow, saving 85% of our harvest.",
                "कैलाश जी, चाली में 40% खुली लकड़ी की जालीदार फर्श रखें। हमने बाहरी छिलकों पर कार्बेन्डाजिम (2 ग्राम/किग्रा) का भुरकाव किया और हवा का संचार बढ़ाया, जिससे 85% फसल बच गई।",
                "Sanjay Kulkarni", p1));
        p1.addComment(new Comment(
                "Avoid stacking more than 4 feet high during humid periods. Also inspect bulbs daily to isolate infected lots immediately.",
                "नमी के मौसम में प्याज को 4 फीट से अधिक ऊंचाई पर न रखें। संक्रमित प्याज को तुरंत अलग करने के लिए रोजाना छंटाई करें।",
                "Dr. Patil (Krishi Vigyan Kendra)", p1));
        postRepository.save(p1);

        // Post 2: Guntur Chilli
        Post p2 = new Post(
                "Chilli Black Thrips control with border trap cropping",
                "Sharing my experience from this season: planting 3 rows of African tall marigold on field borders and spraying cold-pressed neem oil (10,000 ppm) + Spinetoram kept thrips damage below economic injury level in our 3-acre parcel.",
                "मिर्च में गेंदा बॉर्डर ट्रैप क्रॉपिंग से ब्लैक थ्रिप्स का सफल नियंत्रण",
                "इस मौसम का अपना अनुभव साझा कर रहा हूँ: खेत की सीमाओं पर अफ्रीकन गेंदे की 3 कतारें लगाने और कोल्ड-प्रेस्ड नीम तेल (10,000 ppm) + स्पिनटोरम का छिड़काव करने से हमारे 3 एकड़ खेत में थ्रिप्स का नुकसान आर्थिक सीमा से नीचे रहा।",
                null, "Guntur", "Andhra Pradesh", "Suresh Reddy", "Chilli"
        );
        p2.setCreatedAt(LocalDateTime.now().minusHours(8));
        p2.addComment(new Comment(
                "Brilliant result Suresh garu! Did you also use blue sticky sheets? We found 25 sheets per acre captured maximum adults during twilight.",
                "बहुत बढ़िया परिणाम सुरेश गरु! क्या आपने नीले चिपचिपे कार्ड भी लगाए थे? हमने पाया कि शाम के समय प्रति एकड़ 25 कार्ड सबसे अधिक वयस्क कीट पकड़ते हैं।",
                "Venkatesh Rao", p2));
        postRepository.save(p2);

        // Post 3: Ludhiana Wheat
        Post p3 = new Post(
                "HD-3086 Wheat yellow rust scouting report",
                "Noticed scattered yellow rust stripes on flag leaves in our block after heavy morning dew. Propiconazole 25 EC spray completed yesterday at 200 ml/acre in 200 liters of water. Leaf drying stopped within 36 hours.",
                "HD-3086 गेहूं पीला रतुआ (येलो रस्ट) निगरानी रिपोर्ट",
                "सुबह भारी ओस के बाद हमारे खेत में ध्वज पत्तियों पर पीले रतुआ की धारियां देखी गईं। कल 200 लीटर पानी में 200 मिली/एकड़ प्रोपिकोनाजोल 25 EC का छिड़काव पूरा किया। 36 घंटे के भीतर पत्तियों का सूखना रुक गया।",
                null, "Ludhiana", "Punjab", "Harpreet Singh", "Wheat"
        );
        p3.setCreatedAt(LocalDateTime.now().minusHours(18));
        p3.addComment(new Comment(
                "Good timing Harpreet veerji. Never delay rust sprays once temperatures hit 15-22°C with morning fog.",
                "बिल्कुल सही समय पर छिड़काव किया हरप्रीत वीर जी। सुबह के कोहरे और 15-22 डिग्री तापमान में रतुआ के छिड़काव में कभी देरी न करें।",
                "Gurpreet Brar", p3));
        postRepository.save(p3);

        // Post 4: Pune PMKSY Drip
        Post p4 = new Post(
                "PM Krishi Sinchai Yojana 55% drip subsidy credited!",
                "Happy to update fellow farmers that our drip irrigation installation subsidy under PMKSY was cleared through the MahaDBT portal within 18 days of geo-tagging inspection. Happy to guide anyone on document submission.",
                "पीएम कृषि सिंचाई योजना 55% ड्रिप सब्सिडी खाते में जमा!",
                "किसान भाइयों को बताते हुए खुशी हो रही है कि पीएमकेएसवाई के तहत ड्रिप सिंचाई सब्सिडी जियो-टैगिंग सत्यापन के 18 दिनों के भीतर महाडीबीटी पोर्टल के माध्यम से मिल गई। कागजात जमा करने में किसी भी भाई की मदद करने में खुशी होगी।",
                null, "Pune", "Maharashtra", "Anand Shinde", "Irrigation"
        );
        p4.setCreatedAt(LocalDateTime.now().minusDays(1));
        p4.addComment(new Comment(
                "Congratulations! What 7/12 land papers did the Taluka agriculture officer verify during field inspection?",
                "बधाई हो! खेत निरीक्षण के समय तालुका कृषि अधिकारी ने 7/12 खसरा-खतौनी के कौन-से दस्तावेज सत्यापित किए?",
                "Balasaheb Deshmukh", p4));
        postRepository.save(p4);

        // Post 5: Varanasi Organic Tomato
        Post p5 = new Post(
                "Trichoderma viride root drenching gives 100% survival in tomato",
                "Instead of chemical drenching, we enriched well-rotted cow dung manure with 2kg Trichoderma viride per quintal and applied during transplanting. Zero collar rot or damping-off observed despite warm soil temperatures.",
                "टमाटर में ट्राइकोडर्मा विरिडी जड़ शोधन से 100% पौधे सुरक्षित",
                "रासायनिक दवाओं की जगह हमने अच्छी सड़ी गोबर की खाद में 2 किग्रा ट्राइकोडर्मा विरिडी प्रति क्विंटल मिलाकर रोपाई के समय डाला। गर्म तापमान के बावजूद कॉलर रॉट या आर्द्र-गलन का नामोनिशान नहीं दिखा।",
                null, "Varanasi", "Uttar Pradesh", "Rajesh Maurya", "Tomato"
        );
        p5.setCreatedAt(LocalDateTime.now().minusDays(2));
        p5.addComment(new Comment(
                "Organic Trichoderma is a game changer for solanaceous vegetable seedlings. Keep soil moisture moderate for best fungal multiplication.",
                "सब्जियों की पौध के लिए जैविक ट्राइकोडर्मा रामबाण है। फफूंद की अच्छी वृद्धि के लिए मिट्टी में मध्यम नमी बनाए रखें।",
                "Virendra Yadav", p5));
        postRepository.save(p5);
    }

    public List<Post> getPosts(String district) {
        if (district != null && !district.trim().isEmpty() && !district.equalsIgnoreCase("All Districts") && !district.equalsIgnoreCase("all")) {
            return postRepository.findByDistrictIgnoreCaseOrderByCreatedAtDesc(district.trim());
        }
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Transactional
    public Post createPost(Post post) {
        if (post.getAuthorName() == null || post.getAuthorName().trim().isEmpty()) {
            post.setAuthorName("Kisaan Member");
        }
        if (post.getDistrict() == null || post.getDistrict().trim().isEmpty()) {
            post.setDistrict("Nashik");
        }
        if (post.getState() == null || post.getState().trim().isEmpty()) {
            post.setState("Maharashtra");
        }
        if (post.getTitleHi() == null || post.getTitleHi().trim().isEmpty()) {
            post.setTitleHi(translateAgriText(post.getTitle(), "hi"));
        }
        if (post.getDescriptionHi() == null || post.getDescriptionHi().trim().isEmpty()) {
            post.setDescriptionHi(translateAgriText(post.getDescription(), "hi"));
        }
        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Transactional
    public Comment addComment(Long postId, Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));
        if (comment.getAuthorName() == null || comment.getAuthorName().trim().isEmpty()) {
            comment.setAuthorName("Kisaan Member");
        }
        if (comment.getTextHi() == null || comment.getTextHi().trim().isEmpty()) {
            comment.setTextHi(translateAgriText(comment.getText(), "hi"));
        }
        comment.setPost(post);
        comment.setCreatedAt(LocalDateTime.now());
        Comment saved = commentRepository.save(comment);
        post.getComments().add(saved);
        return saved;
    }

    public static String translateAgriText(String text, String targetLang) {
        if (text == null || text.trim().isEmpty()) return text;
        if (!"hi".equalsIgnoreCase(targetLang)) return text;
        if (containsDevanagari(text)) return text;

        String result = text;
        Map<String, String> dict = new LinkedHashMap<>();
        dict.put("yellowing of tomato leaves", "टमाटर की पत्तियों का पीलापन");
        dict.put("yellowing of leaves", "पत्तियों का पीला पड़ना");
        dict.put("yellowing", "पीलापन");
        dict.put("tomato leaves", "टमाटर की पत्तियां");
        dict.put("tomato leaf", "टमाटर की पत्ती");
        dict.put("black mold", "काला फफूंद");
        dict.put("storage rot", "भंडारण सड़न");
        dict.put("black thrips", "काले थ्रिप्स कीट");
        dict.put("trap cropping", "ट्रैप क्रॉपिंग / फंदा फसल");
        dict.put("yellow rust", "पीला रतुआ (येलो रस्ट)");
        dict.put("scouting report", "खेत निरीक्षण रिपोर्ट");
        dict.put("drip subsidy", "ड्रिप सिंचाई सब्सिडी");
        dict.put("credited", "खाते में जमा");
        dict.put("root drenching", "जड़ शोधन (ड्रेंचिंग)");
        dict.put("survival in tomato", "टमाटर पौधों की उत्तरजीविता");
        dict.put("early blight", "अगेती झुलसा");
        dict.put("late blight", "पछेती झुलसा");
        dict.put("septoria leaf spot", "सेप्टोरिया पत्ती धब्बा");
        dict.put("powdery mildew", "सफेद चूर्णी फफूंद (पाउडरी मिल्ड्यू)");
        dict.put("downy mildew", "डाउनी मिल्ड्यू");
        dict.put("damping off", "आर्द्र-गलन रोग");
        dict.put("collar rot", "तना सड़न");
        dict.put("fruit rot", "फल सड़न");
        dict.put("pest control", "कीट नियंत्रण");
        dict.put("prevention steps", "रोकथाम के उपाय");
        dict.put("prevention", "रोकथाम");
        dict.put("symptoms", "रोग के लक्षण");
        dict.put("spray", "छिड़काव");
        dict.put("fertilizer", "खाद व उर्वरक");
        dict.put("organic", "जैविक");
        dict.put("irrigation", "सिंचाई");
        dict.put("leaves", "पत्तियां");
        dict.put("leaf", "पत्ती");
        dict.put("fruit", "फल");
        dict.put("seed", "बीज");
        dict.put("roots", "जड़ें");
        dict.put("soil", "मिट्टी");
        dict.put("crop", "फसल");
        dict.put("tomato", "टमाटर");
        dict.put("onion", "प्याज");
        dict.put("chilli", "मिर्च");
        dict.put("wheat", "गेहूं");
        dict.put("rice", "धान");
        dict.put("cotton", "कपास");
        dict.put("potato", "आलू");
        dict.put("corn", "मक्का");
        dict.put("sugarcane", "गन्ना");
        dict.put("grape", "अंगूर");
        dict.put("mustard", "सरसों");
        dict.put("soybean", "सोयाबीन");
        dict.put("apple", "सेब");
        dict.put("mango", "आम");

        for (Map.Entry<String, String> entry : dict.entrySet()) {
            result = result.replaceAll("(?i)\\b" + java.util.regex.Pattern.quote(entry.getKey()) + "\\b", entry.getValue());
        }
        return result;
    }

    private static boolean containsDevanagari(String s) {
        for (char c : s.toCharArray()) {
            if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.DEVANAGARI) {
                return true;
            }
        }
        return false;
    }
}
