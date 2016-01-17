package com.awesheet.models;

import com.awesheet.enums.ChartType;

public abstract class Chart {
    protected int type;
    protected String title;
    protected String nameX;
    protected String nameY;
    protected Cell[] inputs;
    protected byte[] imageData;

    protected Chart(int type, Cell[] inputs) {
        this.type = type;
        this.inputs = inputs;
        this.title = "";
        this.nameX = "";
        this.nameY = "";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setNameX(String name) {
        nameX = name;
    }

    public String getNameX() {
        return nameX;
    }

    public void setNameY(String name) {
        nameY = name;
    }

    public String getNameY() {
        return nameY;
    }

    public Cell[] getInputs() {
        return inputs;
    }

    public int getType() {
        return type;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public abstract boolean generateImageData();
}
