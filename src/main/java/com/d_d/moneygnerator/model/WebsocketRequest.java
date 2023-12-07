package com.d_d.moneygnerator.model;

import java.util.Map;

public record WebsocketRequest(String id, String method, Map<String, Object> params) {

}
