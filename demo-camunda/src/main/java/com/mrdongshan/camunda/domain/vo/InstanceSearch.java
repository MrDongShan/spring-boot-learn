package com.mrdongshan.camunda.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;


@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class InstanceSearch {
    private String keyword;
    private Date startTime;
    private Date endTime;
    private boolean current;
    private boolean currentUser;
    private Long instanceId;
    /*
    订单类型
     */
    private Integer bizType;
    /**
     * 订单ID
     */
    private Long bizId;
}
