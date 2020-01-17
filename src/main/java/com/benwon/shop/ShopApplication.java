package com.benwon.shop;

import java.util.Properties;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

	@PostConstruct
	void started() {
		Properties props = System.getProperties();
		props.setProperty("javax.accessibility.assistive_technologies", "");
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
}
