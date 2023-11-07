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
import java.util.HashMap;
import java.util.Map;

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
            float avgGrade;
            ArrayList<Person> personList = new ArrayList<>();
            Map<String, Integer> gradeList;
            String [] headerLine = reader.readLine().split(",");

            int i;
            while ((line = reader.readLine()) != null) {
                gradeList = new HashMap<>();
                avgGrade = 0;
                String[] params = line.split(",");
                for (i = 4; i < params.length; i++) {
                    gradeList.put(headerLine[i], Integer.valueOf(params[i]));
                    avgGrade += Integer.parseInt(params[i]);
                }
                avgGrade /= (i-4);
                Person person = new Person(params[0], params[1], Integer.parseInt(params[2]), Integer.parseInt(params[3]), gradeList, avgGrade);
                personList.add(person);
            }
            return personList;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

}
