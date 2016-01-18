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

package com.awesheet.ui;

import com.awesheet.models.Sheet;

public class UICell extends UIModel {
    protected int x;
    protected int y;
    protected int type;
    protected int sheet;
    protected String value;
    protected String displayValue;

    public UICell(int x, int y, int type, String value, String displayValue, Sheet sheet) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.value = value;
        this.displayValue = displayValue;
        this.sheet = sheet.getID();
    }

    public String getID() {
        return x + "x" + y;
    }
}
