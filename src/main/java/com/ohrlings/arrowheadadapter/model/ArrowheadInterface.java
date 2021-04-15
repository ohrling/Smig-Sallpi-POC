package com.ohrlings.arrowheadadapter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrowheadInterface {
    private long id;
    private String interfaceName;

    public ArrowheadInterface() {
    }

    public ArrowheadInterface(long id, String interfaceName) {
        this.id = id;
        this.interfaceName = interfaceName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    @Override
    public String toString() {
        return "ArrowheadInterface{" +
                "id=" + id +
                ", interfaceName='" + interfaceName + '\'' +
                '}';
    }
}
