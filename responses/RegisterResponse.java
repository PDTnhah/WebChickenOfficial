package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.User;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor // ham khoi tao
@NoArgsConstructor
public class RegisterResponse {
    @JsonProperty("messages")
    private String messages;

    @JsonProperty("user")
    private User user;
}
