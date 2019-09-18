package com.mbms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan({"com.mbms"})
public class CouponSystemSpringApplication {

	public static void main(String[] args) {
	ConfigurableApplicationContext context =SpringApplication.run(CouponSystemSpringApplication.class, args);
	System.out.println("Run Application");
	}

}
