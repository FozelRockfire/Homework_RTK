package com.rtk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGradeRequestDTO {

    private int studentId;
    private String subjectName;
    private int newGrade;

}
