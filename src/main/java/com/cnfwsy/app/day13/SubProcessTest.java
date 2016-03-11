package com.cnfwsy.app.day13;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import com.cnfwsy.app.parent.ActivitiInitClass;

/**
 * 
 * @author zhangjh
 *
 */
public class SubProcessTest extends ActivitiInitClass {

	@Test
	@Override
	public void t1() {

		Deployment deployment = repositoryService.createDeployment().name("子流程的例子")
				.addClasspathResource("com/cnfwsy/app/day13/mySubProcess.bpmn").deploy();
		log(deployment.getId() + "=" + deployment.getName());
		creator = "zhangjh";

		businessKey = String.valueOf(System.currentTimeMillis());
		identityService.setAuthenticatedUserId(creator);// 启动实例的人

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("isFreee", true);
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("mySubProcess", businessKey,
				variables);
		String processInstanceId = processInstance.getId();// 流程实例id
		log("processInstanceId:" + processInstanceId);

		List<Task> tangdTasks = taskService.createTaskQuery().taskCandidateOrAssigned("tangd").list();
		for (Task task : tangdTasks) {
			taskService.complete(task.getId());
		}

		List<Task> wangmsTasks = taskService.createTaskQuery().taskCandidateOrAssigned("wangms").list();
		for (Task task : wangmsTasks) {
			taskService.complete(task.getId());
		}
		
		
		
	}

	
	
	
	@Test
	public void t2() {
		List<Task> wangmsTasks = taskService.createTaskQuery().taskCandidateOrAssigned("zhangjh").list();
		for (Task task : wangmsTasks) {
			taskService.complete(task.getId());
		}
	}

	
	
	
	
	
	
}
