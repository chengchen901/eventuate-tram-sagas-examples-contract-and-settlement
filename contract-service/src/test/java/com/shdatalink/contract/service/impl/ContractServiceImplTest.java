package com.shdatalink.contract.service.impl;

import com.shdatalink.contract.ContractApplicationTest;
import com.shdatalink.contract.model.Contract;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ContractServiceImplTest extends ContractApplicationTest {

    @Autowired
    private ContractServiceImpl contractServiceImpl;

    @Test
    public void create() {
        Contract contract = contractServiceImpl.create(1);
        log.info("contract:{}", contract);
    }
}
