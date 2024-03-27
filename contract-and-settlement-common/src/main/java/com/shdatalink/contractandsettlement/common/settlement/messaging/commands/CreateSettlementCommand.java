package com.shdatalink.contractandsettlement.common.settlement.messaging.commands;

import io.eventuate.tram.commands.common.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSettlementCommand  implements Command {
    private Integer contractId;
    private Integer amount;
}
