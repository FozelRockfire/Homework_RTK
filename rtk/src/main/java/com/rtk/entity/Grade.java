package com.rtk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "grades")
public class Grade {
    @Id
    private GradeId id;

    @Column(name = "grade")
    private int grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("subjectName")
    @JoinColumn(name = "subject_name")
    private Subject subject;

}

