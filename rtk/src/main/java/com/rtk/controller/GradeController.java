package com.rtk.controller;

import com.rtk.dto.AverageGradeDTO;
import com.rtk.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/average")
    public ResponseEntity<List<AverageGradeDTO>> getAverageGradesByGroup(@RequestParam int groupNumber) {
        try {
            List<AverageGradeDTO> averageGrades = gradeService.getAverageGradesByGroup(groupNumber);
            return ResponseEntity.ok(averageGrades);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }
    }


    @PutMapping("/{studentId}/{subjectName}")
    public ResponseEntity<String> updateGrade(@PathVariable int studentId, @PathVariable String subjectName, @RequestParam int newGrade) {
        try {
            gradeService.updateGrade(studentId, subjectName, newGrade);
            return ResponseEntity.ok("Оценка ученика с id " + studentId + " по предмету " + subjectName + " обновлена");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ошибка: Ученик или предмет не найдены");
        }
    }
}
