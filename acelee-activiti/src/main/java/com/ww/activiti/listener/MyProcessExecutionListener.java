package com.ww.activiti.listener;

import com.ww.activiti.enumeration.ProcessEventEnum;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @className MyProcessExecutionListener
 * @description 流程实例监听类
 * @author beyond09.hik
 * @date 10:36 2019/12/25
 * @version 1.0
 */
@Component
@Slf4j
public class MyProcessExecutionListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        String eventName = execution.getEventName();
        //start
        if (ProcessEventEnum.START.getName().equals(eventName)) {
            log.info("==================start==================");
        }else if (ProcessEventEnum.END.getName().equals(eventName)) {
            log.info("==================end==================");
        }
    }
}

