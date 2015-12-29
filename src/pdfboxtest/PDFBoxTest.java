/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfboxtest;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import javax.imageio.ImageIO;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;




/**
 *
 * @author Matthew
 */
public class PDFBoxTest{

    /**
     */
   public static void main (String[] args) throws Exception {
        
        GetFundamentals go = new GetFundamentals();
        // Create a document and add a page to it
        go.ticker = "UAL";
        String outputFileName = go.ticker+".pdf";
        PDDocument document = new PDDocument();
        PDPage page1 = new PDPage(PDPage.PAGE_SIZE_LETTER);
        PDPage page2 = new PDPage(PDPage.PAGE_SIZE_LETTER);
        PDPage page3 = new PDPage(PDPage.PAGE_SIZE_LETTER);
        PDPage page4 = new PDPage(PDPage.PAGE_SIZE_LETTER);
            // PDPage.PAGE_SIZE_LETTER is also possible
            // rect can be used to get the page width and height
        document.addPage(page1);
        document.addPage(page2);
        document.addPage(page3);
        document.addPage(page4);

        // Create a new font object selecting one of the PDF base fonts
        PDFont fontPlain = PDType1Font.HELVETICA_BOLD;
 

        // Start a new content stream which will "hold" the to be created content
        PDPageContentStream cos = new PDPageContentStream(document, page1);

        // Define a text content stream using the selected font, move the cursor and draw some text
        setupDefault(cos, fontPlain, document,go);
        setupP1(cos, fontPlain, document);
        cos.close();
         cos = new PDPageContentStream(document, page2);
         setupDefault(cos, fontPlain, document,go);
         setupP2(cos, fontPlain, document);
         cos.close();
         cos = new PDPageContentStream(document, page3);
         setupDefault(cos, fontPlain, document,go);
         setupP3(cos, fontPlain, document);
         cos.close();
         cos = new PDPageContentStream(document, page4);
         setupDefault(cos, fontPlain, document,go);
         setupP4(cos, fontPlain, document);
         cos.close();
        
        // Make sure that the content stream is closed:

        // Save the results and ensure that the document is properly closed:
        document.save(outputFileName);
        document.close();
    }
   
   public static String getDate()
   {
       DateFormat currentDate = new SimpleDateFormat("dd/MM/yy");
       Date dateObject = new Date();
       char day;
       String date = currentDate.format(dateObject);
       String[] dateReturn = date.split("/");
       day = dateReturn[0].charAt(1);
       
       return dateName(dateReturn[1]) + dateNumber(dateReturn[0], day) +" 20" + dateReturn[2];
   }
   public static String dateName(String name)
   {
       switch (name)
       {
           case "01":
               return "January ";
           case "02":
               return "February ";
           case "03":
               return "March ";
           case "04":
               return "April ";
           case "05":
               return "May ";
           case "06":
               return "June ";
           case "07":
               return "July ";
           case "08":
               return "August ";
           case "09":
               return "September ";
           case "10":
               return "October ";
           case "11":
               return "November ";
           case "12":
               return "December ";
       }
       return "ERROR";
   }
   
