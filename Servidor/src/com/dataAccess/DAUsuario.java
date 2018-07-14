package com.dataAccess;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.modelo.Usuario;

public class DAUsuario {
	Session session;
	
	public DAUsuario() {
		session = ConectorSingleton.getInstance().getSession();
	}
	
	//NO USAR JAMAS
	@Deprecated
	public void almacenarUsuarioNuevo(String nombre, String pass) {
		if(usuarioExistente(nombre,pass)==false) {
		session.getTransaction().begin();
		session.saveOrUpdate(new Usuario(nombre,pass));
		session.getTransaction().commit();
		}
	}
	
	public boolean usuarioExistente(String nombre, String pass) {
		if(session==null)
			session = ConectorSingleton.getInstance().getSession();
				
		CriteriaBuilder cb1 = session.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = cb1.createQuery(Usuario.class);
		Root<Usuario> tabla = criteriaQuery.from(Usuario.class);
		//criteriaQuery.select(tabla).where(cb1.equal(tabla.get("alias"), alias));
			
		List<Usuario> lista = session.createQuery(criteriaQuery).getResultList();

		//Criteria c= session.createCriteria(Usuario.class);
		//List<Usuario> lista= (List<Usuario>) c.list(); 
		
		for(Usuario u: lista) {
			if(u.getNombre().equals(nombre) && u.getPassword().equals(pass)) return true;
		}
		session.close();
		return false;
		
	}
}