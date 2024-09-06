package com.friendbook.service.impl;

import com.friendbook.dto.PostImageDTO;
import com.friendbook.dto.PostVideoDTO;
import com.friendbook.dto.PostsDTO;
import com.friendbook.model.Posts;
import com.friendbook.model.Users;
import com.friendbook.repository.PostsRepository;
import com.friendbook.service.AuthenticationService;
import com.friendbook.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {
    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private PostImageRepository postImageRepository;
    @Autowired
    private PostVideoRepository postVideoRepository;
    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public List<PostsDTO> getPosts() {
        return List.of();
    }

    @Override
    public Posts savePost(PostsDTO postsDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users users = authenticationService.findByUsername(username);

        if (users == null) {
            throw new RuntimeException("User already exists");
        }
        Posts posts = new Posts();
        posts.setUsers(users);
        posts.setContent(postsDTO.getContent());
        posts.setImage(postsDTO.getImage());
        posts.setVideo(postsDTO.getVideo());
        return postsRepository.save(posts);
    }

    @Override
    public PostImage savePostImage(long postId,
                                   PostImageDTO postImageDTO) {
        Posts existingPost = postsRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        PostImage newPostImage = new PostImage();
        newPostImage.setPosts(existingPost);
        newPostImage.setImageUrl(postImageDTO.getImageUrl());
        int size = postImageRepository.findByPostsPostId(postId).size();
        if (size > PostImage.MAXIMUM_IMAGE_PER_USER) {
            throw new RuntimeException("Maximum image per user");
        }

        PostImage savedPostImage = postImageRepository.save(newPostImage);

        existingPost.setImage(savedPostImage.getImageUrl());
        postsRepository.save(existingPost);
        return postImageRepository.save(savedPostImage);
    }

    @Override
    public PostVideo savePostVideo(long postId,
                                   PostVideoDTO postVideoDTO) {
        Posts existingPost = postsRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        PostVideo newPostVideo = new PostVideo();
        newPostVideo.setPosts(existingPost);
        newPostVideo.setVideoUrl(postVideoDTO.getVideoUrl());
        int size = postVideoRepository.findByPostsPostId(postId).size();
        if (size > PostVideo.MAXIMUM_VIDEO_PER_USER) {
            throw new RuntimeException("Maximum video per user");
        }

        PostVideo savedPostVideo = postVideoRepository.save(newPostVideo);

        existingPost.setVideo(savedPostVideo.getVideoUrl());
        postsRepository.save(existingPost);
        return postVideoRepository.save(savedPostVideo);
    }

    @Override
    public Posts updatePost(PostsDTO postsDTO) {
        return null;
    }

    @Override
    public Posts findById(long postId) {
        return postsRepository.findById(postId).
                orElseThrow(() -> new RuntimeException("Post not found"));
    }
}
