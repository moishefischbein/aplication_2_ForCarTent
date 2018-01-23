package com.example.moish.aplication_2_forCarRent.model.backend;

import android.content.ContentValues;
import com.example.moish.aplication_2_forCarRent.model.entities.*;

import java.util.List;

/**
 * Created by moish on 16/11/2017.
 */

public interface DB_manager {
    long addClient(ContentValues client);
    boolean removeClient(long id);
    boolean updateClient(long id, ContentValues values);
    List<Client> getClients();

    long addBranch(ContentValues branch);
    boolean removeBranch(long id);
    boolean updateBranch(long id, ContentValues values);
    List<Branch> getBranchs();

    long addCar(ContentValues car);
    boolean removeCar(long id);
    long updateCar(ContentValues values);
    List<Car> getCar();
    public List<Car> getFreeCarOfBranch(Branch branch);

    long addCarReserve(ContentValues carReserve);
    boolean removeCarReserve(int id);
    void updateCarReserve( ContentValues values);
    List<CarReserve> getCarReserve();

    long addCarModel(ContentValues carModel);
    boolean removeCarModel(long id);
    boolean updateCarModel(long id, ContentValues values);
    List<CarModel> getCarModels();

    public boolean isUpdate();
    public void addFreeCars(ContentValues values);




    //-----------------------------//
    public boolean isClientDefined(int id);
    public List<Car> getFreeCars();





}
