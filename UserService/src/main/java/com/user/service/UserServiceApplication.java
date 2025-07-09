package com.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableEurekaClient // this is now outdated
@EnableFeignClients
public class UserServiceApplication {

	/** a bean of the restTemplate so that we can use this restTemplate to call the rating service
	 * to get the ratings of a user. */
	// moving this to configuration class
	/*@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}*/

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
