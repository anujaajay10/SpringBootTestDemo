package com.example.SpringBootTestDemo.service;

import com.example.SpringBootTestDemo.entity.Student;
import com.example.SpringBootTestDemo.exception.StudentNotFoundException;
import com.example.SpringBootTestDemo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {

    private StudentServiceImpl studentServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        studentServiceImplUnderTest = new StudentServiceImpl();
        studentServiceImplUnderTest.studentrepo = mock(StudentRepository.class);
    }

    @Test
    void testAddStudent() {
        // Setup
        final Student student = new Student(0, "name", "address", "rollNo");
        final Student expectedResult = new Student(0, "name", "address", "rollNo");

        // Configure StudentRepository.save(...).
        final Student student1 = new Student(0, "name", "address", "rollNo");
        when(studentServiceImplUnderTest.studentrepo.save(new Student(0, "name", "address", "rollNo")))
                .thenReturn(student1);

        // Run the test
        final Student result = studentServiceImplUnderTest.addStudent(student);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllStudent() {
        // Setup
        final List<Student> expectedResult = List.of(new Student(0, "name", "address", "rollNo"));

        // Configure StudentRepository.findAll(...).
        final List<Student> students = List.of(new Student(0, "name", "address", "rollNo"));
        when(studentServiceImplUnderTest.studentrepo.findAll()).thenReturn(students);

        // Run the test
        final List<Student> result = studentServiceImplUnderTest.getAllStudent();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllStudent_StudentRepositoryReturnsNoItems() {
        // Setup
        when(studentServiceImplUnderTest.studentrepo.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Student> result = studentServiceImplUnderTest.getAllStudent();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetStudentById() {
        // Setup
        final Student expectedResult = new Student(0, "name", "address", "rollNo");

        // Configure StudentRepository.findById(...).
        final Optional<Student> student = Optional.of(new Student(0, "name", "address", "rollNo"));
        when(studentServiceImplUnderTest.studentrepo.findById(0)).thenReturn(student);

        // Run the test
        final Student result = studentServiceImplUnderTest.getStudentById(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetStudentById_StudentRepositoryReturnsAbsent() {
        // Setup
        when(studentServiceImplUnderTest.studentrepo.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> studentServiceImplUnderTest.getStudentById(0))
                .isInstanceOf(StudentNotFoundException.class);
    }

    @Test
    void testDeleteStudentById() {
        // Setup
        // Configure StudentRepository.findById(...).
        final Optional<Student> student = Optional.of(new Student(0, "name", "address", "rollNo"));
        when(studentServiceImplUnderTest.studentrepo.findById(0)).thenReturn(student);

        // Run the test
        final Student result = studentServiceImplUnderTest.deleteStudentById(0);

        // Verify the results
        assertThat(result).isNull();
        verify(studentServiceImplUnderTest.studentrepo).delete(new Student(0, "name", "address", "rollNo"));
    }

    @Test
    void testDeleteStudentById_StudentRepositoryFindByIdReturnsAbsent() {
        // Setup
        when(studentServiceImplUnderTest.studentrepo.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> studentServiceImplUnderTest.deleteStudentById(0))
                .isInstanceOf(StudentNotFoundException.class);
    }

    @Test
    void testGetStudentByName() {
        // Setup
        final List<Student> expectedResult = List.of(new Student(0, "name", "address", "rollNo"));

        // Configure StudentRepository.findAllByName(...).
        final List<Student> students = List.of(new Student(0, "name", "address", "rollNo"));
        when(studentServiceImplUnderTest.studentrepo.findAllByName("name")).thenReturn(students);

        // Run the test
        final List<Student> result = studentServiceImplUnderTest.getStudentByName("name");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetStudentByName_StudentRepositoryReturnsNoItems() {
        // Setup
        when(studentServiceImplUnderTest.studentrepo.findAllByName("name")).thenReturn(Collections.emptyList());

        // Run the test
        final List<Student> result = studentServiceImplUnderTest.getStudentByName("name");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
