package com.cnfwsy.app.day10;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AfterModifyApplyContentProcessor implements TaskListener {
	private static final Logger logger = LoggerFactory
			.getLogger(AfterModifyApplyContentProcessor.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 6143449457731677361L;

	@Override
	public void notify(DelegateTask delegateTask) {
		logger.info("完成 " + delegateTask.getEventName());
	}

}
