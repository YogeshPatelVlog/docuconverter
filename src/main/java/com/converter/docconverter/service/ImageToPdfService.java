package com.converter.docconverter.service;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageToPdfService {

    public File convertImageToPdf(MultipartFile file) throws IOException {

        if (file.isEmpty() || !file.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("Invalid image file");
        }

        // ✅ keep original extension
        String originalName = file.getOriginalFilename();
        String extension = originalName.substring(originalName.lastIndexOf("."));

        File imageFile = File.createTempFile("upload-", extension);
        File pdfFile = File.createTempFile("output-", ".pdf");

        file.transferTo(imageFile);

        PDDocument document = new PDDocument();
        PDImageXObject image =
                PDImageXObject.createFromFile(imageFile.getAbsolutePath(), document);

        PDPage page = new PDPage(
                new PDRectangle(image.getWidth(), image.getHeight()));
        document.addPage(page);

        PDPageContentStream stream =
                new PDPageContentStream(document, page);
        stream.drawImage(image, 0, 0);
        stream.close();

        document.save(pdfFile);
        document.close();

        imageFile.delete(); // cleanup

        return pdfFile;
    }
}

