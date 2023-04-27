package com.example.SpringBootTestDemo.repository;

import com.example.SpringBootTestDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    List<Student> findAllByName(String name);
}
