package com.ohrlings.arrowheadadapter.model;

/*
"id": 10,
        "systemName": "datamanager",
        "address": "arrowhead.ddns.net",
        "port": 8461,
        "createdAt": "2021-03-17 14:08:11",
        "updatedAt": "2021-03-17 14:08:11"

 */

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
