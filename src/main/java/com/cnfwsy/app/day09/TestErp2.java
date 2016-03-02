package com.cnfwsy.app.day09;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import com.cnfwsy.app.parent.ActivitiInitClass;

/**
 * 
 * 
 * 平台负责人--->成本会计--->区域财务总监
 * 
 * @author Administrator
 *
 */
public class TestErp2 extends ActivitiInitClass {

	@Test
	public void t1() {
		//
		repositoryService.createDeployment().name("物流").addClasspathResource("com/cnfwsy/app/day09/物流ERP.bpmn")
				.deploy();

		// 变量
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("pay", 14000);

		ProcessInstance pi = runtimeService.startProcessInstanceByKey("logistics", variables);
		String processId = pi.getId();

		log("processId:" + processId);
		// 平台负责人
		tasks = taskService.createTaskQuery().taskCandidateGroup("leader").list();
		userId = "王梦雪";
		for (Task task : tasks) {
			String taskId = task.getId();
			taskService.claim(taskId, userId);
		}

		tasks = taskService.createTaskQuery().taskAssignee(userId).list();
		for (Task task : tasks) {
			String taskId = task.getId();
			taskService.complete(taskId);
		}
		// 区域成本会计
		userId = "张云霞";
		tasks = taskService.createTaskQuery().taskAssignee(userId).list();
		for (Task task : tasks) {
			String taskId = task.getId();
			taskService.complete(taskId);
		}

		userId = "王美舒";
		tasks = taskService.createTaskQuery().taskAssignee(userId).list();
		for (Task task : tasks) {
			String taskId = task.getId();
			taskService.complete(taskId);
		}

		count = taskService.createTaskQuery().taskAssignee(userId).count();
		log("the numbers of " + userId + " is " + count);

	}
}
