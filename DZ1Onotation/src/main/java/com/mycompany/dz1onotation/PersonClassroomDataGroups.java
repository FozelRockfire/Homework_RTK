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
class PersonClassroomDataGroups {
    int TOTAL_BASKETS = 12;

    private LinkedList<Person>[] baskets = new LinkedList[TOTAL_BASKETS];

    public PersonClassroomDataGroups() {
        for (int i = 0; i < TOTAL_BASKETS; i++) {
            baskets[i] = new LinkedList<>();
        }
    }

    // алгоритм добавления студента в нужную группу согласно критерию
    public void addPerson(Person person) {
        baskets[person.getGroup() - 1].add(person);
    }

    // возвращает студентов конкретной группы
    public Person[] getPersons(int groupNum) {
        return baskets[groupNum - 1].toArray(new Person[baskets[groupNum - 1].size()]);
    }
}
