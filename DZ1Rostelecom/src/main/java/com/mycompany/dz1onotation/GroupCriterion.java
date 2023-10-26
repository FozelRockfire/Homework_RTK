/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.dz1onotation;

/**
 *
 * @author Ilya Popov
 * @param <Person>
 * @param <Integer>
 */
@FunctionalInterface
public interface GroupCriterion<Person, Integer> {

    int getGroupKey(Person person);
}
