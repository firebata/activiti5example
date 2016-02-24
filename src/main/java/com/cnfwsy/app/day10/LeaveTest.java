package com.cnfwsy.app.day10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.junit.Test;

import com.cnfwsy.app.parent.ActivitiInitClass;

/**
 * 流程：领导审批不通过--->结束流程
 * 
 * @author Administrator
 *
 */
public class LeaveTest extends ActivitiInitClass {

	@Test
	public void test() {
		
		repositoryService.createDeployment().addClasspathResource("com/cnfwsy/app/day10/请假流程.bpmn").deploy();
		String processId = runtimeService.startProcessInstanceByKey("leave").getId();
		log("\nprocessId:" + processId);
		
		// 得到笔试的流程
		log("\n***************请假流程开始***************");

		// 部门领导 唐经理领取任务
		long count = taskService.createTaskQuery().taskCandidateGroup("deptLeader").count();

		log("部门领导的任务数量：" + count);
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("deptLeader").list();
		for (Task task : tasks) {
			log("部门领取的任务名:" + task.getName());
			taskService.claim(task.getId(), "唐经理");
		}

		// 唐经理完成任务
		count = taskService.createTaskQuery().taskAssignee("唐经理").count();

		log("唐经理的任务数量：" + count);

		tasks = taskService.createTaskQuery().taskAssignee("唐经理").list();

		for (Task task : tasks) {
			log("部门领取的任务名:" + task.getName());
			Map<String, Object> var = new HashMap<>();
			var.put("deptLeaderPass", false); // 不同意
			var.put("applyUserId", "张经理");
			taskService.complete(task.getId(), var);

		}
		count = taskService.createTaskQuery().taskAssignee("唐经理").count();

		log("唐经理的任务数量：" + count);

		count = taskService.createTaskQuery().taskAssignee("张经理").count();

		log("张经理的任务数量：" + count);

		tasks = taskService.createTaskQuery().taskAssignee("张经理").list();

		for (Task task : tasks) {
			log("张经理的任务名:" + task.getName());
			Map<String, Object> var = new HashMap<>();
			var.put("reApply", false); // 不同意
//			taskService.complete(task.getId(), var);
		}

	}
}