   public static String dateNumber(String number, char day)
   {
       switch (day)
       {
           case '1':
               return number + "st,";
           case '2':
               return number + "nd,";
           case '3':
               return number + "rd,";
           default: 
               return number + "th,";
                   
       }
   }
   public static PDPageContentStream setupDefault(PDPageContentStream cos, PDFont fontPlain, PDDocument document, GetFundamentals go) throws Exception
   {
       cos.setLineWidth(50);
        cos.addLine(20, 745, 590, 745);
        cos.setStrokingColor(Color.black);
        cos.closeAndStroke();

        
        cos.beginText();
        cos.setFont(fontPlain, 16);
        cos.setNonStrokingColor(Color.orange);
        cos.moveTextPositionByAmount(485, 747);
        cos.drawString("Trader");
        cos.endText();

        cos.beginText();
        cos.setFont(fontPlain, 16);
        cos.setNonStrokingColor(Color.LIGHT_GRAY);
        cos.moveTextPositionByAmount(535, 747);
        cos.drawString("Mob");
        cos.endText();
        
        cos.beginText();
        cos.setFont(fontPlain, 12);
        cos.setNonStrokingColor(Color.LIGHT_GRAY);
        cos.moveTextPositionByAmount(448, 732);
        cos.drawString(getDate());
        cos.endText();
        
        cos.beginText();
        cos.setFont(fontPlain, 14);
        cos.setNonStrokingColor(Color.orange);
        cos.moveTextPositionByAmount(45, 747);
        cos.drawString(go.get(go.ticker,"Name") + " [" + go.get(go.ticker,"Symbol") + "]");
        cos.endText();
        
        cos.beginText();
        cos.setFont(fontPlain, 11);
        cos.setNonStrokingColor(Color.orange);
        cos.moveTextPositionByAmount(45, 732);
        cos.drawString( go.get(go.ticker,"Sector") + "->" + go.get(go.ticker, "Industry"));
        cos.endText();
        
         // footer default text
        cos.setLineWidth(30);
        cos.addLine(20, 35, 590, 35);
        cos.setStrokingColor(Color.black);
        cos.closeAndStroke();  
        
        
        cos.beginText();
        cos.setFont(fontPlain, 9);
        cos.setNonStrokingColor(Color.orange);
        cos.moveTextPositionByAmount(45, 31);
        cos.drawString("© Copyright Matthew Gaiser                               tradermob.com");
        cos.endText();
        
        return cos;
   }
   public static PDPageContentStream setupRatingBars(PDPageContentStream cos, PDDocument document, int numberOfBars, int height, String [] headers, String [][] titles, int[] rowCount) throws Exception
   {
       PDFont fontPlain = PDType1Font.HELVETICA;
       int initvarMove = (220-numberOfBars*20)/(numberOfBars + 2) + 20;
       int varMove = height - initvarMove;
       for (int count = 0; count < numberOfBars; count++)
       {
        
        cos.beginText();
        cos.setFont(fontPlain, 12);
        cos.setNonStrokingColor(Color.black);
        cos.moveTextPositionByAmount(45, varMove+18);
        cos.drawString(headers[count]);
        cos.endText();

        // red portion of rating bar
        Color red = new Color(176,23,31);
        cos.setLineWidth(15);
        cos.addLine(35,varMove, 145, varMove);
        cos.setStrokingColor(red);
        cos.closeAndStroke(); 
        
        
        // yellow portion of rating bar
        cos.setLineWidth(15);
        cos.addLine(147, varMove, 257, varMove);
        cos.setStrokingColor(Color.orange);
        cos.closeAndStroke(); 
        
        
        // green portion of rating bar
        Color green = new Color (0,139,69);
        cos.setLineWidth(15);
        cos.addLine(259, varMove, 369, varMove);
        cos.setStrokingColor(green);
        cos.closeAndStroke(); 
        
       
       cos.beginText();
       cos.setFont(fontPlain, 8);
       cos.setNonStrokingColor(Color.black);
       cos.moveTextPositionByAmount(370, varMove + 15);
       cos.drawString("Weighting");
       cos.endText();
        
       
       
        cos.setLineWidth(25);
        cos.addLine(375, varMove, 400, varMove);
        cos.setStrokingColor(Color.BLACK);
        cos.closeAndStroke(); 
        
       cos.beginText();
       cos.setFont(fontPlain, 12);
       cos.setNonStrokingColor(Color.ORANGE);
       cos.moveTextPositionByAmount(377, varMove-5);
       cos.drawString("N/A");
       cos.endText();
        
        int horizontalMove = 410;
        int horizMoveFactor  = (180-rowCount[count]*45)/(rowCount[count]+1) + 45;
        for (int i = 0; i < rowCount[count]; i++)
        {
        cos.beginText();
        cos.setFont(fontPlain, 6);
        cos.setNonStrokingColor(Color.black);
        cos.moveTextPositionByAmount(horizontalMove + horizMoveFactor*i, varMove+10);
        cos.drawString(titles[count][i]);
        cos.endText();
            
        }
        
        varMove = varMove - initvarMove;
        
       }
       
       
       
       
       return cos;
   }
   public static PDPageContentStream setupOverallBars(PDPageContentStream cos, PDDocument document, int numberOfBars)
   {
       return cos;
   }
    
