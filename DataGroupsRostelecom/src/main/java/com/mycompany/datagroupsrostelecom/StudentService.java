/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datagroupsrostelecom;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ilya Popov
 */
public class StudentService {

    private final IDataLoader dataLoader;
    List<Person> personList = new ArrayList();

    public StudentService(IDataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public void loadPersons() {
        personList = dataLoader.LoadData();
    }

    public ArrayList<GroupData> groupsInsertData(Object data, ArrayList<GroupData> groups) {
        personList = dataLoader.LoadData();
        for (Person person : personList) {
            for (GroupData group : groups) {
                group.addPerson(person);
            }
        }
        return groups;
    }
}
