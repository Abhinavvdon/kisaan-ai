package com.kisaan.service;

import com.kisaan.model.Comment;
import com.kisaan.model.Post;
import com.kisaan.repository.CommentRepository;
import com.kisaan.repository.PostRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
            // Post 1: Nashik Onion
            Post p1 = new Post(
                    "Nashik Onion Black Mold & Storage Rot — Prevention steps?",
                    "Due to sudden unseasonal rains last fortnight, we are noticing early bulb softening and black Aspergillus mold in our chawl storage. Has anyone had success with sulfur dusting or ambient ventilation fans? Looking for low-cost preservation advice.",
                    null,
                    "Nashik",
                    "Maharashtra",
                    "Kailash Jadhav",
                    "Onion"
            );
            p1.setCreatedAt(LocalDateTime.now().minusHours(3));
            p1.addComment(new Comment("Kailash ji, ensure your storage has 40% open slatted wooden flooring. We dusted Carbendazim @ 2g/kg on outer skins and improved airflow, saving 85% of our harvest.", "Sanjay Kulkarni", p1));
            p1.addComment(new Comment("Avoid stacking more than 4 feet high during humid periods. Also inspect bulbs daily to isolate infected lots immediately.", "Dr. Patil (Krishi Vigyan Kendra)", p1));
            postRepository.save(p1);

            // Post 2: Guntur Chilli
            Post p2 = new Post(
                    "Chilli Black Thrips control with border trap cropping",
                    "Sharing my experience from this season: planting 3 rows of African tall marigold on field borders and spraying cold-pressed neem oil (10,000 ppm) + Spinetoram kept thrips damage below economic injury level in our 3-acre parcel.",
                    null,
                    "Guntur",
                    "Andhra Pradesh",
                    "Suresh Reddy",
                    "Chilli"
            );
            p2.setCreatedAt(LocalDateTime.now().minusHours(8));
            p2.addComment(new Comment("Brilliant result Suresh garu! Did you also use blue sticky sheets? We found 25 sheets per acre captured maximum adults during twilight.", "Venkatesh Rao", p2));
            postRepository.save(p2);

            // Post 3: Ludhiana Wheat
            Post p3 = new Post(
                    "HD-3086 Wheat yellow rust scouting report",
                    "Noticed scattered yellow rust stripes on flag leaves in our block after heavy morning dew. Propiconazole 25 EC spray completed yesterday at 200 ml/acre in 200 liters of water. Leaf drying stopped within 36 hours.",
                    null,
                    "Ludhiana",
                    "Punjab",
                    "Harpreet Singh",
                    "Wheat"
            );
            p3.setCreatedAt(LocalDateTime.now().minusHours(18));
            p3.addComment(new Comment("Good timing Harpreet veerji. Never delay rust sprays once temperatures hit 15-22°C with morning fog.", "Gurpreet Brar", p3));
            postRepository.save(p3);

            // Post 4: Pune PMKSY Drip
            Post p4 = new Post(
                    "PM Krishi Sinchai Yojana 55% drip subsidy credited!",
                    "Happy to update fellow farmers that our drip irrigation installation subsidy under PMKSY was cleared through the MahaDBT portal within 18 days of geo-tagging inspection. Happy to guide anyone on document submission.",
                    null,
                    "Pune",
                    "Maharashtra",
                    "Anand Shinde",
                    "Irrigation"
            );
            p4.setCreatedAt(LocalDateTime.now().minusDays(1));
            p4.addComment(new Comment("Congratulations! What 7/12 land papers did the Taluka agriculture officer verify during field inspection?", "Balasaheb Deshmukh", p4));
            postRepository.save(p4);

            // Post 5: Varanasi Organic Tomato
            Post p5 = new Post(
                    "Trichoderma viride root drenching gives 100% survival in tomato",
                    "Instead of chemical drenching, we enriched well-rotted cow dung manure with 2kg Trichoderma viride per quintal and applied during transplanting. Zero collar rot or damping-off observed despite warm soil temperatures.",
                    null,
                    "Varanasi",
                    "Uttar Pradesh",
                    "Rajesh Maurya",
                    "Tomato"
            );
            p5.setCreatedAt(LocalDateTime.now().minusDays(2));
            p5.addComment(new Comment("Organic Trichoderma is a game changer for solanaceous vegetable seedlings. Keep soil moisture moderate for best fungal multiplication.", "Virendra Yadav", p5));
            postRepository.save(p5);
        }
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
            post.setAuthorName("Ramesh Patil"); // Sample logged-in farmer
        }
        if (post.getDistrict() == null || post.getDistrict().trim().isEmpty()) {
            post.setDistrict("Nashik");
        }
        if (post.getState() == null || post.getState().trim().isEmpty()) {
            post.setState("Maharashtra");
        }
        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Transactional
    public Comment addComment(Long postId, Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + postId));
        if (comment.getAuthorName() == null || comment.getAuthorName().trim().isEmpty()) {
            comment.setAuthorName("Ramesh Patil");
        }
        comment.setPost(post);
        comment.setCreatedAt(LocalDateTime.now());
        Comment saved = commentRepository.save(comment);
        post.getComments().add(saved);
        return saved;
    }
}
