//package com.converter.docconverter.service;
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.encryption.StandardDecryptionMaterial;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//@Service
//public class UnlockPdfService {
//
//    public File unlockPdf(MultipartFile file, String password) throws IOException {
//        PDDocument document;
//
//        if (password != null && !password.isEmpty()) {
//            // Load protected PDF
//            document = PDDocument.load(file.getInputStream(), new StandardDecryptionMaterial(password));
//        } else {
//            document = PDDocument.load(file.getInputStream());
//        }
//
//        // Remove security
//        document.setAllSecurityToBeRemoved(true);
//
//        // Save unlocked PDF
//        File unlockedFile = File.createTempFile("unlocked-", ".pdf");
//        document.save(unlockedFile);
//        document.close();
//
//        return unlockedFile;
//    }
//}
