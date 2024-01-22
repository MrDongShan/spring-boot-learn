package com.mrdongshan.camunda.domain.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

/**
 * 开启流程VO
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class StartProcessVo {

    /**
     * 流程key
     */
    @NotEmpty(message = "流程key 不能为空!")
    private String processKey;

    /**
     * 参数 - map结构 <key, value>
     */
    private Map<String, Object> params;
}
