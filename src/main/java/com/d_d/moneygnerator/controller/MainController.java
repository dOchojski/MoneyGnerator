package com.d_d.moneygnerator.controller;

import com.d_d.moneygnerator.model.Price;
import com.d_d.moneygnerator.model.PriceComparator;
import com.d_d.moneygnerator.model.enumeration.Broker;
import com.d_d.moneygnerator.service.BrokerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final BrokerService brokerService;

    @GetMapping("/compare")
    public PriceComparator comparePrices(@RequestParam final String symbol,
                                         @RequestParam final Broker firstBroker,
                                         @RequestParam final Broker secondBroker) {
        String now = LocalDateTime.now().toString().replace("T", " ").substring(0, 19);
        Price firstPrice = brokerService.getPriceFor(symbol, firstBroker);
        Price secondPrice = brokerService.getPriceFor(symbol, secondBroker);
        BigDecimal difference = calculatePercentageDifference(firstPrice.price(), secondPrice.price());
        Broker less = firstPrice.price().doubleValue() < secondPrice.price().doubleValue() ? firstBroker : secondBroker;
        Broker more = less == firstBroker ? secondBroker : firstBroker;
        BigDecimal profit = difference.multiply(new BigDecimal("10000"));
        return new PriceComparator(now, firstPrice, secondPrice, difference + "%", profit, less, more);
    }

    @GetMapping("/{broker}/price")
    public Price checkPrice(@PathVariable final Broker broker,
                            @RequestParam final String symbol) {
        return brokerService.getPriceFor(symbol, broker);
    }

    private BigDecimal calculatePercentageDifference(BigDecimal original, BigDecimal newNumber) {
        if (original.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Original value cannot be zero for percentage calculation.");
        }

        BigDecimal difference = newNumber.subtract(original).abs();
        return difference.multiply(new BigDecimal("100")).divide(original, 8, RoundingMode.HALF_UP);
    }
}
