package com.example.demo.configration.p2p;

public class PeerMessage {
    final static String TYPE_ASSIST = "assist";
    final static String TYPE_REGISTER = "register";
    String type = null; // assist, register
    String  intranetIP = null;
    String destinationName = null;
    String name = null;


    public PeerMessage(String type, String intranetIP) {
        this.intranetIP = intranetIP;
        this.type = type;
    }

    public PeerMessage(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIntranetIP() {
        return intranetIP;
    }

    public void setIntranetIP(String intranetIP) {
        this.intranetIP = intranetIP;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationIP) {
        this.destinationName = destinationIP;
    }

    public static String getTypeAssist() {
        return TYPE_ASSIST;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
