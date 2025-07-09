package com.qa.testdata;

public enum OrganizationFilterType {
	
	Name("Organization Name"),
	Level("Organization Level"),
	Type("Organization Type"),
	Id("Organization ID")
	;

	private final String filterType;

	OrganizationFilterType(String Type) {
		this.filterType = Type;
	}

	@Override
	public String toString() {
		return filterType;
	}

}
