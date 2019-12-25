package com.ww.activiti.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author beyond09.hik
 * @version 1.0
 * @className ProcessEventEnum
 * @description 流程节点枚举定义
 * @date 2019/12/25 10:06
 */
@AllArgsConstructor
@Getter
public enum ProcessEventEnum {

    /**
     * 流程起点
     */
    START("start", "class org.activiti.bpmn.model.StartEvent"),

    /**
     * 用户任务
     */
    USER_TASK("", "class org.activiti.bpmn.model.UserTask"),

    /**
     * 流程终点
     */
    END("end", "");

    String name;
    String className;
}
