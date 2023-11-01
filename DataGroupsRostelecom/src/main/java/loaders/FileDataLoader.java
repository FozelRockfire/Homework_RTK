/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loaders;

import dataGroups.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
            reader.readLine(); //пропускаем строку с заголовками
            while ((line = reader.readLine()) != null) {
                String[] params = line.split(",");
                int[] grades = new int[6];
                for (int i = 4; i < params.length; i++) {
                    grades[i - 4] = Integer.parseInt(params[i]);
                }
                Person person = new Person(params[0], params[1], Integer.parseInt(params[2]), Integer.parseInt(params[3]), grades);
                personList.add(person);
            }
            return personList;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}