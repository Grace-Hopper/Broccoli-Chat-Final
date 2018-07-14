package asistente.test;


import java.util.ArrayList;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import asistente.inet.BusquedaInet;
import asistente.inet.Internet;
import asistente.util.Busqueda;
import asistente.clase.Asistente;

public class RF20Tests {

	BusquedaInet busquedaInet;
	
	@Before
	public void setup() {
		busquedaInet = new BusquedaInet("", Internet.BUSQUEDA);
	}
	
	@Test
	public void conexionAPIBusquedaInet() {
		Assert.assertEquals(true, busquedaInet.existJson());
	}
	
	@Test
	public void jsonValidoBusquedaInet() {
		Assert.assertEquals(true, busquedaInet.jsonValido());
	}
	
	@Test
	public void hayResultados() {
		ArrayList<Busqueda> listaBusquedaInet = new ArrayList<>();
		listaBusquedaInet = busquedaInet.obtenerBusqueda();
		Assert.assertEquals(true, !listaBusquedaInet.isEmpty());
	}
	
  @Test
  public void busquedaSuperman() {
    Asistente jenkins = new Asistente("delucas", "jenkins");
    String[] mensajes = {
        "@jenkins buscame: superman"
    };
    for (String mensaje : mensajes) {
      Assert.assertEquals(
          "Superman (cuyo nombre kryptoniano es Kal-El y su nombre terrestre es Clark Kent) "
          + "es un personaje ficticio, un superhéroe de los cómics que aparece en las "
          + "https://es.wikipedia.org/wiki/superman_",
          jenkins.escuchar(mensaje)
      );
    }
  }
  
  @Test
  public void busquedaDBZ() {
    Asistente jenkins = new Asistente("delucas", "jenkins");
    String[] mensajes = {
        "@jenkins buscame: dragon ball z"
    };
    for (String mensaje : mensajes) {
      Assert.assertEquals(
          "canal Fuji Television en Japón, Toei estrenó una continuación titulada "
          + "Dragon Ball Z, la cual incorporó el contenido restante del manga.[1]​ "
          + "Un tercer anime https://es.wikipedia.org/wiki/dragon_ball_z_",
          jenkins.escuchar(mensaje)
      );
    }
  }
  
}

