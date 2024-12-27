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
public class CommonSearchApplication implements CommandLineRunner {
	
	private final WorksiteRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(CommonSearchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		for (int i = 0; i < 10; i++) {
			Worksite worksite = new Worksite();
			worksite.setLocation("location" + i);
			repository.save(worksite);
		}

	}
}
