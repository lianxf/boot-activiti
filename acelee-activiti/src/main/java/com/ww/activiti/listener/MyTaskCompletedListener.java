package com.ww.activiti.listener;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @className MyTaskCompletedListener
 * @description 节点任务完成监听类
 * @author beyond09.hik
 * @date 10:36 2019/12/25
 * @version 1.0
 */
@Component
@Slf4j
public class MyTaskCompletedListener implements TaskListener {

    /**
     * 监听任务的事件
     * @author beyond09.hik
     * @date 10:36 2019/12/25
     * @param delegateTask {@link DelegateTask}
     */
    @Override
    public void notify(DelegateTask delegateTask) {
        String assignee = delegateTask.getAssignee();
        String name = delegateTask.getName();
        Set<String> variableNames = delegateTask.getVariableNames();
        for (String key : variableNames) {
            Object value = delegateTask.getVariable(key);
            log.info(key + " = " + value);
        }
        log.info("一个任务["+name+"]被创建了，由["+assignee+"]负责办理");
    }
}


