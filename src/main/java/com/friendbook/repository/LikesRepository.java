package com.friendbook.repository;

import com.friendbook.model.Likes;
import com.friendbook.model.Posts;
import com.friendbook.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes,Long> {
    Optional<Likes> findByUsersAndPosts(Users user, Posts post);
}
