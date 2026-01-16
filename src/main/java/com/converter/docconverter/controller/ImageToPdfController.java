package com.converter.docconverter.controller;

import com.converter.docconverter.service.ImageToPdfService;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class ImageToPdfController {

    private final ImageToPdfService service;

    public ImageToPdfController(ImageToPdfService service) {
        this.service = service;
    }

    @GetMapping("/image-to-pdf")
    public String page() {
        return "image-to-pdf";
    }

    @PostMapping("/image-to-pdf")
    public ResponseEntity<Resource> convert(
            @RequestParam("file") MultipartFile file) throws IOException {

        File pdf = service.convertImageToPdf(file);

        Resource resource = new FileSystemResource(pdf);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=converted.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);

    }
}
