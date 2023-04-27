package com.example.SpringBootTestDemo.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.CustomLog;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id

    @Column(name = "ID")
    private Integer id;
    @NotBlank(message="the column name should not be blank")
    @Column(name = "NAME")
    private String name;
    @Column(name="ADDRESS")
    private String address;
    @Column(name = "ROLLNO")
    private String rollNo;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rollNo='" + rollNo + '\'' +
                '}';
    }



}
