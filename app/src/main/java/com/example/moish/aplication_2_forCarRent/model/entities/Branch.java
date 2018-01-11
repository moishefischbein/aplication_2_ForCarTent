package com.example.moish.aplication_2_forCarRent.model.entities;

/**
 * Created by moish on 13/11/2017.
 */
public class Branch {

    String City, Street, AdressNumber;
    int    NumberOfParkingAvailable;
    int    BranchNumber_id;

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getAdressNumber() {
        return AdressNumber;
    }

    public void setAdressNumber(String adressNumber) {
        AdressNumber = adressNumber;
    }

    public int getNumberOfParkingAvailable() {
        return NumberOfParkingAvailable;
    }

    public void setNumberOfParkingAvailable(int numberOfParkingAvailable) {
        NumberOfParkingAvailable = numberOfParkingAvailable;
    }

    public int getBranchNumber_id() {
        return BranchNumber_id;
    }

    public void setBranchNumber_id(int branchNumber_id) {
        BranchNumber_id = branchNumber_id;
    }

    public Branch() {
    }

    public Branch(String city, String street, String adressNumber, int numberOfParkingAvailable, int branchNumberId) {
        City = city;
        Street = street;
        AdressNumber = adressNumber;
        NumberOfParkingAvailable = numberOfParkingAvailable;
        BranchNumber_id = branchNumberId;
    }


}
