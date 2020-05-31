package com.example.bizlerstask.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {VehicleInFoRoom.class},version = 1)
public abstract class MyappDatabse extends RoomDatabase {

    public abstract Mydao mydao();

}
