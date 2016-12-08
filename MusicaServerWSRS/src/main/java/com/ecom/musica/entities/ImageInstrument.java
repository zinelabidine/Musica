package com.ecom.musica.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "image_instrument")
public class ImageInstrument implements Serializable {

	private static final long serialVersionUID = 7166099505823083057L;
	private int imageInstrumentId;
	private String image;
	private Instrument instrument;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ImageInstrumentId")
	public int getImageInstrumentId() {
		return imageInstrumentId;
	}

	public void setImageInstrumentId(int imageInstrumentId) {
		this.imageInstrumentId = imageInstrumentId;
	}

	@Lob
	@Column(name = "Image")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "InstrumentId")
	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}
}
