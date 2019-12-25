package com.ww.activiti.enumeration;

import lombok.Getter;

/**
 * @author beyond09.hik
 * @version 1.0
 * @className FileExtensionEnum
 * @description 文件扩展名
 * @date 2019/12/25 9:59
 */
@Getter
public enum FileExtensionEnum {

    /**
     * PNG
     */
    PNG(".png"),

    /**
     * BPMV
     */
    BPMN(".bpmn");

    String ext;

    FileExtensionEnum(String ext) {
        this.ext = ext;
    }
}
