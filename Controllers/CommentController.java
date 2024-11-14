package com.project.shopapp.Controllers;
import com.project.shopapp.dtos.*;
import com.project.shopapp.models.Comment;
import com.project.shopapp.models.User;

import com.project.shopapp.responses.CommentResponse;
import com.project.shopapp.services.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("${api.prefix}/comments")
//@Validated
//Dependency Injection
@RequiredArgsConstructor

public class CommentController {
    private final CommentService commentService;

    @GetMapping("")
    public ResponseEntity<List<CommentResponse>> getAllComments(
            @RequestParam(value = "user_id", required = false) Long userId,
            @RequestParam("product_id") Long productId
    ) {
        List<CommentResponse> commentResponses;
        if (userId == null) {
            commentResponses = commentService.getCommentsByProduct(productId);
        } else {
            commentResponses = commentService.getCommentsByUserAndProduct(userId, productId);
        }
        return ResponseEntity.ok(commentResponses);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> updateComment(
            @PathVariable("id") Long commentId,
            @Valid @RequestBody CommentDTO commentDTO
    ) {
        try {
            User loginUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!Objects.equals(loginUser.getId(), commentDTO.getUserId())) {
                return ResponseEntity.badRequest().body("You cannot update another user's comment");
            }
            commentService.updateComment(commentId, commentDTO);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            // Handle and log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred during comment update.");
        }
    }
    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> insertComment(
            @Valid @RequestBody CommentDTO commentDTO
    ) {
        try {
            // Insert the new comment
            User loginUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(loginUser.getId() != commentDTO.getUserId()) {
                return ResponseEntity.badRequest().body("You cannot comment as another user");
            }
            commentService.insertComment(commentDTO);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            // Handle and log the exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("An error occurred during comment insertion.");
        }
    }
    @PostMapping("/reply")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> replyComment(
            @Valid @RequestBody CommentReplyDTO commentReplyDTO
    ) {
        try {
            User loginUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(!Objects.equals(loginUser.getId(), commentReplyDTO.getUserId())) {
                return ResponseEntity.badRequest().body("You cannot comment as another user");
            }
            Comment commentReply = commentService.replyComment(commentReplyDTO);
            return ResponseEntity.ok(CommentResponse.fromComment(commentReply));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{commentId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().body("");
    }
}
