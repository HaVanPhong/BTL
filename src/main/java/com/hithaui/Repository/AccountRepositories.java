package com.hithaui.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hithaui.Model.Account;


@Repository
public interface AccountRepositories extends JpaRepository<Account, String> {
	Account findByUsername(String username);
	Account findByPostsIdPosts(Integer id);
}
