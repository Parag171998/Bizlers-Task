package com.example.bizlerstask.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bizlerstask.Adapter.VehicleAdapter;
import com.example.bizlerstask.GlobalVariables;
import com.example.bizlerstask.Model.VehicleInFo;
import com.example.bizlerstask.R;
import com.example.bizlerstask.Room.VehicleInFoRoom;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<VehicleInFoRoom> vehicleInFoRoomList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        GlobalVariables.vehicleAdapter = new VehicleAdapter(GlobalVariables.vehicleInFoList,getContext());

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(GlobalVariables.vehicleAdapter);

        return root;
    }
}
