/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.loaders;

import com.mycompany.DTO.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Ilya Popov
 */
public class FileDataLoader implements IDataLoader {

    String path;

    public FileDataLoader(String path) {
        this.path = path;
    }

    @Override
    public ArrayList<Person> LoadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            ArrayList<Person> personList = new ArrayList<>();
            String [] subjects = Arrays.copyOfRange(reader.readLine().split(","), 4, reader.readLine().split(",").length); // строка с предметами
            while ((line = reader.readLine()) != null) {
                String[] params = line.split(",");
                int[] grades = new int[6];
                for (int i = 4; i < params.length; i++) {
                    grades[i - 4] = Integer.parseInt(params[i]);
                }
                Person person = new Person(params[0], params[1], Integer.parseInt(params[2]), Integer.parseInt(params[3]), grades, subjects);
                personList.add(person);
            }
            return personList;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

}
