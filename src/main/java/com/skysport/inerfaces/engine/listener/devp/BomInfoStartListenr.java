package com.skysport.inerfaces.engine.listener.devp;

import com.skysport.core.bean.SpringContextHolder;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 说明:
 * Created by zhangjh on 2016-05-16.
 */
@Component
@Transactional
public class BomInfoStartListenr implements ExecutionListener {
    protected transient Log log = LogFactory.getLog(getClass());

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        RuntimeService runtimeService = SpringContextHolder.getBean("runtimeService");
        String bomId = (String) execution.getVariable("BomId");
        runtimeService.updateBusinessKey(execution.getProcessInstanceId(), bomId);

//        final String eventName = execution.getEventName();
//
//        if (log.isDebugEnabled())
//            log.debug(" > processlistenerevent > " + execution.getProcessBusinessKey() + " > eventName > " + eventName);
//
//        if (execution.getEventName().equals(ProcessListenerEvent.EVENTNAME_START)) {
//            ExecutionEntity thisEntity = (ExecutionEntity) execution;
//            ExecutionEntity superExecEntity = thisEntity.getSuperExecution();
//            String key = "";
//            // Check if this is the main process?.
//            if (superExecEntity == null) {
//                // If it is, get the business key with the main process was launched
//                // with.
//                //TODO use getProcessBusinessKey() from 5.15.1
//
//                key = thisEntity.getBusinessKey();
//
//                if (StringUtils.isEmpty(key)) {
//                    key = thisEntity.getProcessBusinessKey();
//                }
//
//            } else {
//
//                // it could be caller / subprocess ; so get the BusinessKey variable set by the
//                // caller.
//                // This should work for N no of multi- level deep sub processes.
//                key = (String) superExecEntity.getVariable("BusinessKey");
//            }
//
//            // if both the above method fails? get from the internal variable
//            final Purchase purchase = (Purchase) execution.getVariable("purchase");
//
//            if (StringUtils.isNotEmpty(key)) {
//                // Set a process variable with the business key. Can't actually set
//                // business key because business keys
//                // have to be unique per process instance.
//                thisEntity.setVariable("BusinessKey", key);
//            } else {
//                thisEntity.setVariable("BusinessKey", purchase.getBusinessKey());
//            }
//
//            //TODO check the alternative, it's deprecated in 5.15.1 it's been removed.!!!!
//            //execution.updateProcessBusinessKey(purchase.getBusinessKey());
//
//
//            execution.getEngineServices().getRuntimeService().updateBusinessKey(execution.getProcessInstanceId(), key);
//
//            if (log.isDebugEnabled()) {
//                //TODO sometimes this may throw NPE, could be bcoz of this called method execution.getProcessBusinessKey()
//                try {
//                    log.debug(" < update processlistenerevent > " + key + " > eventName > " + eventName + " < purchase < " + purchase.getBusinessKey() + " > processBusinessKey < " + execution.getProcessBusinessKey());
//                } catch (NullPointerException npe) {
//                    log.debug(" key > " + key);
//                }
//
//            }
//
//
//        }
//
//        if (log.isDebugEnabled()) log.debug(" < processlistenerevent < event = " + eventName);
    }
}
