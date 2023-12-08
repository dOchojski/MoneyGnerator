package com.d_d.moneygnerator.client;

import com.d_d.moneygnerator.configuration.BrokerConfig;
import com.d_d.moneygnerator.exception.ApplicationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;

@Getter
@RequiredArgsConstructor
public abstract class AbstractBrokerClient implements BrokerClient {
    private final BrokerConfig brokerConfig;
    private final ObjectMapper objectMapper;

    protected abstract String getPrice(final String symbol);

    @Override
    public BigDecimal getPriceForSymbol(String symbol) {
        String price = getPrice(symbol);
        return new BigDecimal(price, MathContext.UNLIMITED);
    }

    protected <T> T readValue(final String value, Class<T> responseType) {
        try {
            return objectMapper.readValue(value, responseType);
        } catch (JsonProcessingException e) {
            throw new ApplicationException("Couldn't read value: " + value);
        }
    }

    protected <T> T readValue(final String value, TypeReference<T> responseType) {
        try {
            return objectMapper.readValue(value, responseType);
        } catch (JsonProcessingException e) {
            throw new ApplicationException("Couldn't read value: " + value);
        }
    }
}
