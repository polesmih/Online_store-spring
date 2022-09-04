package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CoreApp {

	public static void main(String[] args) {
		SpringApplication.run(CoreApp.class, args);
	}
}
