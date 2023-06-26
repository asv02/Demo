package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.Entity.Product;
import com.example.demo.Entity.Response;
import com.example.demo.Helper.Helper;
import com.example.demo.Repo.ProductRepo;
import com.example.demo.Repo.ResponseRepo;

import com.example.demo.Entity.Response.Status;
import jakarta.mail.MessagingException;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private ResponseRepo responseRepo;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// @EventListener(ApplicationReadyEvent.class)
	@Scheduled(cron = "0 * * * * *")
	public void triggerMail() throws MessagingException {
		List<Response> responses = this.responseRepo.findAll();
		for (Response it : responses) {
			if ((it.getStatus())==Status.ASSIGNED) {
				System.out.println(it.getStatus());
				try {
					Helper.sendSimpleEmail(mailSender,it.getEmail(), "Load Assignment",
						"Load" + it.getLoadId() + "is assigned to you complete it asap,check Liveasy dashboad for more information.");
				} catch (Exception e) {
					System.out.println("Mail not sent.");
				}
			}
		}
	}

}
