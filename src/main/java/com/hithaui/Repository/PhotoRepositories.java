package com.hithaui.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hithaui.Model.Photo;

@Repository
public interface PhotoRepositories extends JpaRepository<Photo, Integer> {

	Photo findByLinkImg(String linkImg);
}
