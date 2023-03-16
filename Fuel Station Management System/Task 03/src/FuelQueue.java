import java.util.ArrayList;

public class FuelQueue {

    private ArrayList<Passenger> passengersList =new ArrayList<Passenger>();

    private int income = 0;

    private int queueLength;

    public int getQueueLength() {
        return queueLength;
    }


    public FuelQueue(int fuelQueueLength) {
        this.queueLength = fuelQueueLength;
    }


    public void addPassenger(String passengerFirstName, String passengerLastName, String vehicleNumber, int noOfLiters) {
        passengersList.add(new Passenger(passengerFirstName,passengerLastName,vehicleNumber,noOfLiters));
    }


    public void removePassenger(int x){
        passengersList.remove(x);
    }


    public int getEmptySpaces(){
        return queueLength-passengersList.size();
    }


    public int getPassengersSize(){
        return passengersList.size();
    }


    public String getFirstName(int x){
        return passengersList.get(x).getPassengerFirstName();
    }

    public String getLastName(int x){
        return passengersList.get(x).getPassengerLastName();
    }

    public String getVehicleNo(int x){
        return passengersList.get(x).getVehicleNumber();
    }

    public int getLitresNeeded(int x){
        return passengersList.get(x).getNoOfLiters();
    }


    public int getIncome(){
        return income;
    }

    public void setIncome(int newIncome){
        this.income = this.income+newIncome;
    }


    public ArrayList getListOfPassengers(){
        ArrayList<String> listOfPassengers = new ArrayList<String>();
        for (int x=0; x<getPassengersSize();x++) {
            String firstName = passengersList.get(x).getPassengerFirstName();
            String lastName = passengersList.get(x).getPassengerLastName();
            listOfPassengers.add(firstName+" "+lastName);
        }
        return listOfPassengers;
    }


    public String getPassengerString(int x){
        String data = null;
        String firstName = passengersList.get(x).getPassengerFirstName();
        String lastName = passengersList.get(x).getPassengerLastName();
        String vehicleNo = passengersList.get(x).getVehicleNumber();
        String litresNeeded = String.valueOf((passengersList.get(x).getNoOfLiters()));
        data = firstName+","+lastName+","+vehicleNo+","+litresNeeded+"\n";
        return data;
    }


    public void clearData(){
        passengersList.clear();
    }


}
