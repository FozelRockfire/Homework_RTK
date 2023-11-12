package com.rtk.controller;

import com.rtk.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/load")
    public ResponseEntity<String> loadFileData() {
        System.out.println("Загрузка началась");
        fileService.loadDBFromCSV();
        return ResponseEntity.ok("Data loaded successfully");
    }
}

