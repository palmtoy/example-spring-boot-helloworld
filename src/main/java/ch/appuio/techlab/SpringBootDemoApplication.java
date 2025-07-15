package ch.appuio.techlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBootDemoApplication {

	// SPRING_APPLICATION_JSON='{"spring.datasource.password":"******"}' ./gradlew clean build bootRun
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}
}
