package dataAccess;

import com.db.Usuario;
//jdbc:h2:D:\Chat Iteracion Final Workspace 2\Servidor/ChatDB
public class TestHibernateORM {

	public static void main(String[] args) throws ClassNotFoundException {
			
		DAUsuario s= new DAUsuario();
		s.almacenarUsuarioNuevo(new Usuario(88,"Gato", "Gato"));
	}

}