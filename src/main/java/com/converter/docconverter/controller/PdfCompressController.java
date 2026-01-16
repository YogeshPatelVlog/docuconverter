package com.converter.docconverter.controller;

import com.converter.docconverter.service.PdfCompressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/compress-pdf")
public class PdfCompressController {

    @Autowired
    private PdfCompressService service;

    @GetMapping
    public String page() {
        return "compress-pdf";
    }

    @PostMapping("/convert")
    public ResponseEntity<FileSystemResource> compress(
            @RequestParam("file") MultipartFile file) throws IOException, IOException {

        File compressed = service.compressPdf(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=compressed.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(new FileSystemResource(compressed));
    }
}
