package com.qa.testdata;

public enum OrganizationType {
	
	Enterprise("Enterprise"),
	Partner("Partner"),
	Other("Others")
	;

	private final String orgType;

	OrganizationType(String Type) {
		this.orgType = Type;
	}

	@Override
	public String toString() {
		return orgType;
	}

}
