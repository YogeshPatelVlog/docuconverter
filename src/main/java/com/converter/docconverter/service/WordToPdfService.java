package com.converter.docconverter.service;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class WordToPdfService {

    public File convertWordToPdf(MultipartFile file) throws Exception {

        // Load Word document
        XWPFDocument wordDoc = new XWPFDocument(file.getInputStream());

        // Create PDF
        File pdfFile = File.createTempFile("word-to-pdf-", ".pdf");
        Document pdfDoc = new Document();
        PdfWriter.getInstance(pdfDoc, new FileOutputStream(pdfFile));

        pdfDoc.open();

        // Copy text paragraph by paragraph
        for (XWPFParagraph para : wordDoc.getParagraphs()) {
            pdfDoc.add(new Paragraph(para.getText()));
        }

        pdfDoc.close();
        wordDoc.close();

        return pdfFile;
    }
}
