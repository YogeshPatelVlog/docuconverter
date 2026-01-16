package com.converter.docconverter.controller;

import com.converter.docconverter.service.WordToPdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping("/word-to-pdf")
public class WordToPdfController {

    @Autowired
    private WordToPdfService service;

    @GetMapping
    public String page() {
        return "word-to-pdf";
    }

    @PostMapping("/convert")
    public ResponseEntity<FileSystemResource> convert(
            @RequestParam("file") MultipartFile file) throws Exception {

        File pdf = service.convertWordToPdf(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=output.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(new FileSystemResource(pdf));
    }
}
