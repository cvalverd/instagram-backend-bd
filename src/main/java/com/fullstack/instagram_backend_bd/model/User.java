package com.fullstack.instagram_backend_bd.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name= "user_seq", sequenceName = "user_seq", allocationSize = 1, initialValue = 1000)
    private Long id;

    @NotNull
    @Column(unique=true)
    @Size(min= 3, max = 10)
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$")
    private String username;

    @NotNull
    @Size(min= 6, max=12)
    private String password;

    @NotNull
    @Column(unique=true)
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotNull
    @Size(min = 3,max = 10)
    private String name;

    private LocalDate birthdate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_info_id", referencedColumnName = "id")
    private ProfileInfo profileInfo;



}
