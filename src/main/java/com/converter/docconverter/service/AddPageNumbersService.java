package com.converter.docconverter.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class AddPageNumbersService {

    public File addPageNumbers(MultipartFile file) throws IOException {

        // Basic validation
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("PDF file is required");
        }

        try (PDDocument document = PDDocument.load(file.getInputStream())) {

            // Remove security if present
            document.setAllSecurityToBeRemoved(true);

            int totalPages = document.getNumberOfPages();

            for (int i = 0; i < totalPages; i++) {

                PDPage page = document.getPage(i);
                PDRectangle mediaBox = page.getMediaBox();

                String text = "Page " + (i + 1) + " of " + totalPages;

                float fontSize = 12;
                float textWidth =
                        PDType1Font.HELVETICA_BOLD.getStringWidth(text) / 1000 * fontSize;

                float x = (mediaBox.getWidth() - textWidth) / 2;
                float y = 20;

                try (PDPageContentStream contentStream =
                             new PDPageContentStream(
                                     document,
                                     page,
                                     PDPageContentStream.AppendMode.APPEND,
                                     true,
                                     true)) {

                    contentStream.beginText();
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, fontSize);
                    contentStream.newLineAtOffset(x, y);
                    contentStream.showText(text);
                    contentStream.endText();
                }
            }

            File outputFile = File.createTempFile("paged-", ".pdf");
            document.save(outputFile);

            return outputFile;
        }
    }
}