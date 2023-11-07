package com.mycompany.DTO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Map;

/**
 *
 * @author Ilya Popov
 */
public class Person {

    private final String surname;
    private final String name;
    private final int age;
    private final int group;

    private float avgGrade;

    private final Map<String, Integer> gradeList;

    public Person(String surname, String name, int age, int group, Map<String, Integer> gradeList, float avgGrade) {
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.group = group;
        this.gradeList = gradeList;
        this.avgGrade = avgGrade;
    }

    public float getAvgGrade() {
        return avgGrade;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getGroup() {
        return group;
    }

    public Map<String, Integer> getGradeList() {
        return gradeList;
    }

    public void setAvgGrade(float avgGrade) {
        this.avgGrade = avgGrade;
    }
}
