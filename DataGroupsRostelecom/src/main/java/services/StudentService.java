/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dataGroups.DataGroup;
import dataGroups.GroupCriterion;
import loaders.IDataLoader;
import dataGroups.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Ilya Popov
 */
public class StudentService {

    List<Person> personList;
    // поля групп для избежания повторного создания
    public DataGroup classDataGroup = null;
    public DataGroup ageDataGroup = null;
    public DataGroup surnameDataGroup = null;

    public StudentService(IDataLoader dataLoader) {
        personList = dataLoader.LoadData();
    }

    public DataGroup fillDataGroup(GroupCriterion<Person> groupCriterion) {
        DataGroup dataGroup = new DataGroup(groupCriterion);
        for (Person person : personList) {
            dataGroup.addPerson(person);
        }
        return dataGroup;
    }

    public void getHelp() {
        System.out.println("""
                Доступные команды:
                1) avgGrade - Вычисление средней оценки в старших классах
                2) onlyFive - Поиск всех отличников, старше 14 лет
                3) sameSurname - Поиск учеников по фамилии
                4) exit - остановка программы""");
    }

    public void avgGrade() {
        if (classDataGroup == null) {
            GroupCriterion<Person> classCriterion = Person::getGroup;
            classDataGroup = fillDataGroup(classCriterion);
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

    public void onlyFive() {
        if (ageDataGroup == null) {
            GroupCriterion<Person> ageCriterion = Person::getAge;
            ageDataGroup = fillDataGroup(ageCriterion);
        }

        System.out.println("Задание 2: Поиск всех отличников, старше 14 лет");
        List<String> honorslist = new ArrayList<>();
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

    public void sameSurname() {
        if (surnameDataGroup == null) {
            GroupCriterion<Person> surnameCriterion = person -> (int) person.getSurname().charAt(0) - 1039;
            surnameDataGroup = fillDataGroup(surnameCriterion);
        }

        System.out.println("Задание 3: Поиск учеников по фамилии");
        ArrayList<String> personsBySurnameList = new ArrayList<>();
        // Используем группировку данных по первой букве фамилии, поскольку необходимо просматривать конкретную фамилию
        // Доступ к списку учеников с одинаковой первой буквой в фамилии, осуществляется за O(1)
        Scanner sc = new Scanner(System.in);
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
