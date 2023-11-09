package com.rtk.controller;

import com.rtk.entity.GradeEntity;
import com.rtk.entity.StudentEntity;
import com.rtk.entity.SubjectEntity;
import com.rtk.repository.StudentRepo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private StudentRepo studentRepo;
    @PostMapping("/")
    public ResponseEntity<String> addStudent(@RequestBody StudentEntity student){
        try {
            studentRepo.save(student);
            return ResponseEntity.ok().body("Студент добавлен");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка обработки запроса");
        }
    }

    @GetMapping("/")
    public ResponseEntity getStudents(){
        try {
            return ResponseEntity.ok().body("Работает");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
