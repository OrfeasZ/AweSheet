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

package com.awesheet.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class BinaryWriter {
    protected ByteArrayOutputStream stream = null;

    public BinaryWriter(ByteArrayOutputStream stream) {
        this.stream = stream;
    }

    public BinaryWriter(int size) {
        this(new ByteArrayOutputStream(size));
    }

    public BinaryWriter() {
        this(32);
    }

    public void write(short data) throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putShort(data);
        writeInternal(buffer);
    }

    public void write(int data) throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(data);
        writeInternal(buffer);
    }

    public void write(long data) throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(data);
        writeInternal(buffer);
    }

    public void write(byte data[]) throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocate(data.length);
        buffer.put(data);
        writeInternal(buffer);
    }

    public void write(byte data) throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocate(1);
        buffer.put(data);
        writeInternal(buffer);
    }

    public void flush() {
        try {
            stream.flush();
        } catch (final IOException ignored) {
        }
    }

    public byte[] toByteArray() {
        if (stream != null) {
            return stream.toByteArray();
        }

        return null;
    }

    protected void writeInternal(ByteBuffer buffer) throws IOException {
        stream.write(buffer.array());
    }
}
