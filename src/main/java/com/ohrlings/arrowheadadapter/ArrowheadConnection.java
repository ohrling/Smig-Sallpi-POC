package com.ohrlings.arrowheadadapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ohrlings.arrowheadadapter.model.ArrowheadService;
import com.ohrlings.arrowheadadapter.model.ArrowheadSystem;
import com.ohrlings.arrowheadadapter.model.ArrowheadSystemsAnswer;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class ArrowheadConnection {
    private final Logger log = LoggerFactory.getLogger(ArrowheadConnection.class);

    private final String baseURL = "http://arrowhead.ddns.net";
    private final int orchPort = 8441;
    private final int srPort = 8443;
    private final ArrowheadSystem adapterSystem = new ArrowheadSystem("/dummyAddress", "string", 1337, "bnearitTestar1");

    private final OkHttpClient client = new OkHttpClient();

    private final ObjectMapper mapper;

    public ArrowheadConnection() {
        mapper = new JsonMapper();
    }

    public ArrowheadService getServiceInformation(ArrowheadSystem system) throws JsonProcessingException {
        ObjectNode payload = mapper.createObjectNode();
        ObjectNode requesterSystem = mapper.createObjectNode();
        requesterSystem.put("consumerSystemId", system.getId());
        requesterSystem.put("systemName", system.getName());
        requesterSystem.put("address", system.getAddress());
        payload.set("requesterSystem", requesterSystem);
        ObjectNode requestedService = mapper.createObjectNode();
        requestedService.put("serviceDefinitionRequirement", "historian");
        ArrayNode interfaces = mapper.createArrayNode();
        interfaces.add("HTTP-INSECURE-JSON");
        requestedService.set("interfaceRequirements", interfaces);
        payload.set("requestedService", requestedService);
        System.out.println(payload);
        return null;
    }

    public ArrowheadSystem registerSystem() throws IOException {
        String registerUrl = baseURL + ":" + srPort + "/serviceregistry/mgmt/systems";
        MediaType mediaType = MediaType.parse("application/json");
        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, mapper.writeValueAsString(adapterSystem));
        Request request = new Request.Builder().url(registerUrl).post(body).build();
        Response systemResponse = client.newCall(request).execute();
        if (systemResponse.body() != null) {
            System.out.println(systemResponse.code());
            if(systemResponse.code() == 200) {
                System.out.println(systemResponse.body().string());
                return mapper.readValue(systemResponse.body().string(), ArrowheadSystem.class);
            } else if(systemResponse.code() == 400) {
                systemResponse.close();
                Response systemsResponse = client.newCall(new Request.Builder().url(registerUrl).get().build()).execute();
                if (systemsResponse.body() != null) {
                    System.out.println(systemsResponse.body().string());
                    ArrowheadSystemsAnswer systems = mapper.readValue(systemsResponse.body().string(), ArrowheadSystemsAnswer.class);
                    for (ArrowheadSystem system :
                            systems.getData()) {
                        if(Objects.equals(system.getName(), adapterSystem.getName())) {
                            return system;
                        }
                    }
                    systemsResponse.close();
                } else {
                    throw new IOException("Didn't get any response from " + registerUrl);
                }
            }
            throw new IOException("Unknown failure when trying to assign system to the Arrowhead cloud.");
        } else {
            throw new IOException("Didn't get any response from " + registerUrl);
        }
    }
}
