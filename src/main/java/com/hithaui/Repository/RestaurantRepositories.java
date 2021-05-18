package com.hithaui.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hithaui.Model.Restaurant;

@Repository
public interface RestaurantRepositories extends JpaRepository<Restaurant, Integer>{
	Restaurant findByName(String name);
}
