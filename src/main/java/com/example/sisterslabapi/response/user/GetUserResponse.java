package com.example.sisterslabapi.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record GetUserResponse(
        @NotBlank Long id,

        String name,

        String username,

        String email,

        String password
) {
}
