package com.example.demo.model;

import java.util.Date;

public class ClientInfo {
    private String clientid;

    private Short connected;

    private Long mostsignbits;

    private Long leastsignbits;

    private Date lastconnecteddate;

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid == null ? null : clientid.trim();
    }

    public Short getConnected() {
        return connected;
    }

    public void setConnected(Short connected) {
        this.connected = connected;
    }

    public Long getMostsignbits() {
        return mostsignbits;
    }

    public void setMostsignbits(Long mostsignbits) {
        this.mostsignbits = mostsignbits;
    }

    public Long getLeastsignbits() {
        return leastsignbits;
    }

    public void setLeastsignbits(Long leastsignbits) {
        this.leastsignbits = leastsignbits;
    }

    public Date getLastconnecteddate() {
        return lastconnecteddate;
    }

    public void setLastconnecteddate(Date lastconnecteddate) {
        this.lastconnecteddate = lastconnecteddate;
    }
}