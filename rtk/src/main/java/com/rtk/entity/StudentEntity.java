package com.rtk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "students")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private int groupNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<GradeEntity> grades;
}
