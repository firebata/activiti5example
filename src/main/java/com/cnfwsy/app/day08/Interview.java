package com.cnfwsy.app.day08;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import com.cnfwsy.app.parent.ActivitiInitClass;

public class Interview extends ActivitiInitClass {
	@Test
	public void t1() {

		repositoryService.createDeployment().addClasspathResource("com/cnfwsy/app/day08/Interview.bpmn").deploy();
		String processId = runtimeService.startProcessInstanceByKey("Interview").getId();
		log("\nprocessId:" + processId);
		// 得到笔试的流程
		log("\n***************笔试流程开始***************");

		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("HR部").list();
		for (Task task : tasks) {
			log("HR部的任务：name:" + task.getName() + ",id:" + task.getId());
			taskService.claim(task.getId(), "唐丹");
		}

		log("唐丹的任务数量：" + taskService.createTaskQuery().taskAssignee("唐丹").count());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("approved", true);
		tasks = taskService.createTaskQuery().taskAssignee("唐丹").list();
		for (Task task : tasks) {
			log("唐丹的任务：name:" + task.getName() + ",id:" + task.getId());
			taskService.complete(task.getId(), params);
		}

		log("唐丹的任务数量：" + taskService.createTaskQuery().taskAssignee("唐丹").count());
		log("***************笔试流程结束***************");
		log("\n***************一面流程开始***************");
		tasks = taskService.createTaskQuery().taskCandidateGroup("技术部").list();

		for (Task task : tasks) {
			log("技术部的任务：name:" + task.getName() + ",id:" + task.getId());
			taskService.claim(task.getId(), "邱经理");
		}

		log("邱经理的任务数量：" + taskService.createTaskQuery().taskAssignee("邱经理").count());
		tasks = taskService.createTaskQuery().taskAssignee("邱经理").list();
		params.put("approved", false);
		for (Task task : tasks) {
			log("邱经理的任务：name:" + task.getName() + ",id:" + task.getId());
			taskService.complete(task.getId(), params);
		}

		log("邱经理的任务数量：" + taskService.createTaskQuery().taskAssignee("邱经理").count());
		log("***************一面流程结束***************");
		log("\n***************二面流程开始***************");

		tasks = taskService.createTaskQuery().taskCandidateGroup("技术部").list();
		for (Task task : tasks) {
			log("技术部的任务：name:" + task.getName() + ",id:" + task.getId());
			taskService.claim(task.getId(), "王经理");
		}

		log("王经理的任务数量：" + taskService.createTaskQuery().taskAssignee("王经理").count());
		tasks = taskService.createTaskQuery().taskAssignee("王经理").list();
		for (Task task : tasks) {
			log("王经理的任务：name:" + task.getName() + ",id:" + task.getId());
			taskService.complete(task.getId(), params);
		}

		log("王经理的任务数量：" + taskService.createTaskQuery().taskAssignee("王经理").count());
		log("***************二面流程结束***************");
		log("***************HR面流程开始***************");
		tasks = taskService.createTaskQuery().taskCandidateGroup("HR部").list();
		for (Task task : tasks) {
			log("HR部的任务：name:" + task.getName() + ",id:" + task.getId());
			taskService.claim(task.getId(), "张经理");
		}
		log("张经理的任务数量：" + taskService.createTaskQuery().taskAssignee("张经理").count());

		tasks = taskService.createTaskQuery().taskAssignee("张经理").list();
		for (Task task : tasks) {
			log("张经理的任务：name:" + task.getName() + ",id:" + task.getId());
			taskService.complete(task.getId());
		}

		log("张经理的任务数量：" + taskService.createTaskQuery().taskAssignee("张经理").count());
		log("***************HR面流程结束***************");
		log("\n***************录用流程开始***************");

		tasks = taskService.createTaskQuery().taskCandidateGroup("HR部").list();
		for (Task task : tasks) {
			log("HR部的任务：name:" + task.getName() + ",id:" + task.getId());
			taskService.claim(task.getId(), "邓经理");
		}

		log("邓经理的任务数量：" + taskService.createTaskQuery().taskAssignee("邓经理").count());
		tasks = taskService.createTaskQuery().taskAssignee("邓经理").list();
		for (Task task : tasks) {
			log("邓经理的任务：name:" + task.getName() + ",id:" + task.getId());
			// taskService.complete(task.getId());
		}

		log("邓经理的任务数量：" + taskService.createTaskQuery().taskAssignee("邓经理").count());
		log("***************录用流程结束***************");
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(processId).singleResult();
		log("\n流程结束时间：" + historicProcessInstance.getEndTime());

	}
}
