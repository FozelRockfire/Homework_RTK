package com.rtk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "grades")
public class GradeEntity {
    @Id
    private GradeId id;

    @Column(name = "grade")
    private int grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    private StudentEntity student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("subjectId")
    private SubjectEntity subject;



    // getters and setters
}

