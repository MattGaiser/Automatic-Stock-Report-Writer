/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfboxtest;

import java.io.*;
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartUtilities; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 *
 * @author Matthew
 */
public class HistandVolChart {
    

    public File chart( String ticker) throws Exception
    {
        BufferedReader reader= null;
        String splitBy = ",";
        String line = "Price";
        double stockPrice;
        File file = new File("C:\\Users\\Matthew\\Documents\\NetBeansProjects\\TradingData\\ticker" +ticker+".data" );
        
        reader = new BufferedReader(new FileReader(file));
        
        DefaultCategoryDataset stockDataSet = new DefaultCategoryDataset();
        while (reader.readLine() != null) {
            line = reader.readLine();
            String[] stockData = line.split(splitBy);
            stockPrice = Double.parseDouble(stockData[0]);
            stockDataSet.addValue(stockPrice, ticker, line);
        }
        
        JFreeChart chartObject = ChartFactory.createLineChart( null, null, null,stockDataSet,PlotOrientation.VERTICAL, false, false, false);
        int width = 300;
        int height = 200;
        File chart = new File("C:\\Users\\Matthew\\Documents\\NetBeansProjects\\PDFBoxTest\\"+ticker +"2.jpg");
        ChartUtilities.saveChartAsJPEG(chart, chartObject, width, height);
        return chart;
    }
}
