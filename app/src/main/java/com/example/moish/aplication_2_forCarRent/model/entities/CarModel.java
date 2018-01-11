package com.example.moish.aplication_2_forCarRent.model.entities;

/**
 * Created by moish on 13/11/2017.
 */

public class CarModel {

    int modelCode_id;
    String companyName;
    String modelName;
    int motorVolume;
    int isAutomatic;
    int numberOfSeats;

    public int getModelCode_id() {
        return modelCode_id;
    }

    public void setModelCode(int modelCode) {
        this.modelCode_id = modelCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getMotorVolume() {
        return motorVolume;
    }

    public void setMotorVolume(int motorVolume) {
        this.motorVolume = motorVolume;
    }

    public int getAutomatic() {
        return isAutomatic;
    }

    public void setAutomatic(int automatic) {
      this.isAutomatic = automatic;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int seats) {
        this.numberOfSeats = seats;
    }

    public CarModel() {

    }

    public CarModel(int modelCode_id, String companyName, String modelName,
                    int motorVolume, int isAutomatic, int numberOfSeats) {
        this.modelCode_id = modelCode_id;
        this.companyName = companyName;
        this.modelName = modelName;
        this.motorVolume = motorVolume;
        this.isAutomatic = isAutomatic;
        this.numberOfSeats = numberOfSeats;
    }
}