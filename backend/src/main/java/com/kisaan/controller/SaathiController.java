package com.kisaan.controller;

import com.kisaan.model.Comment;
import com.kisaan.model.Post;
import com.kisaan.service.SaathiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
public class SaathiController {

    private final SaathiService saathiService;

    public SaathiController(SaathiService saathiService) {
        this.saathiService = saathiService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts(@RequestParam(required = false) String district) {
        return ResponseEntity.ok(saathiService.getPosts(district));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return saathiService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post created = saathiService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String text = payload.get("text");
        String authorName = payload.getOrDefault("authorName", "Ramesh Patil");

        if (text == null || text.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Comment comment = new Comment();
        comment.setText(text.trim());
        comment.setAuthorName(authorName);

        Comment saved = saathiService.addComment(id, comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
