package com.dataAccess;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.modelo.CampoEvento;

public class DAEvento {
	Session session;
	
	public DAEvento() {
		session = ConectorSingleton.getInstance().getSession();
	}
	
	public List<CampoEvento> obtenerEventos(String usuario) {
		//criteriaQuery.select(tabla).where(cb1.equal(tabla.get("alias"), alias));
		if(session==null)
			session = ConectorSingleton.getInstance().getSession();
		String hquery = "FROM CampoEvento E WHERE E.usuario =:usuario";
		Query query = session.createQuery(hquery);
		query.setParameter("usuario",usuario);
		List<CampoEvento> lista = query.getResultList();

		session.clear();
		session.close();
		return lista;
	}
	
	public void agregarEvento(CampoEvento evento){
		if(session==null)
			session = ConectorSingleton.getInstance().getSession();
	
		Transaction transaccion = null;
		try{
		transaccion = session.beginTransaction();
		session.save(evento);
		session.flush();
		transaccion.commit();
		
		session.clear();
		}catch(HibernateException ex){
			if (transaccion!=null) transaccion.rollback();
	         ex.printStackTrace(); 
	     
		}
		
	}
}
