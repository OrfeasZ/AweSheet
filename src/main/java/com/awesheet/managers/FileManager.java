package com.awesheet.managers;

import java.io.*;

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
        File file = new File(path);

        FileInputStream inputStream = null;
        byte data[];

        try {
            inputStream = new FileInputStream(file);

            data = new byte[(int) file.length()];

            inputStream.read(data);
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException ioe) {
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            catch (IOException ignored) {
            }
        }

        return data;

    }

    public boolean saveFile(String path, byte data[]) {
        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(path);
            outputStream.write(data);
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            catch (IOException ignored) {
            }
        }

        return true;
    }

    public boolean fileExists(String path) {
        File file = new File(path);
        return file.exists() && file.isFile();
    }
}
