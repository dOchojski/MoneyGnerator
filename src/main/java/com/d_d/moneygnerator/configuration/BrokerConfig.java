package com.d_d.moneygnerator.configuration;

import lombok.Data;

import java.util.Map;

@Data
public abstract class BrokerConfig {
    protected String apiKey;
    protected String apiSecret;
    protected String baseUrl;
    protected Map<String, String> params;
}
