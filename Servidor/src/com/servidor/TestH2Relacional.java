package com.servidor;

import clase.Asistente;

public class TestH2Relacional {

	public static void main(String[] args) throws ClassNotFoundException {
			Asistente asistente= new Asistente("jenkins");
			String devuelve= asistente.escuchar("buen d�a @jenkins");
			//String devuelve= asistente.escuchar("gracias @jenkins");.
			//String devuelve= asistente.escuchar("me dec�s la hora @jenkins?");
			
			//String devuelve= asistente.escuchar("@jenkins qu� d�a ser� dentro de 22 d�as?");
			//String devuelve= asistente.escuchar("@jenkins cu�nto es 1 + 2");
			//String devuelve= asistente.escuchar("@jenkins jugamos?");
			//String devuelve= asistente.escuchar("@jenkins cu�ntos kilos son 9000 gramos");
			//String devuelve= asistente.escuchar("@jenkins dime las 3 leyes de la rob�tica");
			//String devuelve= asistente.escuchar("@jenkins dime la 1ra ley de la rob�tica.");
			//String devuelve= asistente.escuchar("@jenkins puedes decir una Chuck Norris Facts?");
			//String devuelve= asistente.escuchar("@jenkins cual es mi estado de deudas?");
			//String devuelve= asistente.escuchar("@jenkins dime las 3 leyes de la rob�tica");
			System.out.println(devuelve);
			//devuelve="Disculpa... no entiendo el pedido "+"asdasdasda";
			//System.out.println(devuelve.contains("Disculpa... no entiendo el pedido"));
	}

}
