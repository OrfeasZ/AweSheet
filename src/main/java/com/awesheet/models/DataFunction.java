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

import java.util.ArrayList;

public abstract class DataFunction<T> {
    protected int type;
    protected ArrayList<FunctionArgument> arguments;
    protected String displayValue;
    protected T internalValue;

    protected DataFunction(int type){
        this.type = type;
        arguments = new ArrayList<FunctionArgument>();
        displayValue = "";
    }

    public int getType() {
        return type;
    }

    public ArrayList<FunctionArgument> getArguments() {
        return arguments;
    }

    public FunctionArgument getArgument(int index) {
        return arguments.get(index);
    }

    public void addArgument(FunctionArgument argument) {
        arguments.add(argument);
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public T getInternalValue() {
        return internalValue;
    }

    public abstract boolean parse();
}
