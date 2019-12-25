package com.ww.activiti.controller;

import com.ww.activiti.enumeration.FileExtensionEnum;
import com.ww.activiti.enumeration.ProcessEventEnum;
import com.ww.common.Result;
import com.ww.service.ProcessInfoService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * @className ProcessController
 * @description 流程管理
 * @author beyond09.hik
 * @date 9:45 2019/12/25
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("process")
public class ProcessController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ProcessInfoService processInfoService;

    /**
     * 查看流程图
     * @author beyond09.hik
     * @date 9:45 2019/12/25
     * @param did did
     * @param ext ext
     * @param httpServletResponse httpServletResponse
     */
    @GetMapping("show")
    public void show(@RequestParam("did")String did, @RequestParam("ext")String ext, HttpServletResponse httpServletResponse) throws IOException {
        if (StringUtils.isEmpty(did) || StringUtils.isEmpty(ext)){
            return;
        }
        InputStream in = null;
        ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().deploymentId(did).singleResult();
        if (FileExtensionEnum.PNG.getExt().equalsIgnoreCase(ext)){
            in = repositoryService.getProcessDiagram(processDefinition.getId());
        }else if (FileExtensionEnum.BPMN.getExt().equalsIgnoreCase(ext)){
            in = repositoryService.getResourceAsStream(did, processDefinition.getResourceName());
        }
        if (Objects.nonNull(in)){
            OutputStream out = null;
            byte[] buf = new byte[1024];
            int length = 0;
            try {
                out = httpServletResponse.getOutputStream();
                while ((length = in.read(buf)) != -1) {
                    out.write(buf, 0, length);
                }
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }
    }

    /**
     * 启动一个流程
     * 参数：
     * @param  key:  流程定义KEY
     * @param variables
     *      bussId：  业务对象序号
     *      bussName：业务对象名称
     *      bussType：业务对象类型
     *      startUserId: 发起人id
     *      startUnitId: 发起人单位id
     * @author beyond09.hik
     * @date 9:46 2019/12/25
     * @return com.ww.common.Result<java.lang.String>
     */
    @PostMapping("run/{key}")
    public Result<String> run(@PathVariable String key, @RequestBody Map<String, Object> variables) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, variables);
        log.info("启动一个流程实例，id为：{}",processInstance.getId());
        return new Result<>(processInstance.getId());
    }

    /**
     * 获取所有流程
     * @author beyond09.hik
     * @date 9:48 2019/12/25
     * @return com.ww.common.Result<java.util.List<java.util.Map<java.lang.String,java.lang.Object>>>
     */
    @GetMapping("")
    public Result<List<Map<String,Object>>> list() {
        List<Map<String,Object>> list = processInfoService.process();
        return new Result<>(list);
    }


    /**
     * 获取流程节点
     * @author beyond09.hik
     * @date 9:49 2019/12/25
     * @param proDefId 流程节点ID
     * @return com.ww.common.Result<java.util.List<java.util.Map<java.lang.String,java.lang.Object>>>
     */
    @GetMapping("/{proDefId}/elements")
    public Result<List<Map<String,Object>>> listActivities(@PathVariable String proDefId) {
        List<Map<String,Object>> list = new ArrayList<>();
        BpmnModel model = repositoryService.getBpmnModel(proDefId);
        if(model != null) {
            Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();
            for(FlowElement e : flowElements) {
                log.info("flow element id:" + e.getId() + "  name:" + e.getName() + "   class:" + e.getClass().toString());
                if (ProcessEventEnum.START.getClassName().equalsIgnoreCase(e.getClass().toString()) ||
                        ProcessEventEnum.USER_TASK.getClassName().equalsIgnoreCase(e.getClass().toString())){
                    Map<String,Object> element = new HashMap<>();
                    element.put(e.getId(),e.getName());
                    list.add(element);
                }
            }
        }
        return new Result<>(list);
    }

}
