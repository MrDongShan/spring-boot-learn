package com.mrdongshan.camunda.controller;

import com.mrdongshan.camunda.domain.bo.InstanceBo;
import com.mrdongshan.camunda.domain.bo.TaskBo;
import com.mrdongshan.camunda.domain.vo.InstanceSearch;
import com.mrdongshan.camunda.domain.vo.ProcessFormVo;
import com.mrdongshan.camunda.domain.vo.StartProcessVo;
import com.mrdongshan.camunda.domain.vo.TaskSearch;
import com.mrdongshan.camunda.service.IBpmnService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final IBpmnService bpmnService;

    /**
     * 1、开启一个新流程(附带参数)
     *
     * @param startProcessVo
     * @return
     */
    @PostMapping("/start/process")
    public List<TaskBo> startProcess(@RequestBody StartProcessVo startProcessVo) {
        return bpmnService.startProcess(startProcessVo);
    }

    /**
     * 2、获取待办流程
     *
     * @param search
     * @return
     */
    @PostMapping("/current/process")
    public List<InstanceBo> getCurrentProcess(@RequestBody InstanceSearch search) {
        return bpmnService.getCurrentProcess(search);
    }

    /**
     * 3、获取待办任务
     *
     * @param search
     * @return
     */
    @PostMapping("/task/current")
    public List<TaskBo> getCurrentByProcess(@RequestBody TaskSearch search) {
        return bpmnService.getCurrentTask(search);
    }

    /**
     * 4、完成任务
     *
     * @param vo
     * @return
     */
    @PutMapping("/finish/process/task")
    public List<TaskBo> finishTask(@Validated @RequestBody ProcessFormVo vo) {
        return bpmnService.finishTask(vo);
    }

    /**
     * 5、终止流程
     *
     * @param vo
     */
    @PostMapping("/terminate/process")
    public void terminateProcess(@Validated @RequestBody ProcessFormVo vo) {
        bpmnService.terminateProcess(vo);
    }

    /**
     * 6、任务回退到上一步
     *
     * @param vo
     */
    @PostMapping("/task/back")
    public void backTask(@Validated @RequestBody ProcessFormVo vo) {
        bpmnService.backTask(vo);
    }

    /**
     * 8、分配任务
     *
     * @param vo
     */
    @PostMapping("/task/reAssign")
    public void reAssign(@Validated @RequestBody ProcessFormVo vo) {
        bpmnService.reAssign(vo);
    }

    /**
     * 9、 设置流程变量
     *
     * @param vo
     */
    @PostMapping("/variables")
    public void setVariables(@Validated @RequestBody ProcessFormVo vo) {
        bpmnService.setVariables(vo);
    }
}
