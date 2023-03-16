import java.io.*;
import java.util.*;

public class Task02 {
    // Create an array of fuel queue objects.
    static FuelQueue[] fuelQueArray = new FuelQueue[5];
    static int fuelStock = 6600;
    static int litrePrice = 430;

    public static void main(String[] args) throws IOException {

        // Initialize all the fuel queue objects in the object array

        for (int i = 0; i < fuelQueArray.length; i++) {
            fuelQueArray[i]=new FuelQueue(6);
        }

        Scanner sc = new Scanner(System.in); // Creating scanner object for inputs.

        initial: // Naming the loop
        while (true){ // While loop to continuously run the menu program.

            System.out.println(" ");
            System.out.println("-----LIYANAGE FUEL STATION-----");
            System.out.println(" ");
            System.out.println("Menu Options");
            System.out.println(" ");
            System.out.println("100 or VFQ: View all Fuel Queues.");
            System.out.println("101 or VEQ: View all Empty Queues.");
            System.out.println("102 or ACQ: Add passenger to a Queue.");
            System.out.println("103 or RCQ: Remove a passenger from a Queue. (From a specific location)");
            System.out.println("104 or PCQ: Remove a served passenger.");
            System.out.println("105 or VCS: View Passengers Sorted in alphabetical order");
            System.out.println("106 or SPD: Store Program Data into file.");
            System.out.println("107 or LPD: Load Program Data from file.");
            System.out.println("108 or STK: View Remaining Fuel Stock.");
            System.out.println("109 or AFS: Add Fuel Stock.");
            System.out.println("110 or IFQ: Show the Income of Each Fuel Queue");
            System.out.println("999 or EXT: Exit the Program.");
            System.out.println(" ");
            System.out.print("Enter your option : ");
            String option = sc.nextLine().toUpperCase();
            System.out.println(" ");

            switch (option){ // Declaring SWITCH case to go through functions.

                case "100":
                case "VFQ":
                    System.out.println("-----VIEW ALL QUEUES-----");
                    System.out.println(" ");
                    viewAllQue();
                    break;
                case "101":
                case "VEQ":
                    System.out.println("-----VIEW ALL EMPTY QUEUES-----");
                    System.out.println(" ");
                    viewEmptyQue();
                    break;
                case "102":
                case "ACQ":
                    System.out.println("-----ADD CUSTOMER TO QUEUE-----");
                    System.out.println(" ");
                    addNewPassenger();
                    break;
                case "103":
                case "RCQ":
                    System.out.println("-----REMOVE CUSTOMER FROM SPECIFIC LOCATION-----");
                    System.out.println(" ");
                    removeSpecificPassenger();
                    break;
                case "104":
                case "PCQ":
                    System.out.println("-----REMOVE SERVED CUSTOMER-----");
                    System.out.println(" ");
                    removeServedPassenger();
                    break;
                case "105":
                case "VCS":
                    System.out.println("-----VIEW CUSTOMERS IN ALPHABETICAL ORDER-----");
                    System.out.println(" ");
                    sortPassengers();
                    break;
                case "106":
                case "SPD":
                    System.out.println("-----STORE PROGRAM DATA TO FILE-----");
                    System.out.println(" ");
                    storeProgramData();
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
                    addNewFuelStock();
                    break;
                case "110":
                case "IFQ":
                    System.out.println("-----INCOME OF EACH FUEL QUEUE-----");
                    System.out.println(" ");
                    incomeOfQueues();
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
    public static void viewAllQue(){

        for (int x=0; x<5; x++){ // Loop through all 5 fuel queues.
            int count = 1;
            System.out.println("Fuel Queue "+(x+1));
            int listSize = fuelQueArray[x].getPassengersSize();
            for (int i =0; i<listSize; i++)
            { // Loop through the passengers list in each fuel queue object
                System.out.println(count+". "+ fuelQueArray[x].getFirstName(i)+" "+ fuelQueArray[x].getLastName(i)+" - "+ fuelQueArray[x].getVehicleNo(i));
                count++;
            }
            int emptySpace = fuelQueArray[x].getQueueLength()-listSize;
            for (int j = 0; j < emptySpace ; j++) { // Print "empty" if empty space available.
                System.out.println(count+". "+"empty");
                count++;
            }
            System.out.println(" ");
        }
    }
    //Method 2: View empty fuel pumps.
    public static void viewEmptyQue(){
        for (int x=0; x<5; x++){
            System.out.println("Queue "+(x+1)+" has "+ fuelQueArray[x].getEmptySpaces()+" empty slots available.");
        }
    }
    // Method 3: Add new passenger(Customer).
    public static void addNewPassenger(){

        boolean availableSpace = checkSpaceAvailability();
        if (availableSpace){ // Checking if free space available.
            while (true){
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter the first name of the passenger : ");
                if (sc.hasNext()){
                    String pasFirstName = sc.nextLine();
                    while(!pasFirstName.matches("[a-zA-Z ]+")) {
                        System.out.print("Please enter a valid name! : ");
                        pasFirstName = sc.next();
                    }
                    System.out.print("Enter the second name of the passenger : ");
                    if (sc.hasNext()){
                        String pasSecondName = sc.nextLine();
                        while(!pasSecondName.matches("[a-zA-Z ]+")) {
                            System.out.print("Please enter a valid name! : ");
                            pasSecondName = sc.next();
                        }
                        System.out.print("Enter the vehicle number of the passenger : ");
                        if (sc.hasNext()){
                            String pasVehicleNo = sc.nextLine();
                            System.out.print("Enter the number of litres needed : ");
                            if (sc.hasNextInt()){
                                int pasLitresNeeded = sc.nextInt();
                                int smallestQueue=selectSmallestQueue(); // Checking for the smallest queue in the Fuel Queue array
                                fuelQueArray[smallestQueue].addPassenger(pasFirstName,pasSecondName,pasVehicleNo,pasLitresNeeded);
                                System.out.println(" ");
                                System.out.println(pasFirstName+" "+pasSecondName+" has been added to Queue No.0"+(smallestQueue+1));
                                fuelStock = fuelStock-pasLitresNeeded;
                                break;
                            }else {
                                System.out.println("Please enter a valid amount");
                                continue;
                            }
                        }else {
                            System.out.println("Please enter a valid vehicle number");
                            continue;
                        }
                    }else {
                        System.out.println("Please enter a valid second name");
                        continue;
                    }
                }else {
                    System.out.println("Please enter a valid first name");
                    continue;
                }
            }
        }else {
            System.out.println("All the fuel queues are full");
        }
    }
    //Method 4: Remove a Specified passenger(Customer).
    public static void removeSpecificPassenger(){

        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the Queue number of the passenger : ");
            if (sc.hasNextInt()){
                int queueIndex = sc.nextInt();
                if (queueIndex>0 && queueIndex<6){
                    System.out.println(" ");
                    System.out.print("Enter the index in front of the person you want to remove : ");
                    if (sc.hasNextInt()){
                        int indexOfPas = sc.nextInt();
                        if (indexOfPas>0 && indexOfPas<7){
                            // Get the size of the list
                            int sizeOfList = fuelQueArray[queueIndex-1].getPassengersSize();
                            if (indexOfPas<=sizeOfList){ // Check if there is a passenger in the given index
                                System.out.println(fuelQueArray[queueIndex-1].getFirstName(indexOfPas-1)+" "+ fuelQueArray[queueIndex-1].getLastName(indexOfPas-1)+" has been removed from the queue");
                                fuelStock = fuelStock + fuelQueArray[queueIndex-1].getLitresNeeded(indexOfPas-1);
                                fuelQueArray[queueIndex-1].removePassenger(indexOfPas-1);
                                break;
                            }else {
                                System.out.println("No passenger is in this position. Please try again with a valid index");
                                continue;
                            }
                        }else {
                            System.out.println("Please enter a number between 1 and 6");
                            continue;
                        }
                    }else {
                        System.out.println("Please enter a valid integer");
                        continue;
                    }
                }else {
                    System.out.println("Please enter a number between 1 and 5");
                    continue;
                }
            }else {
                System.out.println("Please enter a valid number");
                continue;
            }
        }
    }
    // Method 5: Remove a served passenger(Customer).
    public static void removeServedPassenger(){

        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the Queue number of the passenger : ");
            if (sc.hasNextInt()){
                int queueIndex = sc.nextInt();
                if (queueIndex>0 && queueIndex<6){
                    if (fuelQueArray[queueIndex-1].getPassengersSize()>0){
                        // Add the income of the passenger to the income of the fuel queue
                        int incomeOfPas = fuelQueArray[queueIndex-1].getLitresNeeded(0)*litrePrice;
                        fuelQueArray[queueIndex-1].setIncome(incomeOfPas);
                        System.out.println(fuelQueArray[queueIndex-1].getFirstName(0)+" "+ fuelQueArray[queueIndex-1].getLastName(0)+" has been served");
                        fuelQueArray[queueIndex-1].removePassenger(0);
                        break;
                    }else {
                        System.out.println("There are no passengers in this queue slot.");
                        continue;
                    }
                }else {
                    System.out.println("Please enter a number between 1 and 5");
                    continue;
                }
            }else {
                System.out.println("Please enter a valid number");
                continue;
            }
        }
    }
    //Method 6: Sort Passengers(Customers) according to their name.
    public static void sortPassengers(){

        // Get passengers in each list as an arraylists.
        ArrayList<String> pump01 = fuelQueArray[0].getListOfPassengers();
        ArrayList<String> pump02 = fuelQueArray[1].getListOfPassengers();
        ArrayList<String> pump03 = fuelQueArray[2].getListOfPassengers();
        ArrayList<String> pump04 = fuelQueArray[3].getListOfPassengers();
        ArrayList<String> pump05 = fuelQueArray[4].getListOfPassengers();
        System.out.print("Fuel Queue 01 : ");
        sortAndPrint(pump01);
        System.out.println(" ");
        System.out.print("Fuel Queue 02 : ");
        sortAndPrint(pump02);
        System.out.println(" ");
        System.out.print("Fuel Queue 03 : ");
        sortAndPrint(pump03);
        System.out.println(" ");
        System.out.print("Fuel Queue 04 : ");
        sortAndPrint(pump04);
        System.out.println(" ");
        System.out.print("Fuel Queue 05 : ");
        sortAndPrint(pump05);
        System.out.println(" ");
    }
    // Method 7: Sort the arraylists.
    public static void sortAndPrint(ArrayList<String> fuelPump) {

        Collections.sort(fuelPump, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) { // Using a comparator to sort based on first and last names
                String[] split01 = o1.split(" ");
                String[] split02 = o2.split(" ");
                String firstName1 = split01[0];
                String lastName1 = split01[1];
                String firstName2 = split02[0];
                String lastName2 = split02[1];
                if (firstName1.compareToIgnoreCase(firstName2) > 0) {
                    return 1;
                } else if (firstName1.compareToIgnoreCase(firstName2) < 0){
                    return -1;
                }else {
                    if (lastName1.compareToIgnoreCase(lastName2) > 0){
                        return 1;
                    }else {
                        return -1;
                    }
                }
            }
        });
        System.out.println(fuelPump);
    }
    // Method 8: Save program data to a text file.
    public static void storeProgramData() throws IOException {

        Scanner sc = new Scanner(System.in);
        FileWriter myWriter = new FileWriter("data.txt"); // Create a FileWriter object
        System.out.println("Are you sure you want to overwrite the saved data if data is already stored?");
        System.out.print("Enter Y / N : ");
        if (sc.hasNextLine()){
            String ans = sc.nextLine().toUpperCase();
            if (ans.equals("Y")){
                for (int x=0; x<5; x++){
                    for (int y = 0; y< fuelQueArray[x].getPassengersSize(); y++){ // Write passenger details to the file.
                        myWriter.write(fuelQueArray[x].getPassengerString(y));
                    }
                    if (fuelQueArray[x].getPassengersSize()< fuelQueArray[x].getQueueLength()){// Write empty spaces if no passenger details available.
                        for (int z = 0; z< fuelQueArray[x].getQueueLength()- fuelQueArray[x].getPassengersSize(); z++){
                            myWriter.write("\n");
                        }
                    }
                }
                // Income of queue.
                for (int z=0; z<5; z++){
                    myWriter.write(fuelQueArray[z].getIncome()+"\n");
                }
                // Save the fuel stock
                myWriter.write(String.valueOf(fuelStock));
                myWriter.close(); // Close the writer object
                System.out.println("The data has been successfully stored");
            }else {
                System.out.println("The saved data won't be overwritten");
            }
        }else{
            System.out.println("Please enter a valid input. Try again");
        }
    }
    // Method 9: Load program data from the text file.
    public static void loadProgramData() throws FileNotFoundException {

        File dataFile = new File("data.txt"); // Create a File object
        Scanner read = new Scanner(dataFile);
        Scanner sc = new Scanner(System.in);

        System.out.println("Are you sure you want to load data and overwrite the current data?");
        System.out.print("Enter Y/N : ");
        if (sc.hasNextLine()){
            String ans = sc.nextLine().toUpperCase();
            if (ans.equals("Y")){
                //Go through all queus and delete previous saved data.
                for (int x=0; x<5; x++){
                    fuelQueArray[x].clearData();
                }

                String passengerDetails;
                for (int x=0; x<5; x++){
                    for (int y=0; y<6; y++){
                        passengerDetails = read.nextLine();
                        // Store each line into an array and separate the data using commas.
                        String[] parts = passengerDetails.split(",");
                        // Check if the line is an empty space.
                        if (passengerDetails.length()>2){
                            String pasFirstName = parts[0];
                            String pasLastName = parts[1];
                            String vehicleNo = parts[2];
                            int litresNeeded = Integer.parseInt(parts[3]);

                            fuelQueArray[x].addPassenger(pasFirstName, pasLastName, vehicleNo, litresNeeded);
                            fuelStock = fuelStock - litresNeeded;
                        }
                    }
                }

                String income;
                for (int z=0; z<5; z++){ // Get the stored income data of each queue and set it to the queue
                    income = read.nextLine();
                    fuelQueArray[z].setIncome(Integer.parseInt(income));
                }
                int newFuelStock = read.nextInt(); // Replace the current fuel stock with the new stock
                fuelStock = newFuelStock;

                read.close();
                System.out.println("The data has been successfully loaded");

            } else {
                System.out.println("The data won't be overwritten");
            }


        }else {
            System.out.println("Please enter a valid input. Try again");
        }
    }
    // Method 10: Add new fuel stock to the current stock
    public static void addNewFuelStock(){

        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the amount of fuel you need to add in litres : ");
            if (sc.hasNextInt()){
                int newStock = sc.nextInt();
                fuelStock = fuelStock + newStock;
                System.out.println(newStock+" litres added to the stock");
                break;
            }else {
                System.out.println("Enter a valid amount");
                continue;
            }
        }
    }
    // Method 11: Get the income of each queue
    public static void incomeOfQueues(){

        for (int x=0; x<5; x++){
            int income = fuelQueArray[x].getIncome();
            System.out.println("Income of Fuel Queue "+(x+1)+" = Rs."+income);
        }

    }
    // Method 12:  Check if at least one fuel queue has an empty space available.
    public static boolean checkSpaceAvailability() {

        boolean available = false;
        for (int x=0; x<5; x++){
            if (fuelQueArray[x].getPassengersSize()<6){
                available = true;
            }
        }
        return available;
    }
    // Method 13: Select the smallest queue out of all the queues.
    public static int selectSmallestQueue() {

        int noOfEmptySpaces = 0;
        int indexSmallestQueue = 0;
        for (int x = 0; x < 5; x++) {
            if (fuelQueArray[x].getEmptySpaces() > noOfEmptySpaces) {
                noOfEmptySpaces = fuelQueArray[x].getEmptySpaces();
                indexSmallestQueue = x;
            }
        }
        return indexSmallestQueue;
    }

}