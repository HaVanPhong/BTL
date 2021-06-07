package com.hithaui.Controller2;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hithaui.DTO.FoodDTO;
import com.hithaui.Exception.NotFoundException;
import com.hithaui.Model.Food;
import com.hithaui.Model.Restaurant;
import com.hithaui.Repository.FoodRepositories;
import com.hithaui.Repository.RestaurantRepositories;

@RestController
@RequestMapping("/api/food")
public class FoodController {
	
	@Autowired
	private FoodRepositories foodRepository;
	
	@Autowired
	private RestaurantRepositories restaurantRepository;
	
	@GetMapping
	public ResponseEntity<?> getAllFood(){
		List<Food> foods= foodRepository.findAll();
		if (foods.size()==0) {
			throw new NotFoundException("Not Found Any Food");
		}
		return ResponseEntity.status(200).body(foods);
	}
	
	@PostMapping("/{idRes}")
	public ResponseEntity<?> createFood(@PathVariable(name = "idRes") Integer idRes, @RequestBody FoodDTO foodDTO){
		Optional<Restaurant> restaurant = restaurantRepository.findById(idRes);
		if (!restaurant.isPresent()) {
			throw new NotFoundException("Not Found This Restaurant, Please change the idRes at url to Post");
		}
		Food food = new Food(foodDTO.getName(), foodDTO.getOldPrice(), foodDTO.getPhanTram(), foodDTO.getDescriptions());
		food.setRestaurant(restaurant.get());
		Food newFood= foodRepository.save(food);
		restaurant.get().getListFood().add(newFood);
		restaurantRepository.save(restaurant.get());
		return ResponseEntity.status(201).body(newFood);
	}
	
	@DeleteMapping("/{idFood}")
	public ResponseEntity<?> deleteFood(@PathVariable(name = "idFood") Integer idFood){
		Optional<Food>  food= foodRepository.findById(idFood);
		if (food.isPresent()) {
			foodRepository.deleteById(idFood);
			return ResponseEntity.status(200).build();
		}
		throw new NotFoundException("Not Found idFood, please make sure idFood is correct");
	}
	
	@GetMapping("/{idRes}")
	public ResponseEntity<?> getFoodOfRestaurant(@PathVariable(name = "idRes") Integer idRes){
		Optional<Restaurant> restaurant = restaurantRepository.findById(idRes);
		List<Food> foods = restaurant.get().getListFood();
		return ResponseEntity.status(200).body(foods);
	}
	
	
}
