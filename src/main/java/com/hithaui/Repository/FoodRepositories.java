package com.hithaui.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hithaui.Model.Food;

@Repository
public interface FoodRepositories extends JpaRepository<Food, Integer>{

}
