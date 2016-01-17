package com.awesheet.managers;

public class FileManager {
    private static FileManager instance = null;

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }

        return instance;
    }

    protected FileManager() {
    }

    public byte[] readFile(String path) {
        return null;
    }

    public boolean saveFile(String path, byte[] data) {
        return false;
    }

    public boolean fileExists(String path) {
        return false;
    }
}
