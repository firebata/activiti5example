package com.cnfwsy.app.day14.timerEventDefinition;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.test.Deployment;
import org.junit.Test;

import com.cnfwsy.app.parent.ActivitiInitClass;

/**
 * 测试定时启动时间和边界异常时间<br/>
 * 本例子，包含流程标准的启动例子：<br/>
 * 1,设置流程启动者()；<br/>
 * 2，设置流程启动变量(variables：流程中启动最好带变量信息)；<br/>
 * 3，指定业务id（businessKey：一般是主业务的主键）：<br/>
 * 
 * 
 * @author zhangjh
 *
 */
public class TimerEventAndBoundaryEventTest extends ActivitiInitClass {

	@Test
	@Override
	@Deployment(resources = "com/cnfwsy/app/day14/timerEventDefinition/TimerEventDefinition.bpmn")
	public void t1() {

		creator = "zhangjh";
		identityService.setAuthenticatedUserId(creator);

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("var_a", "throw exp");

		String processDefinitionKey = "TimerEventAndBoundaryEventTestKey";
		String businessKey = String.valueOf(System.currentTimeMillis());

		runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
		
		
		
		
		
		
		

	}

}
