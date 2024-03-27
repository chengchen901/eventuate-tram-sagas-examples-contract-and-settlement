package com.shdatalink.contract.sagas;

import com.shdatalink.contract.model.Contract;
import com.shdatalink.contract.sagas.data.CreateContractSagaData;
import com.shdatalink.contract.service.ContractService;
import io.eventuate.tram.commands.consumer.CommandWithDestination;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateContractSaga implements SimpleSaga<CreateContractSagaData> {

    @Autowired
    private SettlementServiceProxy settlementService;

    @Autowired
    private ContractService contractService;

    private SagaDefinition<CreateContractSagaData> sagaDefinition =
            step()
                .invokeLocal(this::create)
                .withCompensation(this::reject)
            .step()
                .invokeParticipant(this::createSettlement)
            .step()
                .invokeLocal(this::approve)
            .step()
                .invokeParticipant(this::approveSettlement)
            .build();
    @Override
    public SagaDefinition<CreateContractSagaData> getSagaDefinition() {
        return sagaDefinition;
    }

    private void create(CreateContractSagaData data) {
        log.info("CreateContractSaga 执行 create, data: {}", data);
        Contract contract = contractService.create(data.getAmount());
        data.setContractId(contract.getId());
    }

    private void reject(CreateContractSagaData data) {
        log.info("CreateContractSaga 执行 reject, data: {}", data);
        contractService.reject(data.getContractId());
    }

    private CommandWithDestination createSettlement(CreateContractSagaData data) {
        log.info("CreateContractSaga 执行 createSettlement, data: {}", data);
        return settlementService.createSettlement(data.getContractId(), data.getAmount());
    }

    private void approve(CreateContractSagaData data) {
        log.info("CreateContractSaga 执行 approve, data: {}", data);
        contractService.approve(data.getContractId());
    }

    private CommandWithDestination approveSettlement(CreateContractSagaData data) {
        log.info("CreateContractSaga 执行 approveSettlement, data: {}", data);
        return settlementService.approveSettlement(data.getContractId(), data.getAmount());
    }
}
