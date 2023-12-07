package com.d_d.moneygnerator.service;

import com.d_d.moneygnerator.model.PriceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BinanceApiService {
    private static final String API_BINANCE_URL = "https://api.binance.com/api/v3/ticker/price?symbol=";

    private static final String API_BINANCE_KEY = "YHmoE7d62WIwI9rT2v2YjMUMOviJPNhN6vmdt71IJEJ1mTEbhkQQsmWha5t4gcvz";
    private static final String API_PRIVATE_BINANCE_KEY = "QwBIZvB65qmuNPjKDvpCogf9jRjCz7AKVwocmjW2WmZ9pbHldzR0CTfxSYAZO3yj";
    private static final RestTemplate restTemplate = new RestTemplate();


    public String getCurrentPrice(String symbol) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-MBX-APIKEY", API_BINANCE_KEY);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            return Optional.ofNullable(restTemplate.getForObject(API_BINANCE_URL + symbol, PriceResponse.class))
                    .map(PriceResponse::price)
                    .orElse("");
        } catch (HttpClientErrorException e) {
            log.info("Problem podczas uderzania po symbol: {}", symbol);
            return null;
        }
    }
}
