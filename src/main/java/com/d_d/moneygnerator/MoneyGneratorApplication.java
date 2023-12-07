package com.d_d.moneygnerator;

import com.d_d.moneygnerator.model.PriceResponse;
import com.d_d.moneygnerator.model.SymbolInfo;
import com.d_d.moneygnerator.service.BinanceApiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class MoneyGneratorApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MoneyGneratorApplication.class, args);
        BinanceApiService binanceApiService = context.getBean(BinanceApiService.class);

        String ethUsdt = binanceApiService.getCurrentPrice("ETHUSDT");
        System.out.println(ethUsdt);

        binanceApiService.printAllSymbolsWithPrices();
    }
}
