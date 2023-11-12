package com.rtk.controller;

import com.rtk.dto.StudentWithGradesDTO;
import com.rtk.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<String> addStudentWithGrades(@RequestBody StudentWithGradesDTO studentDTO) {
        try {
            studentService.addStudentWithGrades(studentDTO);
            return ResponseEntity.ok().body("Студент добавлен в БД");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body("Ошибка при добавлении студента: " + e.getMessage());
        }
    }

}
