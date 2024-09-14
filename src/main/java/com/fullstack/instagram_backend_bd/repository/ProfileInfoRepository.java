package com.fullstack.instagram_backend_bd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import  com.fullstack.instagram_backend_bd.model.ProfileInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileInfoRepository extends JpaRepository<ProfileInfo, Long>{}
