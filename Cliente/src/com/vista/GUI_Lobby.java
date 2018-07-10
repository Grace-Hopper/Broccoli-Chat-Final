package com.vista;

import static com.Cliente.Cliente.nombreCliente;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTextField;

import javax.swing.border.EmptyBorder;


import com.Cliente.EntradaSalida;

import com.mensajes.Comandos;
import com.mensajes.Mensaje;
import com.salas.HiloOutputLobby;

import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class GUI_Lobby extends JFrame {

	private static final long serialVersionUID = 7194023903108242857L;
	private JPanel contentPane;

	private JList<String> listaClientesConectados;
	private DefaultListModel<String> modeloListaCliente;
	private DefaultListModel<String> modeloListaSala;
	private JList<String> listaSalas;
	EntradaSalida entradaSalida;
	private JTextPane chatLobby=null;
	private JTextField chatTextBoxLobby;
	
	private HiloOutputLobby outputLobby;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPaneSalas;
	private JPanel panelListaSalas;
	
	public GUI_Lobby() {
		setResizable(false);
		
		
		configurarGUI();
		configurarJListClientes();
		configurarJListSalas();
		
		entradaSalida=EntradaSalida.getInstance();
		entradaSalida.setJframeActual(this);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {

				int confirma=JOptionPane.showConfirmDialog(null,
				        "Realmente desea Salir?", "Realmente desea Salir?", JOptionPane.YES_NO_OPTION);
				if(confirma==0) {
				entradaSalida.escribirMensaje(new Mensaje(Comandos.LOGOUT, nombreCliente));
				entradaSalida.cerrarEntradaSalida(); // Ver si se puede borrar.
				
				dispose();
				} 
				
			}
		});
		
		
		
	}
	
	//------CONFIGURACIONES GUI---------------------
	private void configurarGUI() {
		setTitle("Broccoli Chat Lobby: " + nombreCliente);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 616);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		mnMenu.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		mnMenu.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnMenu);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");

		
		mntmSalir.setHorizontalAlignment(SwingConstants.CENTER);
		mnMenu.add(mntmSalir);
		
		JMenu mnSalas = new JMenu("Salas");
		mnSalas.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		mnSalas.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnSalas);
		
		JMenuItem mntmCrearSala = new JMenuItem("Crear Sala");
		
		mntmCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					GUI_SolicitarNombreSala guiCrearSala = new GUI_SolicitarNombreSala();
					guiCrearSala.setVisible(true);
					
			}
		});
		
		mntmCrearSala.setHorizontalAlignment(SwingConstants.CENTER);
		mnSalas.add(mntmCrearSala);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 83, 179, 427);
		contentPane.add(panel);
		panel.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(new Rectangle(0, 0, 500, 500));
		scrollPane_1.setBounds(0, 0, 179, 637);
		panel.add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(199, 83, 366, 427);
		contentPane.add(scrollPane_2);
		
		chatLobby = new JTextPane();
		chatLobby.setToolTipText("Sala publica. Todos podran leer lo que escribes aqui.");
		chatLobby.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 16));
		desactivarEdicionChatLobby();
		scrollPane_2.setViewportView(chatLobby);
		
		chatTextBoxLobby = new JTextField();
		chatTextBoxLobby.setBounds(199, 521, 366, 33);
		chatTextBoxLobby.setToolTipText("Escriba su mensaje aqui");
		chatTextBoxLobby.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 16));
		
		chatTextBoxLobby.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				outputLobby.mandarMensaje();
			}
		});
		chatTextBoxLobby.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			    int max = 500;
			    if(chatTextBoxLobby.getText().length() > max+1) {
			        e.consume();
			        String shortened = chatTextBoxLobby.getText().substring(0, max);
			        chatTextBoxLobby.setText(shortened);
			    }else if(chatTextBoxLobby.getText().length() > max) {
			        e.consume();
			    }
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

		});
		
		
		contentPane.add(chatTextBoxLobby);
		chatTextBoxLobby.setColumns(10);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}

	private void desactivarEdicionChatLobby() {
		chatLobby.addFocusListener(new FocusListener() {

	        @Override
	        public void focusLost(FocusEvent e) {
	        	chatLobby.setEditable(true);

	        }

	        @Override
	        public void focusGained(FocusEvent e) {
	        	chatLobby.setEditable(false);

	        }
	    });
	}

	private void configurarJListSalas() {
		panelListaSalas = new JPanel();
		panelListaSalas.setBounds(575, 83, 212, 427);
		contentPane.add(panelListaSalas);
		panelListaSalas.setLayout(null);
		
		scrollPaneSalas = new JScrollPane();
		scrollPaneSalas.setBounds(0, 0, 212, 637);
		panelListaSalas.add(scrollPaneSalas);
		
		modeloListaSala= new DefaultListModel<String>();
		
		listaSalas = new JList<String>(modeloListaSala);
		
		listaSalas.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 16));
		listaSalas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPaneSalas.setViewportView(listaSalas);
		
		listaSalas.addKeyListener(new KeyAdapter() {
	        public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	            	StringBuilder informacion = new StringBuilder();
	            	 String salaSeleccionada = (String) listaSalas.getSelectedValue();
	            	 informacion.append(nombreCliente);
		                informacion.append(";");
		                informacion.append(0);
		                informacion.append(";");
		            	informacion.append(salaSeleccionada);
	            	 Mensaje crearMP = new Mensaje(Comandos.InvitacionASalaPublicaAceptada,informacion.toString(),nombreCliente);
	            	 EntradaSalida.getInstance().escribirMensaje(crearMP);
	            	 
	            	 
	            }
	        }
	    });

		listaSalas.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2) {
	            	
	            	StringBuilder informacion = new StringBuilder();
	            	 String salaSeleccionada = (String) listaSalas.getSelectedValue();
	            	informacion.append(nombreCliente);
	                informacion.append(";");
	                informacion.append(0);
	                informacion.append(";");
	            	informacion.append(salaSeleccionada);
	            	 Mensaje crearMP = new Mensaje(Comandos.InvitacionASalaPublicaAceptada,informacion.toString(),nombreCliente);
	            	 EntradaSalida.getInstance().escribirMensaje(crearMP);
	            	 
	   	            	 
	   	            	 
	   	            
	            }
	        }
	    });
		
		JLabel lblUsuario = new JLabel("USUARIO: " + nombreCliente);
		lblUsuario.setBounds(10, 26, 90, 14);
		contentPane.add(lblUsuario);
	}

	private void configurarJListClientes() {
		modeloListaCliente = new DefaultListModel<>();
		listaClientesConectados = new JList<String>(modeloListaCliente);
		listaClientesConectados.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 16));
		listaClientesConectados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(listaClientesConectados);

		listaClientesConectados.addKeyListener(new KeyAdapter() {
	        public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	            	StringBuilder informacion = new StringBuilder();
	            	 String clienteSeleccionado = (String) listaClientesConectados.getSelectedValue();
	            	 if(!clienteSeleccionado.equals(nombreCliente)){
	            		 informacion.append(nombreCliente);
	            	 informacion.append(";");
	            	 informacion.append(clienteSeleccionado);
	            	 Mensaje crearMP = new Mensaje(Comandos.CrearMP,informacion.toString(),nombreCliente);
	            	 EntradaSalida.getInstance().escribirMensaje(crearMP);
	            	 }
	            	 
	            }
	        }
	    });

		listaClientesConectados.addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2) {
	            	
	   	            	StringBuilder informacion = new StringBuilder();
	   	            	 String clienteSeleccionado = (String) listaClientesConectados.getSelectedValue();
	   	            	 if(!clienteSeleccionado.equals(nombreCliente)){
	   	            		 informacion.append(nombreCliente);
	   	            	 informacion.append(";");
	   	            	 informacion.append(clienteSeleccionado);
	   	            	 Mensaje crearMP = new Mensaje(Comandos.CrearMP,informacion.toString(),nombreCliente);
	   	            	 EntradaSalida.getInstance().escribirMensaje(crearMP);
	   	            	 }
	   	            	 
	   	            
	            }
	        }
	    });

	}

	public synchronized JTextPane getChatLobby() {
		return chatLobby;
	}

	public synchronized JTextField getChatTextBoxLobby() {
		return chatTextBoxLobby;
	}

	public void setOutputLobby( HiloOutputLobby outputLobby){
		this.outputLobby = outputLobby;
	}
	
	public HiloOutputLobby getOutputLobby(){
		return outputLobby;
	}
	public synchronized JList<String> getListaClientesConectados() {
		return listaClientesConectados;
	}


	public void agregarSala(String salaNueva){
		
			if(!modeloListaSala.contains(salaNueva))
			modeloListaSala.addElement(salaNueva);	
			
	}
	
	public void quitarSala(String salaRemovida){
		modeloListaSala.removeElement(salaRemovida);
	}
	
	public JList<String> getListaSalasDisp(){
		return listaSalas;
	}

	public void agregarCliente(String usuarioEntrante) {
		modeloListaCliente.addElement(usuarioEntrante);	
	}
	public void quitarCliente(String usuarioSaliendo){
		modeloListaCliente.removeElement(usuarioSaliendo);
	}
}
