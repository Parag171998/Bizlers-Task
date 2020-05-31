package com.example.bizlerstask.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bizlerstask.Model.VehicleInFo;
import com.example.bizlerstask.R;
import com.example.bizlerstask.VehicleDetailsActivity;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {

    List<VehicleInFo> vehicleInFoList;
    Context context;

    public VehicleAdapter(List<VehicleInFo> vehicleInFoList, Context context) {
        this.vehicleInFoList = vehicleInFoList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.custom_recycler_layout,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        byte [] buf = vehicleInFoList.get(position).getImageByte();

        Bitmap bitmap = BitmapFactory.decodeByteArray(buf , 0, buf.length);

        holder.img.setImageBitmap(bitmap);
        holder.number.setText("No :"+vehicleInFoList.get(position).getNumber());
        holder.compName.setText("Make : "+vehicleInFoList.get(position).getCompName());
    }

    @Override
    public int getItemCount() {
        return vehicleInFoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView compName;

        TextView number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.vehicleImg);
            compName = itemView.findViewById(R.id.vehicleName);
            number = itemView.findViewById(R.id.vehicleNo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, VehicleDetailsActivity.class);
                    intent.putExtra("pos",getAdapterPosition());
                    context.startActivity(intent);
                }
            });
        }
    }
}
