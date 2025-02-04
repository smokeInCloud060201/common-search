package vn.com.demo.commonsearch;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@RequiredArgsConstructor
public class CommonSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonSearchApplication.class, args);
	}

}
