package com.cnfwsy.app.parent;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.BeforeClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author zhangjh
 *
 */
public class ActivitiInitClass {

	protected static ClassPathXmlApplicationContext applicationContext;
	protected static RepositoryService repositoryService;
	protected static RuntimeService runtimeService;
	protected static TaskService taskService;
	protected static HistoryService historyService;
	protected static List<Task> tasks;
	protected static long count;
	protected static String userId;

	@BeforeClass
	public static void start() {
		applicationContext = new ClassPathXmlApplicationContext("activiti.cfg.xml");
		repositoryService = (RepositoryService) applicationContext.getBean("repositoryService");
		runtimeService = (RuntimeService) applicationContext.getBean("runtimeService");
		taskService = (TaskService) applicationContext.getBean("taskService");
		historyService = (HistoryService) applicationContext.getBean("historyService");
	}

	protected void log(String logMsg) {
		
		System.out.println(this.getClass().getName() + ":" + logMsg);
		
	}
}
