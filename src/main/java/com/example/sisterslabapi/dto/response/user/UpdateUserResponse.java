package com.example.sisterslabapi.dto.response.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UpdateUserResponse(
        @NotBlank Long id,
        String name,

        String username,

        String email,

        String password
) {
}
