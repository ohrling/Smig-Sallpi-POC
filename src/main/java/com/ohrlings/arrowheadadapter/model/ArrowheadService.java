package com.ohrlings.arrowheadadapter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

/*
{
      "provider": {

      },
      "service": {

      },
      "serviceUri": "/datamanager/historian",
      "secure": "NOT_SECURE",
      "metadata": {},
      "interfaces": [

      ],
      "version": 1,
      "authorizationTokens": null,
      "warnings": [
        "TTL_UNKNOWN"
      ]
    }
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrowheadService {
    private ArrowheadProvider provider;
    private ArrowheadServiceDefinition service;
    private String serviceUri;
    private String secure;
    private Map<String, String> metadata;
    private List<ArrowheadInterface> interfaces;
    private int version;

    public ArrowheadService() {
    }

    public ArrowheadService(ArrowheadProvider provider, ArrowheadServiceDefinition service, String serviceUri, String secure, Map<String, String> metadata, List<ArrowheadInterface> interfaces, int version) {
        this.provider = provider;
        this.service = service;
        this.serviceUri = serviceUri;
        this.secure = secure;
        this.metadata = metadata;
        this.interfaces = interfaces;
        this.version = version;
    }

    public ArrowheadProvider getProvider() {
        return provider;
    }

    public ArrowheadServiceDefinition getService() {
        return service;
    }

    public String getServiceUri() {
        return serviceUri;
    }

    public String getSecure() {
        return secure;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public List<ArrowheadInterface> getInterfaces() {
        return interfaces;
    }

    public int getVersion() {
        return version;
    }

}
