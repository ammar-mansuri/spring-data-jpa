package com.practice.springdatajpa;

import com.github.javafaker.Faker;
import com.practice.springdatajpa.models.Book;
import com.practice.springdatajpa.models.Student;
import com.practice.springdatajpa.models.StudentIdCard;
import com.practice.springdatajpa.repository.BooksRepository;
import com.practice.springdatajpa.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BooksRepository booksRepository;

    private String email = "";

    @BeforeEach
    public void should_add_a_new_student() {
        Faker faker = new Faker();
        email = faker.internet().emailAddress();
        final Random random = new Random();
        final long random14DigitNumber = (long) (random.nextDouble() * 90000000000000L) + 10000000000000L;
        Student student = new Student("ABC", "BCF", email, 26);
        Book book = new Book(faker.book().title(), LocalDateTime.now());
        Book book2 = new Book(faker.book().title(), LocalDateTime.now());
        Book book3 = new Book(faker.book().title(), LocalDateTime.now());
        StudentIdCard studentIdCard = new StudentIdCard(String.valueOf(random14DigitNumber), student);
        student.addBook(book);
        student.addBook(book2);
        student.addBook(book3);
        student.setStudentIdCard(studentIdCard);
        Assertions.assertDoesNotThrow(() -> studentRepository.save(student));
    }


    @DisplayName("Orphan Test - One child Removal")
    @Test
    public void should_update_existing_student() {
        Student student = studentRepository.findStudentByEmail(email).get();
        List<Book> books = student.getBooks();
        student.removeBook(books.get(0));
        Assertions.assertDoesNotThrow(() -> studentRepository.save(student));
    }

    @DisplayName("Orphan Test - All child Removal")
    @Test
    public void should_remove_all_the_associated_childs() {
        Student student = studentRepository.findStudentByEmail(email).get();
        student.getBooks().clear();
        Assertions.assertDoesNotThrow(() -> studentRepository.save(student));
    }

    @DisplayName("Cascading Operation Test")
    @Test
    public void should_remove_childs_when_parent_is_deleted() {

        Student student = studentRepository.findStudentByEmail(email).get();
        Assertions.assertDoesNotThrow(() -> studentRepository.deleteById(student.getId()));
        List<Book> books = booksRepository.findBooksByStudentId(student.getId());
        Assertions.assertEquals(books.size(), 0);

    }

}
