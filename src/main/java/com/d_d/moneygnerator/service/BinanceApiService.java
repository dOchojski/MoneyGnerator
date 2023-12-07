package com.d_d.moneygnerator.service;

import com.d_d.moneygnerator.model.PriceResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class BinanceApiService {

    private static final String API_BINANCE_URL = "https://testnet.binance.vision/api/v3/ticker/price?symbol=";
    private static final String API_BINANCE_KEY = "YHmoE7d62WIwI9rT2v2YjMUMOviJPNhN6vmdt71IJEJ1mTEbhkQQsmWha5t4gcvz";
    private static final String API_PRIVATE_BINANCE_KEY = "QwBIZvB65qmuNPjKDvpCogf9jRjCz7AKVwocmjW2WmZ9pbHldzR0CTfxSYAZO3yj";
    private static final RestTemplate restTemplate = new RestTemplate();


    public static String getCurrentPrice(String symbol) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-MBX-APIKEY", API_BINANCE_KEY);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            PriceResponse response = restTemplate.getForObject(API_BINANCE_URL + symbol, PriceResponse.class);
            return response != null ? response.price() : null;
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return null;
        }
    }
}
