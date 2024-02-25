package com.example.sisterslabapi.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateUserRequest(

         @NotBlank String name,

         @NotBlank String username,

         @NotBlank String email,

         @NotBlank String password
) {
}
