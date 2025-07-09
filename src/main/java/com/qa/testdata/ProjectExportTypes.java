/**
 * 
 */
package com.qa.testdata;

/**
 * 
 */
public enum ProjectExportTypes {
	
	VTW("VTW Export"),
	QTI("qti21")
	;

	private final String projectExportTypes;

	/**
	 * @param text
	 */
	ProjectExportTypes(final String projectExportTypes) {
		this.projectExportTypes = projectExportTypes;
	}

	@Override
	public String toString() {
		return projectExportTypes;
	}

}
