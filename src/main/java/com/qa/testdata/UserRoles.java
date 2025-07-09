package com.qa.testdata;

public enum UserRoles {
	
	Author("author"),
	Reviewer("reviewer"),
	ProjectAdmin("project admin")
	;

	private final String userRole;

	UserRoles(String Role) {
		this.userRole = Role;
	}

	@Override
	public String toString() {
		return userRole;
	}

}
