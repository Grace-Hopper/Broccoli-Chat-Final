package com.dataAccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.modelo.Deudas;
import com.modelo.Usuario;

import asistente.util.CtaCte;

public class DADeudas {
	Session session;
	List<Deudas> deudas;
	List<Usuario> usuarios;
	public DADeudas() {
		session = ConectorSingleton.getInstance().getSession();
	}
	
	public boolean CtaCte_Insertar(String deudor, String acreedor, float importe) {
		Transaction transaccion = null;
		try{
		
				session = ConectorSingleton.getInstance().getSession();
		Deudas deudaNueva = new Deudas();
		Query queryUsuarios = session.createQuery("FROM Usuario WHERE Nombre=:deudor OR Nombre=:acreedor");
		queryUsuarios.setParameter("deudor", deudor);
		queryUsuarios.setParameter("acreedor", acreedor);
		usuarios = queryUsuarios.getResultList();
		if(usuarios.size()==2) {
		
		for(Usuario usuarioActual : usuarios) {
			if(usuarioActual.getNombre().equals(deudor))
				deudaNueva.setDeudor(usuarioActual);
			if(usuarioActual.getNombre().equals(acreedor))
				deudaNueva.setAcreedor(usuarioActual);
		}
		session.close();
		session = ConectorSingleton.getInstance().getSession();
		transaccion = session.beginTransaction();
		Deudas deudaAEnviar = new Deudas();
		deudaAEnviar.setAcreedor(deudaNueva.getAcreedor());
		deudaAEnviar.setDeudor(deudaNueva.getDeudor());
		deudaNueva.setImporte(importe);
		session.save(deudaNueva);
		session.flush();
		transaccion.commit();
		
		session.clear();
		return true;
		}return false;
		}catch(HibernateException ex){
			if (transaccion!=null) transaccion.rollback();
	         ex.printStackTrace(); 
	     return false;
		}finally {
			session.close();
			
		}
	}
	@SuppressWarnings("finally")
	public float CtaCte_ConsultaSaldo(String deudor, String acreedor) {
		float saldo = 0;
		try {
			if(session==null)
				session = ConectorSingleton.getInstance().getSession();
		
			CriteriaBuilder cb1 = session.getCriteriaBuilder();
			CriteriaQuery<Deudas> criteriaQuery = cb1.createQuery(Deudas.class);
			Root<Deudas> tabla = criteriaQuery.from(Deudas.class);
			//criteriaQuery.select(tabla).where(cb1.equal(tabla.get("alias"), alias));
				
			deudas = session.createQuery(criteriaQuery).getResultList();
			for(Deudas deudaActual : deudas) {
				if(deudaActual.getDeudor().equals(deudor) && deudaActual.getAcreedor().equals(acreedor))
					saldo += deudaActual.getImporte();
				if(deudaActual.getDeudor().equals(acreedor) && deudaActual.getAcreedor().equals(deudor))
					saldo -= deudaActual.getImporte();
			}
			return saldo;
			
		} catch (Exception ex){ }
		finally {
			session.close();
			return saldo;
		}
	}
	
	@SuppressWarnings("finally")
	public List<CtaCte> CtaCte_ConsultaEstadoDeuda(String deudor) {
		List<CtaCte> lista = new ArrayList<CtaCte>();
		String acreedor;
		float saldo;
		try {
			if(session==null)
				session = ConectorSingleton.getInstance().getSession();
		
			CriteriaBuilder cb1 = session.getCriteriaBuilder();
			CriteriaQuery<Usuario> criteriaQuery = cb1.createQuery(Usuario.class);
			Root<Usuario> tabla = criteriaQuery.from(Usuario.class);
			List<Usuario> usuarios = session.createQuery(criteriaQuery).getResultList();
			session.close();
			
				session = ConectorSingleton.getInstance().getSession();
		
			for(Usuario usuarioActual : usuarios) {
				acreedor = usuarioActual.getNombre();
				saldo = 0;
				CriteriaBuilder cb2 = session.getCriteriaBuilder();
				CriteriaQuery<Deudas> criteriaQuery2 = cb2.createQuery(Deudas.class);
				Root<Deudas> tabla2 = criteriaQuery2.from(Deudas.class);
				List<Deudas> listaDeudas = session.createQuery(criteriaQuery2).getResultList();
				for(Deudas deudaActual : listaDeudas) {
					if(deudaActual.getDeudor().getNombre().equals(deudor) && deudaActual.getAcreedor().getNombre().equals(acreedor))
						saldo += deudaActual.getImporte();
					if(deudaActual.getDeudor().getNombre().equals(acreedor) && deudaActual.getAcreedor().getNombre().equals(deudor))
						saldo -= deudaActual.getImporte();
				
				}
				lista.add(new CtaCte(acreedor, saldo));
			
			}
				
		} catch (Exception ex){
			ex.printStackTrace();
		}
		finally {
		
			return lista;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean CtaCte_Simplificar(String intermediario, String usuario1, String usuario2) {
		try {
			if(session==null||!session.isConnected())
				session = ConectorSingleton.getInstance().getSession();
			
			float saldoUsuario1 = CtaCte_ConsultaSaldo(intermediario, usuario1);
			float saldoUsuario2 = CtaCte_ConsultaSaldo(intermediario, usuario2);
			
			if(saldoUsuario1 == 0 || saldoUsuario2 == 0)
				return false;
			if(saldoUsuario1 > 0 && saldoUsuario2 > 0)
				return false;
			if(saldoUsuario1 < 0 && saldoUsuario2 < 0)
				return false;
			
			float impMinAbs = Math.min(Math.abs(saldoUsuario1), Math.abs(saldoUsuario2));
			
			if(saldoUsuario1 < 0) {
				//usuario1 es deudor y usuario2 es acreedor
				CtaCte_Insertar(intermediario, usuario1, impMinAbs);
				CtaCte_Insertar(usuario2, intermediario, impMinAbs);
				CtaCte_Insertar(usuario1, usuario2, impMinAbs);
			}else {
				//usuario2 es deudor y usuario1 es acreedor
				CtaCte_Insertar(intermediario, usuario2, impMinAbs);
				CtaCte_Insertar(usuario1, intermediario, impMinAbs);
				CtaCte_Insertar(usuario2, usuario1, impMinAbs);
			}
			
		} catch (Exception ex){ }
		finally {
			session.close();
			return true;
		}
	}
	
	public void CtaCte_DeudasGrupales(String[] v_usuarios, float importe, String usuarioPagador) {
		try {
			
				session = ConectorSingleton.getInstance().getSession();
		
			for (int i = 0; i < v_usuarios.length; i++) {
				CtaCte_Insertar(v_usuarios[i], usuarioPagador, Math.round(importe/v_usuarios.length));
			}
			
		} catch (Exception ex){ }
		finally {
			session.close();
		}
	}
}
