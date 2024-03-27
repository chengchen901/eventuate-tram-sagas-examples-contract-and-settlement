package com.shdatalink.settlement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shdatalink.settlement.model.Settlement;

public interface SettlementService extends IService<Settlement> {

    void createSettlement(Integer contractId, Integer amount);

    void approveSettlement(Integer contractId);
}
