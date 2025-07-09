/**
 * 
 */
package com.qa.testdata;

/**
 * 
 */
public enum WorkFlowStatus {

	ToDo("To Do"),
	InProgress("In Progress"),
	ReadyForReview("Ready For Review"),
	ReadyForExport("Ready For Export"),
	Authoring_Curation("Authoring/Curation"),
	Review("Review"),
	Corrections("Corrections"),
	CopyEditing("Copy editing"),
	Ready("Ready"),
	FinalReview("Final review")
	;

	private final String workflowStatus;

	/**
	 * @param text
	 */
	WorkFlowStatus(final String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}

	@Override
	public String toString() {
		return workflowStatus;
	}

}
