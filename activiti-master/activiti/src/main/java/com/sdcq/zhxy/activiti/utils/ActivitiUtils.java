package com.sdcq.zhxy.activiti.utils;

import com.sdcq.zhxy.activiti.enumeration.ProcessNode;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.springframework.util.StringUtils;

/**
 * @className ActivitiUtils
 * @description Activiti 工具类
 * @author beyond09.hik
 * @date 9:21 2019/12/25
 * @version 1.0
 */
public class ActivitiUtils {
    /**
     * 创建用户任务
     * @author beyond09.hik
     * @date 9:19 2019/12/25
     * @param id  任务id
     * @param name 任务名称
     * @param assignee  任务所属人员
     * @return org.activiti.bpmn.model.UserTask
     */
    public static UserTask createUserTask(String id, String name, String assignee) {
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        userTask.setAssignee(assignee);
        return userTask;
    }

    /**
     * 创建连接线
     * @author beyond09.hik
     * @date 9:21 2019/12/25
     * @param from 开始任务id
     * @param to  连接任务id
     * @param name 连接线名称
     * @param condition 链接条件
     * @return org.activiti.bpmn.model.SequenceFlow {@link SequenceFlow}
     */
    public static SequenceFlow crateSequenceFlow(String from, String to,String name,String condition) {
        SequenceFlow flow = new SequenceFlow();
        flow.setSourceRef(from);
        flow.setTargetRef(to);
        flow.setName(name);

        if(!StringUtils.isEmpty(condition)){
            flow.setConditionExpression(condition);
        }
        return flow;
    }

    /**
     * 创建连接线
     * @author beyond09.hik
     * @date 9:21 2019/12/25
     * @param from 开始任务id
     * @param to  连接任务id
     * @return org.activiti.bpmn.model.SequenceFlow {@link SequenceFlow}
     */
    public static SequenceFlow crateSequenceFlow(String from, String to) {
        SequenceFlow flow = new SequenceFlow();
        flow.setSourceRef(from);
        flow.setTargetRef(to);
        return flow;
    }

    /**
     * 开始任务
     * @author beyond09.hik
     * @date 9:25 2019/12/25
     * @return org.activiti.bpmn.model.StartEvent 开始流程节点
     */
    public static StartEvent crateStartEvent() {
        StartEvent startEvent = new StartEvent();
        startEvent.setId(ProcessNode.START.name());
        return startEvent;
    }

    /**
     * 结束任务
     * @author beyond09.hik
     * @date 9:29 2019/12/25
     * @return org.activiti.bpmn.model.EndEvent 流程结束节点
     */
    public static EndEvent createEndEvent() {
        EndEvent endEvent = new EndEvent();
        endEvent.setId(ProcessNode.END.name());
        return endEvent;
    }
}
