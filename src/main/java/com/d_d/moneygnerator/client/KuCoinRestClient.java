package com.d_d.moneygnerator.client;

import com.d_d.moneygnerator.configuration.KuCoinConfig;
import com.d_d.moneygnerator.exception.ApplicationException;
import com.d_d.moneygnerator.model.enumeration.Broker;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kucoin.sdk.KucoinClientBuilder;
import com.kucoin.sdk.KucoinRestClient;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KuCoinRestClient extends AbstractBrokerClient {
    private final KucoinRestClient restClient;

    public KuCoinRestClient(final KuCoinConfig brokerConfig,
                            final ObjectMapper objectMapper) {
        super(brokerConfig, objectMapper);
        KucoinClientBuilder kucoinClientBuilder = new KucoinClientBuilder()
                .withBaseUrl(brokerConfig.getBaseUrl())
                .withApiKey(brokerConfig.getApiKey(), brokerConfig.getApiSecret(), brokerConfig.getParams().get("passPhase"));
        this.restClient = kucoinClientBuilder.buildRestClient();
    }

    @Override
    protected String getPrice(String symbol) {
        try {
            String transformedSymbol = String
                    .format("%s-%s", symbol.substring(0, 3), symbol.substring(3))
                    .toUpperCase();
            return restClient.symbolAPI().getTicker(transformedSymbol).getPrice().toPlainString();
        } catch (IOException e) {
            throw new ApplicationException("Couldn't get price for KuCoin with symbol: " + symbol, e);
        }
    }

    @Override
    public Broker getBroker() {
        return Broker.KUCOIN;
    }
}
