package com.rtk.services;

import com.rtk.dto.AverageGradeDTO;
import com.rtk.entity.Grade;
import com.rtk.entity.GradeId;
import com.rtk.entity.Student;
import com.rtk.repository.GradeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GradeService {

    @Autowired
    private GradeRepo gradeRepo;

    public List<AverageGradeDTO> getAverageGradesByGroup(int groupNumber) {

        List<AverageGradeDTO> averageGrades = new ArrayList<>();

        List<Grade> grades = gradeRepo.findByStudent_GroupNumber(groupNumber);

        // вычисление средних оценок для каждого ученика
        Map<Student, Double> averageGradeMap = new HashMap<>();
        for (Grade grade : grades) {
            Student student = grade.getStudent();
            double currentAverage = averageGradeMap.getOrDefault(student, 0.0);
            currentAverage += grade.getGrade();
            averageGradeMap.put(student, currentAverage);
        }

        for (Map.Entry<Student, Double> entry : averageGradeMap.entrySet()) {
            Student student = entry.getKey();
            double averageGrade = entry.getValue() / student.getGrades().size();
            averageGrades.add(new AverageGradeDTO(student.getId(), student.getFirstName(), student.getLastName(), averageGrade));
        }

        return averageGrades;
    }

    public void updateGrade(int studentId, String subjectName, int newGrade) {
        Optional<Grade> optionalGrade = gradeRepo.findById(new GradeId(studentId, subjectName));

        if (optionalGrade.isPresent()) {
            Grade grade = optionalGrade.get();
            grade.setGrade(newGrade);
            gradeRepo.save(grade);
        } else {
            throw new NoSuchElementException();
        }
    }
}
