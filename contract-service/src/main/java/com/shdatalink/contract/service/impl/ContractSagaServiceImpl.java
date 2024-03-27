package com.shdatalink.contract.service.impl;

import com.shdatalink.contract.model.Contract;
import com.shdatalink.contract.sagas.CreateContractSaga;
import com.shdatalink.contract.sagas.data.CreateContractSagaData;
import com.shdatalink.contract.service.ContractSagaService;
import com.shdatalink.contract.service.ContractService;
import com.shdatalink.contract.web.request.CreateContractRequest;
import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ContractSagaServiceImpl implements ContractSagaService {
    @Autowired
    private SagaInstanceFactory sagaInstanceFactory;
    @Autowired
    private ContractService contractService;
    @Autowired
    private CreateContractSaga createContractSaga;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Contract createContract(CreateContractRequest createContractRequest) {
        log.info("ContractSagaServiceImpl 开始执行 createContract");
        CreateContractSagaData data = new CreateContractSagaData();
        data.setAmount(createContractRequest.getAmount());
        sagaInstanceFactory.create(createContractSaga, data);
        Contract contract = contractService.getById(data.getContractId());
        log.info("ContractSagaServiceImpl 结束执行 createContract");
        return contract;
    }
}
