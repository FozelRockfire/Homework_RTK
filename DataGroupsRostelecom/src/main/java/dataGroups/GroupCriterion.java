/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dataGroups;

/**
 *
 * @author Ilya Popov
 * @param <T>
 */
@FunctionalInterface
public interface GroupCriterion<T> {

    int getGroupKey(T person);
}
