package com.example.demo.model;

import java.io.Serializable;

public class PlatformResponse implements Serializable {
    short packetLength;
    int pilId;
    byte no;
    byte type;
    short functionCode;
    String data;
    short crc16;

    public short getPacketLength() {
        return packetLength;
    }

    public void setPacketLength(short packetLength) {
        this.packetLength = packetLength;
    }

    public int getPilId() {
        return pilId;
    }

    public void setPilId(int pilId) {
        this.pilId = pilId;
    }

    public byte getNo() {
        return no;
    }

    public void setNo(byte no) {
        this.no = no;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public short getFuncionCode() {
        return functionCode;
    }

    public void setFuncionCode(short functionCode) {
        this.functionCode = functionCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public short getCrc16() {
        return crc16;
    }

    public void setCrc16(short crc16) {
        this.crc16 = crc16;
    }
}
