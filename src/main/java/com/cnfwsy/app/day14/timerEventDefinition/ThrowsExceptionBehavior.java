package com.cnfwsy.app.day14.timerEventDefinition;

import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;

/**
 * 
 * @author zhangjh
 *
 */
public class ThrowsExceptionBehavior implements ActivityBehavior {

	/**
	 * 
	 */
	private static final long serialVersionUID = 587988538565099249L;

	@Override
	public void execute(ActivityExecution execution) throws Exception {
		String var = (String) execution.getVariable("var_a");

		PvmTransition transition = null;
		try {
			executeLogic(var);
			transition = execution.getActivity().findOutgoingTransition("no-exception");
		} catch (Exception e) {
			transition = execution.getActivity().findOutgoingTransition("exception1");
		}
		execution.take(transition);

	}

	private void executeLogic(String var) throws Exception {
		throw new Exception();
	}

}
