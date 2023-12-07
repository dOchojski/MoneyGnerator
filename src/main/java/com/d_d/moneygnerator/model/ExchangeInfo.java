package com.d_d.moneygnerator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeInfo {
    public List<SymbolInfo> symbols;
}
