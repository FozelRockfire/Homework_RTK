package com.rtk.controller;

import com.rtk.dto.AverageGradeResponseDTO;
import com.rtk.dto.UpdateGradeRequestDTO;
import com.rtk.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping("/average")
    public ResponseEntity<List<AverageGradeResponseDTO>> getAverageGradesByGroup(@RequestParam int groupNumber) {
        List<AverageGradeResponseDTO> averageGrades = gradeService.getAverageGradesByGroup(groupNumber);
        return ResponseEntity.ok(averageGrades);
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateGrade(@RequestBody UpdateGradeRequestDTO updateGradeRequestDTO) {
        gradeService.updateGrade(updateGradeRequestDTO);
        return ResponseEntity.ok("Оценка ученика с id " + updateGradeRequestDTO.getStudentId() +
                " по предмету " + updateGradeRequestDTO.getSubjectName() + " обновлена");
    }
}
