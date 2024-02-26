package com.example.sisterslabapi.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record UpdateUserRequest(
         @NotNull Long id,
         String name,

         String username,

         String email,

         String password
) {
}
