package com.letsolve.oyster;

import com.letsolve.oyster.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication

@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class OysterApplication {

	public static void main(String[] args) {
		SpringApplication.run(OysterApplication.class, args);
	}
}
