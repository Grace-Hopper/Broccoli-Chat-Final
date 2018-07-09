package com.servidor;

import java.util.ArrayList;

import com.chain.AgregarClienteASala;
import com.chain.Chain;
import com.chain.ClienteDejandoSala;
import com.chain.CrearMP;
import com.chain.CrearSala;
import com.chain.DesconectarCliente;
import com.chain.EliminarConversacion;
import com.chain.EnviarMsjASala;
import com.chain.InvitarUsuario;
import com.chain.RefrescarListaClientes;
import com.cliente.Cliente;
import com.logs.LoggerCliente;
import com.mensajes.Comandos;
import com.mensajes.Mensaje;
import com.sala.Sala;

/**
 * Singleton. Controlador general del chat. Desde aca se deberia poder hacer
 * todo lo necesario en el funcionamiento publico del chat, como enviar mensajes
 * o logouts. Sera accedida desde multiples hilos al mismo tiempo.
 * <b>Sincronizar!</b>
 * 
 * @author Maxi
 *
 */
public class ControladorServidor {

	private static ControladorServidor instance = null;
	Chain chain;
	private ArrayList<Cliente> clientesEnLobby; // Todos los clientes conectados al chat estan aca. esten o no chateando.
	private ArrayList<Sala> salas;

	protected ControladorServidor() {
		
		clientesEnLobby = new ArrayList<Cliente>();
		salas = new ArrayList<Sala>();
		salas.add(new Sala("Lobby"));
		chain = ensamblarChain();
	}

	public synchronized static ControladorServidor getInstance() {
		if (instance == null) {
			instance = new ControladorServidor();
		}
		return instance;
	}


	public synchronized void manejarMensaje(Mensaje mensaje) {
		chain.manejarPeticion(mensaje);
	}

	private Chain ensamblarChain() {
		AgregarClienteASala  agregarClienteASala = new AgregarClienteASala(salas,clientesEnLobby);
		CrearSala crearSala = new CrearSala(salas, clientesEnLobby);
		DesconectarCliente desconectarCliente = new DesconectarCliente(salas, clientesEnLobby);
		EnviarMsjASala enviarMensaje = new EnviarMsjASala(salas);
		InvitarUsuario invitarUsuario = new InvitarUsuario(clientesEnLobby);
		ClienteDejandoSala clienteDejandoSala = new ClienteDejandoSala(salas,clientesEnLobby);
		RefrescarListaClientes refrescar = new RefrescarListaClientes(clientesEnLobby);
		CrearMP conversacionPrivada = new CrearMP(salas,clientesEnLobby);
		EliminarConversacion eliminarConver = new EliminarConversacion(salas);
	
		agregarClienteASala.enlazarSiguiente(crearSala);
		crearSala.enlazarSiguiente(desconectarCliente);
		desconectarCliente.enlazarSiguiente(clienteDejandoSala);
		clienteDejandoSala.enlazarSiguiente(refrescar);
		refrescar.enlazarSiguiente(eliminarConver);
		eliminarConver.enlazarSiguiente(conversacionPrivada);
		conversacionPrivada.enlazarSiguiente(enviarMensaje);
		enviarMensaje.enlazarSiguiente(invitarUsuario);
		return agregarClienteASala;
	}



	public synchronized void meterEnLobby(Cliente entrante) {
		if (clientesEnLobby.contains(entrante)) {
			LoggerCliente.enviarLog("FALLO: Cliente " + entrante.getNombre() + " intenta loguearse con usuario ya logueado.");
			return;
		}
		LoggerCliente.enviarLog("Cliente " + entrante.getNombre() + " acaba de entrar al chat.");
		clientesEnLobby.add(entrante);
		
		for(Sala s: salas) {
			if(s.getNombre().equals("Lobby")) {
				s.meterCliente(entrante);
				break;
			}
		}

		entrante.getSalida().setCliente(entrante);
		
		aTodos_ClienteConectado(entrante);
		LoggerCliente.enviarLog("Lista de clientes actualizada. Clientes Actuales: " + clientesEnLobby.size());

	}


	private void aTodos_ClienteConectado(Cliente elNuevoEntrante) {

		Mensaje mensaje = new Mensaje(Comandos.ClienteNuevo, elNuevoEntrante.getNombre());

		for (Cliente yaConectados : clientesEnLobby) {
			if (!elNuevoEntrante.equals(yaConectados)) {
				yaConectados.enviarMensaje(mensaje);
			}
		}
		
		for (Cliente c : clientesEnLobby) {
			
				elNuevoEntrante.enviarMensaje(new Mensaje(Comandos.ClienteNuevo, c.getNombre()));
		}
		LoggerCliente.enviarLog("Se envio a todos el nuevo usuario.");
	}

	public ArrayList<Sala> getSalas() {
		return salas;
	}

	public void setSalas(ArrayList<Sala> salas) {
		this.salas = salas;
	}
}
