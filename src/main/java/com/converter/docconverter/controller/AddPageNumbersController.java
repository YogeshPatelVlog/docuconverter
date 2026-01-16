//package com.converter.docconverter.controller;
//
//import com.converter.docconverter.service.AddPageNumbersService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//@Controller
//@RequestMapping("/add-page-numbers")
//public class AddPageNumbersController {
//
//    @Autowired
//    private AddPageNumbersService service;
//
//    @GetMapping
//    public String page() {
//        return "add-page-numbers"; // Thymeleaf HTML file
//    }
//
//    @PostMapping("/convert")
//    public ResponseEntity<InputStreamResource> convert(@RequestParam("file") MultipartFile file) throws IOException {
//        File output = service.addPageNumbers(file);
//
//        InputStreamResource resource = new InputStreamResource(new FileInputStream(output));
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=paged.pdf")
//                .contentType(MediaType.APPLICATION_PDF)
//                .contentLength(output.length())
//                .body(resource);
//    }
//}
