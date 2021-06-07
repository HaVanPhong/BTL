package com.hithaui.Config;

import org.springframework.beans.factory.annotation.Value;

public class MyConstants {
	@Value("${email.name}")
	public static String username;

	
	@Value("${email.pass}")
	public static String password;
	
	
}
