package com.jin.cron;

import java.text.ParseException;

import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

import com.jin.service.SchedulerService;

public abstract class AbsMyCronImpl implements MyCron{

	private static final Logger logger = LoggerFactory.getLogger(AbsMyCronImpl.class);

	@Autowired
	SchedulerService schedulerService;
	
	protected CronTriggerFactoryBean cronTriggerFactoryBean;
	
	
    public void reschedule( String cronExpression ) {    	
    	try {
    		CronTriggerImpl cronTriggerImpl = (CronTriggerImpl)(cronTriggerFactoryBean.getObject());
			cronTriggerImpl.setCronExpression(cronExpression);
	        schedulerService.reschedule(cronTriggerImpl);
	        
	        logger.info("Reschedule trigger " +  cronTriggerImpl.getKey() + " with new schedule " + cronTriggerImpl.getCronExpression());

		} catch (ParseException e) {
			logger.error("Quartz Scheduler can not reschedule with the expression " + cronExpression + ". the error is " + e.getMessage(), e);
			
		}   	
    }
    
    public void pause() {
    	
    	try {
    		CronTriggerImpl cronTriggerImpl = (CronTriggerImpl)(cronTriggerFactoryBean.getObject());
	        schedulerService.pause(cronTriggerImpl);
	        
	        logger.info("Pause trigger " +  cronTriggerImpl.getKey() + " with new schedule " + cronTriggerImpl.getCronExpression());

		} catch (Exception e) {
			logger.error("Quartz Scheduler can not Pause the trigger, the error is " + e.getMessage(), e);
			
		}   	
    }
    
    public void resume() {
    	
    	try {
    		CronTriggerImpl cronTriggerImpl = (CronTriggerImpl)(cronTriggerFactoryBean.getObject());
	        schedulerService.resume(cronTriggerImpl);
	        
	        logger.info("Pause trigger " +  cronTriggerImpl.getKey() + " with new schedule " + cronTriggerImpl.getCronExpression());

		} catch (Exception e) {
			logger.error("Quartz Scheduler can not Pause the trigger, the error is " + e.getMessage(), e);
			
		}   	
    }

}
