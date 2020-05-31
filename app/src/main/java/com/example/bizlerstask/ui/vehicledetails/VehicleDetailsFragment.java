package com.example.bizlerstask.ui.vehicledetails;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bizlerstask.GlobalVariables;
import com.example.bizlerstask.Model.VehicleInFo;
import com.example.bizlerstask.R;
import com.example.bizlerstask.Room.VehicleInFoRoom;
import com.example.bizlerstask.VehicleDetailsActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class VehicleDetailsFragment extends Fragment {

    private EditText number,comName,model,varient;
    private ImageView image;
    private Button addBtn;
    private static final int PICK_FROM_GALLERY = 1;

    List<VehicleInFoRoom> vehicleInFoRoomList;
    VehicleInFoRoom updateVehicle;
    private Uri selectedImage = null;
    int pos;

    VehicleDetailsActivity vehicleDetailsActivity;
    public static VehicleDetailsFragment newInstance() {
        return new VehicleDetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.vehicle_details_fragment, container, false);

        vehicleInFoRoomList = new ArrayList<>();

        vehicleDetailsActivity = (VehicleDetailsActivity) getActivity();

        pos = vehicleDetailsActivity.pos;

        number = root.findViewById(R.id.vehNumber);
        comName = root.findViewById(R.id.name);
        model = root.findViewById(R.id.model);
        varient = root.findViewById(R.id.varient);

        image = root.findViewById(R.id.vehImage);

        addBtn = root.findViewById(R.id.btnAdd);

        inItInFo();

        vehicleInFoRoomList = GlobalVariables.myappDatabse.mydao().getAllVehicles();

        for(VehicleInFoRoom vehicleInFoRoom : vehicleInFoRoomList)
        {
            if(vehicleInFoRoom.getId() == GlobalVariables.vehicleInFoList.get(pos).getId())
            {
                updateVehicle = vehicleInFoRoom;
            }
        }
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (number.getText().toString().isEmpty()
                        || comName.getText().toString().isEmpty() || model.getText().toString().isEmpty()
                        || varient.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "Please fill all the information", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(selectedImage != null)
                    setImageBytes();

                    String sname = comName.getText().toString();

                    String snumber = number.getText().toString();

                    String smodel = model.getText().toString();


                    String svarient = varient.getText().toString();


                    updateVehicle.setCompName(sname);
                    updateVehicle.setNumber(snumber);
                    updateVehicle.setModel(smodel);
                    updateVehicle.setVarient(svarient);

                    GlobalVariables.myappDatabse.mydao().updateVehicle(updateVehicle);

                    GlobalVariables.vehicleInFoList.get(pos).setVarient(svarient);
                    GlobalVariables.vehicleInFoList.get(pos).setCompName(sname);
                    GlobalVariables.vehicleInFoList.get(pos).setModel(smodel);
                    GlobalVariables.vehicleInFoList.get(pos).setNumber(snumber);
                    Toast.makeText(getContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();

                    GlobalVariables.vehicleAdapter.notifyDataSetChanged();
                    getActivity().finish();
                }
            }

        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, PICK_FROM_GALLERY);
            }
        });

        return root;
    }

    private void setImageBytes() {
        InputStream iStream = null;
        try {
            iStream = getContext().getContentResolver().openInputStream(selectedImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            byte[] inputData = getBytes(iStream);

            GlobalVariables.vehicleInFoList.get(pos).setImageByte(inputData);
            updateVehicle.setImageByte(inputData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inItInFo() {
        byte [] buf = GlobalVariables.vehicleInFoList.get(pos).getImageByte();
        Bitmap bitmap = BitmapFactory.decodeByteArray(buf , 0, buf.length);

        image.setImageBitmap(bitmap);

        comName.setText(GlobalVariables.vehicleInFoList.get(pos).getCompName());
        model.setText(GlobalVariables.vehicleInFoList.get(pos).getModel());
        number.setText(GlobalVariables.vehicleInFoList.get(pos).getNumber());
        varient.setText(GlobalVariables.vehicleInFoList.get(pos).getVarient());

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
