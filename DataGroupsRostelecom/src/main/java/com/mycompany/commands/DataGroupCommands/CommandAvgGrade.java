package com.mycompany.commands.DataGroupCommands;

import com.mycompany.DTO.Person;
import com.mycompany.commands.ICommand;
import com.mycompany.dataGroups.GroupCriterion;
import com.mycompany.services.DataGroupStudentService;

public class CommandAvgGrade implements ICommand {

    DataGroupStudentService studentService;

    public CommandAvgGrade(DataGroupStudentService studentService) {
        this.studentService = studentService;
    }
    @Override
    public void execute() {
        if (studentService.dataGroups[0] == null) {
            GroupCriterion<Person> classCriterion = Person::getGroup;
            studentService.addPersonList(classCriterion, 0);
        }

        System.out.println("Задание 1: Вычисление средней оценки в старших классах (10, 11, 12)");
        int count = 0;
        float sum = 0;
        // Используем группировку данных по классам, поскольку необходимо просматривать только старшие классы
        // Доступ к списку учеников 10, 11 или 12 классов, осуществляется за O(1)
        for (int groupNumber = 10; groupNumber <= 12; groupNumber++) {
            for (Person person : studentService.dataGroups[0].getPersons(groupNumber)) {
                for (int i = 0; i < person.getGradeList().length; i++) {
                    sum += person.getGradeList()[i];
                    count++;
                }
            }
        }
        System.out.println("Средний балл всех учеников старших классов по всем оценкам: " + (sum / (count)));
    }
}
