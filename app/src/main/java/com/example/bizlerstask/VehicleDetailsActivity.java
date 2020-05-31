package com.example.bizlerstask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bizlerstask.Room.VehicleInFoRoom;
import com.example.bizlerstask.ui.vehicledetails.VehicleDetailsFragment;

import java.util.ArrayList;
import java.util.List;

public class VehicleDetailsActivity extends AppCompatActivity {

    protected ImageView img;
    protected TextView vehName,number,vehModel,varient;
    Button edit,remove;
    public int pos;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_details_activity);

        getSupportActionBar().hide();
        Intent intent = getIntent();
        pos = intent.getIntExtra("pos",-1);

        img = findViewById(R.id.dVehImg);
        vehName = findViewById(R.id.dName);
        number = findViewById(R.id.dNo);
        vehModel = findViewById(R.id.dModel);
        varient = findViewById(R.id.dVarient);

        edit = findViewById(R.id.btnEdit);
        remove = findViewById(R.id.btnRemove);

        setViews();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, VehicleDetailsFragment.newInstance())
                            .commitNow();
                }
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<VehicleInFoRoom> vehicleInFoRoomList = new ArrayList<>();
                vehicleInFoRoomList = GlobalVariables.myappDatabse.mydao().getAllVehicles();

                for(VehicleInFoRoom vehicleInFoRoom : vehicleInFoRoomList)
                {
                    if(vehicleInFoRoom.getId() == GlobalVariables.vehicleInFoList.get(pos).getId())
                    {
                       GlobalVariables.myappDatabse.mydao().deleteVehicle(vehicleInFoRoom);
                        Toast.makeText(VehicleDetailsActivity.this, "Vehicle Removed", Toast.LENGTH_SHORT).show();
                        GlobalVariables.vehicleInFoList.remove(pos);
                        GlobalVariables.vehicleAdapter.notifyDataSetChanged();
                        finish();
                    }
                }
            }
        });
    }

    private void setViews() {
        byte [] buf = GlobalVariables.vehicleInFoList.get(pos).getImageByte();
        Bitmap bitmap = BitmapFactory.decodeByteArray(buf , 0, buf.length);

        img.setImageBitmap(bitmap);

        vehName.setText("Company : "+GlobalVariables.vehicleInFoList.get(pos).getCompName());
        vehModel.setText("Model : "+GlobalVariables.vehicleInFoList.get(pos).getModel());
        number.setText("Car No : " +GlobalVariables.vehicleInFoList.get(pos).getNumber());
        varient.setText("Varient : "+GlobalVariables.vehicleInFoList.get(pos).getVarient());

    }
}
