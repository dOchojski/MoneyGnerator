package com.d_d.moneygnerator.service;

import com.d_d.moneygnerator.client.BrokerClient;
import com.d_d.moneygnerator.model.Price;
import com.d_d.moneygnerator.model.enumeration.Broker;
import com.d_d.moneygnerator.resolver.BrokerClientResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BrokerService {
    private final BrokerClientResolver brokerClientResolver;

    public Price getPriceFor(final String symbol, final Broker broker) {
        BrokerClient brokerClient = brokerClientResolver.resolve(broker);
        BigDecimal price = brokerClient.getPriceForSymbol(symbol);
        return new Price(broker, price);
    }
}
