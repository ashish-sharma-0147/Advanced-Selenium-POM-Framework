/**
 * 
 */
package com.qa.testdata;

/**
 * @author sharmaa11
 *
 */
public enum BrightCoveVideoID {

	Overview_of_the_Integumentary_System("5100566404001", "Overview_of_the_Integumentary_System"),
	Overview_of_the_Musculoskeletal_System("6306892720112","Overview_of_the_Musculoskeletal_System")
	;

	private final String videoId;

	/**
	 * @param text
	 */
	BrightCoveVideoID(final String id, String abreviation) {
		this.videoId = id;
	}

	@Override
	public String toString() {
		return videoId;
	}

}
