package com.shdatalink.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shdatalink.contract.model.Contract;

public interface ContractService extends IService<Contract> {
    Contract create(Integer amount);

    void reject(Integer contractId);

    void approve(Integer contractId);
}
