package com.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DeudasPK implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = -2758495452715596306L;
		@Column(name = "Deudor")
		private String deudor;
		@Column(name = "Acreedor")
		private String acreedor;
}
