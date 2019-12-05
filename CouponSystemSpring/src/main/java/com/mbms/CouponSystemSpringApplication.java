package com.mbms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.mbms.login.Session;

@SpringBootApplication
@ComponentScan({ "com.mbms" })
public class CouponSystemSpringApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CouponSystemSpringApplication.class, args);
		System.out.println("Run Application");
	}

	@Bean
	public Map<String, Session> tokens() {
		return new HashMap<String, Session>();
	}

}
