/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dz1onotation;

import java.util.LinkedList;

/**
 *
 * @author Ilya Popov
 */
public class PersonNameDataGroup {
    int TOTAL_BASKETS = 32; // корзин по количеству букв расского алфавита, исключая Ё
    
    private LinkedList<Person>[] baskets = new LinkedList[TOTAL_BASKETS];

    public PersonNameDataGroup() {
        for (int i = 0; i < TOTAL_BASKETS; i++) {
            baskets[i] = new LinkedList<>();
        }
    }

    // алгоритм добавления студента в нужную группу согласно критерию
    public void addPerson(Person person) {
        try {
            // получаем численное значение символа в виде кода ASCII
            int firstLetterCode = (int) person.getSurname().charAt(0) - 1040;
            baskets[firstLetterCode].add(person);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: Неизвестный символ");
        }
    }

    // возвращает студентов конкретной группы
    public Person[] getPersons(String firstLetter) {
        try {
            int firstLetterCode = (int) firstLetter.charAt(0) - 1040;
            return baskets[firstLetterCode].toArray(new Person[baskets[firstLetterCode].size()]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: Неизвестный символ");
        }
        return null;
    }
}
