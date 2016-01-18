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

import com.awesheet.interfaces.IDestructible;
import com.awesheet.interfaces.IUIBindable;
import com.awesheet.ui.UICell;
import com.awesheet.ui.UIModel;

public abstract class Cell implements IUIBindable, IDestructible {
    protected int x;
    protected int y;
    protected int type;
    protected String value;
    protected Sheet sheet;

    protected Cell(int type, int x, int y, String value, Sheet sheet) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.value = value;
        this.sheet = sheet;
    }

    @Override
    public void destroy() {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public abstract String getDisplayValue();

    @Override
    public UIModel bind() {
        return new UICell(x, y, type, getValue(), getDisplayValue(), sheet);
    }
}
