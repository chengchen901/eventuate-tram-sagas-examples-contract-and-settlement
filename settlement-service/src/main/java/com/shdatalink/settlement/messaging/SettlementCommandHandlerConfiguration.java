package com.shdatalink.settlement.messaging;

import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SettlementCommandHandlerConfiguration {
    @Bean
    public CommandDispatcher createSettlementCommandDispatcher(SettlementCommandHandler target,
                                                       SagaCommandDispatcherFactory sagaCommandDispatcherFactory) {
        return sagaCommandDispatcherFactory.make("createSettlementCommandDispatcher", target.createSettlementCommandHandlerDefinitions());
    }

    @Bean
    public CommandDispatcher approveSettlementCommandDispatcher(SettlementCommandHandler target,
                                                       SagaCommandDispatcherFactory sagaCommandDispatcherFactory) {
        return sagaCommandDispatcherFactory.make("approveSettlementCommandDispatcher", target.approveSettlementCommandHandlerDefinitions());
    }
}
