package com.rtk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "subjects")
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "subject_name", nullable = false, unique = true)
    private String subjectName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
    private List<GradeEntity> grades;

}
