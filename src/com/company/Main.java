package com.company;

import java.io.*;
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

    private static String[] createWorkHours() {
        String[] hours=new String[500];

        int min=1;
        int max=3;

        int min1=4;
        int max1=12;

        for(int i=0;i<500;i++){
            String startHour=Math.round(Math.random() * ((max - min) + 1)) + min  +"";
            String endHour=Math.round(Math.random() * ((max1 - min1) + 1)) + min1  +"";
            String workHourShift=startHour+"PM--"+endHour+"PM";
            hours[i]=workHourShift;
        }



        return hours;

    }

    private static void writeTabletoFile(String table[][],String FILENAME){
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {


            String content="";
            for(int i=0;i<table.length;i++){
                for(int j=0;j<table[0].length;j++){
                    content+=table[i][j]+",";
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
        String allfoods[]=parseCSV("./src/com/company/csv_data/food.csv");//need dataset for food;
        String allprices[]=createPricesCSV();//need to round them to two places
        String allInventory[]=createInventoryCSV();
        String allPhoneNumbers[]=parseCSV("./src/com/company/csv_data/phone.csv");
        String allBeers[]=parseCSV("./src/com/company/csv_data/beers.csv");
        String allCustomerIds[]=parseCSV("./src/com/company/csv_data/customerID.csv");
        String allBartenderIds[]=parseCSV("./src/com/company/csv_data/bartenderID.csv");
        String allBarLicense[]=parseCSV("./src/com/company/csv_data/barLicense.csv");
        String allManf[]=parseCSV("./src/com/company/csv_data/barmanf.csv");
        String allBarNames[]=parseCSV("./src/com/company/csv_data/barNames.csv");
        String allStates[]=parseCSV("./src/com/company/csv_data/state.csv");
        String allWorkHours[]=createWorkHours();

        ArrayList<String[]> fieldsOfFrequents= new ArrayList<String[]>();
        fieldsOfFrequents.add(allBarLicense);
        fieldsOfFrequents.add(allCustomerIds);
        String Headers[]={"barLicense","customerID"};
        String frequents[][]=generateTable(Headers,fieldsOfFrequents);
        System.out.println();

        ArrayList<String[]> fieldsOfDrinker= new ArrayList<String[]>();
        fieldsOfDrinker.add(allnames);
        fieldsOfDrinker.add(allCustomerIds);
        fieldsOfDrinker.add(allStates);
        Headers= new String[]{"drinkerName", "customerID","State of Residence"};
        String Drinker[][]=generateTable(Headers,fieldsOfDrinker);
        System.out.println();

        ArrayList<String[]> fieldsOfBar= new ArrayList<String[]>();
        fieldsOfBar.add(allBarNames);
        fieldsOfBar.add(allPhoneNumbers);
        fieldsOfBar.add(allBarLicense);
        fieldsOfBar.add(allBartenderIds);
        fieldsOfBar.add(allStates);
        Headers= new String[]{"barName", "phoneNumber","barLicense","bartenderID","State of Residence"};
        String Bar[][]=generateTable(Headers,fieldsOfBar);
        System.out.println();

        ArrayList<String[]> fieldsOfSells= new ArrayList<String[]>();
        fieldsOfSells.add(allBarLicense);
        fieldsOfSells.add(allBeers);
        fieldsOfSells.add(allprices);
        fieldsOfSells.add(allBarNames);
        fieldsOfSells.add(allInventory);
        Headers= new String[]{"barLciense", "Beer","Price","barName","Inventory"};
        String Sells[][]=generateTable(Headers,fieldsOfSells);
        System.out.println();

        ArrayList<String[]> fieldsOfLikes= new ArrayList<String[]>();
        fieldsOfLikes.add(allnames);
        fieldsOfLikes.add(allBeers);
        fieldsOfLikes.add(allfoods);
        Headers= new String[]{"Person Name", "Beer","Food"};
        String Likes[][]=generateTable(Headers,fieldsOfLikes);
        System.out.println();

        ArrayList<String[]> fieldsOfWorks= new ArrayList<String[]>();
        fieldsOfWorks.add(allBartenderIds);
        fieldsOfWorks.add(allBarLicense);
        fieldsOfWorks.add(allWorkHours);
        Headers= new String[]{"bartenderId","barLicense","Work Hours"};
        String Works[][]=generateTable(Headers,fieldsOfWorks);
        System.out.println();

        ArrayList<String[]> fieldsOfBartender= new ArrayList<String[]>();
        fieldsOfBartender.add(allnames);
        fieldsOfBartender.add(allBartenderIds);
        fieldsOfBartender.add(allStates);
        fieldsOfBartender.add(allWorkHours);
        Headers= new String[]{"name","bartenderId","State of Residence","Work Hours"};
        String Bartender[][]=generateTable(Headers,fieldsOfBartender);
        System.out.println();

        writeTabletoFile(Bartender,"bartenderTable.csv");
        writeTabletoFile(Works,"WorksTable.csv");
        writeTabletoFile(Likes,"LikesTable.csv");
        writeTabletoFile(Sells,"SellsTable.csv");
        writeTabletoFile(Bar,"BarTable.csv");
        writeTabletoFile(Drinker,"DrinkerTable.csv");
        writeTabletoFile(frequents,"FrequentsTable.csv");























    }


}