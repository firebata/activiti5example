package com.cnfwsy.app.day04;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import com.cnfwsy.app.parent.ActivitiInitClass;

/**
 * 测试测试并行网关功能
 * 
 * @author zhangjh
 *
 */
public class ParallelGateWay2 extends ActivitiInitClass {

	@Test
	@Override
	public void t1() {

		Deployment deployment = repositoryService.createDeployment().name("并行网关的例子")
				.addClasspathResource("com/cnfwsy/app/day04/ParallelGateWay2.bpmn").deploy();
		log(deployment.getId() + "=" + deployment.getName());
		creator = "zhangjh";

		businessKey = String.valueOf(System.currentTimeMillis());
		identityService.setAuthenticatedUserId(creator);// 启动实例的人

		Map<String, Object> variables = new HashMap<String, Object>();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("shopping2", businessKey, variables);
		String processInstanceId = processInstance.getId();// 流程实例id
		log("processInstanceId:" + processInstanceId);

		Task zjhTask = taskService.createTaskQuery().taskCandidateOrAssigned("zjh").singleResult();
		taskService.complete(zjhTask.getId());
		List<Task> tangdanTasks = taskService.createTaskQuery().taskCandidateOrAssigned("tangdan").list();
		for (Task task : tangdanTasks) {
			taskService.complete(task.getId());
		}

		zjhTask = taskService.createTaskQuery().taskCandidateOrAssigned("zjh").singleResult();
		taskService.complete(zjhTask.getId());

		Task wangmsTask = taskService.createTaskQuery().taskCandidateOrAssigned("wangms").singleResult();
		taskService.complete(wangmsTask.getId());

		tangdanTasks = taskService.createTaskQuery().taskCandidateOrAssigned("tangdan").list();

		for (Task task : tangdanTasks) {
			taskService.complete(task.getId());
		}

	}

}
