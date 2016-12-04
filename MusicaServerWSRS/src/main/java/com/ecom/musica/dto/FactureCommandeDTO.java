package com.ecom.musica.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FactureCommandeDTO {

	@XmlElement
	private int commandeid;

	@XmlElement
	private String facturebase64;

    public int getCommandeid() {
        return commandeid;
    }

    public void setCommandeid(int commandeid) {
        this.commandeid = commandeid;
    }

    public String getFacturebase64() {
        return facturebase64;
    }

    public void setFacturebase64(String facturebase64) {
        this.facturebase64 = facturebase64;
    }
}
