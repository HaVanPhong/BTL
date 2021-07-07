package com.hithaui.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hithaui.Model.Comment;

@Repository
public interface CommentRepositories extends JpaRepository<Comment, Integer>{
	List<Comment> findByPostsIdPosts(Integer id);
	List<Comment> findByRestaurantIdRes(Integer id);
	List<Comment> findByFoodIdFood(Integer id);
}
