package com.project.shopapp.dtos;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor // ham khoi tao
@NoArgsConstructor
public class ForgotPasswordDTO {
    private String phoneNumber;
    private String email;
}
