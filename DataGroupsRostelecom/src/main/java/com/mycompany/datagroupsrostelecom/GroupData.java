/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datagroupsrostelecom;

import java.util.LinkedList;

/**
 *
 * @author Ilya Popov
 */
public class GroupData {

    private final GroupCriterion criterion;
    int TOTAL_BASKETS = 35;

    private final LinkedList<Person>[] baskets = new LinkedList[TOTAL_BASKETS];

    public GroupData(GroupCriterion criterion) {
        this.criterion = criterion;
        for (int i = 0; i < TOTAL_BASKETS; i++) {
            baskets[i] = new LinkedList<>();
        }
    }

    public void addPerson(Person person) {
        int key = criterion.getGroupKey(person);
        baskets[key - 1].add(person);
    }

    public Person[] getPersons(Object param) {
        int key;
        try {
            key = (int) param;
        } catch (ClassCastException e) {
            key = (int) param.toString().charAt(0) - 1039;
        }
        return baskets[key - 1].toArray(new Person[baskets[key - 1].size()]);
    }

}
