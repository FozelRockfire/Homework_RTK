/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.dz1onotation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Ilya Popov
 */
public class DZ1Rostelecom {

    public static void main(String[] args) {
        GroupCriterion<Person, Integer> ageCriterion = person -> person.getAge();
        GroupCriterion<Person, Integer> classCriterion = person -> person.getGroup();
        GroupCriterion<Person, Integer> surnameCriterion = person -> (int) person.getSurname().charAt(0) - 1039;
        
        GroupData ageDataGroup = new GroupData(ageCriterion);
        GroupData classDataGroup = new GroupData(classCriterion);
        GroupData surnameDataGroup = new GroupData(surnameCriterion);
        

        
//        ArrayList<GroupData> groupList = new ArrayList<>(Arrays.asList(ageDataGroup, classDataGroup, surnameDataGroup));
        FileDataLoader loader = new FileDataLoader("students.csv");
        StudentService service = new StudentService(loader);
        CommandBuilder builder = new CommandBuilder(service);
        
        builder.createCommand("sameSurname");
//        service.groupsInsertData("students.csv", groupList);
//        //Во всех заданиях выбираем ту структуру, в которой будет производится наименьшее количество операций
//        System.out.println("Задание 1: Вычисление средней оценки в старших классах (10, 11, 12)");
//        int count = 0;
//        float sum = 0;
//        // Используем группировку данных по классам, поскольку необходимо просматривать только старшие классы
//        // Доступ к списку учеников 10, 11 или 12 классов, осуществляется за O(1)
//        for (int groupNumber = 10; groupNumber <= 12; groupNumber++) {
//            for (Person person : classDataGroup.getPersons(groupNumber)) {
//                for (int i = 0; i < person.getGradeList().length; i++) {
//                    sum += person.getGradeList()[i];
//                    count++;
//                }
//            }
//        }
//        System.out.println("Средний балл всех учеников старших классов по всем оценкам: " + (sum / (count)));
//
//        System.out.println("Задание 2: Поиск всех отличников, старше 14 лет");
//        ArrayList<String> honorslist = new ArrayList();
//        // Используем группировку данных по возрасту, поскольку необходимо просматривать только учеников старше 14 лет
//        // Доступ к списку учеников одного возраста, осуществляется за O(1)
//        for (int age = 14; age <= 18; age++) {
//            for (Person person : ageDataGroup.getPersons(age)) {
//                sum = 0;
//                for (count = 0; count < person.getGradeList().length; count++) {
//                    sum += person.getGradeList()[count];
//                }
//                if (sum / (count) == 5.00) {
//                    honorslist.add(person.getSurname() + " " + person.getName());
//                }
//            }
//        }
//        if (!honorslist.isEmpty()) {
//            System.out.println("Список отличников старше 14 лет:" + Arrays.toString(honorslist.toArray()));
//        } else {
//            System.out.println("Отличников старше 14 лет не найдены");
//        }
//
//        System.out.println("Задание 3: Поиск учеников по фамилии");
//        ArrayList<String> personsBySurnameList = new ArrayList();
//        // Используем группировку данных по первой букве фамилии, поскольку необходимо просматривать конкретную фамилию
//        // Доступ к списку учеников с одинаковой первой буквой в фамилии, осуществляется за O(1)
//        if (args.length > 0) {
//            String Surname = args[0];
//            String firstLetter = Surname.substring(0, 1).toUpperCase();
//            try {
//                for (Person person : surnameDataGroup.getPersons(firstLetter)) {
//                    if (person.getSurname().equals(Surname)) {
//                        personsBySurnameList.add(person.getSurname() + " " + person.getName());
//                    }
//                }
//            } catch (ArrayIndexOutOfBoundsException e) {
//                System.out.println("Ошибка: Неизвестный символ");
//            }
//            if (!personsBySurnameList.isEmpty()) {
//                System.out.println("Список учеников с фамилией " + Surname + " " + Arrays.toString(personsBySurnameList.toArray()));
//            } else {
//                System.out.println("Ученики с фамилией " + Surname + " не найдены");
//            }
//        } else {
//            System.out.println("Для поиска учеников по фамилии необходимо указать первым аргументов фамилию ученика, например\njava -jar DZ1Rostelecom-1.0-SNAPSHOT.jar Яшина");
//        }

    }
}
