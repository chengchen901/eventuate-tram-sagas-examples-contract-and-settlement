package com.shdatalink.contract.sagas;

import com.shdatalink.contractandsettlement.common.settlement.messaging.commands.ApproveSettlementCommand;
import com.shdatalink.contractandsettlement.common.settlement.messaging.commands.CreateSettlementCommand;
import io.eventuate.tram.commands.consumer.CommandWithDestination;
import org.springframework.stereotype.Component;

import static io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder.send;

@Component
public class SettlementServiceProxy {
    CommandWithDestination createSettlement(Integer contractId, Integer amount) {
        return send(new CreateSettlementCommand(contractId, amount))
                .to("createSettlement")
                .build();
    }
    CommandWithDestination approveSettlement(Integer contractId, Integer amount) {
        return send(new ApproveSettlementCommand(contractId, amount))
                .to("approveSettlement")
                .build();
    }
}
