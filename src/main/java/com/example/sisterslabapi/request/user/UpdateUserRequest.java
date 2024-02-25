package com.example.sisterslabapi.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UpdateUserRequest(
         @NotBlank Long id,
         String name,

         String username,

         String email,

         String password
) {
}
