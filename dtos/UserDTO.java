package com.project.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor // ham khoi tao
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "Name is requied")
    @JsonProperty("fullname")
    private String fullName;


    @NotBlank(message = "Phone number is requied")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotBlank(message = "Email is requied")
    @JsonProperty("email")
    private String email;

    @JsonProperty("address")
    private String address;


    @NotBlank(message = "Password is requied")
    private String password;

    @NotBlank(message = "Retype Password is requied")
    @JsonProperty("retype_password")
    private String retypePassword;


    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("facebook_account_id")
    private Integer facebookAccountId = 0 ;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("google_account_id")
    private Integer googleAccountId = 0;

    @NotNull(message = "Role ID is requied")
    @JsonProperty("role_id")
    private Long roleId;

}
