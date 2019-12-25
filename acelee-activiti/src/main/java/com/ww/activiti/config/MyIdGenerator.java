package com.ww.activiti.config;

import org.activiti.engine.impl.cfg.IdGenerator;

import java.util.UUID;

/**
 * @className MyIdGenerator
 * @description Id 生成器
 * @author beyond09.hik
 * @date 9:40 2019/12/25
 * @version 1.0
 */
public class MyIdGenerator  implements IdGenerator {

    @Override
    public String getNextId() {
       return UUID.randomUUID().toString().replaceAll("-", "");
    }
}