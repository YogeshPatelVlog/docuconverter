package com.converter.docconverter.controller;

import com.converter.docconverter.service.HtmlToPdfService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;

@Controller
public class HtmlToPdfController {

    @Autowired
    private HtmlToPdfService htmlToPdfService;

    @GetMapping("/html-to-pdf")
    public String htmlToPdfPage() {
        return "html-to-pdf";
    }

    @PostMapping("/html-to-pdf/convert")
    public ResponseEntity<InputStreamResource> convertHtmlToPdf(@RequestParam("htmlContent") String htmlContent) throws IOException, DocumentException {
        File pdfFile = htmlToPdfService.convertHtmlToPdf(htmlContent);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(pdfFile));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=converted.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdfFile.length())
                .body(resource);
    }
}
