package com.cnfwsy.app.day01;

import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author zhangjh
 *
 */
public class VerifySimpleLeaveProcessTest2直接启动实例 {

	@Test
	public void testStartProcess() {

		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("activiti.cfg.xml");
		RepositoryService repositoryService = (RepositoryService) applicationContext.getBean("repositoryService");
		RuntimeService runtimeService = (RuntimeService) applicationContext.getBean("runtimeService");
		TaskService taskService = (TaskService) applicationContext.getBean("taskService");

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess");

		String procId = processInstance.getId();
		System.out.println("流程定义ID:" + processInstance.getProcessDefinitionId()); // 流程定义ID
		System.out.println("流程实例ID:" + procId);

		TaskQuery query = taskService.createTaskQuery().processInstanceId(procId).orderByTaskName().asc();
		List<Task> tasks = query.list();

		System.out.println("tasksize =" + tasks.size());

	}

}
