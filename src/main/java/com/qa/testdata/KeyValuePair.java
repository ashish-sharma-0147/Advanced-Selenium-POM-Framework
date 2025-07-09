package com.qa.testdata;

public enum KeyValuePair {
	TEXT("Text"),
	SELECT("Select"),
	DATE("Date"),
	TIME("Time"),
	NUMBER("Number")
	;

	private final String keyType;

	/**
	 * @param text
	 */
	KeyValuePair(final String keyType) {
		this.keyType = keyType;
	}

	@Override
	public String toString() {
		return keyType;
	}

}
