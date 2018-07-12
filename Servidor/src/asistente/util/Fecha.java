package asistente.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Fecha implements Comparable<Fecha>{
	
	private Calendar ahora;
	private int a�o;
	private int mes;
	private int dia;
	private int hr_12;
	private int hr_24;
	private int min;
	private int seg;
	private String am_pm;
	private String mes_nombre;
	private String dia_nombre;
	private String[] v_am_pm = {"AM", "PM"};
	private String[] v_mes_nombre = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
	private String[] v_dia_nombre = {"DOMINGO", "LUNES", "MARTES", "MI�RCOLES", "JUEVES", "VIERNES", "S�BADO"};

	public Fecha (int a�o_p, int mes_p, int dia_p, int hr_p, int min_p, int seg_p, char est) {
		this.a�o = a�o_p;
		this.mes = mes_p;
		this.dia = dia_p;
		this.hr_24 = hr_p;
		this.min = min_p;
		this.seg = seg_p;
	}
	
//	 Constructor por defecto seteado al 01/04/2018 15:15:00, para probar con los tests
//	Para que funcione con la fecha del sistema, comentar la linea "ahora.set(2018, 3, 1, 15, 15, 0);"
	public Fecha () {
		this.ahora = Calendar.getInstance();
		//ahora.set(2018, 3, 1, 15, 15, 0);	 
		a�o = ahora.get(Calendar.YEAR);
		mes = ahora.get(Calendar.MONTH) + 1; // 1 (ENERO) y 12 (DICIEMBRE)
		dia = ahora.get(Calendar.DAY_OF_MONTH);
		hr_12 = ahora.get(Calendar.HOUR);
		hr_24 = ahora.get(Calendar.HOUR_OF_DAY);
		min = ahora.get(Calendar.MINUTE);
		seg = ahora.get(Calendar.SECOND);
		am_pm = v_am_pm[ahora.get(Calendar.AM_PM)]; //ahora.get(Calendar.AM_PM)=> 0 (AM) y 1 (PM)
		mes_nombre = v_mes_nombre[ahora.get(Calendar.MONTH)];
		dia_nombre = v_dia_nombre[ahora.get(Calendar.DAY_OF_WEEK) - 1];

	}
	
//	Constructor para Fecha Futuro. Ordesn de ".add" es de segundos hasta a�os.
	public Fecha (int a�o_p, int mes_p, int dia_p, int hr_p, int min_p, int seg_p) {
		this.ahora = Calendar.getInstance();
		//ahora.set(2018, 3, 1, 15, 15, 0);
		if(seg_p != 0)
			ahora.add(Calendar.SECOND, seg_p);
		if(min_p != 0)
			ahora.add(Calendar.MINUTE, min_p);
		if(hr_p != 0)
			ahora.add(Calendar.HOUR, hr_p);
		if(dia_p != 0)
			ahora.add(Calendar.DATE, dia_p);
		if(mes_p != 0)
			ahora.add(Calendar.MONTH, mes_p);
		if(a�o_p != 0)
			ahora.add(Calendar.YEAR, a�o_p);
		a�o = ahora.get(Calendar.YEAR);
		mes = ahora.get(Calendar.MONTH) + 1; // 1 (ENERO) y 12 (DICIEMBRE)
		dia = ahora.get(Calendar.DAY_OF_MONTH);
		hr_12 = ahora.get(Calendar.HOUR);
		hr_24 = ahora.get(Calendar.HOUR_OF_DAY);
		min = ahora.get(Calendar.MINUTE);
		seg = ahora.get(Calendar.SECOND);
		am_pm = v_am_pm[ahora.get(Calendar.AM_PM)]; //ahora.get(Calendar.AM_PM)=> 0 (AM) y 1 (PM)
		mes_nombre = v_mes_nombre[ahora.get(Calendar.MONTH)];
		dia_nombre = v_dia_nombre[ahora.get(Calendar.DAY_OF_WEEK) - 1];

	}
	
	
//	Constructor para Fecha Pasado. Ordesn de ".add" es de a�os a segundos. 
//	Ultimo parametro un String para no distingir del 	Constructor para Fecha Futuro
	public Fecha (int a�o_p, int mes_p, int dia_p, int hr_p, int min_p, int seg_p, String pasado) {
		this.ahora = Calendar.getInstance();
		//ahora.set(2018, 3, 1, 15, 15, 0);
		if(a�o_p != 0)
			ahora.add(Calendar.YEAR, a�o_p);
		if(mes_p != 0)
			ahora.add(Calendar.MONTH, mes_p);
		if(dia_p != 0)
			ahora.add(Calendar.DATE, dia_p);
		if(hr_p != 0)
			ahora.add(Calendar.HOUR, hr_p);
		if(min_p != 0)
			ahora.add(Calendar.MINUTE, min_p);
		if(seg_p != 0)
			ahora.add(Calendar.SECOND, seg_p);
		a�o = ahora.get(Calendar.YEAR);
		mes = ahora.get(Calendar.MONTH) + 1; // 1 (ENERO) y 12 (DICIEMBRE)
		dia = ahora.get(Calendar.DAY_OF_MONTH);
		hr_12 = ahora.get(Calendar.HOUR);
		hr_24 = ahora.get(Calendar.HOUR_OF_DAY);
		min = ahora.get(Calendar.MINUTE);
		seg = ahora.get(Calendar.SECOND);
		am_pm = v_am_pm[ahora.get(Calendar.AM_PM)]; //ahora.get(Calendar.AM_PM)=> 0 (AM) y 1 (PM)
		mes_nombre = v_mes_nombre[ahora.get(Calendar.MONTH)];
		dia_nombre = v_dia_nombre[ahora.get(Calendar.DAY_OF_WEEK) - 1];

	}
	
	
	public Fecha (String fecha, String format_date) throws ParseException {
		
		Date parsed = new Date();
		SimpleDateFormat format = new SimpleDateFormat(format_date);
		parsed = format.parse(fecha);
		this.ahora = Calendar.getInstance();
		ahora.setTime(parsed);
		a�o = ahora.get(Calendar.YEAR);
		mes = ahora.get(Calendar.MONTH) + 1; // 1 (ENERO) y 12 (DICIEMBRE)
		dia = ahora.get(Calendar.DAY_OF_MONTH);
		hr_12 = ahora.get(Calendar.HOUR);
		hr_24 = ahora.get(Calendar.HOUR_OF_DAY);
		min = ahora.get(Calendar.MINUTE);
		seg = ahora.get(Calendar.SECOND);
		am_pm = v_am_pm[ahora.get(Calendar.AM_PM)]; //ahora.get(Calendar.AM_PM)=> 0 (AM) y 1 (PM)
		mes_nombre = v_mes_nombre[ahora.get(Calendar.MONTH)];
		dia_nombre = v_dia_nombre[ahora.get(Calendar.DAY_OF_WEEK) - 1];
		
	}
	
		
	public int getA�o() {
		return a�o;
	}


	public String[] formato_hora =	{"H:MM_AM", "HH:MM_AM", "H:MM:SS_AM", "HH:MM:SS_AM",
									 "H:MM", "HH:MM", "H:MM:SS", "HH:MM:SS"};
	
	
	public String[] formato_fecha =	{"d 'de' MMMMM 'de' yyyy", "dd 'de' MMMMM 'de' yyyy", "DD/MM/AAAA", "DD/MM/AA",
									"nameDay D de mmmm de AAAA"};

	
	public String hora(String format_time) {
		if(format_time.equals(formato_hora[0]))
			return "" + hr_12 + ":" + (min<10?"0":"") + min + " " + am_pm;

		if(format_time.equals(formato_hora[1]))
			return "" + (hr_12<10?"0":"") + hr_12 + ":" + (min<10?"0":"") + min + " " + am_pm;

		if(format_time.equals(formato_hora[2]))
			return "" + hr_12 + ":" + (min<10?"0":"") + min + ":" + (seg<10?"0":"") + seg + " " + am_pm;

		if(format_time.equals(formato_hora[3]))
			return "" + (hr_12<10?"0":"") + hr_12 + ":" + (min<10?"0":"") + min + ":" + (seg<10?"0":"") + seg + " " + am_pm;

		if(format_time.equals(formato_hora[4]))
			return "" + hr_24 + ":" + (min<10?"0":"") + min ;
		
		if(format_time.equals(formato_hora[5]))
			return "" + (hr_24<10?"0":"") + hr_24 + ":" + (min<10?"0":"") + min ;

		if(format_time.equals(formato_hora[6]))
			return "" + hr_24 + ":" + (min<10?"0":"") + min + ":" + (seg<10?"0":"") + seg;

		if(format_time.equals(formato_hora[7]))
			return "" + (hr_24<10?"0":"") + hr_24 + ":" + (min<10?"0":"") + min + ":" + (seg<10?"0":"") + seg;
		
		
		return "" + ahora.getTime();
	}
	
	@Override
	public int compareTo(Fecha otra) {
		
		if(this.a�o == otra.a�o)
			if(this.mes == otra.mes)
				if(this.dia == otra.dia)
					if(this.hr_24 == otra.hr_24)
						if(this.min == otra.min)
							return this.seg - otra.seg;
						else return this.min - otra.min;
					else return this.hr_24 - otra.hr_24;
				else return this.dia - otra.dia;
			else return this.mes - otra.mes;
		else return this.a�o - otra.a�o;
	}

		
	public String fechaToString(String format_date) {
		if(format_date.equals(formato_fecha[0]))
			return "" + dia + " de " + mes_nombre.toLowerCase() + " de " + a�o;

		if(format_date.equals(formato_fecha[1]))
			return "" + (dia<10?"0":"")  + dia + " de " + mes_nombre.toLowerCase() + " de " + a�o;

		if(format_date.equals(formato_fecha[2]))
			return "" + (dia<10?"0":"")  + dia + "/"  + (mes<10?"0":"")  + mes + "/" + a�o;

		if(format_date.equals(formato_fecha[3]))
			return "" + (dia<10?"0":"")  + dia + "/"  + (mes<10?"0":"")  + mes + "/" + a�o%100;

		if(format_date.equals(formato_fecha[4]))	// lunes 2 de abril de 2018"
			return dia_nombre.toLowerCase() + " " + dia + " de " + mes_nombre.toLowerCase() + " de " + a�o;
		return "" + ahora.get(Calendar.DATE);
	}
	
	
	public String diaSemana() {
		return dia_nombre.toLowerCase();
	}
	
	
	public boolean esFechasMenorA(Fecha fecha2){
		Date fec1 = this.ahora.getTime();
		Date fec2 = fecha2.ahora.getTime();
		return ((long)fec1.getTime() < (long)fec2.getTime());
	}
	
	public int restaFechas_Dias(Fecha fechaFin){
		GregorianCalendar fechaInicio = new GregorianCalendar();
		GregorianCalendar fechaFinal = new GregorianCalendar();
		Date fec1 = this.ahora.getTime();
		Date fec2 = fechaFin.ahora.getTime();
		if((long)fec1.getTime() < (long)fec2.getTime()) {
			fechaInicio.setTime(this.ahora.getTime());
			fechaFinal.setTime(fechaFin.ahora.getTime());			
		}
		else {
			fechaInicio.setTime(fechaFin.ahora.getTime());
			fechaFinal.setTime(this.ahora.getTime());	
		}
		int dias = 0;
		if(fechaFinal.get(Calendar.YEAR) == fechaInicio.get(Calendar.YEAR)){
			dias =(fechaFinal.get(Calendar.DAY_OF_YEAR)- fechaInicio.get(Calendar.DAY_OF_YEAR))+1;
		}
		else {
			int rangoA�os = fechaFinal.get(Calendar.YEAR) - fechaInicio.get(Calendar.YEAR);
			for(int i = 0; i <= rangoA�os; i++) {
				int diasA�o = fechaInicio.isLeapYear(fechaInicio.get(Calendar.YEAR)) ? 366 : 365;
				if(i == 0) {
					dias = 1 + dias +(diasA�o - fechaInicio.get(Calendar.DAY_OF_YEAR));
				}
				else if (i == rangoA�os) {
					dias = dias + fechaFinal.get(Calendar.DAY_OF_YEAR);
				}
				else {
					dias = dias + diasA�o;
				}
			}
		}
		return (dias - 1) * ((long)fec1.getTime() < (long)fec2.getTime() ? 1 : -1);
	}
	
	public int diferenciaDeDias() {
		final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; // Milisegundos al d�a 
		//Date hoy = new Date();
		
		Calendar hCal = new GregorianCalendar(2018, 5, 23);
		Date hoy = new Date(hCal.getTimeInMillis());
		
		Calendar calendar = new GregorianCalendar(this.a�o, this.mes-1, this.dia);
		Date fecha = new Date(calendar.getTimeInMillis());
		
		return Math.round((fecha.getTime() - hoy.getTime())/MILLSECS_PER_DAY);
	}
	
	
	public int restaFechas_Semanas(Fecha fecha2){
		GregorianCalendar fechaInicio = new GregorianCalendar();
		GregorianCalendar fechaFinal = new GregorianCalendar();
		Date fec1 = this.ahora.getTime();
		Date fec2 = fecha2.ahora.getTime();
		if((long)fec1.getTime() < (long)fec2.getTime()) {
			fechaInicio.setTime(this.ahora.getTime());
			fechaFinal.setTime(fecha2.ahora.getTime());			
		}
		else {
			fechaInicio.setTime(fecha2.ahora.getTime());
			fechaFinal.setTime(this.ahora.getTime());	
		}
		int dias = 0;
		if(fechaFinal.get(Calendar.YEAR) == fechaInicio.get(Calendar.YEAR)){
			dias =(fechaFinal.get(Calendar.DAY_OF_YEAR)- fechaInicio.get(Calendar.DAY_OF_YEAR))+1;
		}
		else {
			int rangoA�os = fechaFinal.get(Calendar.YEAR) - fechaInicio.get(Calendar.YEAR);
			for(int i = 0; i <= rangoA�os; i++) {
				int diasA�o = fechaInicio.isLeapYear(fechaInicio.get(Calendar.YEAR)) ? 366 : 365;
				if(i == 0) {
					dias = 1 + dias +(diasA�o - fechaInicio.get(Calendar.DAY_OF_YEAR));
				}
				else if (i == rangoA�os) {
					dias = dias + fechaFinal.get(Calendar.DAY_OF_YEAR);
				}
				else {
					dias = dias + diasA�o;
				}
			}
		}
		return ((dias - 1) / 7) * ((long)fec1.getTime() < (long)fec2.getTime() ? 1 : -1);
	}
	
	public int restaFechas_Meses(Fecha fecha2){
		GregorianCalendar fechaInicio = new GregorianCalendar();
		GregorianCalendar fechaFinal = new GregorianCalendar();
		Date fec1 = this.ahora.getTime();
		Date fec2 = fecha2.ahora.getTime();
		if((long)fec1.getTime() < (long)fec2.getTime()) {
			fechaInicio.setTime(this.ahora.getTime());
			fechaFinal.setTime(fecha2.ahora.getTime());			
		}
		else {
			fechaInicio.setTime(fecha2.ahora.getTime());
			fechaFinal.setTime(this.ahora.getTime());	
		}
		int meses = ((fechaFinal.get(Calendar.YEAR) - fechaInicio.get(Calendar.YEAR)) * 12);
		meses += fechaFinal.get(Calendar.MONTH) - fechaInicio.get(Calendar.MONTH);
		meses += (fechaFinal.get(Calendar.DAY_OF_MONTH) < fechaInicio.get(Calendar.DAY_OF_MONTH) ? -1 : 0);
		return meses * ((long)fec1.getTime() < (long)fec2.getTime() ? 1 : -1);
	}
	
	public int restaFechas_A�os(Fecha fecha2){
		GregorianCalendar fechaInicio = new GregorianCalendar();
		GregorianCalendar fechaFinal = new GregorianCalendar();
		Date fec1 = this.ahora.getTime();
		Date fec2 = fecha2.ahora.getTime();
		if((long)fec1.getTime() < (long)fec2.getTime()) {
			fechaInicio.setTime(this.ahora.getTime());
			fechaFinal.setTime(fecha2.ahora.getTime());			
		}
		else {
			fechaInicio.setTime(fecha2.ahora.getTime());
			fechaFinal.setTime(this.ahora.getTime());	
		}
		int a�os = (fechaFinal.get(Calendar.YEAR) - fechaInicio.get(Calendar.YEAR));
		a�os += (fechaFinal.get(Calendar.DAY_OF_YEAR) < fechaInicio.get(Calendar.DAY_OF_YEAR) ? -1 : 0);
		return a�os * ((long)fec1.getTime() < (long)fec2.getTime() ? 1 : -1);
	}
	
	
}