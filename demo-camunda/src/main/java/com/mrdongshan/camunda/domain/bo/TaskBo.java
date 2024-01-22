package com.mrdongshan.camunda.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;


/**
 * 任务BO
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TaskBo extends BaseBo {

    /**
     * 任务code
     */
    private String code;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务描述
     */
    private String comment;


    /**
     * 任务前端路径
     */
    private String url;

    /**
     * 在数据库中定义的参数
     */
    private String parameters;

    /**
     * 执行人
     */
    private Long assignee;

    /**
     * 执行人姓名
     */
    private String assigneeName;

    /**
     * 关联病人Id
     */
    private Long relatedId;

    /**
     * 关联病人姓名
     */
    private String relatedName;

    /**
     * 任务产生时间
     */
    private Date createTime;

    /**
     * 截止时间
     */
    private Date dueDate;

    /**
     * 执行时间:单位-小时
     */
    private BigDecimal executeTime;

    private Long parentId;
    private Boolean available;
    private String runTimeTaskId;

    //以下为流程信息

    private String processDefinitionId;

    /**
     * 流程编号
     */
    private String processDefinitionKey;

    /**
     * 流程编号
     */
    private String processDefinitionName;

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

    private String instanceId;

    /**
     * 过程变量
     */
    private Map<String, Object> variables;

    /**
     * 是否可分配
     */
    private boolean cloudAssign;
}
