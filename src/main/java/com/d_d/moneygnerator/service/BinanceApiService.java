package com.d_d.moneygnerator.service;

import com.d_d.moneygnerator.model.PriceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BinanceApiService {
    private static final String API_BINANCE_URL = "https://api.binance.com/api/v3/ticker/price?symbol=";

    private final RestTemplate restTemplate;

    public String getCurrentPrice(String symbol) {
        try {
            return Optional.ofNullable(restTemplate.getForObject(API_BINANCE_URL + symbol, PriceResponse.class))
                    .map(PriceResponse::price)
                    .orElse("");
        } catch (HttpClientErrorException e) {
            log.info("Problem podczas uderzania po symbol: {}", symbol);
            return null;
        }
    }
}
