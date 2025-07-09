package com.qa.testdata;

public enum NodeType {
	Folder("FOLDER"),
	ContentScreen("CONTENT"),
	Assessment("ASSESSMENT"),
	Assignment("ASSIGNMENT"),
	Forum("FORUM"),
	Resource("RESOURCE"),
	Url("URL"),
	ExternalTool("External Tool"),
	Ebook("EBOOK"),
	GroupActivity("GROUP ACTIVITY"),
	Lesson("LESSON"),
	Topic("TOPIC")
	;

	private final String nodeType;

	/**
	 * @param text
	 */
	NodeType(final String nodeType) {
		this.nodeType = nodeType;
	}

	@Override
	public String toString() {
		return nodeType;
	}
}
