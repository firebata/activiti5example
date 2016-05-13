package com.skysport.inerfaces.engine.listener.devp;

import com.skysport.core.model.common.IApproveService;
import com.skysport.inerfaces.engine.listener.EventPassListener;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 说明:项目最初审批
 * Created by zhangjh on 2016/4/13.
 */
@Component
public class ProjectItemPassListener extends EventPassListener {
    @Autowired
    private IApproveService projectItemManageService;

    @Autowired
    public RuntimeService runtimeService;


    public void executeBefore(DelegateExecution execution) {
        String businessKey = execution.getProcessBusinessKey();//子项目id：启动子项目的时候，设置了业务主键
        //另外一种获取业务主键的方式

        List<String> allbomIds = projectItemManageService.invokePass(businessKey);

        execution.setVariable("allbomIds", allbomIds);//所有的bomid


    }


}
