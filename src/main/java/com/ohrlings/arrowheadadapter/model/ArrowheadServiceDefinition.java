package com.ohrlings.arrowheadadapter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
"id": 14,
        "serviceDefinition": "historian",
        "createdAt": "2021-03-17 14:08:11",
        "updatedAt": "2021-03-17 14:08:11"
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrowheadServiceDefinition {
    private long id;
    private String serviceDefinition;

    public ArrowheadServiceDefinition() {
    }

    public ArrowheadServiceDefinition(long id, String serviceDefinition) {
        this.id = id;
        this.serviceDefinition = serviceDefinition;
    }

    public long getId() {
        return id;
    }

    public String getServiceDefinition() {
        return serviceDefinition;
    }

    @Override
    public String toString() {
        return "ArrowheadServiceDefinition{" +
                "id=" + id +
                ", serviceDefinition='" + serviceDefinition + '\'' +
                '}';
    }
}
