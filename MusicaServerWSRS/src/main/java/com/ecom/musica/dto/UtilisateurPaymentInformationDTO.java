package com.ecom.musica.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UtilisateurPaymentInformationDTO {
	@XmlElement
	private int utilisateurid;
	@XmlElement
	private String cardname;
	@XmlElement
	private String cardnumber;
	@XmlElement
	private String cardmonth;
	@XmlElement
	private String cardyear;

	public int getUtilisateurid() {
		return utilisateurid;
	}

	public void setUtilisateurid(int utilisateurid) {
		this.utilisateurid = utilisateurid;
	}

	public String getCardname() {
		return cardname;
	}

	public void setCardname(String cardname) {
		this.cardname = cardname;
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public String getCardmonth() {
		return cardmonth;
	}

	public void setCardmonth(String cardmonth) {
		this.cardmonth = cardmonth;
	}

	public String getCardyear() {
		return cardyear;
	}

	public void setCardyear(String cardyear) {
		this.cardyear = cardyear;
	}

}
