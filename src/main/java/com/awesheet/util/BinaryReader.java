package com.awesheet.util;

import java.io.IOException;
import java.nio.ByteBuffer;

public class BinaryReader {
    protected ByteBuffer innerBuffer;

    public BinaryReader(byte data[]) {
        innerBuffer = ByteBuffer.wrap(data);
    }

    public short readShort() throws IOException {
        return innerBuffer.getShort();
    }

    public int readInt() throws IOException {
        return innerBuffer.getInt();
    }

    public long readLong() throws IOException {
        return innerBuffer.getLong();
    }

    public byte[] readBytes(int length) throws IOException {
        byte data[] = new byte[length];
        innerBuffer.get(data, 0, length);
        return data;
    }

    public byte readByte() throws IOException {
        return readBytes(1)[0];
    }
}
