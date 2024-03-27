package com.shdatalink.settlement.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shdatalink.settlement.mapper.SettlementMapper;
import com.shdatalink.settlement.model.Settlement;
import com.shdatalink.settlement.model.SettlementState;
import com.shdatalink.settlement.service.SettlementService;
import org.springframework.stereotype.Service;

@Service
public class SettlementServiceImpl extends ServiceImpl<SettlementMapper, Settlement> implements SettlementService {
    @Override
    public void createSettlement(Integer contractId, Integer amount) {
        Settlement settlement = new Settlement();
        settlement.setContractId(contractId);
        settlement.setContent("合同金额为：" + amount);
        settlement.setState(SettlementState.PENDING);
        save(settlement);
    }

    @Override
    public void approveSettlement(Integer contractId) {
        LambdaUpdateWrapper<Settlement> updateWrapper = new UpdateWrapper<Settlement>().lambda();
        updateWrapper.eq(Settlement::getContractId, contractId)
                .set(Settlement::getState, SettlementState.APPROVED);
        update(updateWrapper);
    }
}
