package com.d_d.moneygnerator.ws.client;

public interface BinanceWebSocketClient {

    int subscribeFor(String symbol);

    int subscribeFor(String... symbols);

    void closeConnection(Integer id);

    void closeAllOpenConnections();
}
