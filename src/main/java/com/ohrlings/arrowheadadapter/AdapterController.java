package com.ohrlings.arrowheadadapter;

import com.ohrlings.arrowheadadapter.model.ArrowheadSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;

@RestController
@RequestMapping("/adapter")
public class AdapterController implements Controller {
	private final Logger log = LoggerFactory.getLogger(AdapterController.class);

	ArrowheadSystem adapterSystem;
	private String serviceUri;

	private final ArrowheadConnection connection;

	@Autowired
	public AdapterController(ArrowheadConnection connection) {
		this.connection = connection;
	}

	@PostConstruct
	void init() {
		try {
			adapterSystem = connection.getAdapterSystem(24);
			serviceUri = connection.getServiceUri(adapterSystem.getId());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	@Override
	@PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> pushData(@RequestBody String input) {
		log.info("Data received:\n" + input);
		connection.sendData(adapterSystem, serviceUri, input);
		log.info("Data sent.");
		return new ResponseEntity<>(input, HttpStatus.ACCEPTED);
	}
}
