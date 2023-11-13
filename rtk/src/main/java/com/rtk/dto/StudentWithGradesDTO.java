package com.rtk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentWithGradesDTO {
    private String firstName;
    private String lastName;
    private int age;
    private int groupNumber;
    private List<GradeDTO> grades;

}

