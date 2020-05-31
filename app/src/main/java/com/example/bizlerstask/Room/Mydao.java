package com.example.bizlerstask.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Mydao {

    @Insert
    public void addVehicle(VehicleInFoRoom roomResult);

    @Query("select * from VehicleInFoRoom")
    public List<VehicleInFoRoom> getAllVehicles();

    @Query("DELETE FROM VehicleInFoRoom")
    public void deleteAll();

    @Query("select * from VehicleInFoRoom where id = :id")
    public VehicleInFoRoom chekIfPresent(String id);

    @Delete
    public void deleteVehicle(VehicleInFoRoom roomResult);

    @Update
    public void updateVehicle(VehicleInFoRoom vehicleInFoRoom);
}
