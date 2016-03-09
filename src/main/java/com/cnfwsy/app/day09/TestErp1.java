package com.cnfwsy.app.day09;

import static org.junit.Assert.assertNotNull;
import java.util.HashMap;
import java.util.Map;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import com.cnfwsy.app.parent.ActivitiInitClass;

/**
 * 
 * 
 * 平台负责人--->成本会计--->Receive Task
 * 
 * @author Administrator
 *
 */
public class TestErp1 extends ActivitiInitClass {
	@Test
	public void t1() {

		repositoryService.createDeployment().addClasspathResource("com/cnfwsy/app/day09/物流ERP.bpmn").deploy();

		Map<String, Object> var = new HashMap<String, Object>();
		var.put("pay", 1000);
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("logistics", var);
		String processId = pi.getId();

		log("processId:" + processId);

		long count = taskService.createTaskQuery().taskCandidateGroup("leader").count();

		log("leader组的任务数量:" + count);

		tasks = taskService.createTaskQuery().taskCandidateGroup("leader").list();

		userId = "王梦雪";

		for (Task task : tasks) {
			String taskId = task.getId();
			log("leader组待处理任务:" + task.getName() + ",任务id:" + taskId);
			taskService.claim(taskId, userId);
		}

		count = taskService.createTaskQuery().taskAssignee(userId).count();
		log(userId + "的任务数量:" + count);
		tasks = taskService.createTaskQuery().list();
		for (Task task : tasks) {
			String taskId = task.getId();
			log(userId + "待处理的任务:" + task.getName() + ",任务id：" + taskId);
		}
		count = taskService.createTaskQuery().taskAssignee(userId).count();
		log(userId + "的任务数量：" + count);

		userId = "张云霞";
		count = taskService.createTaskQuery().taskAssignee(userId).count();

		log(userId + "的任务数量:" + count);
		tasks = taskService.createTaskQuery().taskAssignee(userId).list();
		for (Task task : tasks) {
			String taskId = task.getId();
			log("the task name of " + userId + " is " + task.getName() + ",and the task id is " + taskId);
			taskService.complete(taskId);
		}
		count = taskService.createTaskQuery().taskAssignee(userId).count();
		log("the numbers of " + userId + " is " + count);

		Execution execution = runtimeService.createExecutionQuery().processInstanceId(processId).activityId("waitState")
				.singleResult();
		assertNotNull(execution);

		runtimeService.signal(execution.getId());

	}
}
