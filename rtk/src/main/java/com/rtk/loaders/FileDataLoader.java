package com.rtk.loaders;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class FileDataLoader implements IDataLoader<String[]> {

    String path = "students.csv";

    @Override
    public ArrayList<String[]> LoadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            ArrayList<String[]> stringList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                stringList.add(line.split(","));
            }
            return stringList;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

}
