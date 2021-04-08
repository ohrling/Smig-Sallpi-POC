package com.ohrlings.arrowheadadapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.ohrlings.arrowheadadapter.model.ArrowheadService;
import com.ohrlings.arrowheadadapter.model.ArrowheadSystem;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	ArrowheadConnection connection;

	public AdapterController(ArrowheadConnection connection) {
		this.connection = connection;
	}

	@PostConstruct
	void init() {
		try {
			ArrowheadSystem system = connection.registerSystem();
			ArrowheadService service = connection.getServiceInformation(system);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	@PostMapping
	public ResponseEntity<String> pushData(@RequestBody String inputJSON) {
		log.debug(inputJSON);

	/*	try {
			response = arrowheadService.proceedOrchestration(orchestrationRequest);
		} catch (ArrowheadException exception) {
			logger.debug("Orchestration request failed. {}", exception.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if(response == null || response.getResponse().isEmpty()) {
			logger.debug("Orchestration response is empty.");
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		logger.info(response.getResponse().toString());
		final OrchestrationResultDTO result = response.getResponse().get(0);
*/
		return new ResponseEntity<String>(inputJSON, HttpStatus.OK);
	}



}
