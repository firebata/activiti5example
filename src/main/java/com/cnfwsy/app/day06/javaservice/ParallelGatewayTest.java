package com.cnfwsy.app.day06.javaservice;

import static org.junit.Assert.*;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import com.cnfwsy.app.parent.ActivitiInitClass;

/**
 * 测试并行网关与java Service Task
 * 
 * @author Administrator
 *
 */
public class ParallelGatewayTest extends ActivitiInitClass {

	@Test
	public void t1() {
		Deployment deployment = repositoryService.createDeployment().name("并行网关javaservice Task")
				.addClasspathResource("com/cnfwsy/app/day06/javaservice/JavaService.bpmn").deploy();
		log(deployment.getId() + "=" + deployment.getName());

		ProcessInstance pi = runtimeService.startProcessInstanceByKey("AutomaticParalellBasedForkJoin");
		assertEquals(true, pi.isEnded());
	}

}
