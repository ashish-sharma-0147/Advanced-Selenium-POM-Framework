package com.qa.testdata;

public enum ListPerPage {
	TwentyFive("25"),
	Fifty("50"),
	Hundred("100")
	;

	private final String count;

	/**
	 * @param text
	 */
	ListPerPage(final String count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return count;
	}


}
