package com.fullstack.instagram_backend_bd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  com.fullstack.instagram_backend_bd.model.Post;
import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    List<Post> findByProfileInfoId(long profileInfo);
    
}
