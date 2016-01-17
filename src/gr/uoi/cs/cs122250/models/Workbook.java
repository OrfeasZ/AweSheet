package gr.uoi.cs.cs122250.models;

import gr.uoi.cs.cs122250.interfaces.IMessageListener;
import gr.uoi.cs.cs122250.interfaces.ISerializable;

import java.util.HashSet;

public class Workbook implements ISerializable, IMessageListener {
    protected String path;
    protected HashSet<Sheet> sheets;

    Workbook(byte[] data, String path) {
        this.path = path;

        // TODO: Parse workbook.
    }

    Workbook() {
        path = null;
    }

    public String getPath() {
        return path;
    }

    public HashSet<Sheet> getSheets() {
        return sheets;
    }

    public void addSheet(Sheet sheet) {
    }

    public Sheet getSheet(int id) {
        return null;
    }

    public void removeSheet(int id) {

    }

    public void replaceSheet(int id, Sheet newSheet) {

    }

    public boolean isValid() {
        return false;
    }

    @Override
    public byte[] serialize() {
        return new byte[0];
    }

    @Override
    public boolean deserialize(byte[] data) {
        return false;
    }

    @Override
    public void onMessage(UIMessage message) {

    }
}