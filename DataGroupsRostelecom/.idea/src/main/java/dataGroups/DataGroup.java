/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataGroups;

import DTO.Person;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Ilya Popov
 */
public class DataGroup {

    private final GroupCriterion<Person> criterion;
    int TOTAL_BASKETS = 16;

    private LinkedList<Person>[] baskets = new LinkedList[TOTAL_BASKETS];

    public DataGroup(GroupCriterion<Person> criterion) {
        this.criterion = criterion;
        for (int i = 0; i < TOTAL_BASKETS; i++) {
            baskets[i] = new LinkedList<>();
        }
    }

    public void addPerson(Person person) {
        int key = criterion.getGroupKey(person) - 1;
        while (key >= TOTAL_BASKETS) {
            baskets = Arrays.copyOf(baskets, TOTAL_BASKETS * 2);
            for (int i = TOTAL_BASKETS; i < (TOTAL_BASKETS * 2); i++) {
                baskets[i] = new LinkedList<>();
            }
            TOTAL_BASKETS = (TOTAL_BASKETS * 2);
        }
        baskets[key].add(person);
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
