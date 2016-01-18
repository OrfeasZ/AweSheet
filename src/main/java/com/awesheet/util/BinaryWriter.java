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
