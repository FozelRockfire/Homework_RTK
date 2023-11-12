package com.rtk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "seq", initialValue = 1)
    private int id;

    private String firstName;
    private String lastName;
    private int age;
    private int groupNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Grade> grades;
}
