package com.ohrlings.arrowheadadapter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrowheadSystem {
    private Long id;
    private String address;
    private String authenticationInfo;
    private int port;
    @JsonProperty(value = "systemName")
    private String name;

    public ArrowheadSystem() {
    }

    public ArrowheadSystem(long id, String address, String authenticationInfo, int port, String name) {
        this.id = id;
        this.address = address;
        this.authenticationInfo = authenticationInfo;
        this.port = port;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAuthenticationInfo() {
        return authenticationInfo;
    }

    public void setAuthenticationInfo(String authenticationInfo) {
        this.authenticationInfo = authenticationInfo;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
