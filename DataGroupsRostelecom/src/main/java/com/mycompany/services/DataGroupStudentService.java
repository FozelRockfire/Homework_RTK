/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.mycompany.dataGroups.DataGroup;
import com.mycompany.dataGroups.GroupCriterion;
import com.mycompany.loaders.IDataLoader;
import com.mycompany.DTO.Person;

import java.util.List;

/**
 * @author Ilya Popov
 */
public class DataGroupStudentService {

    List<Person> personList;
    public DataGroup dataGroup;

    public DataGroupStudentService(IDataLoader dataLoader) {
        personList = dataLoader.LoadData();
    }

    public void addPersonList(GroupCriterion<Person> groupCriterion) {
        dataGroup = new DataGroup(groupCriterion);
        for (Person person : personList) {
            dataGroup.addPerson(person);
        }
    }

}
