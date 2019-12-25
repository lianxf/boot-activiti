package com.sdcq.zhxy.activiti.config;

import org.activiti.engine.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @className ActivitiConfig
 * @description activiti的配置文件
 * @author beyond09.hik
 * @date 9:14 2019/12/25
 * @version 1.0
 */
@Configuration
public class ActivitiConfig {

   @Resource
   private DataSource dataSource;

   /**
    * 创建activiti的实例对象
    * @author beyond09.hik
    * @date 9:14 2019/12/25
    * @return org.activiti.engine.ProcessEngineConfiguration
    */
   @Bean
   public ProcessEngineConfiguration processEngineConfiguration() {
       ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
       //配置数据源
       configuration.setDataSource(dataSource);
       //如果表不存在就创建
       configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
       //关闭计时器
       configuration.setAsyncExecutorActivate(false);
       return configuration;
   }
   /**
    * 得到程序执行引擎
    * @author beyond09.hik
    * @date 9:16 2019/12/25
    * @return org.activiti.engine.ProcessEngine
    */
    @Bean
    public ProcessEngine processEngine() {
        return processEngineConfiguration().buildProcessEngine();
    }

}

