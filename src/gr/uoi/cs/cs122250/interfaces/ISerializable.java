package gr.uoi.cs.cs122250.interfaces;

public interface ISerializable {
    byte[] serialize();
    boolean deserialize(byte[] data);
}
