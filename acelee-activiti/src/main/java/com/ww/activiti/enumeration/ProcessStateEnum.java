package com.ww.activiti.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author beyond09.hik
 * @version 1.0
 * @className ProcessStateEnum
 * @description 流程状态
 * @date 2019/12/25 9:51
 */
@Getter
@AllArgsConstructor
public enum ProcessStateEnum {

    /**
     * 进行中的流程
     */
    ACTIVE("active"),
    /**
     * 被挂起
     */
    SUSPEND("suspend");

    String status;


}
