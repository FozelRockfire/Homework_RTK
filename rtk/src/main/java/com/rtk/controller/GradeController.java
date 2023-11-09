package com.rtk.controller;

import com.rtk.entity.GradeEntity;
import com.rtk.entity.GradeId;
import com.rtk.entity.StudentEntity;
import com.rtk.entity.SubjectEntity;
import com.rtk.repository.GradeRepo;
import com.rtk.repository.StudentRepo;
import com.rtk.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    private GradeRepo gradeRepo;
    @Autowired
    private SubjectRepo subjectRepo;
    @Autowired
    private StudentRepo studentRepo;

    @PutMapping("/{studentId}/{subjectName}")
    public ResponseEntity<String> updateGrade(@PathVariable Integer studentId, @PathVariable String subjectName, @RequestBody GradeEntity grade) {
        Optional<StudentEntity> student = studentRepo.findById(studentId);
        SubjectEntity subject = subjectRepo.findBySubjectName(subjectName);

        if (student.isPresent() && subject != null) {
            GradeId gradeId = new GradeId(studentId, subjectName);
            grade.setId(gradeId);
            grade.setStudent(student.get());
            grade.setSubject(subject);
            gradeRepo.save(grade);
            return ResponseEntity.ok("Оценка добавлена");
        } else {
            return ResponseEntity.badRequest().body("Студент или предмет не найдены");
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> addGrade(@RequestBody GradeEntity grade) {
        Optional<StudentEntity> student = studentRepo.findById(grade.getStudent().getId());
        SubjectEntity subject = subjectRepo.findBySubjectName(grade.getSubject().getSubjectName());

        if (student.isPresent() && subject != null) {
            grade.setStudent(student.get());
            grade.setSubject(subject);
            gradeRepo.save(grade);
            return ResponseEntity.ok("Оценка добавлена");
        } else {
            return ResponseEntity.badRequest().body("Студент или предмет не найдены");
        }
    }

}
