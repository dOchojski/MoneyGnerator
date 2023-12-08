package com.d_d.moneygnerator.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record WebsocketResponse(String eventType,
                                Long timestamp,
                                String symbol,
                                Long aggregateTradeId,
                                BigDecimal price,
                                BigDecimal quantity,
                                BigDecimal firstTradeId,
                                BigDecimal lastTradeId,
                                Long tradeTimestamp,
                                Boolean isMarketMaker) {
}
