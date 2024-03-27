package com.shdatalink.settlement.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("settlement")
public class Settlement {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer contractId;
    private String content;
    private SettlementState state;
}
