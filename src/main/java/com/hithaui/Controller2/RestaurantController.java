package com.hithaui.Controller2;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hithaui.DTO.RestaurantDTO;
import com.hithaui.Exception.DuplicateException;
import com.hithaui.Exception.NotFoundException;
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
			throw new NotFoundException("Not Found Any Restaurant");
		return ResponseEntity.status(200).body(restaurants);
	}
	
	@PostMapping
	public ResponseEntity<?> createRestaurant(@RequestBody RestaurantDTO restaurantDTO){
		Restaurant restaurant = repositories.findByName(restaurantDTO.getName());
		if (restaurant!=null) {
			throw new DuplicateException("Restaurant had already exists");
		}
		String name= restaurantDTO.getName();
		String address= restaurantDTO.getAddress();
		String location= restaurantDTO.getLocation();
		restaurant= new Restaurant(name, address, location);
		
		Restaurant restaurant2= repositories.save(restaurant);
		return ResponseEntity.status(201).body(restaurant2);
	}
	
	@PostMapping("/{idRes}")
	public ResponseEntity<?> setStar(@PathVariable(name = "idRes") Integer idRes,  @RequestParam("star") double star){
		Optional<Restaurant> restaurant = repositories.findById(idRes);
		if (restaurant.isPresent()) {
			int sl= restaurant.get().getSoLuot();
			double s= restaurant.get().getStar()*sl;
			s+=star;
			sl+=1;
			restaurant.get().setStar((double) s/sl);
			restaurant.get().setSoLuot(sl);
			return ResponseEntity.status(200).body(repositories.save(restaurant.get()));
		}
		throw new NotFoundException("Not Found idRestaurant");
	}
	
}
