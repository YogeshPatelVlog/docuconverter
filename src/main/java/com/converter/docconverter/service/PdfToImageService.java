package com.converter.docconverter.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class PdfToImageService {

    public File convertPdfToImages(MultipartFile file) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        if (!file.getOriginalFilename().toLowerCase().endsWith(".pdf")) {
            throw new IllegalArgumentException("Only PDF files allowed");
        }

        // Save uploaded file to temp PDF
        File tempPdf = File.createTempFile("upload-", ".pdf");
        file.transferTo(tempPdf);

        // PDFBox 2.x loading
        PDDocument document = PDDocument.load(tempPdf);
        PDFRenderer renderer = new PDFRenderer(document);

        File zipFile = File.createTempFile("pdf-images-", ".zip");
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));

        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = renderer.renderImageWithDPI(i, 300);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);

            zos.putNextEntry(new ZipEntry("page-" + (i + 1) + ".png"));
            zos.write(baos.toByteArray());
            zos.closeEntry();
        }

        zos.close();
        document.close();
        tempPdf.delete(); // cleanup

        return zipFile;
    }
}
