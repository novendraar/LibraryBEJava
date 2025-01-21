package com.librarybe.librarybe;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;
import com.librarybe.librarybe.entity.User;
import com.librarybe.librarybe.repository.UserRepository;

@SpringBootApplication
public class LibrarybeApplication {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(LibrarybeApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			Faker faker = new Faker(Locale.forLanguageTag("id"));

			for (int i = 0; i < 10; i++) {
				User userSeed = new User(faker.name().fullName(),
						faker.phoneNumber().phoneNumber(),
						faker.address().fullAddress());
				userRepository.save(userSeed);
			}
		};
	}

}
