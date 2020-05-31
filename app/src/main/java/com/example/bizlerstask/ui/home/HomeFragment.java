package com.example.bizlerstask.ui.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import com.example.bizlerstask.GlobalVariables;
import com.example.bizlerstask.Model.VehicleInFo;
import com.example.bizlerstask.R;
import com.example.bizlerstask.Room.MyappDatabse;
import com.example.bizlerstask.Room.VehicleInFoRoom;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    private EditText number,comName,model,varient;
    private ImageView image;
    private Button addBtn;
    private static final int PICK_FROM_GALLERY = 1;

    private Uri selectedImage = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);




        number = root.findViewById(R.id.vehNumber);
        comName = root.findViewById(R.id.name);
        model = root.findViewById(R.id.model);
        varient = root.findViewById(R.id.varient);

        image = root.findViewById(R.id.vehImage);

        addBtn = root.findViewById(R.id.btnAdd);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                    } else {
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(galleryIntent, PICK_FROM_GALLERY);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedImage == null || number.getText().toString().isEmpty()
                        || comName.getText().toString().isEmpty() || model.getText().toString().isEmpty()
                    || varient.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "Please fill all the information", Toast.LENGTH_SHORT).show();
                }
                else {
                    InputStream iStream = null;
                    try {
                        iStream = getContext().getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        byte[] inputData = getBytes(iStream);

                        VehicleInFoRoom vehicleInFoRoom = new VehicleInFoRoom(inputData,comName.getText().toString(),
                                number.getText().toString(),model.getText().toString(),varient.getText().toString());

                        GlobalVariables.myappDatabse.mydao().addVehicle(vehicleInFoRoom);

                        VehicleInFo vehicleInFo = new VehicleInFo(vehicleInFoRoom.getId(),vehicleInFoRoom.getImageByte()
                        ,vehicleInFoRoom.getCompName(),vehicleInFoRoom.getNumber(),vehicleInFoRoom.getModel(),vehicleInFoRoom.getVarient());

                        GlobalVariables.vehicleInFoList.add(vehicleInFo);

                        if(GlobalVariables.vehicleAdapter != null)
                        {
                            GlobalVariables.vehicleAdapter.notifyDataSetChanged();
                        }
                        Toast.makeText(getContext(), "Vehicle Information Successfully Added ", Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return root;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults)
    {
        switch (requestCode) {
            case PICK_FROM_GALLERY:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, PICK_FROM_GALLERY);
                } else {
                    Toast.makeText(getContext(), "App didn't allow to access gallery", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_FROM_GALLERY)
        {
            if(data != null) {
                image.setImageURI(data.getData());
                selectedImage = data.getData();
            }
        }
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }
}
