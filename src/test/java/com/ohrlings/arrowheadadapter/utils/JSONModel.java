package com.ohrlings.arrowheadadapter.utils;

public class JSONModel {
    public static final String ORCHESTRATOR_RESPONSE_JSON = "{\n" +
            "  \"response\": [\n" +
            "    {\n" +
            "      \"provider\": {\n" +
            "        \"id\": 10,\n" +
            "        \"systemName\": \"datamanager\",\n" +
            "        \"address\": \"arrowhead.ddns.net\",\n" +
            "        \"port\": 8461,\n" +
            "        \"createdAt\": \"2021-03-17 14:08:11\",\n" +
            "        \"updatedAt\": \"2021-03-17 14:08:11\"\n" +
            "      },\n" +
            "      \"service\": {\n" +
            "        \"id\": 14,\n" +
            "        \"serviceDefinition\": \"historian\",\n" +
            "        \"createdAt\": \"2021-03-17 14:08:11\",\n" +
            "        \"updatedAt\": \"2021-03-17 14:08:11\"\n" +
            "      },\n" +
            "      \"serviceUri\": \"/datamanager/historian\",\n" +
            "      \"secure\": \"NOT_SECURE\",\n" +
            "      \"metadata\": {},\n" +
            "      \"interfaces\": [\n" +
            "        {\n" +
            "          \"id\": 2,\n" +
            "          \"interfaceName\": \"HTTP-INSECURE-JSON\",\n" +
            "          \"createdAt\": \"2021-03-02 12:56:16\",\n" +
            "          \"updatedAt\": \"2021-03-02 12:56:16\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"version\": 1,\n" +
            "      \"authorizationTokens\": null,\n" +
            "      \"warnings\": [\n" +
            "        \"TTL_UNKNOWN\"\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    public static final String ARROWHEAD_SYSTEM_RESPONSE_JSON = "{\n" +
            "  \"id\": 24,\n" +
            "  \"systemName\": \"somasvalve79\",\n" +
            "  \"address\": \"bnearit.se\",\n" +
            "  \"port\": 1337,\n" +
            "  \"createdAt\": \"2021-04-12 14:46:56\",\n" +
            "  \"updatedAt\": \"2021-04-12 14:46:56\"\n" +
            "}";
}
