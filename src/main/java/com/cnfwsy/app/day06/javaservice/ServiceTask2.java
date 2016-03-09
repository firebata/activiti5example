package com.cnfwsy.app.day06.javaservice;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;

public class ServiceTask2 implements JavaDelegate {
	private final Logger log = Logger.getLogger(ServiceTask2.class.getName());

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Thread.sleep(10000);
		log.error("variavles=" + execution.getVariables());
		execution.setVariable("task2", "I am task 2");
		log.error("I am task 2.");

	}

}
