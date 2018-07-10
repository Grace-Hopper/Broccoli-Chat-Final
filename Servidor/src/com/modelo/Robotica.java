package com.modelo;

import java.io.Serializable;

public class Robotica implements Serializable {

	private static final long serialVersionUID = -371128921165058773L;
	
	private int ID;
	
	private String frase;
	
	public Robotica() {}

	public Robotica(int iD, String frase) {
		super();
		ID = iD;
		this.frase = frase;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getFrase() {
		return frase;
	}

	public void setFrase(String frase) {
		this.frase = frase;
	}
}
