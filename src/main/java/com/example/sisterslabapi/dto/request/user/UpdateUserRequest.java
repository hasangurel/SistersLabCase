package com.example.sisterslabapi.dto.request.user;

import jakarta.validation.constraints.NotNull;

public record UpdateUserRequest(
        @NotNull Long id,
        String name,
        String username,
        String email,
        String password
) {
}
