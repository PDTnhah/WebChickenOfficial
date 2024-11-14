package com.project.shopapp.services;

import com.project.shopapp.dtos.CommentDTO;
import com.project.shopapp.dtos.CommentReplyDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.models.Comment;
import com.project.shopapp.models.Product;
import com.project.shopapp.models.User;
import com.project.shopapp.repositories.CommentRespository;
import com.project.shopapp.repositories.ProductRepository;
import com.project.shopapp.repositories.UserRepository;
import com.project.shopapp.responses.CommentResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService{

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CommentRespository commentRespository;

    @Override
    @Transactional
    public Comment insertComment(CommentDTO commentDTO) {
        User user = userRepository.findById(commentDTO.getUserId()).orElse(null);
        Product product = productRepository.findById(commentDTO.getProductId()).orElse(null);
        if (user == null || product == null) {
            throw new IllegalArgumentException("User or product not found");
        }
        Comment newComment = Comment.builder()
                .user(user)
                .product(product)
                .content(commentDTO.getContent())
                .build();
        return commentRespository.save(newComment);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        commentRespository.deleteById(commentId);
    }

    @Override
    public void updateComment(Long id, CommentDTO commentDTO) throws DataNotFoundException {
        Comment existingComment = commentRespository.findById(id).orElseThrow(() -> new
                DateTimeException("Comment not exist"));
        existingComment.setContent(commentDTO.getContent());
        commentRespository.save(existingComment);
    }

    @Override
    public List<CommentResponse> getCommentsByUserAndProduct(Long userId, Long productId) {

        List<Comment> comments = commentRespository.findByUserIdAndProductId(userId,productId);
        return comments.stream().map(CommentResponse::fromComment).collect(Collectors.toList());
    }

    @Override
    public List<CommentResponse> getCommentsByProduct(Long productId) {
        List<Comment> comments = commentRespository.findByProductId(productId);
        return comments.stream().map(CommentResponse::fromComment).collect(Collectors.toList());
    }

    @Override
        public Comment replyComment(CommentReplyDTO commentReplyDTO) {
        Optional<Comment> optionalCommentParent = commentRespository.findById(commentReplyDTO.getParentCommentId());
        if (optionalCommentParent.isEmpty()) {
            // Xử lý khi comment cha không tồn tại
            throw new EntityNotFoundException("Parent comment not found");
        }
        Comment commentParent = optionalCommentParent.get();
        User user = userRepository.findById(commentReplyDTO.getUserId()).orElse(null);
        Product product = productRepository.findById(commentReplyDTO.getProductId()).orElse(null);
        Comment commentReply = Comment.builder()
                .parentComment(commentParent)
                .user(user)
                .product(product)
                .content(commentReplyDTO.getContent())
                .build();
        return commentRespository.save(commentReply);
    }
}
