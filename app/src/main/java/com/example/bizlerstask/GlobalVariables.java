package com.example.bizlerstask;

import com.example.bizlerstask.Adapter.VehicleAdapter;
import com.example.bizlerstask.Model.VehicleInFo;
import com.example.bizlerstask.Room.MyappDatabse;

import java.util.ArrayList;
import java.util.List;

public class GlobalVariables {

    public static MyappDatabse myappDatabse;

    public static List<VehicleInFo> vehicleInFoList = new ArrayList<>();

    public  static VehicleAdapter vehicleAdapter = null;
}
