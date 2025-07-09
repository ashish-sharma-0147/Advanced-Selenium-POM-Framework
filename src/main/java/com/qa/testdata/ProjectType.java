package com.qa.testdata;

public enum ProjectType {
	EDUPUB("EDUPUB"),
	OnlineCourse("Online Course"),
	ObjectiveOrganized("Objective Organized"),
	BookOrganized("Book Organized"),
	AdaptiveLearning("Adaptive Learning"),
	EvolveResources("Evolve Resources")
	;

	private final String projectType;

	/**
	 * @param text
	 */
	ProjectType(final String projectType) {
		this.projectType = projectType;
	}

	@Override
	public String toString() {
		return projectType;
	}


}
