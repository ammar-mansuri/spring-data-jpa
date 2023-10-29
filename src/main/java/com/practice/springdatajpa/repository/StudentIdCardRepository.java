package com.practice.springdatajpa.repository;

import com.practice.springdatajpa.models.StudentIdCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentIdCardRepository extends JpaRepository<StudentIdCard, UUID> {
}
