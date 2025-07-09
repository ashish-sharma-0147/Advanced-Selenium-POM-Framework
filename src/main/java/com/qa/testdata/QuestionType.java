package com.qa.testdata;

public enum QuestionType {
	
	MCQ("Multiple Choice/Response"),
	FillInTheBlank("Fill in the Blank"),
	DragAndDrop("Drag and Drop Cloze"),
	DropDown("Drop Down Cloze"),
	ShortAnswer("Short Answer"),
	Composite("Composite"),
	InContext("Incontext Reading Activities"),
	ImageLabeling("Image Labeling"),
	Matching("Matching"),
	Ranking("Ranking")
	;

	private final String questionType;

	QuestionType(String Type) {
		this.questionType = Type;
	}

	@Override
	public String toString() {
		return questionType;
	}

}
