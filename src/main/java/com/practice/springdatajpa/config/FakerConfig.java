package com.practice.springdatajpa.config;

import com.github.javafaker.Faker;
import com.practice.springdatajpa.models.Book;
import com.practice.springdatajpa.models.Student;
import com.practice.springdatajpa.models.StudentIdCard;
import com.practice.springdatajpa.repository.StudentIdCardRepository;
import com.practice.springdatajpa.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Configuration
public class FakerConfig {

    @Bean
    @Transactional
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentIdCardRepository studentIdCardRepository) {
        return args -> {
            Faker faker = new Faker();

            final String firstName = faker.name().firstName();
            final String lastName = faker.name().lastName();
            final String email = String.format("%s.%s@xyz.edu", firstName, lastName);
            final Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55));

            student.addBook(
                    new Book(faker.book().title(), LocalDateTime.now().minusDays(4)));


            student.addBook(
                    new Book(faker.book().title(), LocalDateTime.now()));


            student.addBook(
                    new Book(faker.book().title(), LocalDateTime.now().minusYears(1)));

            final Random random = new Random();
            final long random14DigitNumber = (long) (random.nextDouble() * 90000000000000L) + 10000000000000L;
            StudentIdCard studentIdCard = new StudentIdCard(String.valueOf(random14DigitNumber), student);

            student.setStudentIdCard(studentIdCard);

            studentRepository.save(student);

            studentIdCardRepository.deleteById(UUID.fromString("6b97fd99-47eb-4f09-b719-686ac64cac5a"));
        };
    }
}
