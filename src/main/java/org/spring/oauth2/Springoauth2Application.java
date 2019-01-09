package org.spring.oauth2;

import org.spring.oauth2.user.listener.AuthApplicationReadyListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Springoauth2Application {
	public static void main(String[] args) {
//		SpringApplication.run(Springoauth2Application.class, args);
		SpringApplication apps = new SpringApplication(new Object[]{Springoauth2Application.class});
//		apps.addListeners(new AuthApplicationReadyListener());
		apps.run(args);
	}
}
