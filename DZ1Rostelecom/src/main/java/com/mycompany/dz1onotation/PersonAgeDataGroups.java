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
class PersonAgeDataGroups implements DataGroups{
    int TOTAL_BASKETS = 19;
    
    private LinkedList<Person>[] baskets = new LinkedList[TOTAL_BASKETS];

    public PersonAgeDataGroups() {
        for (int i = 0; i < TOTAL_BASKETS; i++) {
            baskets[i] = new LinkedList<>();
        }
    }

    // Добавление студента в нужную корзину согласно возрасту
    public void addPerson(Person person) {
            baskets[person.getAge() - 1].add(person);
    }

     // возвращает всех студентов с одиноковым возрастом
    public Person[] getPersons(Object param) {
        int age = (int) param;
        return baskets[age - 1].toArray(new Person[baskets[age - 1].size()]);
    }
}
