package com.mrdongshan.itextpdf.controller;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage; 
import com.itextpdf.kernel.pdf.PdfWriter; 
import com.itextpdf.kernel.pdf.canvas.PdfCanvas; 
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Text;
//import com.itextpdf.io.font.FontConstants;
//import com.itextpdf.kernel.font.PdfFontFactory.FontConstants;

public class DrawingLine {     
   public static void main(String args[]) throws Exception {            
      // Creating a PdfWriter       
      String dest = "/Users/mrdongshan/Downloads/drawingLine.pdf";
      PdfWriter writer = new PdfWriter(dest);           
      
      // Creating a PdfDocument object       
      PdfDocument pdfDoc = new PdfDocument(writer);                   
      
      // Creating a Document object       
      Document doc = new Document(pdfDoc);

      // Creating text object
      Text text1 = new Text("Tutorialspoint");

      // Creating a new page       
      PdfPage pdfPage = pdfDoc.addNewPage();               
      
      // Creating a PdfCanvas object       
      PdfCanvas canvas = new PdfCanvas(pdfPage);              
      
      // Initial point of the line       
      canvas.moveTo(100, 300);              
      
      // Drawing the line       
      canvas.lineTo(500, 300);           
      
      // Closing the path stroke       
      canvas.closePathStroke();
      
      // Closing the document       
      doc.close();  
      
      System.out.println("Object drawn on pdf successfully");             
   }     
}