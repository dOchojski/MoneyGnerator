package com.d_d.moneygnerator.client;

import com.d_d.moneygnerator.configuration.BrokerConfig;
import com.d_d.moneygnerator.model.enumeration.Broker;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.contek.invoker.kraken.api.ApiFactory;
import io.contek.invoker.kraken.api.websocket.market.TradeChannel;

public class KrakenRestClient extends AbstractBrokerClient {

    private ApiFactory apiFactory;

    public KrakenRestClient(BrokerConfig brokerConfig, ObjectMapper objectMapper) {
        super(brokerConfig, objectMapper);
    }


    @Override
    protected String getPrice(String symbol) {
        TradeChannel ethusdt = apiFactory.ws().market().getTradeChannel(symbol);
        System.out.println(ethusdt);
        return null;
    }

    @Override
    public Broker getBroker() {
        return Broker.KRAKEN;
    }
}
