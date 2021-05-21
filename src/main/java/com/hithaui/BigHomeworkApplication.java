package com.hithaui;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.cloudinary.Cloudinary;

//SecurityAutoConfiguration.class
//@EnableJpaRepositories("com.hithaui.Repository.AccountRepositories")
//scanBasePackages = {"com.hithaui.Repository.AccountRepositories" }
//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
//		UserDetailsServiceAutoConfiguration.class, SecurityAutoConfiguration.class })
//@EnableJpaRepositories("com.hithaui.Repository.AccountRepositories")

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
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
