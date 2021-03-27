package com.ohrlings.arrowheadadapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/adapter")
public class AdapterController implements Controller {

    Logger logger = LoggerFactory.getLogger(AdapterController.class);

    @Override
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> pushData(String testData) {
        logger.info(testData);
        return new ResponseEntity<>(testData, HttpStatus.OK);
    }
}
