package com.cnfwsy.app.day11;

import org.activiti.engine.repository.Deployment;

import com.cnfwsy.app.parent.ActivitiInitClass;
import org.junit.Test;

public class SkySportV01 extends ActivitiInitClass {
	/**
	 * 
	 */
	@Test
	public void t1() {
		Deployment deployment = repositoryService.createDeployment()
				.addClasspathResource("com/cnfwsy/app/day11/SkySportV01_temp.bpmn").deploy();
		String processId = runtimeService.startProcessInstanceByKey("skysportProcess").getId();
		log("processId:" + processId);
		processId = runtimeService.startProcessInstanceByKey("skysportProcess").getId();
		log("processId:" + processId);
	}
}
