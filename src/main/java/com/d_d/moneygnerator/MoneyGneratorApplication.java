package com.d_d.moneygnerator;

import com.d_d.moneygnerator.service.BinanceApiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoneyGneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoneyGneratorApplication.class, args);

        String ethPrice = BinanceApiService.getCurrentPrice("ETHUSDT");
        System.out.println("Aktualna cena ETH/USDT to: " + ethPrice);

        String adaPrice = BinanceApiService.getCurrentPrice("ADAUSDT");
        System.out.println("Aktualna cena ADA/USDT to: " + adaPrice);
    }

}
