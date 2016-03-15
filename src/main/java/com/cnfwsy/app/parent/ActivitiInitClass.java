package com.cnfwsy.app.parent;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author zhangjh
 *
 */

public abstract class ActivitiInitClass implements ActivitiInter {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	protected ClassPathXmlApplicationContext applicationContext;
	protected RepositoryService repositoryService;
	protected RuntimeService runtimeService;
	protected TaskService taskService;
	protected HistoryService historyService;
	protected IdentityService identityService;
	protected List<Task> tasks;
	protected long count;
	protected String userId;
	protected String creator;
	protected String businessKey;

	/**
	 * 开始测试
	 */
	@BeforeClass
	public static void setUpForClass() throws Exception {
		System.out.println("++++++++ 开始测试 ++++++++");
	}

	/**
	 * 结束测试
	 */
	@AfterClass
	public static void testOverForClass() throws Exception {
		System.out.println("-------- 结束测试 --------");
	}

	@Before
	public void start() {
		applicationContext = new ClassPathXmlApplicationContext("activiti.cfg.xml");
		repositoryService = (RepositoryService) applicationContext.getBean("repositoryService");
		runtimeService = (RuntimeService) applicationContext.getBean("runtimeService");
		taskService = (TaskService) applicationContext.getBean("taskService");
		historyService = (HistoryService) applicationContext.getBean("historyService");
		identityService = (IdentityService) applicationContext.getBean("identityService");
	}

	protected void log(String logMsg) {
		logger.info(this.getClass().getName() + ":" + logMsg);

	}

}
