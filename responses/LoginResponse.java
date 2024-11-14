package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor // ham khoi tao
@NoArgsConstructor
public class LoginResponse {
    @JsonProperty("messages")
    private String messages;

    @JsonProperty("token")
    private String token;

    @JsonProperty("refresh_token")
    private String refreshToken;
    private String tokenType = "Bearer";
    //user's detail
    private Long id;
    private String username;

    private List<String> roles;
}