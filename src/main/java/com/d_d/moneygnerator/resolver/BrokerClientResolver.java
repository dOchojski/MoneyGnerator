package com.d_d.moneygnerator.resolver;

import com.d_d.moneygnerator.client.BrokerClient;
import com.d_d.moneygnerator.exception.ApplicationException;
import com.d_d.moneygnerator.model.enumeration.Broker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BrokerClientResolver {
    private final List<BrokerClient> clients;

    public BrokerClient resolve(final Broker broker) {
        return clients
                .stream()
                .filter(brokerClient -> brokerClient.getBroker() == broker)
                .findFirst()
                .orElseThrow(() -> new ApplicationException("Couldn't resolve broker for: " + broker));
    }
}
