package com.rtk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "subjects")
public class Subject {

    @Id
    @Column(name = "subject_name")
    private String subjectName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
    private List<Grade> grades;

}
