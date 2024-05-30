package io.github.mqdev.front_cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FrontCursosApplication {

	public static void main(String[] args) {

		if (isRunningLocally()) {
			System.setProperty("host.api.gestao.cursos", "http://localhost:8080");
		}

		SpringApplication.run(FrontCursosApplication.class, args);
	}

	private static boolean isRunningLocally() {
		try {
			String profile = System.getProperty("spring.profiles.active");
			return profile == null || profile.equals("local");
		} catch (Exception e) {
			return false;
		}
	}

}
