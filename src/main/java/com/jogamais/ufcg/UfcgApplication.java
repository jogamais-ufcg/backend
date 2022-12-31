package com.jogamais.ufcg;

import com.jogamais.ufcg.models.Permission;
import com.jogamais.ufcg.models.User;
import com.jogamais.ufcg.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class UfcgApplication {

	public static void main(String[] args) {
		SpringApplication.run(UfcgApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.createPermission(new Permission(null, "USER"));
			userService.createPermission(new Permission(null, "ADMIN"));
			userService.createPermission(new Permission(null, "SUPER_ADMIN"));
			userService.createPermission(new Permission(null, "MANAGER"));

			userService.create(new User(null, "Eduardo", "19602262079", "eduardogb@gmail.com", "120110905", "88992255497", "12345", new ArrayList<>(), true, true, false, new Date(), true, false));

			userService.addPermissionToUser("eduardogb@gmail.com", "ADMIN");

		};
	}

}
