package com.sdcq.zhxy.activiti.enumeration;

/**
 * @author beyond09.hik
 * @version 1.0
 * @className ProcessNodeEnum
 * @description 流程节点
 * @date 2019/12/25 9:26
 */
public enum  ProcessNodeEnum {

    /**
     * 开始节点
     */
    START("start"),

    /**
     * 结束节点
     */
    END("end");

    private String name;

    ProcessNode(String name) {
        this.name = name;
    }
}

