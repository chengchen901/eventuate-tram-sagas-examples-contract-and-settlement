package com.shdatalink.contract.sagas.data;

import lombok.Data;

@Data
public class CreateContractSagaData {
    private Integer contractId;
    private Integer amount;
}
