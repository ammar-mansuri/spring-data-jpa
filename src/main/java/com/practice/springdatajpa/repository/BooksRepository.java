package com.practice.springdatajpa.repository;

import com.practice.springdatajpa.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BooksRepository extends JpaRepository<Book, UUID> {

    List<Book> findBooksByStudentId(final UUID studentId);
}
