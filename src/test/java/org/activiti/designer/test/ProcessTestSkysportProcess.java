package org.activiti.designer.test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import java.io.FileInputStream;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;


/**
 * 
 * @author zhangjh 
 *
 */
public class ProcessTestSkysportProcess {

	private String filename = "D:\\work\\project\\activiti\\src\\main\\java\\com\\cnfwsy\\app\\day11\\SkySportV01.bpmn";

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();

	@Test
	public void startProcess() throws Exception {
		
		RepositoryService repositoryService = activitiRule.getRepositoryService();
		repositoryService.createDeployment().addInputStream("skysportProcess.bpmn20.xml", new FileInputStream(filename))
				.deploy();
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("name", "Activiti");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("skysportProcess", variableMap);
		assertNotNull(processInstance.getId());
		System.out.println("id " + processInstance.getId() + " " + processInstance.getProcessDefinitionId());
		
	}

}