   public static PDPageContentStream setupP1 (PDPageContentStream cos, PDFont fontPlain, PDDocument document) throws Exception
   {
       
        
       
       
        // Price and Volume Header 
        
        cos.setLineWidth(15);
        cos.addLine(20, 702, 300, 702);
        cos.setStrokingColor(Color.black);
        cos.closeAndStroke();  
        
        cos.beginText();
        cos.setFont(fontPlain, 11);
        cos.setNonStrokingColor(Color.orange);
        cos.moveTextPositionByAmount(45, 699);
        cos.drawString("Price and Trading Volume Charts");
        cos.endText();
        HistandVolChart year1 = new HistandVolChart();
        
        // One year price and volume chart
        try {
          
            BufferedImage awtImage = ImageIO.read(new File("C:\\Users\\Matthew\\Documents\\NetBeansProjects\\PDFBoxTest\\XOM.jpg"));
            PDXObjectImage ximage = new PDPixelMap(document, awtImage);
            float scale = 0.9f; // alter this value to set the image size
            cos.drawXObject(ximage, 15, 500, ximage.getWidth()*scale, ximage.getHeight()*scale);
        } catch (FileNotFoundException e) {
            System.out.println("No image for you");
        }
        // Three year price and volume chart
        try {
          
            BufferedImage awtImage = ImageIO.read(new File("C:\\Users\\Matthew\\Documents\\NetBeansProjects\\PDFBoxTest\\XOM.jpg"));
            PDXObjectImage ximage = new PDPixelMap(document, awtImage);
            float scale = 0.9f; // alter this value to set the image size
            cos.drawXObject(ximage, 315, 500, ximage.getWidth()*scale, ximage.getHeight()*scale);
        } catch (FileNotFoundException e) {
            System.out.println("No image for you");
        }

        
        
        // Automatic Analysis Header
        
        cos.setLineWidth(15);
        cos.addLine(20, 472, 300, 472);
        cos.setStrokingColor(Color.black);
        cos.closeAndStroke();  
        
        cos.beginText();
        cos.setFont(fontPlain, 11);
        cos.setNonStrokingColor(Color.orange);
        cos.moveTextPositionByAmount(45, 469);
        cos.drawString("Automatic Analysis");
        cos.endText();
        
        // Comparison Table Header
        
        cos.setLineWidth(15);
        cos.addLine(20, 252, 300, 252);
        cos.setStrokingColor(Color.black);
        cos.closeAndStroke();  
        
        cos.beginText();
        cos.setFont(fontPlain, 11);
        cos.setNonStrokingColor(Color.orange);
        cos.moveTextPositionByAmount(45, 249);
        cos.drawString("Comparison Table");
        cos.endText();
        
        
        // Basic stats header
        
        cos.setLineWidth(15);
        cos.addLine(320, 472, 590, 472);
        cos.setStrokingColor(Color.black);
        cos.closeAndStroke();  
        
        cos.beginText();
        cos.setFont(fontPlain, 11);
        cos.setNonStrokingColor(Color.orange);
        cos.moveTextPositionByAmount(345, 469);
        cos.drawString("Basic Stats and Data");
        cos.endText();
        
       
        return cos;
   }
   public static PDPageContentStream setupP2 (PDPageContentStream cos, PDFont fontPlain, PDDocument document) throws Exception
{
    String[] headers = {"Price to Sales", "Price to Equity", "Price to Book", "Dividend"};
    String[][] titles={{"Price to Sales", "Relative to Index", "Relative to Industry Median"},
        {"Price to Earnings", "Relative to Index", "Relative to Industry"},
        {"Price to Book","Relative to Index","Relative to Industry Median"}, {"Dividend Yield","",""}};
    int [] titleCount = {2,2,3,1};

    cos.setLineWidth(15);
        cos.addLine(20, 702, 300, 702);
        cos.setStrokingColor(Color.black);
        cos.closeAndStroke();  
        
        cos.beginText();
        cos.setFont(fontPlain, 11);
        cos.setNonStrokingColor(Color.orange);
        cos.moveTextPositionByAmount(45, 699);
        cos.drawString("Relative and Overall Valuation");
        cos.endText();
        
        setupRatingBars(cos,document,4,702,headers, titles, titleCount);
        
        String [] headersMomentum = {"Relative Strength","Seasonality"};
        String[][] titles2= {{"Relative Strength Value", "Relative Strength Industry Average","",""},
            {"[xMon] Avg", "[xMon1] Avg","[xMon] Industry","[xMon1] Industry"}};
        int[] titleCount2 = {2,4}; 

         cos.setLineWidth(15);
        cos.addLine(20, 402, 300, 402);
        cos.setStrokingColor(Color.black);
        cos.closeAndStroke();  
        
        cos.beginText();
        cos.setFont(fontPlain, 11);
        cos.setNonStrokingColor(Color.orange);
        cos.moveTextPositionByAmount(45, 399);
        cos.drawString("Momentum Analysis");
        cos.endText();
        
        setupRatingBars(cos,document,2,402,headersMomentum, titles2,titleCount2);
    return cos;
}
public static PDPageContentStream setupP3 (PDPageContentStream cos, PDFont fontPlain, PDDocument document) throws Exception
{
    String[] headers = {"Magnitude of Returns", "Volatility", "Correlation","Price Point"};
    String[][] titles = {{"Best Day", "Worst Day","Best Month","Worst Month"},
        {"STD Dev 30 Day", "STD Dev 60 day","Average Daily Return",""},
        {"60 Day Correlation Industry", "60 Day Correlation Index","",""},
        {"Price Percentile","","",""}
    };
    int[] titleCount = {4,3,2,1};
        cos.setLineWidth(15);
        cos.addLine(20, 702, 300, 702);
        cos.setStrokingColor(Color.black);
        cos.closeAndStroke();  
        
        cos.beginText();
        cos.setFont(fontPlain, 11);
        cos.setNonStrokingColor(Color.orange);
        cos.moveTextPositionByAmount(45, 699);
        cos.drawString("Risk and Volatility");
        cos.endText();

        setupRatingBars(cos,document,4,702,headers,titles,titleCount);
         cos.setLineWidth(15);
        cos.addLine(20, 402, 300, 402);
        cos.setStrokingColor(Color.black);
        cos.closeAndStroke();  
        
        cos.beginText();
        cos.setFont(fontPlain, 11);
        cos.setNonStrokingColor(Color.orange);
        cos.moveTextPositionByAmount(45, 399);
        cos.drawString("Technical Indicators");
        cos.endText();
    
    return cos;
}
public static PDPageContentStream setupP4 (PDPageContentStream cos, PDFont fontPlain, PDDocument document) throws Exception
{
     cos.setLineWidth(15);
        cos.addLine(20, 702, 300, 702);
        cos.setStrokingColor(Color.black);
        cos.closeAndStroke();  
        
        cos.beginText();
        cos.setFont(fontPlain, 11);
        cos.setNonStrokingColor(Color.orange);
        cos.moveTextPositionByAmount(45, 699);
        cos.drawString("Disclosure");
        cos.endText();
        
        cos.setLineWidth(15);
        cos.addLine(20, 552, 300, 552);
        cos.setStrokingColor(Color.black);
        cos.closeAndStroke();  
        
        cos.beginText();
        cos.setFont(fontPlain, 11);
        cos.setNonStrokingColor(Color.orange);
        cos.moveTextPositionByAmount(45, 549);
        cos.drawString("Definitions");
        cos.endText();

         cos.setLineWidth(15);
        cos.addLine(20, 402, 300, 402);
        cos.setStrokingColor(Color.black);
        cos.closeAndStroke();  
        
        cos.beginText();
        cos.setFont(fontPlain, 11);
        cos.setNonStrokingColor(Color.orange);
        cos.moveTextPositionByAmount(45, 399);
        cos.drawString("Disclaimer");
        cos.endText();
    
    aboutTheAnalyst (cos, fontPlain, document);
    return cos;
}
public static PDPageContentStream disclosure (PDPageContentStream cos, PDFont fontPlain, PDDocument document) throws Exception
{
    
    return cos;
}
public static PDPageContentStream disclaimer (PDPageContentStream cos, PDFont fontPlain, PDDocument document) throws Exception
{
    
    return cos;
}
public static PDPageContentStream definitions (PDPageContentStream cos, PDFont fontPlain, PDDocument document) throws Exception
{
    
    return cos;
}
public static PDPageContentStream aboutTheAnalyst (PDPageContentStream cos, PDFont fontPlain, PDDocument document) throws Exception
{
    
    cos.setLineWidth(15);
        cos.addLine(20, 230, 300, 230);
        cos.setStrokingColor(Color.black);
        cos.closeAndStroke();  
        
        cos.beginText();
        cos.setFont(fontPlain, 11);
        cos.setNonStrokingColor(Color.orange);
        cos.moveTextPositionByAmount(45, 227);
        cos.drawString("About the Analyst");
        cos.endText();
        
    cos.setLineWidth(140);
    cos.addLine(20, 145, 590, 145);
    cos.setStrokingColor(Color.lightGray);
    cos.closeAndStroke(); 
    
    try 
    {
        BufferedImage analystPic = ImageIO.read(new File("analyst.jpg"));
        PDXObjectImage pic = new PDPixelMap(document,analystPic);
        float scale = 0.3f;
        cos.drawXObject(pic, 55, 85, pic.getWidth()*scale, pic.getHeight()*scale);
        
    }   
    
    
    catch (FileNotFoundException e)
    {
        
    }
    return cos;
}
}


