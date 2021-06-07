package com.hithaui.Config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	
	@Value("${email.name}")
	public String username;

	
	@Value("${email.pass}")
	public String password;
	
	@Bean
	public JavaMailSender getJavaMailSender() {		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.sparkpostmail.com"); //smtp.gmail.com
        mailSender.setPort(587);
 
        mailSender.setUsername(username);
        mailSender.setPassword(password);
 
        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");//
        props.put("mail.smtp.socketFactory.port", 587);
        props.put("mail.smtp.socketFactory.fallback", "true");
        props.put("mail.smtp.starttls.enable", "true");//
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.enable", "false");
        props.put("mail.debug", "true");//
 
        return mailSender;
	}
	

}
