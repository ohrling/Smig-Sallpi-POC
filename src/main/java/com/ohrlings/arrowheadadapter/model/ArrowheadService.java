package com.ohrlings.arrowheadadapter.model;

import org.springframework.boot.jackson.JsonComponent;

import java.util.Map;

@JsonComponent
public class ArrowheadService {
    private Map<String, String> attribute;
    private ArrowheadSystem consumerSystem;
    private String createdAt;
    private boolean foreign;
    private Long id;
    private int priority;
    private Map<String, Object> serviceDefinition;
    private Map<String, Object> serviceInterface;
    private String updatedAt;

    public ArrowheadService() {
    }

    public ArrowheadService(Map<String, String> attribute, ArrowheadSystem consumerSystem, String createdAt, boolean foreign, Long id, int priority, Map<String, Object> serviceDefinition, Map<String, Object> serviceInterface, String updatedAt) {
        this.attribute = attribute;
        this.consumerSystem = consumerSystem;
        this.createdAt = createdAt;
        this.foreign = foreign;
        this.id = id;
        this.priority = priority;
        this.serviceDefinition = serviceDefinition;
        this.serviceInterface = serviceInterface;
        this.updatedAt = updatedAt;
    }

    public Map<String, String> getAttribute() {
        return attribute;
    }

    public void setAttribute(Map<String, String> attribute) {
        this.attribute = attribute;
    }

    public ArrowheadSystem getConsumerSystem() {
        return consumerSystem;
    }

    public void setConsumerSystem(ArrowheadSystem consumerSystem) {
        this.consumerSystem = consumerSystem;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isForeign() {
        return foreign;
    }

    public void setForeign(boolean foreign) {
        this.foreign = foreign;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Map<String, Object> getServiceDefinition() {
        return serviceDefinition;
    }

    public void setServiceDefinition(Map<String, Object> serviceDefinition) {
        this.serviceDefinition = serviceDefinition;
    }

    public Map<String, Object> getServiceInterface() {
        return serviceInterface;
    }

    public void setServiceInterface(Map<String, Object> serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ArrowheadService{" +
                "id=" + id +
                '}';
    }
}
