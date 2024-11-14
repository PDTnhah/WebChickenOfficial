package com.project.shopapp.Controllers;

import com.project.shopapp.components.LocalizationUtils;
import com.project.shopapp.components.convertors.CategoryMessageConvertor;
import com.project.shopapp.dtos.CategoryDTO;
import com.project.shopapp.models.Category;
import com.project.shopapp.responses.CategoryListResponse;
import com.project.shopapp.responses.CategoryResponse;
import com.project.shopapp.responses.UpdateCategoryResponse;
import com.project.shopapp.services.CategoryService;
import com.project.shopapp.utils.MessageKeys;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/categories")
//@Validated
public class CategoryController {
    private  final KafkaTemplate<String , Object> kafkaTemplate;

    private final CategoryService categoryService;
    private final LocalizationUtils localizationUtils;
    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    // Neu tham so truyen vao la mot doi tuongthi sao => data tranfer object  = request object
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDTO categoryDTO,
                                              BindingResult result) {
        if(result.hasErrors()) {
            List<String> errorMessage = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return  ResponseEntity.badRequest().body(errorMessage);
        }
        Category category = categoryService.createCategory(categoryDTO);
        CategoryResponse  categoryResponse = new CategoryResponse();
//        this.kafkaTemplate.send("insert-a-category", category);
//        this.kafkaTemplate.setMessageConverter(new CategoryMessageConvertor());
        categoryResponse.setCategory(category);
        return ResponseEntity.ok(categoryResponse);
    }
    //hiển thị tất cả categories
    @GetMapping("") //https://localhost:8088/api/v1/categories?page=1&limit=10
    public ResponseEntity<CategoryListResponse> getAllCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        List<Category> categories = categoryService.getAllCategories();
//        this.kafkaTemplate.send("get-all-categories", categories);

        return ResponseEntity.ok(CategoryListResponse.builder()
                        .categories(categories)
                .build());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(
            @PathVariable("id") Long categoryId
    ) {
        try {
            Category existingCategory = categoryService.getCategoryById(categoryId);
            return ResponseEntity.ok(existingCategory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategories(@Valid @PathVariable long id,@Valid @RequestBody CategoryDTO categoryDTO) {
        categoryService.updateCategory(id, categoryDTO);
//        UpdateCategoryResponse updateCategoryResponse = new UpdateCategoryResponse();
        categoryService.updateCategory(id, categoryDTO);
        //updateCategoryResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.UPDATE_CATEGORY_SUCCESSFULLY));
        return ResponseEntity.ok().body("");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategories(@PathVariable long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("deleteCategory with id : " + id + "successfully" );
    }
}
