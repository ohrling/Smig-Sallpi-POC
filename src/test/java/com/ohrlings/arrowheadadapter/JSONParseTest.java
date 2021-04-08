package com.ohrlings.arrowheadadapter;

import com.ohrlings.arrowheadadapter.model.ArrowheadSystem;
import com.ohrlings.arrowheadadapter.model.OrchestratorAnswer;
import com.ohrlings.arrowheadadapter.utils.JSONModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JSONParseTest {

    @Autowired
    private JacksonTester<ArrowheadSystem> registeredArrowheadSystemJson;

    @Autowired
    private JacksonTester<OrchestratorAnswer> registeredArrowheadOrchestratorJson;

    @Test
    public void shouldReturnASystemObjectFromProvidedJSON() throws IOException {
        assertThat(registeredArrowheadSystemJson.parseObject(JSONModel.REGISTERED_ARROWHEAD_SYSTEM).getId())
                .isEqualTo(1);
    }

    @Test
    public void shouldReturnAOrchestratorAnswerObjectFromProvidedJSON() throws IOException {
        final OrchestratorAnswer orchestratorAnswer = registeredArrowheadOrchestratorJson.parseObject(JSONModel.ORCHESTRATOR_JSON);

        assertThat(orchestratorAnswer.getCount())
            .isEqualTo(1);
        assertThat(orchestratorAnswer.getData().get(0).getId())
                .isEqualTo(12);
    }
}
