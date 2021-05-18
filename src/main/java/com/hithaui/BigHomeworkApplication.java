package com.hithaui;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.cloudinary.Cloudinary;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class }, 
	scanBasePackages = {"com.hithaui.Repository.AccountRepositories"})
public class BigHomeworkApplication {

	@Value("${cloudinary.url}")
	private String CLOUDINARY_URL;
	
	public static void main(String[] args) {
		SpringApplication.run(BigHomeworkApplication.class, args);
	}
	
	@Bean
	public Cloudinary cloudinary() {
		Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
		return cloudinary;
	}

}
