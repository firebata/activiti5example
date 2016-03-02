package com.cnfwsy.app.day06.javaservice;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;

public class ServiceTask1 implements JavaDelegate {
	private final Logger log = Logger.getLogger(ServiceTask1.class.getName());

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Thread.sleep(5000);
		log.error("variavles=" + execution.getVariables());
		execution.setVariable("task1", "I am task 1");
		log.error("I am task 1.");

	}

}
