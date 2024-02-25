package com.example.sisterslabapi.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateUserResponse(

        @NotBlank String name,

        @NotBlank String username,

        @NotBlank String email,

        @NotBlank String password
) {
}
