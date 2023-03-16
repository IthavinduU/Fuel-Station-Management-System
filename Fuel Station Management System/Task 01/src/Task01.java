import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
public class Task01 {
    // Creating 3 arrays for the fuel pumps.
    private static String[] pump1 = new String[6];
    private static String[] pump2 = new String[6];
    private static String[] pump3 = new String[6];
    // Updating the fuel stock
    private static int fuelStock = 6600;
    public static void main(String[] args) {
        //Scanner object is created
        Scanner sc = new Scanner(System.in);

        //Declaring menu options.
            System.out.println(" ");
            System.out.println("-----LIYANAGE FUEL STATION-----");
            System.out.println(" ");
            System.out.println("Menu Options :");
            System.out.println(" ");
            System.out.println("100 or VFQ: View all Fuel Queues.");
            System.out.println("101 or VEQ: View all Empty Queues.");
            System.out.println("102 or ACQ: Add customer to a Queue.");
            System.out.println("103 or RCQ: Remove a customer from a Queue.(From a specific location)");
            System.out.println("104 or PCQ: Remove a served customer.");
            System.out.println("105 or VCS: View Customers Sorted in alphabetical order");
            System.out.println("106 or SPD: Store Program Data into file.");
            System.out.println("107 or LPD: Load Program Data from file.");
            System.out.println("108 or STK: View Remaining Fuel Stock.");
            System.out.println("109 or AFS: Add Fuel Stock.");
            System.out.println("999 or EXT: Exit the Program.");
            initial:
            while (true){
            System.out.println(" ");
            System.out.print("Enter your option : ");
            String option = sc.nextLine().toUpperCase();
            System.out.println(" ");

            // Initializing menu options to the switch case.

            switch (option){
                case "100":
                case "VFQ":
                    System.out.println("-----VIEW ALL QUEUES-----");
                    System.out.println(" ");
                    viewAllPumps();
                    break;
                case "101":
                case "VEQ":
                    System.out.println("-----VIEW ALL EMPTY QUEUES-----");
                    System.out.println(" ");
                    viewOnlyEmptyPumps();
                    break;
                case "102":
                case "ACQ":
                    System.out.println("-----ADD CUSTOMER TO QUEUE-----");
                    System.out.println(" ");
                    addNewCustomer();
                    break;
                case "103":
                case "RCQ":
                    System.out.println("-----REMOVE CUSTOMER FROM SPECIFIC LOCATION-----");
                    System.out.println(" ");
                    removeCustomerSpecific();
                    break;
                case "104":
                case "PCQ":
                    System.out.println("-----REMOVE SERVED CUSTOMER-----");
                    System.out.println(" ");
                    removeServedCustomer();
                    break;
                case "105":
                case "VCS":
                    System.out.println("-----VIEW CUSTOMERS IN ALPHABETICAL ORDER-----");
                    System.out.println(" ");
                    sortCustomersAlph();
                    break;
                case "106":
                case "SPD":
                    System.out.println("-----STORE PROGRAM DATA TO FILE-----");
                    System.out.println(" ");
                    saveProgramData();
                    break;
                case "107":
                case "LPD":
                    System.out.println("-----LOAD PROGRAM DATA FROM FILE-----");
                    System.out.println(" ");
                    loadProgramData();
                    break;
                case "108":
                case "STK":
                    System.out.println("REMAINING FUEL STOCK : " + fuelStock + " LITRES.");
                    System.out.println(" ");
                    break;
                case "109":
                case "AFS":
                    System.out.println("-----ADD NEW FUEL STOCK-----");
                    System.out.println(" ");
                    addFuelStock();
                    break;
                case "999":
                case "EXT":
                    System.out.println("-----EXITING THE PROGRAM-----");
                    System.out.println(" ");
                    break initial;
                default:
                    System.out.println("INVALID CODE! ENTER VALID INPUT.");
            }
        }
    }

