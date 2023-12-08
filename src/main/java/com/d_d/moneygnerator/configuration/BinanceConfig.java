package com.d_d.moneygnerator.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "broker.config.binance")
public class BinanceConfig extends BrokerConfig {
}
