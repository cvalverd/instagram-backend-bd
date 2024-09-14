package com.fullstack.instagram_backend_bd.api.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileInfoUpdateRequest {
   private String title;

   @Size(max = 150)
   private String description;
   
   private String profilePicUrl;
}
