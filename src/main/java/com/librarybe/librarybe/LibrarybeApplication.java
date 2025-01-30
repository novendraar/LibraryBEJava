package com.librarybe.librarybe;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;
import com.librarybe.librarybe.entity.Authors;
import com.librarybe.librarybe.entity.Books;
import com.librarybe.librarybe.entity.Category;
import com.librarybe.librarybe.entity.Publisher;
import com.librarybe.librarybe.entity.User;
import com.librarybe.librarybe.repository.AuthorsRepository;
import com.librarybe.librarybe.repository.BookRepository;
import com.librarybe.librarybe.repository.CategoryRepository;
import com.librarybe.librarybe.repository.PublisherRepository;
import com.librarybe.librarybe.repository.UserRepository;

@SpringBootApplication
public class LibrarybeApplication {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	AuthorsRepository authorsRepository;

	@Autowired
	PublisherRepository publisherRepository;

	@Autowired
	BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(LibrarybeApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			Faker faker = new Faker(Locale.forLanguageTag("id"));

			Random random = new Random();

			for (int i = 0; i < 10; i++) {
				User userSeed = new User(faker.name().fullName(),
						faker.phoneNumber().phoneNumber(),
						faker.address().fullAddress());
				userRepository.save(userSeed);
			}

			for (int i = 0; i < 5; i++) {
				Category categorySeed = new Category(faker.book().genre());
				categoryRepository.save(categorySeed);
			}

			for (int i = 0; i < 10; i++) {
				Authors authors = new Authors(faker.book().author());
				authorsRepository.save(authors);
			}

			for (int i = 0; i < 10; i++) {
				Publisher publisher = new Publisher(faker.book().publisher(), faker.address().fullAddress());
				publisherRepository.save(publisher);
			}

			for (int i = 0; i < 10; i++) {
				Integer randomYear = 1990 + random.nextInt(50);
				LocalDate randomDate = LocalDate.of(randomYear, 1, 1);

				Authors authorSeed = authorsRepository.findById(1 + random.nextLong(10)).orElse(null);

				Category categprySeed = categoryRepository.findById(1 + random.nextLong(5)).orElse(null);

				Publisher publisherSeed = publisherRepository.findById(1 + random.nextLong(10)).orElse(null);

				Books book = new Books(faker.book().title(), faker.lorem().sentence(), randomDate, authorSeed,
						categprySeed, publisherSeed);

				bookRepository.save(book);

			}
		};
	}

}
