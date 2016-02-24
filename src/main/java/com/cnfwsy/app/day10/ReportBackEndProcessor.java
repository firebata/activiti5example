package com.cnfwsy.app.day10;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportBackEndProcessor implements TaskListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1740937180533045981L;
	private static final Logger logger = LoggerFactory
			.getLogger(AfterModifyApplyContentProcessor.class);

	@Override
	public void notify(DelegateTask delegateTask) {

		logger.info("完成 " + delegateTask.getEventName());
	}

}
