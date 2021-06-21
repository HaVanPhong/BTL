package com.hithaui.Controller2;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.PostPersist;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hithaui.Exception.NotFoundException;
import com.hithaui.Model.Account;
import com.hithaui.Model.Posts;
import com.hithaui.Repository.AccountRepositories;
import com.hithaui.Repository.PostRepositories;

@RestController
@RequestMapping("/api/posts")
public class PostsController {

	@Autowired
	private AccountRepositories accountRespository;
	
	@Autowired
	private PostRepositories postsRepositories;
	
	@Autowired
	private Cloudinary cloudinary;
	
	@GetMapping
	public ResponseEntity<?> getAllPost (){
		List<Posts> posts= postsRepositories.findAll();
		if (posts.size()==0) {
			throw new NotFoundException("Not found any posts");
		}
		return ResponseEntity.status(200).body(posts);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getAllPostByIdAccount (@PathVariable(name = "id") String id){
		List<Posts> posts= postsRepositories.findByAccountId(id);
		if (posts.size()==0) {
			throw new NotFoundException("Not found Any Posts Of This Account");
		}
		return ResponseEntity.status(200).body(posts);
	}

	//============
	@GetMapping("/getAccount/{id}")
	public ResponseEntity<?> getAccountByPostsID(@PathVariable(name = "id") Integer id){
		Account ac= accountRespository.findByPostsIdPosts(id);
		return ResponseEntity.status(200).body(ac);
	}
	
	@PatchMapping("/{id}/create_only_Content")
	public ResponseEntity<?> createPostOnLyContent(@PathVariable(name = "id") String id, @PathParam("content") String content){
		Optional<Account> account= accountRespository.findById(id);
		if (!account.isPresent())		{
			throw new NotFoundException("Not Found Account");
		}
		Posts post = new Posts(account.get(), content);
		account.get().getPosts().add(post);
		accountRespository.save(account.get());//postsRepositories.save(post)
		return ResponseEntity.status(201).body(account.get().getPosts().get(account.get().getPosts().size()-1));
	}
	
	@PatchMapping("/{id}/create_with_photo")
	public ResponseEntity<?> createPostWithImg(@PathVariable(name = "id") String id, @PathParam("content") String content, @RequestParam("img") MultipartFile file) throws IOException{
		Optional<Account> account= accountRespository.findById(id);
		if (!account.isPresent())		{
			throw new NotFoundException("Not Found Account");
		}

		Map<?, ?> map = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
		String link = map.get("secure_url").toString();		
		
		Posts post = new Posts(account.get(), content, link);
		account.get().getPosts().add(post);
		accountRespository.save(account.get());//postsRepositories.save(post)
		return ResponseEntity.status(201).body(account.get().getPosts().get(account.get().getPosts().size()-1));
		
	}
	
	@PatchMapping("/{idPosts}/like")
	public ResponseEntity<?> likePosts (@PathVariable(name = "idPosts") Integer id){
		Optional<Posts> posts = postsRepositories.findById(id);
		if (!posts.isPresent()) {
			throw new NotFoundException("Not Found Posts");
		}
		posts.get().setLike(posts.get().getLike()+1);
		return ResponseEntity.status(200).body(postsRepositories.save(posts.get()));
	}
	
	
	@DeleteMapping("/del/{idPosts}")
	public ResponseEntity<?> deletePosts (@PathVariable(name = "idPosts") Integer idPosts){
		Optional<Posts> posts = postsRepositories.findById(idPosts);
		if (posts.isPresent()) {
			postsRepositories.deleteById(idPosts);
		}else {
			throw new NotFoundException("Posts did not exists");
		}
		return ResponseEntity.status(200).build();
	}
	
	
	
	
}
