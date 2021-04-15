package com.ohrlings.arrowheadadapter;

import com.ohrlings.arrowheadadapter.model.ArrowheadSystem;
import com.ohrlings.arrowheadadapter.model.ArrowheadOrchestrationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.UnknownServiceException;
import java.util.Objects;

@Service
public class ArrowheadConnection {
    private final Logger log = LoggerFactory.getLogger(ArrowheadConnection.class);

    private final String baseURL = "http://arrowhead.ddns.net";
    private final int orchPort = 8441;
    private final int srPort = 8443;

    private final RestTemplate restTemplate;

    public ArrowheadConnection(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ArrowheadSystem getAdapterSystem(long systemId) throws IOException {
        String serviceRegistryUrl = baseURL + ":" + srPort + "/serviceregistry/mgmt/systems/" + systemId;
        ResponseEntity<ArrowheadSystem> response = restTemplate.getForEntity(serviceRegistryUrl, ArrowheadSystem.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("System {} registered.", Objects.requireNonNull(response.getBody()).getName());
            return response.getBody();
        } else if(response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new UnknownServiceException("Arrowhead cloud didn't return any system with id " + systemId);
        }
        throw new UnknownServiceException("Unknown failure when trying fetch system information from Arrowhead cloud.");
    }

    public String getServiceUri(long systemId) throws IOException {
        String orchestratorUrl = baseURL + ":" + orchPort + "/orchestrator/orchestration/" + systemId;
        ResponseEntity<ArrowheadOrchestrationResponse> response = restTemplate.getForEntity(orchestratorUrl, ArrowheadOrchestrationResponse.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("Orchestration rule obtained for system {}.", Objects.requireNonNull(response.getBody()).getResponse().get(0).getProvider().getSystemName());
            return buildUri(response.getBody());
        } else if(response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new UnknownServiceException("Arrowhead cloud didn't return any services from " + orchestratorUrl);
        }
        throw new UnknownServiceException("Unknown failure when trying fetch service information from Arrowhead cloud.");

    }

    private String buildUri(ArrowheadOrchestrationResponse orchestration) {
        String address = orchestration.getResponse().get(0).getProvider().getAddress();
        int port = orchestration.getResponse().get(0).getProvider().getPort();
        String endpoint = orchestration.getResponse().get(0).getServiceUri();
        return "http://" + address + ":" + port + endpoint;
    }

    public HttpStatus sendData(ArrowheadSystem adapterSystem, String serviceUri, String data) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String datamanagerUri = serviceUri + "/" + adapterSystem.getName() + "/" + "valvecontrol";
        HttpEntity<String> entity = new HttpEntity<>(data, headers);
        return restTemplate.exchange(datamanagerUri, HttpMethod.PUT, entity, String.class).getStatusCode();
    }
}
