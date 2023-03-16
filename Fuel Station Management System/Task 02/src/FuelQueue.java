import java.util.ArrayList;
// Create an object for Fuel Queues
public class FuelQueue {

    private ArrayList<Passenger> passengersList =new ArrayList<Passenger>(); // Create an arraylist of Passenger objects to store data

    private int income = 0; // Creating variable to store income of each queue.

    private int queueLength; // Max number of passengers per queue

    public int getQueueLength() {
        return queueLength;
    } // Getter for the max length of a queue

    // Create a constructor for the FuelQue object
    public FuelQueue(int fuelQueueLength) {
        this.queueLength = fuelQueueLength;
    }

    // Method to add passengers to the arraylist of passengers
    public void addPassenger(String passengerFirstName, String passengerLastName, String vehicleNumber, int noOfLiters) {
        passengersList.add(new Passenger(passengerFirstName,passengerLastName,vehicleNumber,noOfLiters));
    }

    // Method to remove a specific passenger from the arraylist
    public void removePassenger(int x){
        passengersList.remove(x);
    }

    // Method to get the number of empty spaces in the arraylist
    public int getEmptySpaces(){
        return queueLength-passengersList.size();
    }

    // Method to get the number of passengers in the arraylist
    public int getPassengersSize(){
        return passengersList.size();
    }

    // Getters and setters to access attributes of each passenger
    public String getFirstName(int x){
        return passengersList.get(x).getPassengerFirstName();
    }

    public String getLastName(int x){
        return passengersList.get(x).getPassengerLastName();
    }

    public String getVehicleNo(int x){
        return passengersList.get(x).getVehicleNo();
    }

    public int getLitresNeeded(int x){
        return passengersList.get(x).getFuelNeededLitres();
    }

    // Getter and setter to access the income variable
    public int getIncome(){
        return income;
    }

    public void setIncome(int newIncome){
        this.income = this.income+newIncome;
    }

    // Method to get the list of passengers as an arraylist for sorting
    public ArrayList getListOfPassengers(){
        ArrayList<String> listOfPassengers = new ArrayList<String>();
        for (int x=0; x<getPassengersSize();x++) {
            String firstName = passengersList.get(x).getPassengerFirstName();
            String lastName = passengersList.get(x).getPassengerLastName();
            listOfPassengers.add(firstName+" "+lastName);
        }
        return listOfPassengers;
    }

    // Method to store data in the text file in a specific way
    public String getPassengerString(int x){
        String data = null;
        String firstName = passengersList.get(x).getPassengerFirstName();
        String lastName = passengersList.get(x).getPassengerLastName();
        String vehicleNo = passengersList.get(x).getVehicleNo();
        String litresNeeded = String.valueOf((passengersList.get(x).getFuelNeededLitres()));
        data = firstName+","+lastName+","+vehicleNo+","+litresNeeded+"\n";
        return data;
    }

    // Method to clear all the data in a FuelQueue object
    public void clearData(){
        passengersList.clear();
    }


}
