package com.ohrlings.arrowheadadapter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.jackson.JsonComponent;

import java.util.List;

@JsonComponent
public class ArrowheadSystemsAnswer {
    private List<ArrowheadSystem> data;
    private int count;

    public ArrowheadSystemsAnswer() {
    }

    public ArrowheadSystemsAnswer(List<ArrowheadSystem> data, int count) {
        this.data = data;
        this.count = count;
    }

    public List<ArrowheadSystem> getData() {
        return data;
    }

    public void setData(List<ArrowheadSystem> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
