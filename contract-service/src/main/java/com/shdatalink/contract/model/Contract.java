package com.shdatalink.contract.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("contract")
public class Contract {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer amount;
    private ContractState state;
}
