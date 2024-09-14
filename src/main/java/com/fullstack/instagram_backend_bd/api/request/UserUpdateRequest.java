package com.fullstack.instagram_backend_bd.api.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateRequest {

   @NotNull
   @Size(min = 3, max = 10)
   @Pattern(regexp = "^[a-zA-Z0-9._-]+$")
   private String username;

   @NotNull
   @Size(min = 6, max = 12)
   private String password;

   @NotNull
   @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
   private String email;
   
   private LocalDate birthdate;
}
