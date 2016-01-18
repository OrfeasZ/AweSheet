/*
 * AweSheet - Simple Open-Source Spreadsheet Editor
 * Copyright (c) 2015 - 2016, Orfeas - Ioannis Zafeiris, Nikolaos Fylakis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
