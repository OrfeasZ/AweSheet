package gr.uoi.cs.cs122250.models;

import gr.uoi.cs.cs122250.enums.ChartType;

public abstract class Chart {
    protected ChartType type;
    protected String title;
    protected String nameX;
    protected String nameY;
    protected Cell[] inputs;
    protected byte[] imageData;

    protected Chart(ChartType type, Cell[] inputs) {
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
        return this.title;
    }

    public void setNameX(String name) {
        this.nameX = name;
    }

    public String getNameX() {
        return this.nameX;
    }

    public void setNameY(String name) {
        this.nameY = name;
    }

    public String getNameY() {
        return this.nameY;
    }

    public Cell[] getInputs() {
        return this.inputs;
    }

    public ChartType getType() {
        return this.type;
    }

    public byte[] getImageData() {
        return this.imageData;
    }

    public abstract boolean generateImageData();
}
