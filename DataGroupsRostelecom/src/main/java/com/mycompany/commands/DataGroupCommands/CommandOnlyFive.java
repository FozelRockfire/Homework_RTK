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
        int sum;
        int count;
        for (int age = 14; age <= 18; age++) {
            for (Person person : studentService.dataGroups[1].getPersons(age)) {
                sum = 0;
                for (count = 0; count < person.getGradeList().length; count++) {
                    sum += person.getGradeList()[count];
                }
                if ((double) sum / (count) == 5.00) {
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
