package com.d_d.moneygnerator.model;

import java.util.List;
import java.util.Map;

public record WebsocketResponse(String id,
                                Integer status,
                                Map<String, Object> result,
                                Map<String, Object> error,
                                List<Map<String, Object>> rateLimits) {
}
