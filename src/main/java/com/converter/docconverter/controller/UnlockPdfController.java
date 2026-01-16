//package com.converter.docconverter.controller;
//
//import com.converter.docconverter.service.UnlockPdfService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//@Controller
//public class UnlockPdfController {
//
//    @Autowired
//    private UnlockPdfService unlockPdfService;
//
//    @GetMapping("/unlock-pdf")
//    public String unlockPdfPage() {
//        return "unlock-pdf";
//    }
//
//    @PostMapping("/unlock-pdf/convert")
//    public ResponseEntity<InputStreamResource> unlockPdf(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("password") String password
//    ) throws IOException {
//        File unlockedFile = unlockPdfService.unlockPdf(file, password);
//
//        InputStreamResource resource = new InputStreamResource(new FileInputStream(unlockedFile));
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=unlocked.pdf")
//                .contentType(MediaType.APPLICATION_PDF)
//                .contentLength(unlockedFile.length())
//                .body(resource);
//    }
//}
