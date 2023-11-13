package com.rtk.loaders;

import com.rtk.dto.GradeDTO;
import com.rtk.dto.StudentWithGradesDTO;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FileDataLoader implements IDataLoader<StudentWithGradesDTO> {

    String path = "students.csv";

    @Override
    public List<StudentWithGradesDTO> loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            List<StudentWithGradesDTO> studentList = new ArrayList<>();
            List<GradeDTO> gradeList = new ArrayList<>();
            String[] subjects = new String[6];

            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                String[] params = line.split(",");
                if (isFirstLine) {
                    subjects = Arrays.copyOfRange(params, 4, params.length);
                    isFirstLine = false;
                } else {
                    gradeList.clear();
                    for (int i = 4; i < params.length; i++) {
                        GradeDTO grade = new GradeDTO();
                        grade.setSubjectName(subjects[i - 4]);
                        grade.setGrade(Integer.parseInt(params[i]));
                        gradeList.add(grade);
                    }
                    StudentWithGradesDTO student = new StudentWithGradesDTO();
                    student.setLastName(params[0]);
                    student.setFirstName(params[1]);
                    student.setAge(Integer.parseInt(params[2]));
                    student.setGroupNumber(Integer.parseInt(params[3]));
                    student.setGrades(gradeList);
                    studentList.add(student);
                }
            }
            return studentList;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
