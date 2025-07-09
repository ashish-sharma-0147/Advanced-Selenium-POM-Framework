package com.qa.testdata;

public enum TaskPriority {
	
	LOW("Low"),
	MEDIUM("Medium"),
	HIGH("High"),
	BLOCKER("Blocker")
	;

	private final String taskPriority;

	TaskPriority(String priority) {
		this.taskPriority = priority;
	}

	@Override
	public String toString() {
		return taskPriority;
	}

}
