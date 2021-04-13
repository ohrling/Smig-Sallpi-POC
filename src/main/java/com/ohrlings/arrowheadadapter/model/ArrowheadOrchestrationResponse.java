package com.ohrlings.arrowheadadapter.model;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.jackson.JsonComponent;

import java.util.List;

/*
* {
  "response": [

  ]
}
* */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrowheadOrchestrationResponse {
    private List<ArrowheadService> response;

    public ArrowheadOrchestrationResponse() {
    }

    public ArrowheadOrchestrationResponse(List<ArrowheadService> response) {
        this.response = response;
    }

    public List<ArrowheadService> getResponse() {
        return response;
    }

    public void setResponses(List<ArrowheadService> response) {
        this.response = response;
    }
}
