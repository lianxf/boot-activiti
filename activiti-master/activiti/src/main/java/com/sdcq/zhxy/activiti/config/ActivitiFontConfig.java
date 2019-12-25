package com.sdcq.zhxy.activiti.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;

/**
 * @className ActivitiFontConfig
 * @description Activiti乱码设置
 * @author beyond09.hik
 * @date 9:16 2019/12/25
 * @version 1.0
 */
@Configuration
public class ActivitiFontConfig implements ProcessEngineConfigurationConfigurer {

    private static final String FONT = "宋体";

    /**
     * 解決工作流生成图片乱码问题
     * @author beyond09.hik
     * @date 9:16 2019/12/25
     * @param processEngineConfiguration {@link SpringProcessEngineConfiguration}
     */
    @java.lang.Override
    public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {
        processEngineConfiguration.setActivityFontName(FONT);
        processEngineConfiguration.setAnnotationFontName(FONT);
        processEngineConfiguration.setLabelFontName(FONT);
    }
}
