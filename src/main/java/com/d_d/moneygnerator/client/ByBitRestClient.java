package com.d_d.moneygnerator.client;

import com.bybit.api.client.restApi.BybitApiMarketRestClient;
import com.bybit.api.client.service.BybitApiClientFactory;
import com.d_d.moneygnerator.configuration.ByBitConfig;
import com.d_d.moneygnerator.exception.ApplicationException;
import com.d_d.moneygnerator.model.enumeration.Broker;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

public class ByBitRestClient extends AbstractBrokerClient {

    private final BybitApiMarketRestClient restClient;

    public ByBitRestClient(final ByBitConfig brokerConfig,
                           final ObjectMapper objectMapper) {
        super(brokerConfig, objectMapper);
        this.restClient = BybitApiClientFactory
                .newInstance(brokerConfig.getApiKey(), brokerConfig.getApiSecret(), brokerConfig.getBaseUrl())
                .newMarketDataRestClient();
    }

    @Override
    protected String getPrice(String symbol) {
        try {
//            BigDecimal priceForSymbol = restClient;
            return null;
        } catch (Exception e) {
            throw new ApplicationException("Couldn't get price for ByBit with symbol: " + symbol, e);
        }
    }

    @Override
    public Broker getBroker() {
        return Broker.BYBIT;
    }
}
