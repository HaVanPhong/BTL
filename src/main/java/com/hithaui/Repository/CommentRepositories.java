package com.hithaui.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hithaui.Model.Comment;

@Repository
public interface CommentRepositories extends JpaRepository<Comment, Integer>{
	
}
