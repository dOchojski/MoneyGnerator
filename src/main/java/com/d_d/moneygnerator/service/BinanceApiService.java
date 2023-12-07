package com.d_d.moneygnerator.service;

import com.d_d.moneygnerator.model.ExchangeInfo;
import com.d_d.moneygnerator.model.PriceResponse;
import com.d_d.moneygnerator.model.SymbolInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BinanceApiService {
    private static final String API_BINANCE_ALL_SYMBOL_PRICE_URL = "https://api.binance.com/api/v3/ticker/";
    private static final String API_BINANCE_SYMBOL_PRICE_URL = "https://api.binance.com/api/v3/ticker/price?symbol=";
    private static final String API_BINANCE_EXCHANGE_INFO_URL = "https://api.binance.com/api/v3/exchangeInfo";


    @Value("${binance.api.key}")
    private String apiBinanceKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public String getCurrentPrice(String symbol) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-MBX-APIKEY", apiBinanceKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            return Optional.ofNullable(restTemplate.getForObject(API_BINANCE_SYMBOL_PRICE_URL + symbol, PriceResponse.class))
                    .map(PriceResponse::price)
                    .orElse("");
        } catch (HttpClientErrorException e) {
            log.error("Problem while fetching symbol: {}", symbol, e);
            return null;
        }
    }

    public List<PriceResponse> getAllSymbolsWithPrices() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-MBX-APIKEY", apiBinanceKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<PriceResponse[]> response = restTemplate.exchange(API_BINANCE_ALL_SYMBOL_PRICE_URL + "/price", HttpMethod.GET, entity, PriceResponse[].class);
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            log.error("Error fetching all prices", e);
            return null;
        }
    }

    public void printAllSymbolsWithPrices(){
        List<PriceResponse> allPrices = getAllSymbolsWithPrices();
        if (allPrices != null) {
            allPrices.forEach(price -> System.out.println("Symbol: " + price.symbol() + ", Cena: " + price.price()));
        }
    }


    public List<SymbolInfo> getAllSymbols() {
        try {
            String response = restTemplate.getForObject(API_BINANCE_EXCHANGE_INFO_URL, String.class);
            ExchangeInfo exchangeInfo = objectMapper.readValue(response, ExchangeInfo.class);
            return exchangeInfo.symbols;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
