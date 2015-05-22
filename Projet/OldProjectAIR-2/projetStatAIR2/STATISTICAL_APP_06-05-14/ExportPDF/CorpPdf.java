package ExportPDF;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class CorpPdf {
 public PdfPTable table, tablecorel;
 public Paragraph paraHeader, paraIntro, paraResume, paraResume1, paraResume2, paraResume4, paraResume5, paraResume7, paraResume8, paraResume9, paraResume10;
 public Paragraph[] paraResume3;
 private Font titre;
 private Font contenu;
 private Font italic;
 
 public CorpPdf(String name, int nbLine, int nbCol, int nbClass, String[] nomClass, int missData) {
  
  titre = new Font(Font.FontFamily.HELVETICA, 18, Font.UNDERLINE);
  contenu = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
  italic = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC);
  String descript = "Here is a summary of the imported file and statistical calculations or graphical display shown in the active window for the export pdf";
  
  paraHeader = new Paragraph(name, titre);
  paraHeader.setAlignment(Element.ALIGN_CENTER);
  paraHeader.setSpacingAfter(30);
  
  paraIntro = new Paragraph(descript,italic);
  paraIntro.setAlignment(Element.ALIGN_CENTER);
  paraIntro.setSpacingAfter(15);
  
  paraResume = new Paragraph("1. "+" Number of Instances:"+" "+nbLine+" reparted in"+" "+nbClass+" classes",contenu);
  paraResume.setAlignment(Element.ALIGN_LEFT);
  paraResume.setSpacingAfter(15);
  
  paraResume1 = new Paragraph("2. "+" Number of Attributes:"+" "+nbCol+" numeric, predictive attributes and the class", contenu);
  paraResume1.setAlignment(Element.ALIGN_LEFT);
  paraResume1.setSpacingAfter(15);
  
  paraResume2 = new Paragraph("3. "+" Attribute information:", contenu);
  paraResume2.setAlignment(Element.ALIGN_LEFT);
  paraResume2.setSpacingAfter(10);
  
//  paraResume3 = new Paragraph[nomCol.length];
//  for(int i=0; i<nomCol.length; i++){
//   paraResume3[i] = new Paragraph(" -"+nomCol[i]);      
//   paraResume3[i].setAlignment(Element.ALIGN_LEFT);
//   paraResume3[i].setSpacingAfter(10);
//  }
  
  String classes = "";
  for(int i=0; i<nomClass.length; i++){
	  classes = classes.concat(nomClass[i]+"; ");
  }
  
  paraResume4 = new Paragraph("4. "+" Class name: "+classes, contenu);
  paraResume4.setAlignment(Element.ALIGN_LEFT);
  paraResume4.setSpacingAfter(10);
  
  
  paraResume5 = new Paragraph("5. "+" Missing Attributes:"+" "+missData, contenu);
  paraResume5.setAlignment(Element.ALIGN_LEFT);
  paraResume5.setSpacingAfter(15);
  
  paraResume7 = new Paragraph("6. "+" Table of statistical calculations", contenu);
  paraResume7.setAlignment(Element.ALIGN_LEFT);
  paraResume7.setSpacingAfter(20);
  
  paraResume8 = new Paragraph("6. "+" Histogram", contenu);
  paraResume8.setAlignment(Element.ALIGN_LEFT);
  paraResume8.setSpacingAfter(20);
  
  paraResume9 = new Paragraph("6. "+" Scatter Plot", contenu);
  paraResume9.setAlignment(Element.ALIGN_LEFT);
  paraResume9.setSpacingAfter(20);
  
  paraResume10 = new Paragraph("6. "+" Corellation Matrix", contenu);
  paraResume10.setAlignment(Element.ALIGN_LEFT);
  paraResume10.setSpacingAfter(20);
  
  table = new PdfPTable(8);
  tablecorel = new PdfPTable(5);
  
 }
 
 public void createTab(Document document, int nbColumn, double[] tabMean, double[] tabMediane, double[] tabSD,
      double[] tabMin, double[] tabMax, double[] tabVar, String[] tabMode){
	  document.open();
	  PdfPCell cell1 =  new PdfPCell(new Phrase("Columns", contenu));
	  table.addCell(cell1);
  
	  PdfPCell cell2 =  new PdfPCell(new Phrase("Mean", contenu));
	  table.addCell(cell2);

	  PdfPCell cell3 =  new PdfPCell(new Phrase("Mediane", contenu));
	  table.addCell(cell3);
	  
	  PdfPCell cell4 =  new PdfPCell(new Phrase("Stand Dev.", contenu));
	  table.addCell(cell4);
  
      PdfPCell cell5 =  new PdfPCell(new Phrase("Min", contenu));
      table.addCell(cell5);
        
      PdfPCell cell6 =  new PdfPCell(new Phrase("Max", contenu));
      table.addCell(cell6);

	  PdfPCell cell7 =  new PdfPCell(new Phrase("Variance", contenu));
	  table.addCell(cell7);
	  
	  PdfPCell cell8 =  new PdfPCell(new Phrase("Mode", contenu));
	  table.addCell(cell8);
  
  float[] columnWidths5 = {1.6f, 1.5f, 1.6f, 2.0f, 1.5f, 1.5f, 1.6f, 1.5f};
  
  for(int i=1; i<=nbColumn; i++){
	   table.addCell(new PdfPCell(new Phrase(""+i)));
	   table.addCell(new PdfPCell(new Phrase(""+tabMean[i-1])));
	   table.addCell(new PdfPCell(new Phrase(""+tabMediane[i-1])));
	   table.addCell(new PdfPCell(new Phrase(""+tabSD[i-1])));
	   table.addCell(new PdfPCell(new Phrase(""+tabMin[i-1])));
	   table.addCell(new PdfPCell(new Phrase(""+tabMax[i-1])));
	   table.addCell(new PdfPCell(new Phrase(""+tabVar[i-1])));
	   table.addCell(new PdfPCell(new Phrase(""+tabMode[i-1])));
  }
  try {
	  table.setWidths(columnWidths5);
   
  } catch (DocumentException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }
 
 public void createTabCorel(Document document, int nbcol1, int nbcol2, double[][] corelValue){
	  
		  document.open();
		  PdfPCell cell1 =  new PdfPCell(new Phrase("", contenu));
		  tablecorel.addCell(cell1);
	  
		  PdfPCell cell2 =  new PdfPCell(new Phrase("Var1", contenu));
		  tablecorel.addCell(cell2);

		  PdfPCell cell3 =  new PdfPCell(new Phrase("Var2", contenu));
		  tablecorel.addCell(cell3);
		  
		  PdfPCell cell4 =  new PdfPCell(new Phrase("Var3", contenu));
		  tablecorel.addCell(cell4);
	  
	      PdfPCell cell5 =  new PdfPCell(new Phrase("Var4", contenu));
	      tablecorel.addCell(cell5);
	        
	     
	  
	  float[] columnWidths5 = {1.6f, 1.5f, 1.6f, 2.0f, 1.5f};
	  
	  for(int i=1; i<=nbcol1; i++){
		  //for(int j=0; i<nbcol2; j++){
		   //tablecorel.addCell(new PdfPCell(new Phrase("Var"+i)));
		   tablecorel.addCell(new PdfPCell(new Phrase(""+corelValue[i][i])));
		  } 
	  
		  
	  try {
		  tablecorel.setWidths(columnWidths5);
	   
	  } catch (DocumentException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	 }
 
 public void createImage(JFreeChart chart, Document document){
	 Image image = null;
	 //document.open();
  	File filetemp = new File("Test.png");
  try {
	  ChartUtilities.saveChartAsPNG(filetemp, chart, 400, 350);
  } catch (IOException e) {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
  }
  try {
   image = Image.getInstance("Test.png");
  } catch (BadElementException e) {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
  } catch (MalformedURLException e) {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
  } catch (IOException e) {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
  }
  try {
	  document.add(image);
  } catch (DocumentException e) {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
  }
  	filetemp.delete();
  	document.close();
 }

}
