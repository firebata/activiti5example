package com.cnfwsy.app.day12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import com.cnfwsy.app.parent.ActivitiInitClass;

public class TestLeave_zjh extends ActivitiInitClass {

	@Test
	public void t1() {

		repositoryService.createDeployment().addClasspathResource("com/cnfwsy/app/day12/leave.bpmn").deploy();
		identityService.setAuthenticatedUserId("zhangjh");
		long curTime = System.currentTimeMillis();
		// 指定候选组id
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("deptLeader", "1648708b-7d5b-11e5-9ad5-fcaa14d039f1");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave", String.valueOf(curTime),params);// id不一样
		String procId = processInstance.getId();

		List<Task> tasks = taskService.createTaskQuery().taskCandidateUser("749dd55e-b52b-11e5-b7d6-fcaa14d039f1").list();
		log("tasks:" + tasks);

		tasks = taskService.createTaskQuery().taskCandidateOrAssigned("749dd55e-b52b-11e5-b7d6-fcaa14d039f1").list();
		log("tasks:" + tasks);
		
		
		
		
	}

}
