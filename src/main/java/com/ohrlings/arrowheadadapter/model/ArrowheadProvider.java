package com.ohrlings.arrowheadadapter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrowheadProvider {
    private long id;
    private String systemName;
    private String address;
    private int port;

    public ArrowheadProvider() {
    }

    public ArrowheadProvider(long id, String systemName, String address, int port) {
        this.id = id;
        this.systemName = systemName;
        this.address = address;
        this.port = port;
    }

    public long getId() {
        return id;
    }

    public String getSystemName() {
        return systemName;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "ArrowheadProvider{" +
                "id=" + id +
                ", systemName='" + systemName + '\'' +
                ", address='" + address + '\'' +
                ", port=" + port +
                '}';
    }
}
