package com.hithaui.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hithaui.Model.Notification;

@Repository
public interface NotificationRepositories extends JpaRepository<Notification, Integer>{
	
}
