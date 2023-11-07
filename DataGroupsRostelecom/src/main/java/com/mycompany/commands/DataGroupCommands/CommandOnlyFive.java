package com.mycompany.commands.DataGroupCommands;

import com.mycompany.DTO.Person;
import com.mycompany.commands.ICommand;
import com.mycompany.dataGroups.GroupCriterion;
import com.mycompany.services.DataGroupStudentService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandOnlyFive implements ICommand {

    DataGroupStudentService studentService;

    public CommandOnlyFive(DataGroupStudentService studentService) {
        this.studentService = studentService;
    }


    @Override
    public void execute() {
        if (studentService.dataGroups[1] == null) {
            GroupCriterion<Person> ageCriterion = Person::getAge;
            studentService.addPersonList(ageCriterion, 1);
        }

        System.out.println("Задание 2: Поиск всех отличников, старше 14 лет");
        List<String> honorslist = new ArrayList<>();
        // Используем группировку данных по возрасту, поскольку необходимо просматривать только учеников старше 14 лет
        // Доступ к списку учеников одного возраста, осуществляется за O(1)
        for (int age = 14; age <= 18; age++) {
            for (Person person : studentService.dataGroups[1].getPersons(age)) {
                if (person.getAvgGrade() == 5.00) {
                    honorslist.add(person.getSurname() + " " + person.getName());
                }
            }
        }
        if (!honorslist.isEmpty()) {
            System.out.println("Список отличников старше 14 лет:" + Arrays.toString(honorslist.toArray()));
        } else {
            System.out.println("Отличники старше 14 лет не найдены");
        }
    }
}
