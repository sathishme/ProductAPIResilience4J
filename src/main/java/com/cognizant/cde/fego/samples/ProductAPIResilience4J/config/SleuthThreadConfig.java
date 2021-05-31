//****************** Generated by UpShift Transformer Logic ******************/

package com.cognizant.cde.fego.samples.ProductAPIResilience4J.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceAsyncTaskExecutor;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableAsync
@EnableScheduling
public class SleuthThreadConfig extends AsyncConfigurerSupport implements SchedulingConfigurer {

    @Autowired
    private BeanFactory beanFactory;

    private static final int CORE_POOL_SIZE = 1;

    private static final int MAX_POOL_SIZE = 1;

    @Bean
    public Executor executor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        threadPoolTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        threadPoolTaskExecutor.initialize();
        return new LazyTraceExecutor(beanFactory, threadPoolTaskExecutor);
    }

    @Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        threadPoolTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        threadPoolTaskExecutor.initialize();
        return new LazyTraceAsyncTaskExecutor(beanFactory, threadPoolTaskExecutor);
    }

    @Bean(destroyMethod = "shutdown")
    public Executor schedulingExecutor() {
        return Executors.newScheduledThreadPool(CORE_POOL_SIZE);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(schedulingExecutor());
    }
}
