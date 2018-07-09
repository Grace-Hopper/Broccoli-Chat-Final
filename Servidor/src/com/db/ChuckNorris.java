package com.db;

import java.io.Serializable;

public class ChuckNorris implements Serializable {

	private static final long serialVersionUID = -5153794647898719855L;
	
	private int ID;
	
	private String frase;
	
	public ChuckNorris() {}

	public ChuckNorris(int iD, String frase) {
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
