package com.shdatalink.contract.controller;

import com.shdatalink.contract.model.Contract;
import com.shdatalink.contract.service.ContractSagaService;
import com.shdatalink.contract.web.request.CreateContractRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    private ContractSagaService contractSagaService;
    @PostMapping("/create")
    public Contract createContract(@RequestBody CreateContractRequest createContractRequest) {
        return contractSagaService.createContract(createContractRequest);
    }
}
