package com.d_d.moneygnerator.service;

import com.d_d.moneygnerator.model.PriceResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class BinanceApiService {

    private static final String API_BINANCE_URL = "https://api.binance.com/api/v3/ticker/price?symbol=";
    private static final RestTemplate restTemplate = new RestTemplate();


    public static String getCurrentPrice(String symbol) {
        try {
            PriceResponse response = restTemplate.getForObject(API_BINANCE_URL + symbol, PriceResponse.class);
            return response != null ? response.price() : null;
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return null;
        }
    }
}
