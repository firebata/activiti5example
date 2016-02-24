package com.cnfwsy.app.day10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.junit.Test;

import com.cnfwsy.app.parent.ActivitiInitClass;

/**
 * 流程：领导审批不通过--->重新申请--->领导不通过--->结束申请
 * 
 * @author Administrator
 *
 */
public class LeaveTest2 extends ActivitiInitClass {

	@Test
	public void test() {
		repositoryService.createDeployment()
				.addClasspathResource("com/cnfwsy/app/day10/请假流程.bpmn")
				.deploy();
		String processId = runtimeService.startProcessInstanceByKey("leave")
				.getId();
		System.out.println("\nprocessId:" + processId);
		// 得到笔试的流程
		System.out.println("\n***************请假流程开始***************");

		deal(false, true);// 领导审批不通过--->重新申请

		deal(false, false);// 领导不通过--->结束申请
	}

	private void deal(boolean deptLeaderPass, boolean reApply) {
		// 部门领导 唐丹领取任务
		long count = taskService.createTaskQuery()
				.taskCandidateGroup("deptLeader").count();

		System.out.println("部门领导的任务数量：" + count);

		List<Task> tasks = taskService.createTaskQuery()
				.taskCandidateGroup("deptLeader").list();
		for (Task task : tasks) {
			System.out.println("部门领取的任务名:" + task.getName());
			taskService.claim(task.getId(), "唐丹");
		}

		// 唐丹完成任务
		count = taskService.createTaskQuery().taskAssignee("唐丹").count();

		System.out.println("唐丹的任务数量：" + count);

		tasks = taskService.createTaskQuery().taskAssignee("唐丹").list();

		for (Task task : tasks) {
			System.out.println("部门领取的任务名:" + task.getName());
			Map<String, Object> var = new HashMap<>();
			var.put("deptLeaderPass", deptLeaderPass); // 不同意
			var.put("applyUserId", "张建华");
			taskService.complete(task.getId(), var);
		}
		count = taskService.createTaskQuery().taskAssignee("唐丹").count();

		System.out.println("唐丹的任务数量：" + count);
		//

		count = taskService.createTaskQuery().taskAssignee("张建华").count();

		System.out.println("张建华的任务数量：" + count);

		tasks = taskService.createTaskQuery().taskAssignee("张建华").list();

		for (Task task : tasks) {
			System.out.println("张建华的任务名:" + task.getName());
			Map<String, Object> var = new HashMap<>();
			var.put("reApply", reApply); // 不同意
			taskService.complete(task.getId(), var);
		}
	}
}
