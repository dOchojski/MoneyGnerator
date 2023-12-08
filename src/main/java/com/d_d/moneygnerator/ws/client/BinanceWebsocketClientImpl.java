package com.d_d.moneygnerator.ws.client;

import com.binance.connector.client.WebSocketStreamClient;
import com.binance.connector.client.impl.WebSocketStreamClientImpl;
import com.d_d.moneygnerator.model.WebsocketResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class BinanceWebsocketClientImpl implements BinanceWebSocketClient {
    private final WebSocketStreamClient client;
    private final ObjectMapper objectMapper;

    public BinanceWebsocketClientImpl() {
        this.client = new WebSocketStreamClientImpl();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public int subscribeFor(String symbol) {
        return client.aggTradeStream(symbol, ((event) -> log.info("Received event=[{}]", mapEventToResponse(event))));
    }

    @Override
    public int subscribeFor(String... symbols) {
        ArrayList<String> streams = Arrays
                .stream(symbols)
                .map(String::toLowerCase)
                .map(sym -> sym + "@trade")
                .collect(Collectors.toCollection(ArrayList::new));
        return client.combineStreams(streams, ((event) -> log.info("Received event=[{}]", mapEventsToResponse(event))));
    }

    @Override
    public void closeConnection(Integer id) {
        client.closeConnection(id);
    }

    @Override
    public void closeAllOpenConnections() {
        client.closeAllConnections();
    }

    private WebsocketResponse mapEventToResponse(String event) {
        try {
            Map<String, Object> parsedEvent = objectMapper.readValue(event, new TypeReference<>() {
            });
            return createResponse(parsedEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private WebsocketResponse mapEventsToResponse(String event) {
        try {
            Map<String, Object> parsedEvent = objectMapper.readValue(event, new TypeReference<>() {
            });
            Map<String, Object> data = (Map<String, Object>) parsedEvent.get("data");
            return createResponse(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private WebsocketResponse createResponse(Map<String, Object> parsedEvent) {
        BigDecimal fValue = new BigDecimal(0);
        if (parsedEvent.get("f") != null) {
            fValue = new BigDecimal(String.valueOf(parsedEvent.get("f")));
        }
        BigDecimal lValue = new BigDecimal(0);
        if (parsedEvent.get("l") != null) {
            lValue = new BigDecimal(String.valueOf(parsedEvent.get("l")));
        }
        return new WebsocketResponse(
                (String) parsedEvent.get("e"),
                (Long) parsedEvent.get("E"),
                (String) parsedEvent.get("s"),
                (Long) parsedEvent.get("a"),
                new BigDecimal(String.valueOf(parsedEvent.get("p"))),
                new BigDecimal(String.valueOf(parsedEvent.get("q"))),
                fValue,
                lValue,
                (Long) parsedEvent.get("T"),
                (Boolean) parsedEvent.get("m")
        );
    }
}
