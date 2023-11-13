package com.rtk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AverageGradeDTO {
    private int studentId;
    private String firstName;
    private String lastName;
    private double averageGrade;
}

