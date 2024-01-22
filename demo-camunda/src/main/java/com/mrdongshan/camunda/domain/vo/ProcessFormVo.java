package com.mrdongshan.camunda.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;


/**
 * 流程参数VO
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ProcessFormVo {

    /**
     * 流程实例id
     */
    private String processInstanceId;

    /**
     * 流程定义id
     */
    private String processDefinitionId;

    /**
     * 流程定义key
     */
    private String processDefinitionKey;

    /**
     * 任务id
     */
    private String runTimeTaskId;

    /**
     * 任务code
     */
    private String taskCode;

    /**
     * 下一个任务的执行者
     */
    private String nextTaskAssignee;

    /**
     * 参数 - map结构 <key, value>
     */
    private Map<String, Object> params;
}
