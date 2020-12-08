package com.safetynet.safetynetalerts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.safetynetalerts.model")
@SpringBootApplication
public class SafetyNetAlertsApplication {
	public static void main(final String[] args) {
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
	}

}
