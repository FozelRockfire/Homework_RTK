package com.rtk.services;

import com.rtk.dto.AverageGradeResponseDTO;
import com.rtk.dto.GradeDTO;
import com.rtk.dto.UpdateGradeRequestDTO;
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

    public List<AverageGradeResponseDTO> getAverageGradesByGroup(int groupNumber) throws NoSuchElementException {
        List<AverageGradeResponseDTO> avgGrades  = gradeRepo.calculateAverageGradesByGroup(groupNumber);
        if (!avgGrades.isEmpty()){
            return gradeRepo.calculateAverageGradesByGroup(groupNumber);
        } else {
            throw new NoSuchElementException("Не найдено учеников с оценками в указанной группе");
        }
    }

    public void updateGrade(UpdateGradeRequestDTO updateGradeRequestDTO) throws NoSuchElementException {
        Optional<Grade> optionalGrade = gradeRepo.findById(new GradeId(updateGradeRequestDTO.getStudentId(), updateGradeRequestDTO.getSubjectName()));

        if (optionalGrade.isPresent()) {
            Grade grade = optionalGrade.get();
            grade.setGrade(updateGradeRequestDTO.getNewGrade());
            gradeRepo.save(grade);
        } else {
            throw new NoSuchElementException("Не найден ученик или предмет с указанной оценкой");
        }
    }
}
