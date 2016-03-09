package com.cnfwsy.app.day13.task;

import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.log4j.Logger;

public class CheckBankTask implements TaskListener {
	private final Logger log = Logger.getLogger(CheckBankTask.class.getName());
	/**
	 * 
	 */
	private static final long serialVersionUID = -7723965750062779699L;

	@SuppressWarnings("unchecked")
	@Override
	public void notify(DelegateTask delegateTask) {
		log.info("i am CheckBankTask.");
		System.out.println("in : " + delegateTask.getVariables());
		delegateTask.getVariables().get("in");
		Map<String, Object> inMaps = (Map<String, Object>) delegateTask.getVariables().get("in");
		Map<String, Object> outMaps = (Map<String, Object>) delegateTask.getVariables().get("out");
		inMaps.put("next", "CheckBankTask");
		outMaps.put("reponse", "subprocess:CheckBankTask->CheckMerchantTask");

	}

}
