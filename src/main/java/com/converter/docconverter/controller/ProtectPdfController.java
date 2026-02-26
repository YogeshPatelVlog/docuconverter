package com.converter.docconverter.controller;

import com.converter.docconverter.service.ProtectPdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/protect-pdf")
public class ProtectPdfController {

    @Autowired
    private ProtectPdfService protectPdfService;

    @GetMapping
    public String page() {
        return "protect-pdf"; // Thymeleaf HTML file name
    }

    @PostMapping("/convert")
    public ResponseEntity<InputStreamResource> protectPdfFile(@RequestParam("file") MultipartFile file,
                                                              @RequestParam("password") String password) throws IOException {
        File protectedFile = protectPdfService.protectPdf(file, password);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(protectedFile));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + protectedFile.getName())
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(protectedFile.length())
                .body(resource);
    }
}
