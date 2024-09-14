package com.fullstack.instagram_backend_bd.service.post;

import java.util.List;
import java.util.Optional;

import com.fullstack.instagram_backend_bd.api.request.PostCreateRequest;
import com.fullstack.instagram_backend_bd.model.Post;

public interface PostService {
   Post savePost(PostCreateRequest post);
   Optional<Post> findPostById(Long id);
   List<Post> findPostsByProfileInfoId(Long profileInfoId);
   List<Post> findAllPosts();
   void deletePostById(Long id);
}
