package com.project.shopapp.responses;

import com.project.shopapp.models.Category;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor // ham khoi tao
@NoArgsConstructor
public class CategoryListResponse {
    private List<Category> categories;
}
