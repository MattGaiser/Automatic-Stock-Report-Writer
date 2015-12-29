/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfboxtest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

/**
 *
 * @author Matthew
 */
public class GetFundamentals {

    public String ticker;
    public  String get(String ticker, String whatToGet) throws Exception
    {
        BufferedReader reader= null;
        String splitBy = ",";
        String line;
        File file = new File("C:\\Users\\Matthew\\Documents\\NetBeansProjects\\FundDataParser\\fundamental\\" +ticker+"fundamental.stockInfo" );
        
        reader = new BufferedReader(new FileReader(file));
        line = reader.readLine();
        String [] stockData = line.split(splitBy);
        
        switch (whatToGet)
        {
            case "Symbol":
                return stockData[0];
            case "Name":
                return stockData[1];
            case "Sector":
                return stockData[2];
            case "Industry":
                return stockData[3];
            case "PE":
                return stockData[4];
            case "EPS":
                return stockData[5]; 
            case "DivYield":
                return stockData[6];
            case "Shares":
                return stockData[7];
            case "DPS":
                return stockData[8];
            case "PEG":
                return stockData[9];
            case "PtS":
                return stockData[10];
            case "PtB":
                return stockData[11];
        }
        return "ERROR";
    }
}
