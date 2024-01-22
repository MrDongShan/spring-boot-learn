package com.mrdongshan.camunda.service;



import com.mrdongshan.camunda.domain.bo.InstanceBo;
import com.mrdongshan.camunda.domain.bo.TaskBo;
import com.mrdongshan.camunda.domain.vo.InstanceSearch;
import com.mrdongshan.camunda.domain.vo.ProcessFormVo;
import com.mrdongshan.camunda.domain.vo.StartProcessVo;
import com.mrdongshan.camunda.domain.vo.TaskSearch;

import java.util.List;

public interface IBpmnService {


    List<TaskBo> finishTask(ProcessFormVo vo);

    List<TaskBo> getCurrentTask(TaskSearch search);

    List<InstanceBo> getCurrentProcess(InstanceSearch search);

    void terminateProcess(ProcessFormVo vo);

    void backTask(ProcessFormVo vo);
    void reAssign(ProcessFormVo vo);

    void setVariables(ProcessFormVo vo);

    List<TaskBo> startProcess(StartProcessVo startProcessVo);

}
