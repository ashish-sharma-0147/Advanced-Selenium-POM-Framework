package com.qa.util;

import org.openqa.selenium.WebElement;

public class ElementPropertyGetter {

	enum Attributes{
		id,
		type,
		classs
	}
	public static String getPropertyText(WebElement element) {
		final String hyphon = "-";
		//final String Attribute_Type = "type";
		//final String Attribute_ID = "id";
		//final String ElementText = element.getText();
		final String ElementTag  = element.getTagName();
		final String ElementId = element.getAttribute(Attributes.id.toString());
		final String ElementType = element.getAttribute(Attributes.type.toString());
		final String ElementClass = element.getAttribute(Attributes.classs.toString());
		
		
		/*if(!ElementText.trim().isEmpty()) {
			return ElementTag+hyphon+ElementText;
		}else*/ if(!ElementId.equals(null) || !ElementId.trim().isEmpty()) {
			return ElementTag+hyphon+ElementId;
		}else if(!ElementId.equals(null) || !ElementType.trim().isEmpty()) {
			return ElementTag+hyphon+ElementType;
		}else if(!ElementId.equals(null) || !ElementClass.trim().isEmpty()) {
			return ElementTag+hyphon+ElementClass;
		}else {
			return ElementTag;
		}
	}
}
