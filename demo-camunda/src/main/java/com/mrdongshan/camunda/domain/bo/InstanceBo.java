package com.mrdongshan.camunda.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 流程实例BO
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InstanceBo extends BaseBo {

    private Long initiatorId;

    private String initiatorName;

    private Long processId;

    /**
     * 流程名称
     */
    private String processName;

    /**
     * 流程编码
     */
    private String processCode;

    /**
     * 流程开始时间
     */
    private Date instanceStartTime;

    /**
     * 流程发起人
     */
    private Long instanceStartUser;

    /**
     * 流程发起人名字
     */
    private String instanceStartUserName;

    /**
     * 当前任务
     */
    private Long currTaskId;

    /**
     * 当前任务编号
     */
    private String currTaskCode;

    /**
     * 当前任务名称
     */
    private String currTaskName;

    /**
     * 当前任务执行人名称
     */
    private String currTaskAssigneeName;

    /**
     * 进度
     */
    private Integer progress;

    private Boolean success;

    private Date startTime;

    private Date endTime;

    private Long bizId;

    private Integer bizType;

    /**
     * 关联病人Id
     */
    private Long relatedId;

    /**
     * 关联病人姓名
     */
    private String relatedName;

    /**
     * 执行时间:单位-小时
     */
    private BigDecimal executeTime;

    /**
     * 状态
     */
    private Integer state;
}

