package com.ww.activiti.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author beyond09.hik
 * @version 1.0
 * @className DealTypeEnum
 * @description  DealTypeEnum
 * @date 2019/12/25 10:12
 */
@Getter
@AllArgsConstructor
public enum DealTypeEnum {

    /**
     * 驳回
     */
    REJECT("1", "S00000"),

    /**
     * 通过
     */
    PASS("0", "");

    String value;

    String elementKey;
}
