package com.ohrlings.arrowheadadapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adapter")
public class AdapterController implements Controller {

    Logger logger = LoggerFactory.getLogger(AdapterController.class);

    @Override
    @PostMapping
    public ResponseEntity<String> pushData(@RequestBody String testData) {
        logger.info(testData);
        return new ResponseEntity<>(testData, HttpStatus.OK);
    }
}
