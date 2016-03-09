package com.cnfwsy.app.day02;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnfwsy.app.parent.ActivitiInitClass;

/**
 * 
 * @author zhangjh
 *
 */
public class TestVacationRequest extends ActivitiInitClass {
	private static final Logger logger = LoggerFactory.getLogger(TestVacationRequest.class);

	@Test
	public void t1() {
		Deployment deployment = repositoryService.createDeployment().name("请假流程")
				.addClasspathResource("com/cnfwsy/app/day02/VacationRequest.bpmn").deploy();

		logger.info("deployment: " + deployment.getName());

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employeeName", "kafeitu");
		variables.put("numberOfDays", new Integer(4));
		variables.put("vacationMotivation", "I'm really tired!");

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", variables);
		logger.info("pid:" + processInstance.getId());

		logger.info("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("deptLeader").list();
		List<Task> tasks_2 = taskService.createTaskQuery().taskCandidateOrAssigned("leaderuser").list();

		for (Task task : tasks) {
			logger.info("Task available: " + task.getName());
			taskService.claim(task.getId(), "leaderuser");
		}
		
		List<Task> tasks_3 = taskService.createTaskQuery().taskCandidateOrAssigned("leaderuser").list();

		Task task = tasks.get(0);
		Map<String, Object> variables2 = new HashMap<String, Object>();
		variables2.put("vacationApproved", "false");
		variables2.put("managerMotivation", "我们有最长请假时间，不能超过3天");

		taskService.complete(task.getId(), variables2);

		List<Task> tasks2 = taskService.createTaskQuery().taskCandidateGroup("deptLeader").list();

		logger.info("deptLeader的待处理任务:" + tasks2.size());

		List<Task> tasks3 = taskService.createTaskQuery().taskAssignee("kafeitu").list();
		List<Task> zhangjhsTask = taskService.createTaskQuery().taskAssignee("Zhangjh").list();

		logger.info("kafeitu的待处理任务:" + tasks3.size());
		logger.info("Zhangjh的待处理任务:" + zhangjhsTask.size());

		Map<String, Object> variables4 = new HashMap<String, Object>();
		variables4.put("numberOfDays", "3");
		variables4.put("startDate", "10-12-2015 09:00");
		variables4.put("vacationMotivation", "I'm really tired!");
		variables4.put("resendRequest", "true");
		taskService.complete(tasks3.get(0).getId(), variables4);

	}

}
