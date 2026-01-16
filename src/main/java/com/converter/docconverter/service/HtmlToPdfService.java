package com.converter.docconverter.service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class HtmlToPdfService {

    public File convertHtmlToPdf(String htmlContent) throws IOException, DocumentException {
        File pdfFile = File.createTempFile("html-to-pdf-", ".pdf");

        try (OutputStream os = new FileOutputStream(pdfFile)) {
            Document document = new Document();
            PdfWriter.getInstance(document, os);
            document.open();

            // Parse HTML content
            HTMLWorker htmlWorker = new HTMLWorker(document);
            htmlWorker.parse(new StringReader(htmlContent));

            document.close();
        }

        return pdfFile;
    }
}
