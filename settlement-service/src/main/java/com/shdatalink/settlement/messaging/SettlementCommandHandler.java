package com.shdatalink.settlement.messaging;

import com.shdatalink.contractandsettlement.common.settlement.messaging.commands.ApproveSettlementCommand;
import com.shdatalink.contractandsettlement.common.settlement.messaging.commands.CreateSettlementCommand;
import com.shdatalink.contractandsettlement.common.settlement.messaging.replies.CreateSettlementFailure;
import com.shdatalink.contractandsettlement.common.settlement.messaging.replies.CreateSettlementSuccess;
import com.shdatalink.settlement.service.SettlementService;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

@Slf4j
@Component
public class SettlementCommandHandler {
    @Autowired
    private SettlementService settlementService;

    public CommandHandlers createSettlementCommandHandlerDefinitions() {
        return SagaCommandHandlersBuilder
                .fromChannel("createSettlement")
                .onMessage(CreateSettlementCommand.class, this::createSettlement)
                .build();
    }

    public Message createSettlement(CommandMessage<CreateSettlementCommand> cm) {
        CreateSettlementCommand cmd = cm.getCommand();
        try {
            log.info("SettlementCommandHandler 处理 createSettlement, cm: {}", cm);
            if (cmd.getAmount() == 1) {
                throw new RuntimeException("合同金额不能为1（自定义异常）");
            }
            settlementService.createSettlement(cmd.getContractId(), cmd.getAmount());
            return withSuccess(new CreateSettlementSuccess());
        } catch (Exception e) {
            log.info("SettlementCommandHandler 处理异常 createSettlement, cm: {}", cm, e);
            return withFailure(new CreateSettlementFailure());
        }
    }

    public CommandHandlers approveSettlementCommandHandlerDefinitions() {
        return SagaCommandHandlersBuilder
                .fromChannel("approveSettlement")
                .onMessage(ApproveSettlementCommand.class, this::approveSettlement)
                .build();
    }

    public Message approveSettlement(CommandMessage<ApproveSettlementCommand> cm) {
        ApproveSettlementCommand cmd = cm.getCommand();
        try {
            log.info("SettlementCommandHandler 处理 approveSettlement, cm: {}", cm);
            settlementService.approveSettlement(cmd.getContractId());
            return withSuccess();
        } catch (Exception e) {
            log.info("SettlementCommandHandler 处理异常 approveSettlement, cm: {}", cm, e);
            return withFailure();
        }
    }
}
