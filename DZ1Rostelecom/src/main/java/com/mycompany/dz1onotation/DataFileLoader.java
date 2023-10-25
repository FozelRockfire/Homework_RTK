/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dz1onotation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class DataFileLoader implements IDataLoader {

    public void LoadData (Object data, ArrayList<? extends DataGroups> groups) {
        try (BufferedReader reader = new BufferedReader(new FileReader(data.toString()))) {
            String line;
            reader.readLine(); //пропускаем строку с заголовками
            while ((line = reader.readLine()) != null) {
                String[] params = line.split(",");
                int[] grades = new int[6];
                for (int i = 4; i < params.length; i++) {
                    grades[i - 4] = Integer.parseInt(params[i]);
                }
                Person person = new Person(params[0], params[1], Integer.parseInt(params[2]), Integer.parseInt(params[3]), grades);
                for (DataGroups group : groups) {
                    group.addPerson(person);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
