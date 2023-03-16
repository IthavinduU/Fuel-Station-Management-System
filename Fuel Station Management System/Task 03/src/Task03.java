import java.io.*;
import java.util.*;

public class Task03 {

    static FuelQueue[] fuelQueArray = new FuelQueue[5];
    static int fuelStock = 6600;
    static int litrePrice = 430;
    public static ArrayList<Passenger> waitingQueue =new ArrayList<Passenger>();//Arraylist for waiting queue.

    public static void main(String[] args) throws IOException {

        // Initialize all the fuel queue objects in the object array

        for (int i = 0; i < fuelQueArray.length; i++) {
            fuelQueArray[i] = new FuelQueue(6);
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
            switch (option) { // Using a switch case to loop through all the functions

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
                    System.out.println("-----ADD A PASSENGER TO QUEUE-----");
                    System.out.println(" ");
                    addNewPassenger();
                    break;
                case "103":
                case "RCQ":
                    System.out.println("-----REMOVE A PASSENGER FROM SPECIFIC LOCATION-----");
                    System.out.println(" ");
                    removeSpecificPassenger();
                    break;
                case "104":
                case "PCQ":
                    System.out.println("-----REMOVE SERVED PASSENGER");
                    System.out.println(" ");
                    removeServedPassenger();
                    break;
                case "105":
                case "VCS":
                    System.out.println("-----VIEW PASSENGERS SORTED IN ALPHABETICAL ORDER-----");
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
                    System.out.println(fuelStock + " LITRES OF FUEL REMAINING");
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
                    System.out.println("-----INCOME OF EACH PUMP-----");
                    System.out.println(" ");
                    incomeOfQueues();
                    break;
                case "999":
                case "EXT":
                    System.out.println("-----EXIT-----");
                    System.out.println(" ");
                    break initial;
                default:
                    System.out.println("PLEASE ENTER VALID INPUT!");
            }
        }

    }

    public static void viewAllQue() {

        for (int x = 0; x < 5; x++) {
            int count = 1;
            System.out.println("----- FUEL PUMP " + (x + 1) + " -----");
            int sizeOfList = fuelQueArray[x].getPassengersSize();
            for (int i = 0; i < sizeOfList; i++) {
                System.out.println(count + ". " + fuelQueArray[x].getFirstName(i) + " " + fuelQueArray[x].getLastName(i) + " - " + fuelQueArray[x].getVehicleNo(i));
                count++;
            }
            int emptySpaces = fuelQueArray[x].getQueueLength() - sizeOfList;
            for (int j = 0; j < emptySpaces; j++) {
                System.out.println(count + ". " + "EMPTY");
                count++;
            }
            System.out.println(" ");
        }
    }

    public static void viewEmptyQue() {

        for (int x = 0; x < 5; x++) {
            System.out.println("FUEL QUEUE " + (x + 1) + " HAS " + fuelQueArray[x].getEmptySpaces() + " EMPTY SLOTS");
        }
    }

