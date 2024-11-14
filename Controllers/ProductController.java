package com.project.shopapp.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import com.project.shopapp.dtos.ProductDTO;
import com.project.shopapp.dtos.ProductImageDTO;
import com.project.shopapp.dtos.RatingDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.models.Product;
import com.project.shopapp.models.ProductImage;
import com.project.shopapp.models.Rating;
import com.project.shopapp.responses.ProductListResponse;
import com.project.shopapp.responses.ProductResponse;
import com.project.shopapp.responses.RatingListResponse;
import com.project.shopapp.responses.RatingResponse;
import com.project.shopapp.services.IProductRedisService;
import com.project.shopapp.services.IProductService;
import com.project.shopapp.utils.MessageKeys;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final IProductService productService;
    //private final IProductRedisService productRedisService;
    private static final String UPLOAD_DIR = "uploads";

    @PostMapping("")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createProduct(
            @Valid @RequestBody ProductDTO productDTO,

            BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errors = result.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errors);
            }
            //productRedisService.clear();
            Product newProduct = productService.createProduct(productDTO);


            // Lưu thông tin sản phẩm vào database (giả sử productService.createProduct(productDTO))
            // productService.createProduct(productDTO);

            return ResponseEntity.ok(ProductResponse.fromProduct(newProduct));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping(value = "uploads/{id}", consumes = {"multipart/form-data"})
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> uploadImages(@PathVariable("id") Long productId,@ModelAttribute("files") List<MultipartFile> files) {
        //productRedisService.clear();
        try {
            Product existingProduct = productService.getProductById(productId);
            files = files == null ? new ArrayList<MultipartFile>() : files;
            if(files.size() > ProductImage.MAXIMUM_IMAGES_PER_PRODUCT) {
                return ResponseEntity.badRequest().body("You can only upload maximum 5 images");
            }
            List<ProductImage> productImages = new ArrayList<>();
            for(MultipartFile file : files) {
                // Kiểm tra kích thước file
                if(file.getSize() == 0) continue;
                if (file.getSize() > 10 * 1024 * 1024) {
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                            .body("File is too large! Max size is 10MB");
                }

                // Kiểm tra loại file
                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                            .body("File must be an image");
                }

                // Lưu file vào thư mục uploads
                String filename = productService.storeFile(file);
                ProductImage productImage = productService.createProductImage(
                        existingProduct.getId()
                        , ProductImageDTO.builder()
                                .productId(existingProduct.getId())
                                .imageUrl(filename).build());

                //Luu vao product_images
                productImages.add(productImage);

            }
            return ResponseEntity.ok().body(productImages);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/images/{imageName}")
    public ResponseEntity<?> viewImage(@PathVariable String imageName) {
        try {
            Path imagePath = Paths.get("uploads/"+imageName);
            UrlResource resource = new UrlResource(imagePath.toUri());
            if(resource.exists()) {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
            } else {
                    return  ResponseEntity.ok()
                            .contentType(MediaType.IMAGE_JPEG)
                            .body(new UrlResource(Paths.get("uploads/notfound.jpg").toUri()));
                }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
//    private String storeFile(MultipartFile file) throws IOException {
//        if(!isImageFile(file) || file.getOriginalFilename() == null) {
//            throw new IOException("Invalid image format");
//        }
//        String originalFilename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//        String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
//
//        Path uploadPath = Paths.get(UPLOAD_DIR);
//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
//
//        Path destination = uploadPath.resolve(uniqueFilename);
//        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
//
//        return uniqueFilename;
//    }

//    private boolean isImageFile(MultipartFile file) {
//        String contentType = file.getContentType();
//        return contentType != null && contentType.startsWith("image/");
//    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getProductsById(
            @PathVariable("id") Long productId
    )  {
        try {
            Product existingProduct = productService.getProductById(productId);
            return ResponseEntity.ok(ProductResponse.fromProduct(existingProduct));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/by-ids")
    public  ResponseEntity<?> getProductsByIds(@RequestParam("ids") String ids) {
        //Truyen chuoi id san pham 1,3,5,7
        try {
            List<Long> productsId = Arrays.stream(ids.split(","))
                    .map(Long::parseLong)
                    .toList();
            List<Product> products = productService.findByProductsByIds(productsId);
            List<ProductResponse> productResponses = products.stream().map(ProductResponse::fromProduct).toList();
            //productRedisService.clear();
            return ResponseEntity.ok(productResponses);
        }
        catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<ProductListResponse> getProducts(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0" , name="category_id") Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam( defaultValue = "12") int limit
    ) throws JsonProcessingException {
        PageRequest pageRequest = PageRequest.of(
                page,
                limit,

                Sort.by("id").ascending());
        logger.info(String.format("keyword = %s , category_id = %d, page = %d , limit = %d",
                keyword, categoryId, page, limit));
        int totalPages = 0;
        //List<ProductResponse> productResponses = productRedisService.getAllProducts(keyword,categoryId,pageRequest);
        //Lay tong so trang
       //if(productResponses == null) {
            Page<ProductResponse> productPage = productService.getAllProducts(keyword, categoryId,pageRequest);
             totalPages = productPage.getTotalPages();
           List<ProductResponse> products = productPage.getContent();
            //productResponses = productPage.getContent();
//            productRedisService.saveAllProducts(
//                    productResponses,
//                    keyword,
//                    categoryId,
//                    pageRequest
//            );
        //}
        return ResponseEntity.ok(ProductListResponse
                .builder()
                .products(products)
                .totalPages(totalPages)
                .build());
    }
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        try {
            productService.deleteProduct(id);
            //productRedisService.clear();
            return ResponseEntity.ok("");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long productId,
                                           @RequestBody ProductDTO productDTO) {
        try {
            Product updateProduct = productService.updateProduct(productId, productDTO);
            //productRedisService.clear();
            return ResponseEntity.ok(updateProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }
    // Các phương thức khác như getProducts, getProductById, deleteProduct sẽ được giữ nguyên
    @PostMapping("/generateFakeProducts")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> generateFakeProducts()  {
        Faker faker = new Faker();

        for (int i = 0; i < 100 ; i++) {
            String productName = faker.commerce().productName();
            if(productService.existsByName(productName)) continue;
            ProductDTO productDTO = ProductDTO.builder()
                    .name(productName)
                    .price(faker.number().numberBetween(0,90000000))
                    .description(faker.lorem().sentence())
                    .thumbnail("")
                    .categoryId((long)faker.number().numberBetween(1,5))
                    .build();
            try {
                productService.createProduct(productDTO);
            }
            catch (Exception e) {
                return  ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.ok("Fake products created successfully");
    }
    @PostMapping("/{id}/{user_id}/rating")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') " )
    public ResponseEntity<?> ratingProduct(@Valid @RequestBody RatingDTO ratingDTO) {
        try {
            Rating rating = productService.ratingProduct(ratingDTO.getProductId(), ratingDTO.getUserId(), ratingDTO.getStars());
            return ResponseEntity.ok(RatingResponse.fromRating(rating));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping("/{productId}/{userId}/getrating")
    //@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getRatingProductOfUser(@Valid @PathVariable("userId") Long userId,
                                           @Valid @PathVariable("productId") Long productId) {
        try {
            Rating rating = productService.getRatingProductOfUser(productId, userId);
            return ResponseEntity.ok(RatingResponse.fromRating(rating));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{productId}/getCountRating")
    public ResponseEntity<?> getCountRating(@Valid @PathVariable("productId") Long productId) {
        List<Rating> ratings = productService.getCountRating(productId);

        // Chuyển đổi List<Rating> thành List<RatingResponse>
        List<RatingResponse> ratingResponses = ratings.stream()
                .map(RatingResponse::fromRating) // Sử dụng phương thức chuyển đổi từ Rating sang RatingResponse
                .collect(Collectors.toList());

        // Tạo đối tượng RatingListResponse và trả về trong ResponseEntity
        RatingListResponse ratingListResponse = RatingListResponse.builder()
                .ratingResponses(ratingResponses)
                .build();

        return ResponseEntity.ok(ratingListResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProduct() {
        List<Product> products = productService.getAllProduct();
        List<ProductResponse> productResponses = products.stream()
                .map(ProductResponse::fromProduct)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productResponses);
    }

}
