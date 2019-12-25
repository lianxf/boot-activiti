package com.ww.activiti.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @className TaskVariableEnum
 * @description Task 变量参数定义
 * @author beyond09.hik
 * @date 10:20 2019/12/25
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public enum TaskVariableEnum {

    /**
     * 处理类型
     */
    DEAL_TYPE("dealType"),
    /**
     * 处理结果原因
     */
    DEAL_REASON("dealReason"),

    /**
     * 驳回key
     */
    REJECT_ELEM_KEY("rejectElemKey"),

    /**
     * 处理人ID
     */
    DEAL_USER_ID("dealUserId"),

    /**
     * 处理单元ID
     */
    DEAL_UNIT_ID("dealUnitId");

    String key;
}
