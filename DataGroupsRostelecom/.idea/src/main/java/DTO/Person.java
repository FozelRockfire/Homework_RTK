package DTO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Ilya Popov
 */
public class Person {

    private final String surname;
    private final String name;
    private final int age;
    private final int group;
    private final int[] gradeList;
    private final String[] subjectList;

    public Person(String surname, String name, int age, int group, int[] gradeList, String[] subjectList) {
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.group = group;
        this.gradeList = gradeList;
        this.subjectList = subjectList;
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

    public int[] getGradeList() {
        return gradeList;
    }

    public String[] getSubjectList() {
        return subjectList;
    }
}
