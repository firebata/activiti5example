package com.cnfwsy.app.day07;

import java.util.List;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import com.cnfwsy.app.parent.ActivitiInitClass;

/**
 * 
 * @author zhangjh
 *
 */
public class Interview extends ActivitiInitClass {

	@Test
	public void t1() {

		repositoryService.createDeployment().addClasspathResource("com/cnfwsy/app/day07/Interview.bpmn").deploy();
		String processId = runtimeService.startProcessInstanceByKey("Interview").getId();

		System.out.println("\nprocessId:" + processId);
		// 得到笔试的流程
		System.out.println("\n***************笔试流程开始***************");

		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("HR DEPT").list();
		for (Task task : tasks) {
			System.out.println("HR DEPT的任务：name:" + task.getName() + ",id:" + task.getId());
			taskService.claim(task.getId(), "唐丹");
		}
		System.out.println("唐丹的任务数量：" + taskService.createTaskQuery().taskAssignee("唐丹").count());

		tasks = taskService.createTaskQuery().taskAssignee("唐丹").list();
		for (Task task : tasks) {
			System.out.println("唐丹的任务：name:" + task.getName() + ",id:" + task.getId());
			taskService.complete(task.getId());
		}

		System.out.println("唐丹的任务数量：" + taskService.createTaskQuery().taskAssignee("唐丹").count());
		System.out.println("***************笔试流程结束***************");

		System.out.println("\n***************一面流程开始***************");
		tasks = taskService.createTaskQuery().taskCandidateGroup("TECH DEPT").list();

		for (Task task : tasks) {
			System.out.println("TECH DEPT的任务：name:" + task.getName() + ",id:" + task.getId());
			taskService.claim(task.getId(), "李经理");
		}

		System.out.println("李经理的任务数量：" + taskService.createTaskQuery().taskAssignee("李经理").count());
		tasks = taskService.createTaskQuery().taskAssignee("李经理").list();
		for (Task task : tasks) {
			System.out.println("李经理的任务：name:" + task.getName() + ",id:" + task.getId());
			taskService.complete(task.getId());
		}

		System.out.println("李经理的任务数量：" + taskService.createTaskQuery().taskAssignee("李经理").count());
		System.out.println("***************一面流程结束***************");

		//
		System.out.println("\n***************二面流程开始***************");
		tasks = taskService.createTaskQuery().taskCandidateGroup("TECH DEPT").list();
		for (Task task : tasks) {
			System.out.println("TECH DEPT的任务：name:" + task.getName() + ",id:" + task.getId());
			taskService.claim(task.getId(), "王经理");
		}

		System.out.println("王经理的任务数量：" + taskService.createTaskQuery().taskAssignee("王经理").count());
		tasks = taskService.createTaskQuery().taskAssignee("王经理").list();

		for (Task task : tasks) {
			System.out.println("王经理的任务：name:" + task.getName() + ",id:" + task.getId());
			taskService.complete(task.getId());
		}

		System.out.println("王经理的任务数量：" + taskService.createTaskQuery().taskAssignee("王经理").count());
		System.out.println("***************二面流程结束***************");

		System.out.println("***************HR面流程开始***************");
		tasks = taskService.createTaskQuery().taskCandidateGroup("HR DEPT").list();

		for (Task task : tasks) {
			System.out.println("HR DEPT的任务：name:" + task.getName() + ",id:" + task.getId());
			taskService.claim(task.getId(), "张经理");
		}

		System.out.println("张经理的任务数量：" + taskService.createTaskQuery().taskAssignee("张经理").count());
		tasks = taskService.createTaskQuery().taskAssignee("张经理").list();

		for (Task task : tasks) {
			System.out.println("张经理的任务：name:" + task.getName() + ",id:" + task.getId());
			taskService.complete(task.getId());
		}

		System.out.println("张经理的任务数量：" + taskService.createTaskQuery().taskAssignee("张经理").count());
		System.out.println("***************HR面流程结束***************");

		System.out.println("\n***************录用流程开始**************休息*");
		tasks = taskService.createTaskQuery().taskCandidateGroup("HR DEPT").list();

		for (Task task : tasks) {
			System.out.println("HR DEPT的任务：name:" + task.getName() + ",id:" + task.getId());
			taskService.claim(task.getId(), "邓经理");
		}

		System.out.println("邓经理的任务数量：" + taskService.createTaskQuery().taskAssignee("邓经理").count());
		tasks = taskService.createTaskQuery().taskAssignee("邓经理").list();

		for (Task task : tasks) {
			System.out.println("邓经理的任务：name:" + task.getName() + ",id:" + task.getId());
			// taskService.complete(task.getId());
		}

		System.out.println("邓经理的任务数量：" + taskService.createTaskQuery().taskAssignee("邓经理").count());
		System.out.println("***************录用流程结束***************");

		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(processId).singleResult();

		System.out.println("\n流程结束时间：" + historicProcessInstance.getEndTime());
	}
}
