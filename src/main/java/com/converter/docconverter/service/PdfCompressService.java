package com.converter.docconverter.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class PdfCompressService {

    public File compressPdf(MultipartFile file) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        if (!file.getOriginalFilename().toLowerCase().endsWith(".pdf")) {
            throw new IllegalArgumentException("Only PDF files allowed");
        }

        // ✅ Load PDF from MultipartFile InputStream
        InputStream pdfInputStream = file.getInputStream();
        PDDocument document = PDDocument.load(pdfInputStream);

        // Remove unused objects (optional)
        document.setAllSecurityToBeRemoved(true);

        // Save compressed PDF to temp file
        File compressedFile = File.createTempFile("compressed-", ".pdf");

        // Save PDF (PDFBox 2.x does basic compression automatically)
        document.save(compressedFile);
        document.close();

        return compressedFile;
    }
}
