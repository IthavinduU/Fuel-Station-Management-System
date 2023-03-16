package com.example.task04;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class HelloController {
    @FXML
    private TextField filterField;

    @FXML
    private TableView<Passenger> table;

    @FXML
    private TableColumn<Passenger, String> firstName;

    @FXML
    private TableColumn<Passenger, String> lastName;

    @FXML
    private TableColumn<Passenger, String> vehicleNo;

    @FXML
    private TableColumn<Passenger, Integer> litres;

    @FXML
    private TableColumn<Passenger, String> queue;


    static ObservableList<Passenger> list = FXCollections.observableArrayList(


    );


    public HelloController() throws FileNotFoundException {
    }


    public void initialize() {

        firstName.setCellValueFactory(new PropertyValueFactory<Passenger, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Passenger, String>("lastName"));
        vehicleNo.setCellValueFactory(new PropertyValueFactory<Passenger, String>("vehicleNo"));
        litres.setCellValueFactory(new PropertyValueFactory<Passenger, Integer>("litres"));
        queue.setCellValueFactory(new PropertyValueFactory<Passenger, String>("queue"));
        table.setItems(list);
        // Reference - https://www.youtube.com/watch?v=-Aud0cDh-J8
        File dataFile = new File("D:\\University Files\\IIT\\Level 4\\Software Development II\\SDII Coursework\\w1899297_CW\\Task 03\\QueueData.txt"); // Create a File object
        Scanner read = null; // Create a Scanner object to read the file
        try {
            read = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner sc = new Scanner(System.in);


        String passengerDetails;
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 6; y++) {
                passengerDetails = read.nextLine();
                String[] parts = passengerDetails.split(",");
                String count = "QueueNo."+(x+1);
                // Check if the line is an empty space
                if (passengerDetails.length() > 2) {
                    String pasFirstName = parts[0];
                    String pasLastName = parts[1];
                    String vehicleNo = parts[2];
                    int litresNeeded = Integer.parseInt(parts[3]);
                    list.add(new Passenger(pasFirstName, pasLastName, vehicleNo, litresNeeded, count));
                }
            }
        }

        String unimportant;
        for (int y=0; y<6; y++){
            unimportant = read.nextLine();
        }

        String waitingListDetails;
        // Use a while loop until there is no data
        while (read.hasNextLine()){
            waitingListDetails = read.nextLine();
            // Store each line into an array and separate the data using commas
            String[] sections = waitingListDetails.split(",");
            // Check if the line is an empty space
            // Add passenger using the comma separated values in the array above
            if (waitingListDetails.length()>2){
                String waitFirstName = sections[0];
                String waitLastName = sections[1];
                String waitVehicleNo = sections[2];
                int waitLitresNeeded = Integer.parseInt(sections[3]);
                list.add(new Passenger(waitFirstName,waitLastName,waitVehicleNo,waitLitresNeeded,"Waiting list"));
            }

        }


        FilteredList<Passenger> filteredData = new FilteredList<>(list, b-> true);

        filterField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate(passenger -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (passenger.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (passenger.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else if (passenger.getVehicleNo().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;
                else
                    return false;

            });
        });

        SortedList<Passenger> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedData);
    }

    }



