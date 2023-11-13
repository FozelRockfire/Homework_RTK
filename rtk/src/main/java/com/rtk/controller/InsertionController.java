package com.rtk.controller;

import com.rtk.dto.StudentWithGradesDTO;
import com.rtk.services.InsertionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insertion")
public class InsertionController {

    @Autowired
    private InsertionService insertionService;

    @PostMapping("/load")
    public ResponseEntity<String> loadFileData() {
        System.out.println("Загрузка началась");
        insertionService.loadDBFromCSV();
        return ResponseEntity.ok("Загрузка данных из БД прошла успешно");
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStudentWithGrades(@RequestBody StudentWithGradesDTO studentDTO) {
        try {
            insertionService.addStudentWithGrades(studentDTO);
            return ResponseEntity.ok().body("Студент добавлен в БД");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body("Ошибка при добавлении студента: " + e.getMessage());
        }
    }
}

