package com.ohrlings.arrowheadadapter;

import com.ohrlings.arrowheadadapter.model.ArrowheadOrchestrationResponse;
import com.ohrlings.arrowheadadapter.model.ArrowheadSystem;
import com.ohrlings.arrowheadadapter.utils.JSONModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JSONParseTest {

    @Autowired
    private JacksonTester<ArrowheadOrchestrationResponse> registeredArrowheadOrchestratorJson;

    @Autowired
    private JacksonTester<ArrowheadSystem> registeredArrowheadSystem;

    @Test
    public void shouldReturnAArrowheadOrchestrationResponseObjectFromProvidedJSON() throws IOException {
        ArrowheadOrchestrationResponse response = registeredArrowheadOrchestratorJson.parseObject(JSONModel.ORCHESTRATOR_RESPONSE_JSON);

        assertThat(response.getResponse())
                .isNotEmpty();
    }

    @Test
    public void shouldParseTheWholeProvidedJSONIntoValidObjects() throws IOException {
        ArrowheadOrchestrationResponse orchestrationResponse = registeredArrowheadOrchestratorJson.parseObject(JSONModel.ORCHESTRATOR_RESPONSE_JSON);

        assertThat(orchestrationResponse.getResponse().get(0).getProvider().getId()).isEqualTo(10L);
        assertThat(orchestrationResponse.getResponse().get(0).getService().getId()).isEqualTo(14L);
        assertThat(orchestrationResponse.getResponse().get(0).getInterfaces().get(0).getId()).isEqualTo(2L);
        assertThat(orchestrationResponse.getResponse().get(0).getServiceUri()).isEqualTo("/datamanager/historian");
        assertThat(orchestrationResponse.getResponse().get(0).getVersion()).isEqualTo(1);
        assertThat(orchestrationResponse.getResponse().get(0).getSecure()).isEqualTo("NOT_SECURE");
    }

    @Test
    public void shouldBeAbleToCreateAValidUriFromProvidedJSON() throws IOException {
        String address = registeredArrowheadOrchestratorJson.parseObject(JSONModel.ORCHESTRATOR_RESPONSE_JSON).getResponse()
                .get(0)
                .getProvider()
                .getAddress();
        int port = registeredArrowheadOrchestratorJson.parseObject(JSONModel.ORCHESTRATOR_RESPONSE_JSON).getResponse()
                .get(0)
                .getProvider()
                .getPort();
        String serviceUri = registeredArrowheadOrchestratorJson.parseObject(JSONModel.ORCHESTRATOR_RESPONSE_JSON).getResponse()
                .get(0)
                .getServiceUri();
        String actual = address + ":" + port + serviceUri;

        assertThat(actual).isEqualTo("arrowhead.ddns.net:8461/datamanager/historian");
    }

    @Test
    public void shouldReturnAValidSystemFromProvidedJson() throws IOException {
        ArrowheadSystem system = registeredArrowheadSystem.parseObject(JSONModel.ARROWHEAD_SYSTEM_RESPONSE_JSON);

        assertThat(system.getId()).isEqualTo(24L);
        assertThat(system.getName()).isEqualTo("somasvalve79");
        assertThat(system.getAddress()).isEqualTo("bnearit.se");
        assertThat(system.getPort()).isEqualTo(1337);
    }
}
