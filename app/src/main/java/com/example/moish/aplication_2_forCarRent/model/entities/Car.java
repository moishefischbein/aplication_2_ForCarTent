package com.example.moish.aplication_2_forCarRent.model.entities;

/**
 * Created by Daniel on 14/11/2017.
 */

public class Car {
    int     fixedBranch;
    int     model;
    double  kilometersTraveled;
    int     carNumber_id;

    public Car(int fixedBranch, int model, double kilometersTraveled, int carNumber_id) {
        this.fixedBranch = fixedBranch;
        this.model = model;
        this.kilometersTraveled = kilometersTraveled;
        this.carNumber_id = carNumber_id;
    }

    public Car() {
        fixedBranch = 0;
        model = 0;
        kilometersTraveled = 0;
        carNumber_id = 0;
    }

    public int getFixedBranch() {
        return fixedBranch;
    }

    public void setFixedBranch(int fixedBranch) {
        this.fixedBranch = fixedBranch;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public double getKilometersTraveled() {
        return kilometersTraveled;
    }

    public void setKilometersTraveled(double kilometersTraveled) {
        this.kilometersTraveled = kilometersTraveled;
    }

    public int getCarNumber_id() {
        return carNumber_id;
    }

    public void setCarNumber_id(int carNumber_id) {
        this.carNumber_id = carNumber_id;
    }
}