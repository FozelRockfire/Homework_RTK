/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dz1onotation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author User
 */
public class onlyFiveCommand implements Command {
    private final StudentService studentService;

    public onlyFiveCommand(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void execute() {
        if (studentService.personList.isEmpty()) {
            studentService.loadPersons(); // Загрузить данные, если не загружены
        }
        GroupCriterion<Person, Integer> ageCriterion = person -> person.getAge();
        GroupData ageDataGroup = new GroupData(ageCriterion);
        
        for (Person person: studentService.personList){
            ageDataGroup.addPerson(person);
        }
        System.out.println("Задание 2: Поиск всех отличников, старше 14 лет");
        ArrayList<String> honorslist = new ArrayList();
        // Используем группировку данных по возрасту, поскольку необходимо просматривать только учеников старше 14 лет
        // Доступ к списку учеников одного возраста, осуществляется за O(1)
        int sum;
        int count;
        for (int age = 14; age <= 18; age++) {
            for (Person person : ageDataGroup.getPersons(age)) {
                sum = 0;
                for (count = 0; count < person.getGradeList().length; count++) {
                    sum += person.getGradeList()[count];
                }
                if (sum / (count) == 5.00) {
                    honorslist.add(person.getSurname() + " " + person.getName());
                }
            }
        }
        if (!honorslist.isEmpty()) {
            System.out.println("Список отличников старше 14 лет:" + Arrays.toString(honorslist.toArray()));
        } else {
            System.out.println("Отличников старше 14 лет не найдены");
        }
    }
}
