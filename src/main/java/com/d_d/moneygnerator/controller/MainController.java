package com.d_d.moneygnerator.controller;

import com.d_d.moneygnerator.ws.client.BinanceWebSocketClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class MainController {
    private final BinanceWebSocketClient webSocketClient;
    private final ConcurrentHashMap<String, Integer> cache;

    public MainController(final BinanceWebSocketClient webSocketClient) {
        this.webSocketClient = webSocketClient;
        this.cache = new ConcurrentHashMap<>();
    }

    @GetMapping("/subscribe")
    public String testSubscribe(@RequestParam String symbols) {
        String[] symbolsToSubscribe = symbols.split(",");
        int id = webSocketClient.subscribeFor(symbolsToSubscribe);
        String streamId = UUID.randomUUID().toString();
        cache.put(streamId, id);
        return streamId;
    }

    @DeleteMapping("/subscribe/{streamId}")
    public void testUnsubscribe(@PathVariable String streamId) {
        if (!cache.containsKey(streamId)) {
            return;
        }
        Integer idToUnsubscribe = cache.get(streamId);
        webSocketClient.closeConnection(idToUnsubscribe);
    }
}
