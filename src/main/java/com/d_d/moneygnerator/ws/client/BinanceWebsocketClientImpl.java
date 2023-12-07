package com.d_d.moneygnerator.ws.client;

import com.binance.connector.client.WebSocketStreamClient;
import com.binance.connector.client.impl.WebSocketStreamClientImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BinanceWebsocketClientImpl implements BinanceWebSocketClient {
    private static final String URL = "wss://testnet.binance.vision/ws-api/v3";
    private final WebSocketStreamClient client;

    public BinanceWebsocketClientImpl() {
        this.client = new WebSocketStreamClientImpl(URL);
    }

    @Override
    public int subscribeFor(String symbol) {
        return client.aggTradeStream(symbol, ((event) -> log.info("Received event=[{}]", event)));
    }

    @Override
    public int subscribeFor(String... symbols) {
        ArrayList<String> streams = Arrays
                .stream(symbols)
                .collect(Collectors.toCollection(ArrayList::new));
        return client.combineStreams(streams, ((event) -> log.info("Received event=[{}]", event)));
    }

    @Override
    public void closeConnection(Integer id) {
        client.closeConnection(id);
    }

    @Override
    public void closeAllOpenConnections() {
        client.closeAllConnections();
    }
}