    //User defined methods for each case.
    //Method 1: View all the fuel pumps.
    private static void viewAllPumps(){
        System.out.println("FUEL PUMP 01 : ");
        checkIfNull(pump1);
        System.out.println(" ");
        System.out.println("FUEL PUMP 02 : ");
        checkIfNull(pump2);
        System.out.println(" ");
        System.out.println("FUEL PUMP 03 : ");
        checkIfNull(pump3);
    }
    private static void checkIfNull(String[] fuelPumps){
        for (String x: fuelPumps){
            if (x==null){
                System.out.println("EMPTY");
            }else {
                System.out.println(x);
            }
        }
        System.out.println(" ");
    }
    //Method 2: View empty fuel pumps.
    private static void viewOnlyEmptyPumps() {
        int count1 = 0;
        for (String x : pump1) {
            if (x==null) {
                count1++;
            }
        }
        System.out.println("Fuel queue 01 has " + count1 + " empty slots");

        int count2 = 0;
        for (String x : pump2) {
            if (x==null) {
                count2++;
            }
        }
        System.out.println("Fuel queue 02 has " + count2 + " empty slots");

        int count3 = 0;
        for (String x : pump3) {
            if (x==null) {
                count3++;
            }
        }
        System.out.println("Fuel queue 03 has " + count3 + " empty slots");
    }
        // Method 3: Add new customer.
    private static void addNewCustomer(){

        outer:
        while (true) {
            boolean spaceAvailble = false;
            Scanner sc = new Scanner(System.in);
            System.out.print("ENTER FUEL PUMP NUMBER : ");
            if (sc.hasNextInt()){
                int pumpNo = sc.nextInt();
                if (pumpNo>=1 && pumpNo<=3){
                    if (pumpNo==1){
                        spaceAvailble = checkSpace(pump1);
                        if (spaceAvailble){
                            System.out.print("ENTER CUSTOMER NAME : ");
                            String customerName = sc.next();
                            while(!customerName.matches("[a-zA-Z ]+")) {
                                System.out.print("ENTER A VALID NAME!  : ");
                                customerName = sc.next();
                            }
                            for (int x=0; x<=5; x++){
                                if (pump1[x]==null) {
                                    pump1[x] = customerName;
                                    System.out.println("CUSTOMER " + customerName +" ADDED TO QUEUE 01");
                                    fuelStock -= 10;
                                    if (fuelStock <=500){
                                        System.out.println("WARNING! ONLY "+ fuelStock +"LITRES REMAINING IN PUMP 01!");
                                    }
                                    break outer;
                                }
                            }
                        }else {
                            System.out.println("FUEL QUEUE 01 IS FULL. TRY OTHER PUMP");
                            break outer;
                        }
                        //
                    } else if (pumpNo==2) {
                        spaceAvailble = checkSpace(pump2);
                        if (spaceAvailble){
                            System.out.print("ENTER CUSTOMER NAME : ");
                            String customerName = sc.next();
                            while(!customerName.matches("[a-zA-Z ]+")) {
                                System.out.print("ENTER A VALID NAME!  : ");
                                customerName = sc.next();
                            }
                            for (int x=0; x<=5; x++){
                                if (pump2[x]==null) {
                                    pump2[x] = customerName;
                                    System.out.println("CUSTOMER " + customerName + " ADDED TO QUEUE 02");
                                    fuelStock -= 10;
                                    if (fuelStock <=500){
                                        System.out.println("WARNING!ONLY "+ fuelStock +"LITRES REMAINING  IN PUMP 02");
                                    }
                                    break outer;
                                }
                            }
                        }else {
                            System.out.println("FUEL QUEUE 02 IS FULL. TRY OTHER PUMP");
                            break outer;
                        }

                    } else if (pumpNo==3) {
                        spaceAvailble = checkSpace(pump3);
                        if (spaceAvailble){
                            System.out.print("ENTER CUSTOMER NAME : ");
                            String customerName = sc.next();
                            while(!customerName.matches("[a-zA-Z ]+")) {
                                System.out.print("ENTER VALID NAME : ");
                                customerName = sc.next();
                            }
                            for (int x=0; x<=5; x++){
                                if (pump3[x]==null) {
                                    pump3[x] = customerName;
                                    System.out.println("CUSTOMER " + customerName + " ADDED TO QUEUE 03");
                                    fuelStock -= 10;
                                    if (fuelStock <=500){
                                        System.out.println("WARNING! ONLY "+ fuelStock +"LITRES REMAINING IN PUMP 03");
                                    }
                                    break outer;
                                }
                            }
                        }else {
                            System.out.println("FUEL QUEUE 03 IS FULL. TRY OTHER PUMP");
                            break outer;
                        }
                    }
                }else {
                    System.out.println("ENTER NUMBER 1-3");
                    continue;
                }
            }else {
                System.out.println("ENTER NUMBER 1-3");
                continue;
            }
        }
    }
    //Method 4:
    private static boolean checkSpace(String[] pumpNo){
        boolean spaceAvailable=false;
        for (int x=0; x<=5; x++){
            if (pumpNo[x]==null){
                spaceAvailable = true;
                break;
            }
        }
        return spaceAvailable;
    }
    //MEthod 5: Remove customer.
    private static void removeCustomerSpecific() {
        outer:
        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.print("ENTER QUEUE NUMBER OF SERVED CUSTOMER : ");
            if (sc.hasNextInt()){
                int queueNO = sc.nextInt();
                switch (queueNO){
                    case 1:
                        System.out.println("ENTER CUSTOMER QUEUE LOCATION :");
                        if (sc.hasNextInt()){
                            int location = sc.nextInt();
                            if (location>0 && location<7){
                                if (pump1[location-1]==null){
                                    System.out.println("LOCATION IS ALREADY EMPTY.");
                                    break;
                                }else{
                                    System.out.println("CUSTOMER "+ pump1[location-1]+" REMOVED.");
                                    pump1[location-1] = null;
                                    for (int x=location-1; x<=4; x++){
                                        pump1[x] = pump1[x+1];
                                    }
                                    pump1[5] = null;
                                    break;
                                }
                            }else {
                                System.out.println("ENTER NUMBER 1-6");
                                continue outer;
                            }
                        }else {
                            System.out.println("ENTER NUMBER 1-6");
                            continue outer;
                        }

                    case 2:
                        System.out.println("ENTER CUSTOMER QUEUE LOCATION :");
                        if (sc.hasNextInt()){
                            int location = sc.nextInt();
                            if (pump2[location-1]==null){
                                System.out.println("LOCATION IS ALREADY EMPTY");
                                break;
                            }else{
                                System.out.println("CUSTOMER "+ pump1[location-1]+" REMOVED.");
                                pump2[location-1] = null;
                                for (int x=location-1; x<=4; x++){
                                    pump2[x] = pump2[x+1];
                                }
                                pump2[5] = null;
                                break;
                            }
                        }else {
                            System.out.println("ENTER NUMBER 1-6");
                            continue outer;
                        }
                    case 3:
                        System.out.println("ENTER CUSTOMER QUEUE LOCATION :");
                        if (sc.hasNextInt()){
                            int location = sc.nextInt();
                            if (pump3[location-1]==null){
                                System.out.println("LOCATION IS ALREADY EMPTY");
                                break;
                            }else{
                                System.out.println("CUSTOMER "+ pump1[location-1]+" REMOVED.");
                                pump3[location-1] = null;
                                for (int x=location-1; x<=4; x++){
                                    pump3[x] = pump3[x+1];
                                }
                                pump3[5] = null;
                                break;
                            }
                        }else {
                            System.out.println("ENTER NUMBER 1-6");
                            continue outer;
                        }
                    default:
                        System.out.println("ENTER NUMBER 1-3");
                }
                break;
            }else {
                System.out.println("INVALID OPTION. TRY AGAIN.");
                continue outer;
            }
        }
    }
    //Method 5: Remove served customer.
    private static void removeServedCustomer(){
        outer:
        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.print("ENTER SERVED CUSTOMER'S QUEUE NUMBER :");
            if (sc.hasNextInt()){
                int queueNO = sc.nextInt();
                switch (queueNO){
                    case 1:
                        System.out.println("CUSTOMER "+ pump1[0]+" SERVED");
                        pump1[0] = null;
                        for (int x=0; x<=4; x++){
                            pump1[x] = pump1[x+1];
                        }
                        pump1[5]=null;
                        break;
                    case 2:
                        System.out.println("CUSTOMER "+ pump2[0]+" SERVED");
                        pump2[0] = null;
                        for (int x=0; x<=4; x++){
                            pump2[x] = pump2[x+1];
                        }
                        pump2[5]=null;
                        break;
                    case 3:
                        System.out.println("CUSTOMER "+ pump3[0]+" SERVED");
                        pump3[0] = null;
                        for (int x=0; x<=4; x++){
                            pump3[x] = pump3[x+1];
                        }
                        pump3[5]=null;
                        break;
                    default:
                        System.out.println("ENTER NUMBER 1-3");
                }
                break;
            }else {
                System.out.println("INVALID OPTION. TRY AGAIN.");
                continue outer;
            }
        }
    }
    //Method 6: View customers sorted in alphabetical order.
    private static void sortCustomersAlph(){
        String[] combinedArray = new String[18];
        String[] arr1 = appendAll(pump1);
        String[] arr2 = appendAll(pump2);
        String[] arr3 = appendAll(pump3);

        sortingAlgorithm(arr1);
        sortingAlgorithm(arr2);
        sortingAlgorithm(arr3);
    }
    //Method 7: Sorting Algorithm.
    private static void sortingAlgorithm(String[] pumpNo){
        String [] arr = new String[6];
        int count=0;
        for(int i=0;i<pumpNo.length;i++) {
            count=0;
            for(int j=0;j<pumpNo.length;j++)
            {
                if(pumpNo[i].compareTo(pumpNo[j])>0)
                {
                    count++;
                }
            }
            arr[count]=pumpNo[i];
        }
        for(String x:arr) {
            if (x==null){
                System.out.println("EMPTY");
            }else {
                System.out.println(x);
            }
        }
        System.out.println(" ");
    }
    //Method 08: Sort alphabetical order.
    private static String[] appendAll(String[] arr){
        String[] newArr = new String[6];
        for (int x=0; x<=5; x++){
            if (arr[x]==null){
                newArr[x]="EMPTY";
            }else {
                newArr[x]=arr[x];
            }
        }
        return newArr;
    }
    //Method 9: Store program data to a file.
    private static void saveProgramData(){
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("D:\\University Files\\IIT\\Level 4\\Software Development II\\SDII Coursework\\w1899297_CW\\Task 01\\QueueData.txt"));
            bw.write("-----LIYANAGE FUEL STATION - LOGS-----"+"\n");
            bw.write("\n");
            bw.write("FUEL QUEUE 01 : "+ Arrays.toString(pump1)+"\n");
            bw.write("FUEL QUEUE 02 : "+ Arrays.toString(pump2)+"\n");
            bw.write("FUEL QUEUE 03 : "+ Arrays.toString(pump3)+"\n");
            bw.write("\n"+ fuelStock +" LITRES OF FUEL REMAINING."+"\n");
            bw.write("Coded by: Thavindu Liyanage");
            bw.close();
            System.out.println("PROGRAM DATA SAVED SUCCESSFULLY.");
        }catch (Exception e){
            System.out.println("ERROR OCCURRED WHILE STORING DATA. TRY AGAIN.");
        }
    }
    //Method 10: Load program data from a file.
    private static void loadProgramData(){
        try {
            BufferedReader bRead = new BufferedReader(
                    new FileReader("D:\\University Files\\IIT\\Level 4\\Software Development II\\SDII Coursework\\w1899297_CW\\Task 01\\QueueData.txt"));
            String s;
            while (( s = bRead.readLine()) != null){
                System.out.println(s);
            }
            bRead.close();
        }catch (Exception e){
            System.out.println("ERROR OCCURRED WHILE LOADING DATA. TRY AGAIN.");
        }
    }
    //Method 11: Add new fuel stock.
    private static void addFuelStock(){
        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.print("ENTER THE FUEL STOCK AMOUNT IN LITRES : ");
            if (sc.hasNextInt()){
                int newStock = sc.nextInt();
                fuelStock = fuelStock + newStock;
                System.out.println(newStock+" LITRES ADDED TO YOUR STOCK");
                break;
            }else {
                System.out.println("INVALID FORMAT. ENTER VALID AMOUNT.");
                continue;
            }
        }
    }
}






