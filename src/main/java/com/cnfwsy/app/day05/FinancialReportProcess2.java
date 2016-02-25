package com.cnfwsy.app.day05;

import static org.junit.Assert.*;

import java.util.List;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnfwsy.app.parent.ActivitiInitClass;

/**
 * 委托个人的任务(其他人)：<br/>
 * ACT_RU_TASK表中的OWNER_字段：受理人委托其他人操作该TASK的时候，受理人就成了委托人OWNER_，其他人就成了受理人ASSIGNEE_
 * 
 * @author zhangjh
 *
 */
public class FinancialReportProcess2 extends ActivitiInitClass {

	private static final Logger logger = LoggerFactory.getLogger(FinancialReportProcess2.class);

	@Test
	public void t1() {

		// 部署
		// Deployment deployment = repositoryService.createDeployment()
		// .addClasspathResource("com/cnfwsy/app/day05/FinancialReportProcess2.bpmn").deploy();
		// logger.info(deployment.getId() + "=" + deployment.getName());

		// 启动
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("financialReport2");// id不一样
		String procId = processInstance.getId();
		logger.info("procId:" + procId);

		// 查询个人任务
		List<Task> zhangjhTasks = taskService.createTaskQuery().taskAssignee("zhangjh").list();
		logger.info("zhangjh tasks 共有:" + zhangjhTasks.size() + "个任务");
		// 委托个人任务
		for (Task task : zhangjhTasks) {
			logger.info("==>" + task);

			taskService.delegateTask(task.getId(), "tangdan");
		}
		// 查询未完成的委派任务
		Task task0 = taskService.createTaskQuery().taskDelegationState(DelegationState.PENDING).singleResult();
		assertEquals("tangdan", task0.getAssignee());
		assertEquals("zhangjh", task0.getOwner());

		// 被委派人完成任务
		List<Task> tangdanTasks = taskService.createTaskQuery().taskAssignee("tangdan").list();
		logger.info("tangdan tasks 现有:" + tangdanTasks.size() + "个任务");
		for (Task task : tangdanTasks) {
			logger.info("==>" + task);
			taskService.resolveTask(task.getId());
		}

		// 查询已完成的委派任务
		Task task1 = taskService.createTaskQuery().taskDelegationState(DelegationState.RESOLVED).singleResult();
		assertEquals("zhangjh", task1.getAssignee());
		assertEquals("zhangjh", task1.getOwner());

		// 查询个人任务
		zhangjhTasks = taskService.createTaskQuery().taskAssignee("zhangjh").list();
		logger.info("zhangjh tasks 共有:" + zhangjhTasks.size() + "个任务");
		
		// 需要委托人自己任务
		for (Task task : zhangjhTasks) {
			logger.info("==>" + task);
			taskService.complete(task.getId());
		}

		List<Task> managementTasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
		logger.info("management tasks现有:" + managementTasks.size() + "个任务");

		for (Task task : managementTasks) {
			logger.info("==>" + task);
			taskService.claim(task.getId(), "wangmeishu"); // 认领
		}

		List<Task> wangmeishuTasks = taskService.createTaskQuery().taskAssignee("wangmeishu").list();

		logger.info("wangmeishu tasks现有:" + wangmeishuTasks.size() + "个任务");

		for (Task task : wangmeishuTasks) {
			logger.info("==>" + task);
			taskService.complete(task.getId());
		}

		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(procId).singleResult();
		if (pi != null) {
			System.out.println("当前流程在：" + pi.getActivityId());
		} else {
			System.out.println("流程已结束!!");
		}

	}
}
