package com.skysport.inerfaces.engine.listener.devp;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import com.skysport.inerfaces.model.develop.bom.IBomService;

public class ProjectItemFinishApproveListener implements JavaDelegate {

	@Resource(name = "bomManageService")
	private IBomService bomManageService;
	@Autowired
	public RuntimeService runtimeService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String projectId = execution.getProcessBusinessKey();
		List<String> allbomIds = bomManageService.queryAllBomIdsByProjectId(projectId);
		execution.setVariable("allbomIds", allbomIds);// 所有的bomid
		RuntimeService runtimeService = execution.getEngineServices().getRuntimeService();
		runtimeService.activateProcessInstanceById(arg0);
	}

}
