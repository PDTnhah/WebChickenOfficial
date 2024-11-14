package com.project.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor // ham khoi tao
@NoArgsConstructor
public class OrderDTO {
    @Min(value = 1 , message = "user's ID must be > 0")
    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("fullname")
    private String fullName;

    private String email;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is required")
    @Size(min = 10 , max = 11 , message = "Phone number must at least 10 characters")
    private String phoneNumber;

    private String address;

    private String province;

    private String district;

    private String commune;

    private String note;

    @JsonProperty("total_money")
    @Min(value = 0, message = "Total money >= 0")

    private Float totalMoney;

    @JsonProperty("is_active")
    private boolean active;

    @JsonProperty("order_date")
    private LocalDate orderDate;

    @JsonProperty("shipping_date")
    private LocalDate shippingDate;

    @JsonProperty("shipping_method")
    private String shippingMethod;

    @JsonProperty("shipping_address")
    private String shippingAddress;

    @JsonProperty("status")
    private String status;

    @JsonProperty("payment_method")
    private String paymentMethod;

    @JsonProperty("coupon_code")
    private String couponCode;

    @JsonProperty("cart_items")
    private List<CartItemDTO> cartItems;

}