package com.example.task04;

public class Passenger {
    private String firstName;
    private String lastName;
    private String vehicleNo;
    private int litres;
    private String queue;

    // Create a constructor

    public Passenger(String firstName, String lastName, String vehicleNo, int litres, String queue) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.vehicleNo = vehicleNo;
        this.litres = litres;
        this.queue = queue;
    }



    //Create getters and setters to access the attributes


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public int getLitres() {
        return litres;
    }

    public String getQueue() {
        return queue;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public void setLitres(int litres) {
        this.litres = litres;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }
}


