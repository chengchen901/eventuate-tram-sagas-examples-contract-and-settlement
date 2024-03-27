package com.shdatalink.contract.service;

import com.shdatalink.contract.model.Contract;
import com.shdatalink.contract.web.request.CreateContractRequest;

public interface ContractSagaService {
    Contract createContract(CreateContractRequest createContractRequest);
}