    public static void addNewPassenger(){

        boolean available = checkSpaceAvailability();
        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.print("ENTER CUSTOMERS FIRST NAME : ");
            if (sc.hasNext()) {
                String pasFirstName = sc.nextLine();
                while (!pasFirstName.matches("[a-zA-Z ]+")) {
                    System.out.print("PLEASE ENTER A VALID NAME!! : ");
                    pasFirstName = sc.next();
                }
                System.out.print("ENTER CUSTOMERS LAST NAME :  ");
                if (sc.hasNext()) {
                    String pasSecondName = sc.nextLine();
                    while (!pasSecondName.matches("[a-zA-Z ]+")) {
                        System.out.print("PLEASE ENTER A VALID NAME!! : ");
                        pasSecondName = sc.next();
                    }
                    System.out.print("ENTER CUSTOMER VEHICLE NO : ");
                    if (sc.hasNext()) {
                        String pasVehicleNo = sc.nextLine();
                        System.out.print("ENTER THE NUMBER OF LITRES NEEDED : ");
                        if (sc.hasNextInt()) {
                            int pasLitresNeeded = sc.nextInt();
                            if (available) {
                                int smallestQueue = selectSmallestQueue();

                                fuelQueArray[smallestQueue].addPassenger(pasFirstName, pasSecondName, pasVehicleNo, pasLitresNeeded);
                                System.out.println(" ");
                                System.out.println(pasFirstName + " " + pasSecondName + " HAS BEEN ADDED TO THE QUEUE O" + (smallestQueue + 1));
                                fuelStock = fuelStock - pasLitresNeeded;

                                if (fuelStock<=500){
                                    System.out.println("WARNING ! ONLY "+fuelStock+" LITRES REMAINING IN THE STOCK");
                                }
                                break;
                            } else {
                                System.out.println("----------------------------------------");
                                System.out.println("ALL QUEUES ARE FULL!!");

                                System.out.println("PASSENGER WILL BE ADDED TO THE WAITING QUEUE");

                                waitingQueue.add(new Passenger(pasFirstName,pasSecondName,pasVehicleNo,pasLitresNeeded));
                                System.out.println(" ");
                                System.out.println(pasFirstName + " " + pasSecondName + " HAS BEING ADDED TO THE WAITING QUEUE");
                                fuelStock = fuelStock - pasLitresNeeded;

                                if (fuelStock<=500){
                                    System.out.println("WARNING ! ONLY "+fuelStock+" LITRES REMAINING IN THE STOCK");
                                }
                                break;
                            }
                        } else {
                            System.out.println("PLEASE ENTER VALID NAME");
                            continue;
                        }
                    } else {
                        System.out.println("PLEASE ENTER VALID VEHICLE NO");
                        continue;
                    }
                }else {
                    System.out.println("PLEASE ENTER VALID LAST NAME");
                    continue;
                }
            }else {
                System.out.println("PLEASE ENTER VALID FIRST NAME");
                continue;

            }
        }
    }

    public static void removeSpecificPassenger(){

        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.print("ENTER THE QUEUE NO OF THE PASSENGER : ");
            if (sc.hasNextInt()){ // Input validation
                int indexOfQueue = sc.nextInt();
                if (indexOfQueue>0 && indexOfQueue<6){
                    System.out.println(" ");
                    viewAllQue();
                    System.out.print("ENTER THE INDEX NO OF THE CUSTOMER YOU WISH TO REMOVE: ");
                    if (sc.hasNextInt()){
                        int indexOfPas = sc.nextInt();
                        if (indexOfPas>0 && indexOfPas<7){

                            int sizeOfList = fuelQueArray[indexOfQueue-1].getPassengersSize();
                            if (indexOfPas<=sizeOfList){
                                System.out.println(fuelQueArray[indexOfQueue-1].getFirstName(indexOfPas-1)+" "+ fuelQueArray[indexOfQueue-1].getLastName(indexOfPas-1)+" HAS BEEN REMOVED FROM THE LIST");

                                fuelStock = fuelStock + fuelQueArray[indexOfQueue-1].getLitresNeeded(indexOfPas-1);

                                fuelQueArray[indexOfQueue-1].removePassenger(indexOfPas-1);

                                if (!waitingQueue.isEmpty()){
                                    fuelQueArray[indexOfQueue-1].addPassenger(waitingQueue.get(0).getPassengerFirstName(), waitingQueue.get(0).getPassengerLastName(), waitingQueue.get(0).getVehicleNumber(), waitingQueue.get(0).getNoOfLiters());
                                    waitingQueue.remove(0);
                                    break;
                                }

                                break;
                            }else {
                                System.out.println("NO CUSTOMER IN THE ENTERED POSITION. TRY WITH A VALID INDEX");
                                continue;
                            }
                        }else {
                            System.out.println("PLEASE ENTER NUMBER BETWEEN 1 AND 6");
                            continue;
                        }
                    }else {
                        System.out.println("ENTER VALID INTEGER");
                        continue;
                    }
                }else {
                    System.out.println("PLEASE ENTER NUMBER BETWEEN 1 AND 5");
                    continue;
                }
            }else {
                System.out.println("ENTER VALID NUMBER.");
                continue;
            }
        }
    }

    public static void removeServedPassenger(){

        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.print("ENTER THE QUEUE NO OF THE PASSSENGER: ");
            if (sc.hasNextInt()){
                int indexOfQueue = sc.nextInt();
                if (indexOfQueue>0 && indexOfQueue<6){
                    if (fuelQueArray[indexOfQueue-1].getPassengersSize()>0){

                        int incomeOfPas = fuelQueArray[indexOfQueue-1].getLitresNeeded(0)*litrePrice;
                        fuelQueArray[indexOfQueue-1].setIncome(incomeOfPas);

                        System.out.println(fuelQueArray[indexOfQueue-1].getFirstName(0)+" "+ fuelQueArray[indexOfQueue-1].getLastName(0)+" HAS BEEN SERVED");
                        fuelQueArray[indexOfQueue-1].removePassenger(0);

                        if (!waitingQueue.isEmpty()){
                            fuelQueArray[indexOfQueue-1].addPassenger(waitingQueue.get(0).getPassengerFirstName(), waitingQueue.get(0).getPassengerLastName(), waitingQueue.get(0).getVehicleNumber(), waitingQueue.get(0).getNoOfLiters());
                            waitingQueue.remove(0);
                            break;
                        }

                        break;
                    }else {
                        System.out.println("THERE ARE NO PASSENGERS IN THIS QUEUE");
                        continue;
                    }
                }else {
                    System.out.println("PLEASE ENTER NUMBER IN BETWEEN 1 AND 5");
                    continue;
                }
            }else {
                System.out.println("PLEASE ENTER VALID NUMBER");
                continue;
            }
        }
    }

    public static void sortPassengers(){


        ArrayList<String> fuelPump01 = fuelQueArray[0].getListOfPassengers();
        ArrayList<String> fuelPump02 = fuelQueArray[1].getListOfPassengers();
        ArrayList<String> fuelPump03 = fuelQueArray[2].getListOfPassengers();
        ArrayList<String> fuelPump04 = fuelQueArray[3].getListOfPassengers();
        ArrayList<String> fuelPump05 = fuelQueArray[4].getListOfPassengers();


        System.out.print("FUEL QUEUE 01 : ");
        sortAndPrint(fuelPump01);
        System.out.println(" ");
        System.out.print("FUEL QUEUE 02 : ");
        sortAndPrint(fuelPump02);
        System.out.println(" ");
        System.out.print("FUEL QUEUE 03 : ");
        sortAndPrint(fuelPump03);
        System.out.println(" ");
        System.out.print("FUEL QUEUE 04 : ");
        sortAndPrint(fuelPump04);
        System.out.println(" ");
        System.out.print("FUEL QUEUE 05 : ");
        sortAndPrint(fuelPump05);
        System.out.println(" ");
    }

    public static void sortAndPrint(ArrayList<String> fuelPump) {


        Collections.sort(fuelPump, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] split1 = o1.split(" ");
                String[] split2 = o2.split(" ");
                String firstName1 = split1[0];
                String lastName1 = split1[1];
                String firstName2 = split2[0];
                String lastName2 = split2[1];
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

    public static void storeProgramData() throws IOException {

        Scanner sc = new Scanner(System.in);
        FileWriter myWriter = new FileWriter("QueueData.txt");
        System.out.println("ARE YOU SURE YOU WANT TO OVERWRITE THE DATA AND SAVE THE PROGRAM?");
        System.out.print("ENTER Y / N : ");
        if (sc.hasNextLine()){
            String ans = sc.nextLine().toUpperCase();
            if (ans.equals("Y")){

                for (int x=0; x<5; x++){
                    for (int y = 0; y< fuelQueArray[x].getPassengersSize(); y++){
                        myWriter.write(fuelQueArray[x].getPassengerString(y));
                    }
                    if (fuelQueArray[x].getPassengersSize()< fuelQueArray[x].getQueueLength()){
                        for (int z = 0; z< fuelQueArray[x].getQueueLength()- fuelQueArray[x].getPassengersSize(); z++){
                            myWriter.write("\n");
                        }
                    }
                }

                for (int z=0; z<5; z++){
                    myWriter.write(fuelQueArray[z].getIncome()+"\n");
                }

                myWriter.write(String.valueOf(fuelStock+"\n"));


                for (int i = 0; i< waitingQueue.size(); i++){
                    myWriter.write(getWaitingList(i));
                }

                myWriter.close();
                System.out.println("DATA HAS BEEN SAVED SUCCESSFULLY");
            }else {
                System.out.println("SAVED DATA WON'T BE OVERWRITTEN");
            }
        }else{
            System.out.println("PLEASE ENTER VALID INPUT");
        }
    }

    public static void loadProgramData() throws FileNotFoundException {


        File dataFile = new File("QueueData.txt");
        Scanner read = new Scanner(dataFile);
        Scanner sc = new Scanner(System.in);

        System.out.println("ARE YOU SURE YOU WANT TO LOAD THE DATA AND OVERWRITE THE PROGRAM?");
        System.out.print("ENTER Y/N : ");
        if (sc.hasNextLine()){
            String ans = sc.nextLine().toUpperCase();
            if (ans.equals("Y")){

                for (int x=0; x<5; x++){
                    fuelQueArray[x].clearData();
                }

                String passengerDetails;
                for (int x=0; x<5; x++){
                    for (int y=0; y<6; y++){
                        passengerDetails = read.nextLine();

                        String[] parts = passengerDetails.split(",");

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
                for (int z=0; z<5; z++){
                    income = read.nextLine();
                    fuelQueArray[z].setIncome(Integer.parseInt(income));
                }
                int newFuelStock = read.nextInt();
                fuelStock = newFuelStock;

                String waitingListDetails;

                while (read.hasNextLine()){
                    waitingListDetails = read.nextLine();

                    String[] sections = waitingListDetails.split(",");

                    if (waitingListDetails.length()>2){
                        String waitFirstName = sections[0];
                        String waitLastName = sections[1];
                        String waitVehicleNo = sections[2];
                        int waitLitresNeeded = Integer.parseInt(sections[3]);
                        waitingQueue.add(new Passenger(waitFirstName,waitLastName,waitVehicleNo,waitLitresNeeded));

                        fuelStock = fuelStock - waitLitresNeeded;
                    }
                }

                read.close();
                System.out.println("DATA LOADED SUCCESSFULLY");

            } else {
                System.out.println("DATA WON'T BE OVERWRITTEN");
            }


        }else {
            System.out.println("PLEASE ENTER VALID INPUT");
        }
    }

    public static void addNewFuelStock(){

        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.print("ENTER THE FUEL AMOUNT IN LITRES: ");
            if (sc.hasNextInt()){
                int newStock = sc.nextInt();
                fuelStock = fuelStock + newStock;
                System.out.println(newStock+" LITRES ADDED TO THE STOCK");
                break;
            }else {
                System.out.println("ENTER VALID AMOUNT");
                continue;
            }
        }
    }

    public static void incomeOfQueues(){

        for (int x=0; x<5; x++){
            int income = fuelQueArray[x].getIncome();
            System.out.println("INCOME OF FUEL QUEUE "+(x+1)+" = Rs."+income);
        }

    }

    public static boolean checkSpaceAvailability() {

        boolean available = false;
        for (int x=0; x<5; x++){
            if (fuelQueArray[x].getPassengersSize()<6){
                available = true;
            }
        }
        return available;
    }

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

    public static String getWaitingList(int x){
        String data = null;
        String firstName = waitingQueue.get(x).getPassengerFirstName();
        String lastName = waitingQueue.get(x).getPassengerLastName();
        String vehicleNo = waitingQueue.get(x).getVehicleNumber();
        String litresNeeded = String.valueOf((waitingQueue.get(x).getNoOfLiters()));
        data = firstName+","+lastName+","+vehicleNo+","+litresNeeded+"\n";
        return data;
    }

}