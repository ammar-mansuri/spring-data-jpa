package com.practice.springdatajpa;

import com.github.javafaker.Faker;
import com.practice.springdatajpa.models.Book;
import com.practice.springdatajpa.models.Student;
import com.practice.springdatajpa.models.StudentIdCard;
import com.practice.springdatajpa.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class StudentRepositoryTests {

    @Autowired
    StudentRepository studentRepository;

    private String email = "";

    @BeforeEach
    public void should_add_a_new_student() {
        Faker faker = new Faker();
        email = faker.internet().emailAddress();
        final Random random = new Random();
        final long random14DigitNumber = (long) (random.nextDouble() * 90000000000000L) + 10000000000000L;
        Student student = new Student("ABC", "BCF", email, 26);
        Book book = new Book("XYZ", LocalDateTime.now());
        StudentIdCard studentIdCard = new StudentIdCard(String.valueOf(random14DigitNumber), student);
        student.addBook(book);
        student.setStudentIdCard(studentIdCard);
        Assertions.assertDoesNotThrow(() -> studentRepository.save(student));
    }


    @Test
    public void should_update_existing_student() {
        Student student = studentRepository.findStudentByEmail(email).get();
        List<Book> books = student.getBooks();
        student.removeBook(books.get(0));
        Assertions.assertDoesNotThrow(() -> studentRepository.save(student));

    }

}
