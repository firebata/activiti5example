package com.cnfwsy.app.day05;

import java.util.List;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnfwsy.app.parent.ActivitiInitClass;

/**
 * 个人签收组的任务<br/>
 * FinancialReportProcess.bpmn已在activiti.cfg.xml文件中配置，spring负责加载
 * 
 * @author zhangjh
 *
 */
public class FinancialReportProcess extends ActivitiInitClass {

	private static final Logger logger = LoggerFactory.getLogger(FinancialReportProcess.class);

	@Test
	public void t1() {

		// 部署
		// Deployment deployment = repositoryService.createDeployment()
		// .addClasspathResource(
		// "com/cnfwsy/app/day05/FinancialReportProcess.bpmn")
		// .deploy();
		// logger.info(deployment.getId() + "=" + deployment.getName());
		// // 启动

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("financialReport");
		String procId = processInstance.getId();
		logger.info("procId:" + procId);

		//

		// 查询任务
		List<Task> accountancyTasks = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
		logger.info("accountancy tasks 共有:" + accountancyTasks.size() + "个任务");

		for (Task task : accountancyTasks) {
			logger.info("==>" + task);
			taskService.claim(task.getId(), "zhangjh"); // 认领
		}

		List<Task> zhangjhTasks = taskService.createTaskQuery().taskAssignee("zhangjh").list();
		logger.info("zhangjh tasks 共有:" + zhangjhTasks.size() + "个任务");

		for (Task task : zhangjhTasks) {
			logger.info("Task for zhangjh: " + task.getName()); // Complete the
			// task
			taskService.complete(task.getId());// 完成任务
		}

		zhangjhTasks = taskService.createTaskQuery().taskAssignee("zhangjh").list();
		logger.info("zhangjh tasks 现有:" + zhangjhTasks.size() + "个任务");

		List<Task> managementTasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
		logger.info("management tasks共有:" + managementTasks.size() + "个任务");

		for (Task task : managementTasks) {
			logger.info("==>" + task);
			taskService.claim(task.getId(), "tangdan"); // 认领
		}

		managementTasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
		logger.info("management tasks现有:" + managementTasks.size() + "个任务");
		List<Task> tangdanTasks = taskService.createTaskQuery().taskAssignee("tangdan").list();
		logger.info("tangdan tasks现有:" + tangdanTasks.size() + "个任务");
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(procId).singleResult();
		if (pi != null) {
			System.out.println("当前流程在：" + pi.getActivityId());
		} else {
			System.out.println("流程已结束!!");
		}
		for (Task task : tangdanTasks) {
			logger.info("Task for tangdan: " + task.getName()); // Complete the
			// task
			// taskService.complete(task.getId());// 完成任务
		}
		logger.info("tangdan tasks现有:" + tangdanTasks.size() + "个任务");
		//
		// HistoricProcessInstance historicProcessInstance =
		// historyService.createHistoricProcessInstanceQuery()
		// .processInstanceId(procId).singleResult();
		// logger.info("Process instance end time: " +
		// historicProcessInstance.getEndTime());

	}
}
