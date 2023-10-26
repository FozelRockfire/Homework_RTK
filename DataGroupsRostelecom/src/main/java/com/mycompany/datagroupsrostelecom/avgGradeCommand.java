/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datagroupsrostelecom;

/**
 *
 * @author Ilya Popov
 */
public class avgGradeCommand implements Command {

    private final StudentService studentService;

    public avgGradeCommand(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void execute() {
        if (studentService.personList.isEmpty()) {
            studentService.loadPersons(); // Загрузить данные, если не загружены
        }
        GroupCriterion<Person, Integer> classCriterion = person -> person.getGroup();
        GroupData classDataGroup = new GroupData(classCriterion);
        for (Person person : studentService.personList) {
            classDataGroup.addPerson(person);
        }
        System.out.println("Задание 1: Вычисление средней оценки в старших классах (10, 11, 12)");
        int count = 0;
        float sum = 0;
        // Используем группировку данных по классам, поскольку необходимо просматривать только старшие классы
        // Доступ к списку учеников 10, 11 или 12 классов, осуществляется за O(1)
        for (int groupNumber = 10; groupNumber <= 12; groupNumber++) {
            for (Person person : classDataGroup.getPersons(groupNumber)) {
                for (int i = 0; i < person.getGradeList().length; i++) {
                    sum += person.getGradeList()[i];
                    count++;
                }
            }
        }
        System.out.println("Средний балл всех учеников старших классов по всем оценкам: " + (sum / (count)));
    }
}
