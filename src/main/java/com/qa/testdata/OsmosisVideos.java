/**
 * 
 */
package com.qa.testdata;

/**
 * @author sharmaa11
 *
 */
public enum OsmosisVideos {
	
	Anatomy_of_the_ascending_spinal_cord_pathways("Anatomy_of_the_ascending_spinal_cord_pathways"),
	Anatomy_of_the_basal_ganglia("Anatomy_of_the_basal_ganglia"),
	Introduction_to_the_cranial_nerves("Introduction_to_the_cranial_nerves"),
	Anatomy_of_the_cranial_base("Anatomy_of_the_cranial_base"),
	Anatomy_of_the_anterior_and_medial_thigh("Anatomy_of_the_anterior_and_medial_thigh"),
	Anatomy_of_the_female_reproductive_organs_of_the_pelvis("Anatomy_of_the_female_reproductive_organs_of_the_pelvis")
	
	;

	private final String video;

	/**
	 * @param text
	 */
	OsmosisVideos(String video) {
		this.video = video;
	}

	@Override
	public String toString() {
		return video;
	}


}
