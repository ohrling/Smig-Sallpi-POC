package com.ohrlings.arrowheadadapter;

import org.springframework.http.ResponseEntity;

public interface Controller {

    ResponseEntity<String> pushData(String testData);
}
