package com.hithaui.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hithaui.Model.Posts;

@Repository
public interface PostRepositories extends JpaRepository<Posts, Integer> {
	
}