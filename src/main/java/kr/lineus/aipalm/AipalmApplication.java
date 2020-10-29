package kr.lineus.aipalm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AipalmApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AipalmApplication.class, args);
		System.out.println("Springboot");
	}

}
