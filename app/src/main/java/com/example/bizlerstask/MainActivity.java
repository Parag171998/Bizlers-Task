package com.example.bizlerstask;

import android.os.Bundle;

import com.example.bizlerstask.Model.VehicleInFo;
import com.example.bizlerstask.Room.MyappDatabse;
import com.example.bizlerstask.Room.VehicleInFoRoom;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<VehicleInFoRoom> vehicleInFoRoomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


        GlobalVariables.myappDatabse = Room.databaseBuilder(this, MyappDatabse.class, "vehicleinfodb").allowMainThreadQueries().build();

        //GlobalVariables.myappDatabse.mydao().deleteAll();

        if(GlobalVariables.myappDatabse.mydao().getAllVehicles() != null)
        {
            vehicleInFoRoomList = new ArrayList<>();
            vehicleInFoRoomList.addAll(GlobalVariables.myappDatabse.mydao().getAllVehicles());

            for(VehicleInFoRoom vehicleInFoRoom : vehicleInFoRoomList)
            {
                VehicleInFo vehicleInFo = new VehicleInFo(vehicleInFoRoom.getId(),vehicleInFoRoom.getImageByte()
                        ,vehicleInFoRoom.getCompName(),vehicleInFoRoom.getNumber(),vehicleInFoRoom.getModel(),vehicleInFoRoom.getVarient());

                GlobalVariables.vehicleInFoList.add(vehicleInFo);
            }
        }
        getSupportActionBar().hide();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

}
