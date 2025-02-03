package vn.com.demo.commonsearch;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import vn.com.demo.commonsearch.worksite.entities.Worksite;
import vn.com.demo.commonsearch.worksite.repository.WorksiteRepository;

@SpringBootApplication
@EnableCaching
@RequiredArgsConstructor
public class CommonSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonSearchApplication.class, args);
	}

}
