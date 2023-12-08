package com.d_d.moneygnerator.model;

import com.d_d.moneygnerator.model.enumeration.Broker;

import java.math.BigDecimal;

public record Price(Broker broker, BigDecimal price) {
}
