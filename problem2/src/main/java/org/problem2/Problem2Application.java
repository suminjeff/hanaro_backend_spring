package org.problem2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Problem2Application {

	public static void main(String[] args) {
		SpringApplication.run(Problem2Application.class, args);
	}

}
