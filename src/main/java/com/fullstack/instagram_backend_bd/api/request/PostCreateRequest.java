package com.fullstack.instagram_backend_bd.api.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostCreateRequest {
   
   @NotNull
   @Size(max = 150)
   private String caption;

   @NotNull
   private String imageUrl;

   @NotNull
   private Long ProfileInfoId;
}
