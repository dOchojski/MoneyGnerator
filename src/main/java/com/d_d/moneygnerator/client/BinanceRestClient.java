package com.d_d.moneygnerator.client;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.impl.SpotClientImpl;
import com.d_d.moneygnerator.configuration.BinanceConfig;
import com.d_d.moneygnerator.model.enumeration.Broker;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
public class BinanceRestClient extends AbstractBrokerClient {
    private final SpotClient client;

    public BinanceRestClient(final BinanceConfig brokerConfig,
                             final ObjectMapper objectMapper) {
        super(brokerConfig, objectMapper);
        this.client = new SpotClientImpl();
    }

    @Override
    protected String getPrice(String symbol) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("symbol", symbol.toUpperCase());
        String response = client.createMarket().tickerSymbol(params);
        return (String) readValue(response, new TypeReference<Map<String, Object>>() {
        }).get("price");
    }

    @Override
    public Broker getBroker() {
        return Broker.BINANCE;
    }
}
