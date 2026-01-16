package com.converter.docconverter.controller;

import com.converter.docconverter.service.PdfToImageService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class PdfToImageController {

    private final PdfToImageService pdfToImageService;

    public PdfToImageController(PdfToImageService pdfToImageService) {
        this.pdfToImageService = pdfToImageService;
    }

    @GetMapping("/pdf-to-image")
    public String page() {
        return "pdf-to-image"; // create this HTML page
    }

    @PostMapping("/pdf-to-image")
    public ResponseEntity<Resource> convert(@RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty() || !file.getContentType().equals("application/pdf")) {
            throw new IllegalArgumentException("Invalid file type. Please upload a PDF.");
        }

        File zipFile = pdfToImageService.convertPdfToImages(file); // returns ZIP of images

        Resource resource = new FileSystemResource(zipFile);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + zipFile.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
