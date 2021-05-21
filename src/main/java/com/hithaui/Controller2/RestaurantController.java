package com.hithaui.Controller2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hithaui.Model.Restaurant;
import com.hithaui.Repository.RestaurantRepositories;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantRepositories repositories;
	
	@GetMapping
	public ResponseEntity<?> getAllRestaurant(){
		List<Restaurant> restaurants = repositories.findAll();
		if (restaurants.size()==0)
			return ResponseEntity.status(200).body("trống");
		return ResponseEntity.status(200).body(restaurants);
	}
}
