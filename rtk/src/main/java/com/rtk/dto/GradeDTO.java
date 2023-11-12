package com.rtk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GradeDTO {
    private String subjectName;
    private int grade;
}