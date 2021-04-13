package com.ohrlings.arrowheadadapter;

import com.ohrlings.arrowheadadapter.model.ArrowheadSystem;
import com.ohrlings.arrowheadadapter.model.ArrowheadOrchestrationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.UnknownServiceException;
import java.util.Objects;

/*
    System from Service Registry:
 {
  "id": 24,
  "systemName": "somasvalve79",
  "address": "bnearit.se",
  "port": 1337,
  "createdAt": "2021-04-12 14:46:55",
  "updatedAt": "2021-04-12 14:46:55"
 }
    Orchestration-store rule?:
 {
  "data": [
    {
      "id": 2,
      "serviceDefinition": {
        "id": 14,
        "serviceDefinition": "historian",
        "createdAt": "2021-03-17 14:08:11",
        "updatedAt": "2021-03-17 14:08:11"
      },
      "consumerSystem": {
        "id": 24,
        "systemName": "somasvalve79",
        "address": "bnearit.se",
        "port": 1337,
        "createdAt": "2021-04-12 14:46:56",
        "updatedAt": "2021-04-12 14:46:56"
      },
      "foreign": false,
      "providerSystem": {
        "id": 10,
        "systemName": "datamanager",
        "address": "arrowhead.ddns.net",
        "port": 8461,
        "createdAt": "2021-03-17 14:08:11",
        "updatedAt": "2021-03-17 14:08:11"
      },
      "providerCloud": null,
      "serviceInterface": {
        "id": 2,
        "interfaceName": "HTTP-INSECURE-JSON",
        "createdAt": "2021-03-02 12:56:16",
        "updatedAt": "2021-03-02 12:56:16"
      },
      "priority": 1,
      "attribute": null,
      "createdAt": "2021-04-12 14:48:50",
      "updatedAt": "2021-04-12 14:48:50"
    }
  ],
  "count": 1
  }

  Authorization mgmt/intracloud
 {
  "data": [
    {
      "id": 1,
      "consumerSystem": {
        "id": 24,
        "systemName": "somasvalve79",
        "address": "bnearit.se",
        "port": 1337,
        "createdAt": "2021-04-12 14:46:56",
        "updatedAt": "2021-04-12 14:46:56"
      },
      "providerSystem": {
        "id": 10,
        "systemName": "datamanager",
        "address": "arrowhead.ddns.net",
        "port": 8461,
        "createdAt": "2021-03-17 14:08:11",
        "updatedAt": "2021-03-17 14:08:11"
      },
      "serviceDefinition": {
        "id": 14,
        "serviceDefinition": "historian",
        "createdAt": "2021-03-17 14:08:11",
        "updatedAt": "2021-03-17 14:08:11"
      },
      "interfaces": [
        {
          "id": 2,
          "interfaceName": "HTTP-INSECURE-JSON",
          "createdAt": "2021-03-02 12:56:16",
          "updatedAt": "2021-03-02 12:56:16"
        }
      ],
      "createdAt": "2021-04-12 14:55:08",
      "updatedAt": "2021-04-12 14:55:08"
    }
  ],
  "count": 1
 }
 Orchestrator/orchestration response:
 {
  "response": [
    {
      "provider": {
        "id": 10,
        "systemName": "datamanager",
        "address": "arrowhead.ddns.net",
        "port": 8461,
        "createdAt": "2021-03-17 14:08:11",
        "updatedAt": "2021-03-17 14:08:11"
      },
      "service": {
        "id": 14,
        "serviceDefinition": "historian",
        "createdAt": "2021-03-17 14:08:11",
        "updatedAt": "2021-03-17 14:08:11"
      },
      "serviceUri": "/datamanager/historian",
      "secure": "NOT_SECURE",
      "metadata": {},
      "interfaces": [
        {
          "id": 2,
          "interfaceName": "HTTP-INSECURE-JSON",
          "createdAt": "2021-03-02 12:56:16",
          "updatedAt": "2021-03-02 12:56:16"
        }
      ],
      "version": 1,
      "authorizationTokens": null,
      "warnings": [
        "TTL_UNKNOWN"
      ]
    }
  ]
}
 */

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

    public void sendData(ArrowheadSystem adapterSystem, String serviceUri, String data) {'

     /* http://arrowhead.ddns.net:8461/datamanager/historian => 
        {
          "systems": [
            "5g",
            "somasvalve79"
          ]
        }

        http://arrowhead.ddns.net:8461/datamanager/historian/somasvalve79 =>
        {
        "services": [
            "historian"
            ]
        }

        http://arrowhead.ddns.net:8461/datamanager/historian/somasvalve79/historian?params=%7B%7D =>
        [
          {
            "bn": "urn:ah:somasvalve79:historian:",
            "bt": 0
          },
          {
            "n": "string",
            "u": "string",
            "v": 0,
            "vs": "string",
            "vb": true
          }
        ]
*/
        String datamanagerUri = serviceUri + "/" + adapterSystem.getName() + "/" + ""; // TODO: Unknown value endpoint: /datamanager/historian/{systemName}/{serviceName}
        restTemplate.put(datamanagerUri, data);
    }
}
