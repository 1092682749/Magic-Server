package com.example.demo.configration.p2p;

public class PeerInfo {
    String natIP = null;
    Integer port = null;
    String intraNetIP = null;
    String name = null;

    PeerInfo(){}
    PeerInfo(String natIP, String intraNetIP, Integer port) {
        this.natIP = natIP;
        this.intraNetIP = intraNetIP;
        this.port = port;
    }
    public String getNatIP() {
        return natIP;
    }

    public void setNatIP(String natIP) {
        this.natIP = natIP;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getIntraNetIP() {
        return intraNetIP;
    }

    public void setIntraNetIP(String intraNetIP) {
        this.intraNetIP = intraNetIP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
