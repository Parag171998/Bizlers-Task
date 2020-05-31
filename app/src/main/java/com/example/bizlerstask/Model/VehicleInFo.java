package com.example.bizlerstask.Model;

public class VehicleInFo {
    private int id;

    private byte[] imageByte;

    private String compName;

    private String number;

    private String model;

    private String varient;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
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

    public VehicleInFo(int id, byte[] imageByte, String compName, String number, String model, String varient) {
        this.id = id;
        this.imageByte = imageByte;
        this.compName = compName;
        this.number = number;
        this.model = model;
        this.varient = varient;
    }
}
