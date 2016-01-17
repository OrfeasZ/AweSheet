package com.awesheet.interfaces;

public interface ISerializable {
    byte[] serialize();
    boolean deserialize(byte[] data);
}
