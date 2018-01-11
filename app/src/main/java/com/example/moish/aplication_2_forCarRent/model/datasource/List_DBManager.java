package com.example.moish.aplication_2_forCarRent.model.datasource;

import android.content.ContentValues;
import com.example.moish.aplication_2_forCarRent.model.*;
import com.example.moish.aplication_2_forCarRent.model.backend.DB_manager;
import com.example.moish.aplication_2_forCarRent.model.backend.Functions;
import com.example.moish.aplication_2_forCarRent.model.entities.Branch;
import com.example.moish.aplication_2_forCarRent.model.entities.Car;
import com.example.moish.aplication_2_forCarRent.model.entities.CarReserve;
import com.example.moish.aplication_2_forCarRent.model.entities.Client;

import android.util.Log;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by moish on 16/11/2017.
 */


    public class List_DBManager implements DB_manager {

    static List<Client> clients;
    static List<Branch> branches;
    static List<CarReserve> carReserves;
    static List<Car> cars;

    static {
        clients = new ArrayList<>();
        branches = new ArrayList<>();
        carReserves = new ArrayList<>();
        cars = new ArrayList<>();
    }


    //----------------------Client Functions-----------------//

    public boolean isThisClientExist(long id)
    {

        //if exist this Client in the List of clients
        for (Iterator<Client> iter = clients.iterator(); iter.hasNext(); ) {
            Client element = iter.next();
            if(element.getId()== id);
                return  true ;
        }
        return false;

    }

    @Override
    public long addClient(ContentValues client) {

        Client c = Functions.contentValuesToClient(client);
        Log.d("ListDBManager", c.toString());
        clients.add(c);
        return c.getId();}
/*
        boolean isExistThisClient = isThisClientExist(c.getId());
        if(isExistThisClient == false)
        {
          clients.add(c);
          return c.getId();
        }
    return 0;
    }
*/


    @Override
    public boolean removeClient(long id) {

        for (Iterator<Client> iter = clients.iterator(); iter.hasNext(); ) {
            Client element = iter.next();
            if(element.getId()== id);

            iter.remove();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateClient(long id, ContentValues values) {
        return false;
    }

    @Override
    public List<Client> getClients() {
        return clients;
    }

    //--------------------Car Model Functions------------------//
    @Override
    public long addCarModel(ContentValues carModel) {

        carModels.add((Functions.contentValuesToCarModel(carModel)));
        return Functions.contentValuesToCarModel(carModel).getModelCode_id();
    }

    @Override
    public boolean removeCarModel(long id) {
        for (CarModel item : carModels) {
            if(item.getModelCode_id() == id) {
                carModels.remove(item);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateCarModel(long id, ContentValues values) {
        return false;
    }

    @Override
    public List<CarModel> getCarModels() {
        return carModels;
    }

    //-----------------------Branch Function-----------------//
    @Override
    public long addBranch(ContentValues branch) {

        branches.add(Functions.contentValuesToBranch(branch));
        return Functions.contentValuesToBranch(branch).getBranchNumber_id();
    }

    @Override
    public boolean removeBranch(long id) {

        for (Branch item : branches) {
            if(item.getBranchNumber_id() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateBranch(long id, ContentValues values) {
        return false;
    }

    @Override
    public List<Branch> getBranchs() {
      return branches;
    }

    //---------------------------Car Function------------------//
    @Override
    public long addCar(ContentValues car) {
        Car carToAdd = Functions.contentValuesToCar(car);
        cars.add(carToAdd);
        return carToAdd.getCarNumber_id();
    }

    @Override
    public boolean removeCar(long id) {
        Boolean isPartOf = false;
        Car carToRemove = null;
        for (Car c:cars) {
            if(c.getCarNumber_id() == id) {
                isPartOf = true;
                carToRemove = c;
                break;
            }
        }
        cars.remove(carToRemove);
        return isPartOf;
    }

    @Override
    public boolean updateCar(long id, ContentValues values) {
        Car carToUpdate = Functions.contentValuesToCar(values);
        Car carToRemove = null;
        Boolean isPartOf = false;
        for (Car c: cars) {
            if(c.getCarNumber_id() == id) {
                carToRemove = c;
                isPartOf = true;
                break;
            }
        }
        cars.remove(carToRemove);
        cars.add(carToUpdate);
        return isPartOf;
    }

    @Override
    public List<Car> getCar() {
        return cars;
    }

    //------------------------Car Reserve Function----------------//
    @Override
    public long addCarReserve(ContentValues carReserve) {
        CarReserve theCarReserve = Functions.contentValuesToCarReserve(carReserve);
        carReserves.add(theCarReserve);
        return theCarReserve.getReserveNumber_id();
    }

    @Override
    public boolean removeCarReserve(int id) {
        Boolean isPartOf = false;
        CarReserve reserveToRemove = null;
        for (CarReserve c:carReserves) {
            if(c.getReserveNumber_id() == id) {
                isPartOf = true;
                reserveToRemove = c;
                break;
            }
        }
        carReserves.remove(reserveToRemove);
        return isPartOf;
    }

    @Override
    public boolean updateCarReserve(long id, ContentValues values) {
        CarReserve carReserveToUpdate = Functions.contentValuesToCarReserve(values);
        CarReserve carReserveToRemove = null;
        Boolean isPartOf = false;
        for (CarReserve c: carReserves) {
            if(c.getReserveNumber_id() == id) {
                carReserveToRemove = c;
                isPartOf = true;
                break;
            }
        }
        carReserves.remove(carReserveToRemove);
        carReserves.add(carReserveToUpdate);
        return isPartOf;
    }

    @Override
    public List<CarReserve> getCarReserve() {
        return carReserves;
    }

    @Override
    public boolean isUpdate() {
        return false;
    }

    @Override
    public boolean isClientDefined(int id) {
        return false;
    }

    @Override
    public List<com.example.moish.aplication_2_forCarRent.entities.Car> getFreeCars() {
        return null;
    }


}


