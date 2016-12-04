package com.ecom.musica.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TestDTO {
	@XmlElement
	private String stringTest;
	
	@XmlElement
	private String stringTest2;
	
	public String getStringTest() {
		return stringTest;
	}

	public void setStringTest(String stringTest) {
		this.stringTest = stringTest;
	}

	public String getStringTest2() {
		return stringTest2;
	}

	public void setStringTest2(String stringTest2) {
		this.stringTest2 = stringTest2;
	}
	
}
