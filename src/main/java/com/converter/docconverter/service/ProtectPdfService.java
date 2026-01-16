//package com.converter.docconverter.service;
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
//import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import static org.apache.pdfbox.pdmodel.PDDocument.*;
//
//@Service
//public class ProtectPdfService {
//
//    public File protectPdf(MultipartFile file, String password) throws IOException {
//        // Load PDF
//        PDDocument document = PDDocument.load(file.getInputStream());
//
//        // Set owner and user password
//        String ownerPassword = password; // owner can modify
//        String userPassword = password;  // user can open
//
//        // Set permissions (allow print, copy content etc.)
//        StandardProtectionPolicy spp = new StandardProtectionPolicy(ownerPassword, userPassword, null);
//        spp.setEncryptionKeyLength(128);
//        spp.setPermissions(new AccessPermission());
//
//        // Apply protection
//        document.protect(spp);
//
//        // Save new PDF
//        File protectedFile = File.createTempFile("protected-", ".pdf");
//        document.save(new FileOutputStream(protectedFile));
//        document.close();
//
//        return protectedFile;
//    }
//}
