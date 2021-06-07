package com.hithaui.Controller2;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hithaui.Exception.NotFoundException;
import com.hithaui.Model.Account;
import com.hithaui.Model.Comment;
import com.hithaui.Model.Food;
import com.hithaui.Model.Posts;
import com.hithaui.Model.Restaurant;
import com.hithaui.Repository.AccountRepositories;
import com.hithaui.Repository.CommentRepositories;
import com.hithaui.Repository.FoodRepositories;
import com.hithaui.Repository.PostRepositories;
import com.hithaui.Repository.RestaurantRepositories;
@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	public CommentRepositories commentRepository;
	
	@Autowired
	private AccountRepositories accountRepositories;
	
	@Autowired
	private PostRepositories postRepositories;
	
	@Autowired
	private RestaurantRepositories restaurantRepository;
	
	@Autowired
	private Cloudinary cloudinary;
	
	@Autowired
	private FoodRepositories foodRepository;
	
	@GetMapping
	public ResponseEntity<?> getAllComment(){
		List<Comment> comments= commentRepository.findAll();
		if (comments.size()==0) {
			throw new NotFoundException("Not Found Any Comment");
		}
		return ResponseEntity.status(200).body(comments);
	}
	
	@PostMapping("/posts/{id}/{idPosts}")
	public ResponseEntity<?> createCmtPost (@PathVariable(name = "id")  String id, @PathVariable(name = "idPosts") Integer idPosts,
			@RequestParam("content") String content){
		Optional<Account> account = accountRepositories.findById(id); 
		if (!account.isPresent()) {
			throw new NotFoundException("Not Found Account or Account Error");
		}
		Optional<Posts> posts = postRepositories.findById(idPosts);
		if (!posts.isPresent()) {
			throw new NotFoundException("Bài viết đã không tồn tại");
		}
		Comment comment = new Comment(content);
		comment.setAccount(account.get());
		comment.setPosts(posts.get());
		return ResponseEntity.status(201).body(commentRepository.save(comment));		
	}
	
	@PostMapping("/posts_img/{id}/{idPosts}")
	public ResponseEntity<?> createCmtPost_With_Img (@PathVariable(name = "id")  String id, @PathVariable(name = "idPosts") Integer idPosts,
			@RequestParam("content") String content, @RequestParam("img") MultipartFile file) throws IOException{
		Optional<Account> account = accountRepositories.findById(id); 
		if (!account.isPresent()) {
			throw new NotFoundException("Not Found Account or Account Error");
		}
		Optional<Posts> posts = postRepositories.findById(idPosts);
		if (!posts.isPresent()) {
			throw new NotFoundException("Bài viết đã không tồn tại");
		}
		Map<?, ?> map =  cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
		String link = map.get("secure_url").toString();
		Comment comment = new Comment(content, link);
		comment.setAccount(account.get());
		comment.setPosts(posts.get());
		return ResponseEntity.status(201).body(commentRepository.save(comment));		
	}
	
	
	// restaurant 
	@PostMapping("/restaurant/{id}/{idRes}")
	public ResponseEntity<?> createCmtRestaurant (@PathVariable(name = "id")  String id, @PathVariable(name = "idRes") Integer idRes,
			@RequestParam("content") String content){
		Optional<Account> account = accountRepositories.findById(id); 
		if (!account.isPresent()) {
			throw new NotFoundException("Not Found Account or Account Error");
		}
		Optional<Restaurant> restaurant = restaurantRepository.findById(idRes);
		if (!restaurant.isPresent()) {
			throw new NotFoundException("Nhà hàng này đã không tồn tại");
		}
		Comment comment = new Comment(content);
		comment.setAccount(account.get());
		comment.setRestaurant(restaurant.get());
		return ResponseEntity.status(201).body(commentRepository.save(comment));		
	}
	
	@PostMapping("/restaurant_img/{id}/{idRes}")
	public ResponseEntity<?> createCmtRestaurant_With_Img (@PathVariable(name = "id")  String id, @PathVariable(name = "idRes") Integer idRes,
			@RequestParam("content") String content, @RequestParam("img") MultipartFile file) throws IOException{
		Optional<Account> account = accountRepositories.findById(id); 
		if (!account.isPresent()) {
			throw new NotFoundException("Not Found Account or Account Error");
		}
		Optional<Restaurant> restaurant = restaurantRepository.findById(idRes);
		if (!restaurant.isPresent()) {
			throw new NotFoundException("Nhà hàng này đã không tồn tại");
		}
		Map<?, ?> map =  cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
		String link = map.get("secure_url").toString();
		Comment comment = new Comment(content, link);
		comment.setAccount(account.get());
		comment.setRestaurant(restaurant.get());
		return ResponseEntity.status(201).body(commentRepository.save(comment));		
	}
	
	@GetMapping("/posts/{idPosts}")
	public ResponseEntity<?> getCommentOfPosts (@PathVariable(name = "idPosts") Integer idPosts){
		Optional<Posts> posts = postRepositories.findById(idPosts);
		if (!posts.isPresent()) {
			throw new NotFoundException("Bài viết đã không còn tồn tại");
		}
		List<Comment> cmts = commentRepository.findByPostsIdPosts(idPosts);
		return ResponseEntity.status(200).body(cmts);
	}
	
	@GetMapping("/restaurant/{idRes}")
	public ResponseEntity<?> getCommentOfRestaurant (@PathVariable(name = "idRes") Integer idRes){
		Optional<Restaurant> restaurant = restaurantRepository.findById(idRes);
		if (!restaurant.isPresent()) {
			throw new NotFoundException("Nhà hàng đã không còn tồn tại");
		}
		List<Comment> cmts = commentRepository.findByRestaurantIdRes(idRes);
		return ResponseEntity.status(200).body(cmts);
	}
	
	
	//food
	@PostMapping("/food/{id}/{idFood}")
	public ResponseEntity<?> createCmtFood (@PathVariable(name = "id")  String id, @PathVariable(name = "idFood") Integer idFood,
			@RequestParam("content") String content){
		Optional<Account> account = accountRepositories.findById(id); 
		if (!account.isPresent()) {
			throw new NotFoundException("Not Found Account or Account Error");
		}
		Optional<Food> food = foodRepository.findById(idFood);
		if (!food.isPresent()) {
			throw new NotFoundException("Món ăn này đã không tồn tại");
		}
		Comment comment = new Comment(content);
		comment.setAccount(account.get());
		comment.setFood(food.get());
		return ResponseEntity.status(201).body(commentRepository.save(comment));		
	}
	
	@PostMapping("/food_img/{id}/{idFood}")
	public ResponseEntity<?> createCmtFood_With_Img (@PathVariable(name = "id")  String id, @PathVariable(name = "idFood") Integer idFood,
			@RequestParam("content") String content, @RequestParam("img") MultipartFile file) throws IOException{
		Optional<Account> account = accountRepositories.findById(id); 
		if (!account.isPresent()) {
			throw new NotFoundException("Not Found Account or Account Error");
		}
		Optional<Food> food = foodRepository.findById(idFood);
		if (!food.isPresent()) {
			throw new NotFoundException("Món ăn này đã không tồn tại");
		}
		Map<?, ?> map =  cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
		String link = map.get("secure_url").toString();
		Comment comment = new Comment(content, link);
		comment.setAccount(account.get());
		comment.setFood(food.get());
		return ResponseEntity.status(201).body(commentRepository.save(comment));		
	}
	
	@GetMapping("/Food/{idFood}")
	public ResponseEntity<?> getAllCmtOfFood(@PathVariable(name = "idFood") Integer idFood){
		List<Comment> cmts = commentRepository.findByFoodIdFood(idFood);
		if (cmts.size()==0) {
			throw new NotFoundException("Not Found Any Comment For The Food");
		}
		return ResponseEntity.status(200).body(cmts);
	}
	
}
