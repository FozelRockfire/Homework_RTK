package com.rtk.controller;

import com.rtk.entity.SubjectEntity;
import com.rtk.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    private SubjectRepo subjectRepo;
    @PostMapping("/")
    public ResponseEntity<String> addSubject(@RequestBody SubjectEntity subject){
        try {
            subjectRepo.save(subject);
            return ResponseEntity.ok().body("Предмет добавлен");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка обработки запроса");
        }
    }
}
