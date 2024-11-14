package com.project.shopapp.responses;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor // ham khoi tao
@NoArgsConstructor
public class CommentListResponse {
    private List<CommentResponse> commentResponseList;
}
