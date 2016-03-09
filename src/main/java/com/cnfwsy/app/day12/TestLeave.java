package com.cnfwsy.app.day12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import com.cnfwsy.app.parent.ActivitiInitClass;

public class TestLeave extends ActivitiInitClass {

	@Test
	public void t1() {

		repositoryService.createDeployment().addClasspathResource("com/cnfwsy/app/day12/leave.bpmn").deploy();
		identityService.setAuthenticatedUserId("zhangjh");
		long curTime = System.currentTimeMillis();
		// 指定候选组id
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("deptLeader", "deptLeader");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave", String.valueOf(curTime),
				params);// id不一样
		String procId = processInstance.getId();

		List<Task> tasks = taskService.createTaskQuery().taskCandidateUser("leaderuser").list();
		log("tasks:" + tasks);

		tasks = taskService.createTaskQuery().taskCandidateOrAssigned("leaderuser").list();
		log("tasks:" + tasks);
		
		
		

	}

}
