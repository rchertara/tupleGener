package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class Main {



    public static String[] parseCSV(String csvFile){



        BufferedReader br = null;
        String line = "";
        String entireFile="";
        String arr[] = new String[0];


        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                entireFile+=line;
            }

            arr =entireFile.split(",");
            arr[arr.length-1]=null;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return arr;
    }

    public static String[] createPricesCSV(){
        String[] prices=new String[500];
        double max=10;
        double min=5;
        DecimalFormat format= new DecimalFormat(".##");
        for(int i=0;i<500;i++){
            double price=  ((Math.random() * ((max - min) + 1)) + min);
            price=Math.round(Float.parseFloat(format.format(price)));
            prices[i]=price+"";
        }

        return prices;
    }







    public static void main(String[] args) {


        String allnames[]=parseCSV("/Users/mobiledev/IdeaProjects/tupleGen/src/com/company/csv data/CSV_Database_of_First_Names.csv");
        String allfoods[]=parseCSV("");//need dataset for food;
        String allprices[]=createPricesCSV();//need to round them to two places
        System.out.printf("");




    }
}