package com.fullstack.instagram_backend_bd.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name= "profile_info")
public class ProfileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_info_seq")
    @SequenceGenerator(name= "profile_info_seq", sequenceName = "profile_info_seq", allocationSize = 1, initialValue = 1000)
    private Long id;

    private String title = "Usuario nuevo";

    @Size(max = 150)
    private String description = "Este es el valor por defecto";

    private String profilePicUrl = "/img/default_profile.png";

    private String followers = "0";

    private String following = "0";

    @OneToMany(mappedBy = "profileInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Post> posts;

}