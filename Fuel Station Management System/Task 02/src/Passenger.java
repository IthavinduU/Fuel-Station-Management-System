// Create an object for passengers
public class Passenger {

    // Declare attributes

    private String passengerFirstName;
    private String passengerLastName;
    private String vehicleNo;
    private int fuelNeededLitres;

    // Creating a constructor

    public Passenger(String passengerFirstName, String passengerLastName, String vehicleNumber, int noOfLiters) {
        this.passengerFirstName = passengerFirstName;
        this.passengerLastName = passengerLastName;
        this.vehicleNo = vehicleNumber;
        this.fuelNeededLitres = noOfLiters;
    }

    // Getters and setters to access the attributes.

    public String getPassengerFirstName() {
        return passengerFirstName;
    }

    public String getPassengerLastName() {
        return passengerLastName;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public int getFuelNeededLitres() {
        return fuelNeededLitres;
    }

    public void setPassengerFirstName(String passengerFirstName) {
        this.passengerFirstName = passengerFirstName;
    }

    public void setPassengerLastName(String passengerLastName) {
        this.passengerLastName = passengerLastName;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public void setFuelNeededLitres(int fuelNeededLitres) {
        this.fuelNeededLitres = fuelNeededLitres;
    }

}

