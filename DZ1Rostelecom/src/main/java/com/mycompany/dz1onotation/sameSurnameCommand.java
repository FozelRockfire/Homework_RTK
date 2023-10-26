/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dz1onotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class sameSurnameCommand implements Command {

    private final StudentService studentService;

    public sameSurnameCommand(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void execute() {
        if (studentService.personList.isEmpty()) {
            studentService.loadPersons(); // Загрузить данные, если не загружены
        }
        GroupCriterion<Person, Integer> surnameCriterion = person -> (int) person.getSurname().charAt(0) - 1039;
        GroupData surnameDataGroup = new GroupData(surnameCriterion);

        for (Person person : studentService.personList) {
            surnameDataGroup.addPerson(person);
        }

        System.out.println("Задание 3: Поиск учеников по фамилии");
        ArrayList<String> personsBySurnameList = new ArrayList();
        // Используем группировку данных по первой букве фамилии, поскольку необходимо просматривать конкретную фамилию
        // Доступ к списку учеников с одинаковой первой буквой в фамилии, осуществляется за O(1)
        Scanner sc = new Scanner(System.in, "windows-1251");
        System.out.print("Введите фамилию для поиска: ");
        String surname = sc.nextLine();
        String firstLetter = surname.substring(0, 1).toUpperCase();
        try {
            for (Person person : surnameDataGroup.getPersons(firstLetter)) {
                if (person.getSurname().equals(surname)) {
                    personsBySurnameList.add(person.getSurname() + " " + person.getName());
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: Неизвестный символ");
        }
        if (!personsBySurnameList.isEmpty()) {
            System.out.println("Список учеников с фамилией " + surname + " " + Arrays.toString(personsBySurnameList.toArray()));
        } else {
            System.out.println("Ученики с фамилией " + surname + " не найдены");
        }
    }
}
