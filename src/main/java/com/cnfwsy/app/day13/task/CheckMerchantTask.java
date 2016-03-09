package com.cnfwsy.app.day13.task;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.log4j.Logger;

public class CheckMerchantTask implements TaskListener {
	private final Logger log = Logger.getLogger(CheckMerchantTask.class.getName());

	@Override
	public void notify(DelegateTask delegateTask) {
			
	}

}
