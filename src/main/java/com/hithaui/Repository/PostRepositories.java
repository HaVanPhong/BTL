package com.hithaui.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hithaui.Model.Posts;

@Repository
public interface PostRepositories extends JpaRepository<Posts, Integer> {
	List<Posts> findByAccountId(String id);
	List<Posts> findByAccountIdNew(String idNew);
}
