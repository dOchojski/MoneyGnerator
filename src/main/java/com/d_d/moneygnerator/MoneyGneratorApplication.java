package com.d_d.moneygnerator;

import com.d_d.moneygnerator.service.BinanceApiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MoneyGneratorApplication {
    private static final BinanceApiService binanceApiService = new BinanceApiService(new RestTemplate());

    public static void main(String[] args) {
        SpringApplication.run(MoneyGneratorApplication.class, args);

        String ethPrice = binanceApiService.getCurrentPrice("ETHUSDT");
        System.out.println("Aktualna cena ETH/USDT to: " + ethPrice);

        String adaPrice = binanceApiService.getCurrentPrice("ADAUSDT");
        System.out.println("Aktualna cena ADA/USDT to: " + adaPrice);
    }

}
