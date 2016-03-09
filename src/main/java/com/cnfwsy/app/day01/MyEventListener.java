package com.cnfwsy.app.day01;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;

public class MyEventListener implements ActivitiEventListener {

	@Override
	public void onEvent(ActivitiEvent event) {
		// TODO Auto-generated method stub
		switch (event.getType()) {

		case JOB_EXECUTION_SUCCESS:
			System.out.println("A job well done!");
			break;

		case JOB_EXECUTION_FAILURE:
			System.out.println("A job has failed...");
			break;

		default:
			System.out.println("Event received: " + event.getType());
		}
	}

	@Override
	public boolean isFailOnException() {
		// TODO Auto-generated method stub
		// The logic in the onEvent method of this listener is not critical,
		// exceptions
		// can be ignored if logging fails...
		return false;
	}

}
