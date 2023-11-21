package com.rtk.loaders;

import com.rtk.dto.GradeDTO;
import com.rtk.dto.CreateStudentWithGradesRequestDTO;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FileDataLoader implements DataLoader<CreateStudentWithGradesRequestDTO> {

    String path = "students.csv";

//    @Override
//    public List<CreateStudentWithGradesRequestDTO> loadData() {
//        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
//
//            List<CreateStudentWithGradesRequestDTO> studentList = new ArrayList<>();
//
//            //Обработка первой строки с параметрами
//            String[] firstLine = reader.readLine().split(",");
//            String[] subjects = Arrays.copyOfRange(firstLine, 4, firstLine.length);
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] params = line.split(",");
//                List<GradeDTO> gradeList = new ArrayList<>();
//                for (int i = 4; i < params.length; i++) {
//                    GradeDTO grade = new GradeDTO();
//                    grade.setSubjectName(subjects[i - 4]);
//                    grade.setGrade(Integer.parseInt(params[i]));
//                    gradeList.add(grade);
//                }
//                CreateStudentWithGradesRequestDTO student = new CreateStudentWithGradesRequestDTO();
//                student.setLastName(params[0]);
//                student.setFirstName(params[1]);
//                student.setAge(Integer.parseInt(params[2]));
//                student.setGroupNumber(Integer.parseInt(params[3]));
//                student.setGrades(gradeList);
//                studentList.add(student);
//            }
//            return studentList;
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//        return null;
//    }

    @Override
    public List<CreateStudentWithGradesRequestDTO> loadData() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));

            List<CreateStudentWithGradesRequestDTO> studentList = new ArrayList<>();

            // Обработка первой строки с параметрами
            String[] firstLine = lines.get(0).split(",");
            String[] subjects = Arrays.copyOfRange(firstLine, 4, firstLine.length);

            for (int currentLine = 1; currentLine < lines.size(); currentLine++) {
                String[] params = lines.get(currentLine).split(",");
                List<GradeDTO> gradeList = new ArrayList<>();
                for (int j = 4; j < params.length; j++) {
                    GradeDTO grade = new GradeDTO();
                    grade.setSubjectName(subjects[j - 4]);
                    grade.setGrade(Integer.parseInt(params[j]));
                    gradeList.add(grade);
                }
                CreateStudentWithGradesRequestDTO student = new CreateStudentWithGradesRequestDTO();
                student.setLastName(params[0]);
                student.setFirstName(params[1]);
                student.setAge(Integer.parseInt(params[2]));
                student.setGroupNumber(Integer.parseInt(params[3]));
                student.setGrades(gradeList);
                studentList.add(student);
            }
            return studentList;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
