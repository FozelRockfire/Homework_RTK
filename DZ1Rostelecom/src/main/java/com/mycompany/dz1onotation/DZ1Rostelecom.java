/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.dz1onotation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Ilya Popov
 */
public class DZ1Rostelecom {

    private static void fillTables(String filePath, ArrayList<? extends DataGroups> groups) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            reader.readLine(); //пропускаем строку с заголовками
            while ((line = reader.readLine()) != null) {
                String[] params = line.split(",");
                int[] grades = new int[6];
                for (int i = 4; i < params.length; i++) {
                    grades[i - 4] = Integer.parseInt(params[i]);
                }
                Person person = new Person(params[0], params[1], Integer.parseInt(params[2]), Integer.parseInt(params[3]), grades);
                for (DataGroups group : groups) {
                    group.addPerson(person);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        PersonClassroomDataGroups personsByClass = new PersonClassroomDataGroups();
        PersonAgeDataGroups personsByAge = new PersonAgeDataGroups();
        PersonNameDataGroup personsBySurname = new PersonNameDataGroup();
        ArrayList<DataGroups> groupList = new ArrayList<>(Arrays.asList(personsByClass, personsByAge, personsBySurname));
        
        DZ1Rostelecom.fillTables("students.csv", groupList);
        //Во всех заданиях выбираем ту структуру, в которой будет производится наименьшее количество операций
        System.out.println("Задание 1: Вычисление средней оценки в старших классах (10, 11, 12)");
        int count = 0;
        float sum = 0;
        // Используем группировку данных по классам, поскольку необходимо просматривать только старшие классы
        for (int groupNumber = 10; groupNumber <= 12; groupNumber++) {
            personsByClass.getPersons(groupNumber);
            for (Person person : personsByClass.getPersons(groupNumber)) {
                for (int i = 0; i < person.getGradeList().length; i++) {
                    sum += person.getGradeList()[i];
                    count++;
                }
            }
        }
        System.out.println("Средний балл всех учеников старших классов по всем оценкам: " + (sum / (count)));

        System.out.println("Задание 2: Поиск всех отличников, старше 14 лет");
        ArrayList<String> honorslist = new ArrayList();
        // Используем группировку данных по возрасту, поскольку необходимо просматривать только учеников старше 14 лет
        for (int age = 14; age <= 18; age++) {
            personsByAge.getPersons(age);
            for (Person person : personsByAge.getPersons(age)) {
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

        System.out.println("Задание 3: Поиск учеников по фамилии");
        ArrayList<String> personsBySurnameList = new ArrayList();
        // Используем группировку данных по прервой букве фамилии, поскольку необходимо просматривать конкретную фамилию
        if (args.length > 0) {
            String Surname = args[0];
            String firstLetter = Surname.substring(0, 1);
            try {
                for (Person person : personsBySurname.getPersons(firstLetter)) {
                    if (person.getSurname().equals(Surname)) {
                        personsBySurnameList.add(person.getSurname() + " " + person.getName());
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Ошибка: Неизвестный символ");
            }
            if (!personsBySurnameList.isEmpty()) {
                System.out.println("Список учеников с фамилией " + Surname + " " + Arrays.toString(personsBySurnameList.toArray()));
            } else {
                System.out.println("Ученики с фамилией " + Surname + " не найдены");
            }
        } else {
            System.out.println("Для поиска учеников по фамилии необходимо указать первым аргументов фамилию ученика, например\njava -jar DZ1Rostelecom-1.0-SNAPSHOT.jar Яшина");
        }

    }
}
