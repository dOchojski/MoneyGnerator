package com.d_d.moneygnerator.model;

import com.d_d.moneygnerator.model.enumeration.Broker;

import java.math.BigDecimal;

public record PriceComparator(String time, Price firstPrice, Price secondPrice, String difference, BigDecimal profit, Broker less, Broker more) {
}
