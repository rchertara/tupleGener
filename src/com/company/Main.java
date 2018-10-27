package com.company;

import java.io.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

// nextInt is normally exclusive of the top value,
// so add 1 to make it inclusive

public class Main {



    public static String[][] generateTable(String[] headers, ArrayList<String[]> allArrays){

        String table[][]= new String[20000][allArrays.size()];

        for(int i=0;i<20000;i++){
            for(int j=0;j<allArrays.size();j++){
                if(i==0){
                    table[i][j]=headers[j];
                }
                else{

                    String [] oneField=allArrays.get(j);
                    String value="";

                    if(j==0){
                        value = oneField[i];
                        table[i][j] = value;
                    }
                    else{
                        int max = oneField.length - 1;
                        int min = 0;
                        value = oneField[(int) ((Math.random() * ((max - min) + 1)) + min)];
                        table[i][j] = value;
                    }

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

    private static String[] createWorkHours() {
        String[] hours=new String[500];

        int min=11;
        int max=11;

        int min1=11;
        int max1=11;

        for(int i=0;i<500;i++){
            String startHour=Math.round(Math.random() * ((max - min))) + min  +"";
            String endHour=Math.round(Math.random() * ((max1 - min1))) + min1  +"";
            String workHourShift=startHour+"AM--"+endHour+"PM";
            hours[i]=workHourShift;
        }



        return hours;

    }
    private static String[] createTimeStamps(){
        String [] timeStamps=new String[20000];
        //'YYYY-MM-DD HH:MM:SS' format. The supported range is '1000-01-01 00:00:00' to '9999-12-31 23:59:59'.

        String HH []={"11","12","13","14","15","16","17","18","19","23"};
        String MM []= {"01","02","03","04","05","06","07","08","09","10","11","12"};
        String SS[]=new String[60];
        for(int i=0;i<60;i++){
            if(i<10){
                SS[i]="0"+i;
            }
            else{
                SS[i]=i+"";
            }
        }

        int max=0;
        int min=0;
        for(int i=0;i<timeStamps.length;i++){
            max=HH.length-1;
            String hours=HH[(int) ((Math.random() * ((max - min) + 1)) + min)];
            max=MM.length-1;
            String months=MM[(int) ((Math.random() * ((max - min) + 1)) + min)];
            max=SS.length-1;
            String seconds=SS[(int) ((Math.random() * ((max - min) + 1)) + min)];

            timeStamps[i]=hours+":"+months+":"+seconds;
        }

        return timeStamps;

    }

    private static void writeTabletoFile(String table[][],String FILENAME){
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {


            String content="";
            for(int i=0;i<table.length;i++){
                for(int j=0;j<table[0].length;j++){
                    if(j!=table[0].length-1){
                        content+=table[i][j]+",";
                    }
                    else{
                        content+=table[i][j];
                    }
                }
                content+="\n";
            }




            fw = new FileWriter(FILENAME);
            bw = new BufferedWriter(fw);
            bw.write(content);

            System.out.println("Done");

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
    }









    public static void main(String[] args) {

        //fix relative path issues
        String allnames[]=parseCSV("./src/com/company/csv_data/names.csv");
        String allbartenderNames[]=parseCSV("./src/com/company/csv_data/bartenderNames.csv");
        String allfoods[]=parseCSV("./src/com/company/csv_data/food.csv");//need dataset for food;
        String allprices[]=createPricesCSV();//need to round them to two places
        String allInventory[]=createInventoryCSV();
        String allPhoneNumbers[]=parseCSV("./src/com/company/csv_data/phone.csv");
        String allBeers[]=parseCSV("./src/com/company/csv_data/beers.csv");
        String allCustomerIds[]=parseCSV("./src/com/company/csv_data/customerID.csv");
        String allBartenderIds[]=parseCSV("./src/com/company/csv_data/bartenderID.csv");
        String allBarLicense[]=parseCSV("./src/com/company/csv_data/barLicense.csv");
        String allBarNames[]=parseCSV("./src/com/company/csv_data/barNames.csv");
        String allStates[]=parseCSV("./src/com/company/csv_data/state.csv");
        String allWorkHours[]=createWorkHours();
        String allTransID[]=parseCSV("./src/com/company/csv_data/transactionID.csv");
        String allTimeStamps[]=createTimeStamps();


//        ArrayList<String[]> fieldsOfTransactions= new ArrayList<String[]>();
//        fieldsOfTransactions.add(allTransID);
//        fieldsOfTransactions.add(allBarLicense);
//        fieldsOfTransactions.add(allCustomerIds);
//        fieldsOfTransactions.add(allBeers);
//        fieldsOfTransactions.add(allfoods);
//        fieldsOfTransactions.add(allTimeStamps);
//        String [] Headers= new String[]{"Transaction ID", "Bar License","Customer ID","Beer","Food","Time Issued"};
//        String Drinker[][]=generateTable(Headers,fieldsOfTransactions);
//        writeTabletoFile(Drinker,"TransactionTable.csv");
//        System.out.println();



/*
        //
        ArrayList<String[]> fieldsOfDrinker= new ArrayList<String[]>();
        fieldsOfDrinker.add(allnames);
        fieldsOfDrinker.add(allCustomerIds);
        fieldsOfDrinker.add(allStates);
        String [] Headers= new String[]{"drinkerName", "customerID","State of Residence"};
        String Drinker[][]=generateTable(Headers,fieldsOfDrinker);
        writeTabletoFile(Drinker,"DrinkerTable.csv");
        System.out.println();


*/



//        ArrayList<String[]> fieldsOfFrequents= new ArrayList<String[]>();
//        fieldsOfFrequents.add(allBarLicense);
//        fieldsOfFrequents.add(allCustomerIds);
//        String Headers[]={"barLicense","customerID"};
//        String frequents[][]=generateTable(Headers,fieldsOfFrequents);
//        writeTabletoFile(frequents,"freqTable.csv");
//        System.out.println();


//
//        ArrayList<String[]> fieldsOfBar= new ArrayList<String[]>();
//        fieldsOfBar.add(allBarNames);
//        fieldsOfBar.add(allBarLicense);
//        //fieldsOfBar.add(allBartenderIds);
//        fieldsOfBar.add(allStates);
//        fieldsOfBar.add(allWorkHours);
//        String []Headers= new String[]{"barName","barLicense","State of Residence","Hours of Operation"};
//        String Bar[][]=generateTable(Headers,fieldsOfBar);
//        writeTabletoFile(Bar,"barTable.csv");
//        System.out.println();
//
//        ArrayList<String[]> fieldsOfSells= new ArrayList<String[]>();
//        fieldsOfSells.add(allBarLicense);
//        fieldsOfSells.add(allBeers);
//        fieldsOfSells.add(allfoods);
//        fieldsOfSells.add(allprices);
//        fieldsOfSells.add(allBarNames);
//        fieldsOfSells.add(allInventory);
//        String [] Headers= new String[]{"barLicense", "Beer","Food","Price","barName","Inventory"};
//        String Sells[][]=generateTable(Headers,fieldsOfSells);
//        writeTabletoFile(Sells,"SellsTable.csv");
//        System.out.println();
//
//        ArrayList<String[]> fieldsOfLikes= new ArrayList<String[]>();
//        fieldsOfLikes.add(allnames);
//        fieldsOfLikes.add(allBeers);
//        fieldsOfLikes.add(allfoods);
//        String[]Headers= new String[]{"Person Name", "Beer","Food"};
//        String Likes[][]=generateTable(Headers,fieldsOfLikes);
//        writeTabletoFile(Likes,"likesTable");
//        System.out.println();
//
//        ArrayList<String[]> fieldsOfWorks= new ArrayList<String[]>();
//        fieldsOfWorks.add(allBartenderIds);
//        fieldsOfWorks.add(allBarLicense);
//        fieldsOfWorks.add(allWorkHours);
//        String [] Headers= new String[]{"bartenderId","barLicense","Work Hours"};
//        String Works[][]=generateTable(Headers,fieldsOfWorks);
//        writeTabletoFile(Works,"worksTable.csv");
//        System.out.println(); figure out work
//
//        ArrayList<String[]> fieldsOfBartender= new ArrayList<String[]>();
//        fieldsOfBartender.add(allbartenderNames);
//        fieldsOfBartender.add(allBartenderIds);
//        fieldsOfBartender.add(allStates);
//        String []Headers= new String[]{"name","bartenderId","State of Residence","Work Hours"};
//        String Bartender[][]=generateTable(Headers,fieldsOfBartender);
//        writeTabletoFile(Bartender,"bartenderTable");
//        System.out.println();
////
//























    }


}