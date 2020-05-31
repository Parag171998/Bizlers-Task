package com.example.bizlerstask.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class VehicleInFoRoom {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private byte[] imageByte;

    @ColumnInfo
    private String compName;

    @ColumnInfo
    private String number;

    @ColumnInfo
    private String model;

    @ColumnInfo
    private String varient;


    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }

    public VehicleInFoRoom(byte[] imageByte, String compName, String number, String model, String varient) {
        this.imageByte = imageByte;
        this.compName = compName;
        this.number = number;
        this.model = model;
        this.varient = varient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVarient() {
        return varient;
    }

    public void setVarient(String varient) {
        this.varient = varient;
    }


}
