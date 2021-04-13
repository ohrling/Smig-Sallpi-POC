package com.ohrlings.arrowheadadapter.utils;

public class JsonModels {
    public static final String ORCHESTRATOR_REQUEST = "{\n" +
            "  \"preferredProviders\": [\n" +
            "    {\n" +
            "      \"providerSystem\": {\n" +
            "        \"address\": \"arrowhead.ddns.net\",\n" +
            "        \"port\": 8461,\n" +
            "        \"systemName\": \"datamanager\"\n" +
            "      }\n" +
            "    }\n" +
            "  ],\n" +
            "  \"requestedService\": {\n" +
            "    \"interfaceRequirements\": [\n" +
            "      \"HTTP-INSECURE-JSON\"\n" +
            "    ],\n" +
            "    \"pingProviders\": true,\n" +
            "    \"securityRequirements\": [\n" +
            "      \"NOT_SECURE\"\n" +
            "    ],\n" +
            "    \"serviceDefinitionRequirement\": \"historian\"\n" +
            "  },\n" +
            "  \"requesterSystem\": {\n" +
            "    \"address\": \"bnearit.se\",\n" +
            "    \"port\": 1337,\n" +
            "    \"systemName\": \"somasvalve79\"\n" +
            "  }\n" +
            "}";
}
