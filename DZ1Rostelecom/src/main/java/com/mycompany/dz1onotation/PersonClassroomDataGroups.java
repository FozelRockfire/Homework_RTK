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
class PersonClassroomDataGroups implements DataGroups {

    int TOTAL_BASKETS = 12;

    private final LinkedList<Person>[] baskets = new LinkedList[TOTAL_BASKETS];

    public PersonClassroomDataGroups() {
        for (int i = 0; i < TOTAL_BASKETS; i++) {
            baskets[i] = new LinkedList<>();
        }
    }

    // Добавление студента в нужную корзину согласно номеру группы
    public void addPerson(Person person) {
        baskets[person.getGroup() - 1].add(person);
    }

    // возвращает всех студентов с одиноковым номером группы
    public Person[] getPersons(Object param) {
        int groupNum = (int) param;
        return baskets[groupNum - 1].toArray(new Person[baskets[groupNum - 1].size()]);
    }
}
