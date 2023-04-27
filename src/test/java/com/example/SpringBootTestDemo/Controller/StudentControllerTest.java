package com.example.SpringBootTestDemo.Controller;

import com.example.SpringBootTestDemo.controller.StudentController;
import com.example.SpringBootTestDemo.entity.Student;
import com.example.SpringBootTestDemo.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentService studentservice;
    private Student student;
    @BeforeEach
    void setup(){
        student=new Student(1,"Anuja","Thiruvalla","245235");

    }
    @Test
    void testSaveStudent() throws Exception {
        Student student = new Student(1,"Anuja","Thiruvalla","245235");
        Mockito.when(studentservice.addStudent(student)).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders.post("/student/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\r\n" +
                                " \"id\" : 1, \r\n" +
                                " \"name\" : \"Anuja\", \r\n" +
                                " \"address\" :\"Thiruvalla\", \r\n" +
                                " \"rollNo\" : \"245235\" \r\n" + "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
