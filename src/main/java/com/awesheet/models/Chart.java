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

package com.awesheet.models;

import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Chart {
    protected int type;
    protected String title;
    protected String nameX;
    protected String nameY;
    protected Cell[] inputs;
    protected byte[] imageData;

    protected Chart(int type, Cell inputs[]) {
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

    protected DefaultCategoryDataset createDataset() {
        // Do we have enough data to work with?
        if (inputs.length < 4) {
            return null;
        }

        int minX = inputs[0].getX();
        int minY = inputs[0].getY();
        int maxX = inputs[0].getX();
        int maxY = inputs[0].getY();

        HashMap<String, Cell> mappedCells = new HashMap<String, Cell>();

        for (Cell cell : inputs) {
            if (cell.getX() < minX) {
                minX = cell.getX();
            }

            if (cell.getY() < minY) {
                minY = cell.getY();
            }

            if (cell.getX() > maxX) {
                maxX = cell.getX();
            }

            if (cell.getY() > maxY) {
                maxY = cell.getY();
            }

            mappedCells.put(cell.getX() + "x" + cell.getY(), cell);
        }

        // Check again if we have data to work with.
        if (maxX - minX < 1 || maxY - minY < 1) {
            return null;
        }

        // Collect all the labels.
        List<String> labels = new ArrayList<String>();

        for (int y = minY + 1; y <= maxY; ++y) {
            String label = getCellValue(mappedCells, minX, y);

            // We can't empty labels.
            if (label.length() == 0) {
                return null;
            }

            labels.add(label);
        }

        // Create our dataset.
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int x = minX + 1; x <= maxX; ++x) {
            String groupName = getCellValue(mappedCells, x, minY);

            // We can't allow non-named groups.
            if (groupName.length() == 0) {
                return null;
            }

            for (int y = minY + 1; y <= maxY; ++y) {
                String cellValue = getCellValue(mappedCells, x, y);
                Double numberValue = 0.0;

                if (cellValue.length() > 0) {
                    try {
                        numberValue = Double.parseDouble(cellValue);
                    } catch (NumberFormatException ignored) {}
                }

                dataset.addValue(numberValue, groupName, labels.get(y - minY - 1));
            }
        }

        return dataset;
    }

    private String getCellValue(HashMap<String, Cell> cells, int x, int y) {
        if (!cells.containsKey(x + "x" + y)) {
            return "";
        }

        return cells.get(x + "x" + y).getDisplayValue();
    }
}
