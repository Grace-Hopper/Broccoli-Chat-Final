package com.modelo;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name = "CtaCte")
public class Deudas {
	@Id
	@GeneratedValue(generator="sqlite")
	@TableGenerator(name="sqlite", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="sqliteTestTable")
	private int idDeudor;
	@ManyToOne
	@JoinColumn(name = "Deudor", referencedColumnName = "Nombre")
	private Usuario deudor;

	@ManyToOne
	@JoinColumn(name = "Acreedor", referencedColumnName = "Nombre")
	private Usuario acreedor;
	
	@Column(name = "Importe")
	private float importe;
	
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public Usuario getDeudor() {
		return deudor;
	}
	public void setDeudor(Usuario deudor) {
		this.deudor = deudor;
	}
	public Usuario getAcreedor() {
		return acreedor;
	}
	public void setAcreedor(Usuario acreedor) {
		this.acreedor = acreedor;
	}



}
