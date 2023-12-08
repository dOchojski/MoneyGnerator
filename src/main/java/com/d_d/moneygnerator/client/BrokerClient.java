package com.d_d.moneygnerator.client;

import com.d_d.moneygnerator.model.enumeration.Broker;

import java.math.BigDecimal;

public interface BrokerClient {

    BigDecimal getPriceForSymbol(final String symbol);

    Broker getBroker();
}
