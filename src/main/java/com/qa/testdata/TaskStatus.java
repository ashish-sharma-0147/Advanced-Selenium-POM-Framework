package com.qa.testdata;

public enum TaskStatus {
	
	OPEN("Open"),
	NEED_CLARIFICATION("Need Clarification"),
	FIXED("Fixed"),
	VERIFIED("Verified"),
	IGNORED("Ignored")
	;

	private final String taskStatus;

	TaskStatus(String Type) {
		this.taskStatus = Type;
	}

	@Override
	public String toString() {
		return taskStatus;
	}

}
