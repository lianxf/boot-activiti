package com.ww.activiti.controller;

import com.ww.activiti.vo.ReProcdef;
import com.ww.common.RestServiceController;
import com.ww.util.ToWeb;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @className ActivitiController
 * @description 流程控制接口
 * @author beyond09.hik
 * @date 9:40 2019/12/25
 * @version 1.0
 */
@RestController
@RequestMapping("prof")
public class ActivitiController implements RestServiceController<ProcessDefinition, String> {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    /**
     * 启动一个流程
     * @author beyond09.hik
     * @date 9:41 2019/12/25
     * @param key 流程key
     * @return java.lang.Object
     */
    @GetMapping("start")
    public Object start(@RequestParam("key")String key) {
        runtimeService.startProcessInstanceByKey(key);
        return ToWeb.buildResult().refresh();
    }


    @Override
    public Object getOne(String s) {
        return null;
    }

    @Override
    public Object getList(@RequestParam(value = "rowSize", defaultValue = "1000", required = false) Integer rowSize,
                          @RequestParam(value = "page", defaultValue = "1", required = false) Integer page) {
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery()
                .listPage(rowSize * (page - 1), rowSize);
        long count = repositoryService.createProcessDefinitionQuery().count();
        List<ReProcdef> list = new ArrayList<>();
        for (ProcessDefinition processDefinition : processDefinitions) {
            ReProcdef reProcdef = new ReProcdef(processDefinition);
            list.add(reProcdef);
        }

        return ToWeb.buildResult().setRows(
                ToWeb.Rows.buildRows()
                        .setRowSize(rowSize)
                        .setTotalPages((int) (count / rowSize + 1))
                        .setTotalRows(count)
                        .setList(list)
                        .setCurrent(page)
        );
    }

    @Override
    public Object postOne(ProcessDefinition entity) {
        return null;
    }

    @Override
    public Object putOne(String s, ProcessDefinition entity) {
        return null;
    }

    @Override
    public Object patchOne(String s, ProcessDefinition entity) {
        return null;
    }

    @Override
    public Object deleteOne(@PathVariable("id")String id) {
        //根据deploymentID删除定义的流程，普通删除
        repositoryService.deleteDeployment(id);
        System.out.println("普通删除--流程定义删除成功");
        return ToWeb.buildResult().refresh();
        //强制删除
//        repositoryService.deleteDeployment(id, true);
//        System.out.println("强制删除--流程定义删除成功");
    }


}
