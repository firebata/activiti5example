package com.cnfwsy.app.day04;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import com.cnfwsy.app.parent.ActivitiInitClass;

public class ParallelGateWay extends ActivitiInitClass {

	@Test
	@Override
	public void t1() {

		Deployment deployment = repositoryService.createDeployment().name("并行网关的例子")
				.addClasspathResource("com/cnfwsy/app/day04/ParallelGateWay.bpmn").deploy();
		log(deployment.getId() + "=" + deployment.getName());
		creator = "zhangjh";

		businessKey = String.valueOf(System.currentTimeMillis());
		identityService.setAuthenticatedUserId(creator);// 启动实例的人

		Map<String, Object> variables = new HashMap<String, Object>();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("shopping", businessKey, variables);
		String processInstanceId = processInstance.getId();// 流程实例id
		log("processInstanceId:" + processInstanceId);

		Task tangdanTask = taskService.createTaskQuery().taskCandidateOrAssigned("tangdan").singleResult();
		Task zjhTask = taskService.createTaskQuery().taskCandidateOrAssigned("zjh").singleResult();
		taskService.complete(tangdanTask.getId());
		taskService.complete(zjhTask.getId());

		tangdanTask = taskService.createTaskQuery().taskCandidateOrAssigned("tangdan").singleResult();
		taskService.complete(tangdanTask.getId());
		zjhTask = taskService.createTaskQuery().taskCandidateOrAssigned("zjh").singleResult();
		taskService.complete(zjhTask.getId());
		
		
		
		tangdanTask = taskService.createTaskQuery().taskCandidateOrAssigned("tangdan").singleResult();
		taskService.complete(tangdanTask.getId());
		
		
		
	}

}
