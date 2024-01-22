package com.mrdongshan.camunda.domain.bo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 流程BO
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProcessBo extends BaseBo {

    /**
     * 编码
     */
    private String code;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 描述
     */
    private String comment;

    /**
     * 名称
     */
    private String name;

    /**
     * 业务类型
     */
    private Integer bizType;

    private Boolean available;
    private List<TaskBo> tasks;
    private List<InstanceBo> instances;
}
