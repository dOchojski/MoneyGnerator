package com.d_d.moneygnerator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SymbolInfo {
    public String symbol;
}
