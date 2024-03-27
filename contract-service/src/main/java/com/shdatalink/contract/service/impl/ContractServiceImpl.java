package com.shdatalink.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shdatalink.contract.mapper.ContractMapper;
import com.shdatalink.contract.model.Contract;
import com.shdatalink.contract.model.ContractState;
import com.shdatalink.contract.service.ContractService;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements ContractService {
    @Override
    public Contract create(Integer amount) {
        Contract contract = new Contract();
        contract.setAmount(amount);
        contract.setState(ContractState.PENDING);
        save(contract);
        return contract;
    }

    @Override
    public void reject(Integer contractId) {
        LambdaUpdateWrapper<Contract> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Contract::getId, contractId)
                .set(Contract::getState, ContractState.REJECTED);
        update(updateWrapper);
    }

    @Override
    public void approve(Integer contractId) {
        LambdaUpdateWrapper<Contract> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Contract::getId, contractId)
                .set(Contract::getState, ContractState.APPROVED);
        update(updateWrapper);
    }
}
