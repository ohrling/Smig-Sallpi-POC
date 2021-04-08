package com.ohrlings.arrowheadadapter.model;

import org.springframework.boot.jackson.JsonComponent;

import java.util.List;

@JsonComponent
public class OrchestratorAnswer {
    private int count;
    private List<ArrowheadService> data;

    public OrchestratorAnswer() {
    }

    public OrchestratorAnswer(int count, List<ArrowheadService> data) {
        this.count = count;
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public List<ArrowheadService> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "OrchestratorAnswer{" +
                "count=" + count +
                ", data=" + data.toString() +
                '}';
    }
}
