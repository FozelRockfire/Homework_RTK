/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dz1onotation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class StudentService {

    private final IDataLoader dataLoader;

    public StudentService(IDataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public ArrayList<? extends DataGroups> groupsInsertData(Object data, ArrayList<? extends DataGroups> groups) {
        List<Person> personList;
        personList = dataLoader.LoadData(data);
        for (Person person : personList) {
            for (DataGroups group : groups) {
                group.addPerson(person);
            }
        }
        return groups;
    }
    // Добавьте методы для обработки данных, например, для вычисления статистики.
}
