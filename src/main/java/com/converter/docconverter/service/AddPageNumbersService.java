//package com.converter.docconverter.service;
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.common.PDRectangle;
//import org.apache.pdfbox.pdmodel.font.PDType1Font;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//@Service
//public class AddPageNumbersService {
//
//    public File addPageNumbers(MultipartFile file) throws IOException {
//        // Load the PDF
//        PDDocument document = PDDocument.load(file.getInputStream());
//
//        int totalPages = document.getNumberOfPages();
//
//        for (int i = 0; i < totalPages; i++) {
//            PDPage page = document.getPage(i);
//            PDRectangle mediaBox = page.getMediaBox();
//
//            // Create content stream to write on the page
//            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
//
//            // Page number text
//            String pageNumber = "Page " + (i + 1) + " of " + totalPages;
//
//            // Set font and position (bottom center)
//            contentStream.beginText();
//            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
//            float stringWidth = PDType1Font.HELVETICA_BOLD.getStringWidth(pageNumber) / 1000 * 12;
//            float x = (mediaBox.getWidth() - stringWidth) / 2;
//            float y = 20; // 20 units from bottom
//            contentStream.newLineAtOffset(x, y);
//            contentStream.showText(pageNumber);
//            contentStream.endText();
//
//            contentStream.close();
//        }
//
//        // Save new PDF
//        File outputFile = File.createTempFile("paged-", ".pdf");
//        document.save(new FileOutputStream(outputFile));
//        document.close();
//
//        return outputFile;
//    }
//}
