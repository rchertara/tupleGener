package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main {



    public static String[][] generateTable(String[] headers, ArrayList<String[]> allArrays){

        String table[][]= new String[1000][allArrays.size()];

        for(int i=0;i<1000;i++){
            for(int j=0;j<allArrays.size();j++){
                if(i==0){
                    table[i][j]=headers[j];
                }
                else{
                    String [] oneField=allArrays.get(j);
                    String value=oneField[(int) (Math.random()*(oneField.length-1))];
                    table[i][j]=value;
                }
            }
        }



        return table;
    }

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


    private static String[] createInventoryCSV() {
        String[] prices=new String[500];
        double max=250;
        double min=50;
        DecimalFormat format= new DecimalFormat(".##");
        for(int i=0;i<500;i++){
            double price=  ((Math.random() * ((max - min) + 1)) + min);
            price=Math.round(Float.parseFloat(format.format(price)));
            prices[i]=price+"";
        }

        return prices;
    }









    public static void main(String[] args) {

        //fix relative path issues
        String allnames[]=parseCSV("./src/com/company/csv_data/names.csv");
        String allfoods[]=parseCSV("./src/com/company/csv_data/food.csv");//need dataset for food;
        String allprices[]=createPricesCSV();//need to round them to two places
        String allInventory[]=createInventoryCSV();
        String allPhoneNumbers[]=parseCSV("./src/com/company/csv_data/phone.csv");
        String allBeers[]=parseCSV("./src/com/company/csv_data/beers.csv");
        String allCustomerIds[]=parseCSV("./src/com/company/csv_data/customerID.csv");
        String allBartenderIds[]=parseCSV("./src/com/company/csv_data/bartenderID.csv");
        String allBarLicense[]=parseCSV("./src/com/company/csv_data/barLicense.csv");


        ArrayList<String[]> fieldsOfFrequents= new ArrayList<String[]>();
        fieldsOfFrequents.add(allBarLicense);
        fieldsOfFrequents.add(allCustomerIds);
        String frequentsHeaders[]={"barLicense","customerID"};
        String frequents[][]=generateTable(frequentsHeaders,fieldsOfFrequents);
        System.out.println();










    }
}