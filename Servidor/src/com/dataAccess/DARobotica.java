package com.dataAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.modelo.ChuckNorris;
import com.modelo.Robotica;
import com.modelo.Usuario;

public class DARobotica {
	Session session;
	
	public DARobotica() {
		session = ConectorSingleton.getInstance().getSession();
	}
	
	public List<Robotica> obtenerTodasLasFrase() {
		if(session==null)
			session = ConectorSingleton.getInstance().getSession();
	
		CriteriaBuilder cb1 = session.getCriteriaBuilder();
		CriteriaQuery<Robotica> criteriaQuery = cb1.createQuery(Robotica.class);
		Root<Robotica> tabla = criteriaQuery.from(Robotica.class);
		//criteriaQuery.select(tabla).where(cb1.equal(tabla.get("alias"), alias));
			
		List<Robotica> lista = session.createQuery(criteriaQuery).getResultList();

		//Criteria c= session.createCriteria(Usuario.class);
		//List<Usuario> lista= (List<Usuario>) c.list(); 
		
	/*	ArrayList<String> frases= new ArrayList<String>();
		
		for(Robotica r: lista) {
			frases.add(r.getFrase());
		}*/
		session.close();
		return lista;
		
	}
	public String obtenerFraseNumero(int numeroDeFrase) {
		if(session==null)
			session = ConectorSingleton.getInstance().getSession();
	
		CriteriaBuilder cb1 = session.getCriteriaBuilder();
		CriteriaQuery<Robotica> criteriaQuery = cb1.createQuery(Robotica.class);
		Root<Robotica> tabla = criteriaQuery.from(Robotica.class);
		//criteriaQuery.select(tabla).where(cb1.equal(tabla.get("alias"), alias));
			
		List<Robotica> lista = session.createQuery(criteriaQuery).getResultList();

		//Criteria c= session.createCriteria(Usuario.class);
		//List<Usuario> lista= (List<Usuario>) c.list(); 
		
		session.close();
		
		if(numeroDeFrase>0 && numeroDeFrase<lista.size())
			return	lista.get(numeroDeFrase).getFrase();
		return "Numero de frase incorrecta";
	}
	
	public String obtenerFraseRandom() {
		if(session==null)
			session = ConectorSingleton.getInstance().getSession();
	
		CriteriaBuilder cb1 = session.getCriteriaBuilder();
		CriteriaQuery<Robotica> criteriaQuery = cb1.createQuery(Robotica.class);
		Root<Robotica> tabla = criteriaQuery.from(Robotica.class);
		//criteriaQuery.select(tabla).where(cb1.equal(tabla.get("alias"), alias));
			
		List<Robotica> lista = session.createQuery(criteriaQuery).getResultList();

		//Criteria c= session.createCriteria(Usuario.class);
		//List<Usuario> lista= (List<Usuario>) c.list(); 
		session.close();
		
		
		Random random=new Random(System.currentTimeMillis());
		return lista.get(random.nextInt(lista.size())).getFrase();
	}
}