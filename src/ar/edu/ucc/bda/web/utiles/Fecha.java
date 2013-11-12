package ar.edu.ucc.bda.web.utiles;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Fecha {

	
	public Fecha(){
	}
	
	private Calendar obtenerFecha(String strFecha)
	{
		if(strFecha==null){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			System.out.println();
			strFecha=dateFormat.format(cal.getTime());
		}
		
		String [] a=strFecha.split("-");
		int [] b=new int[3];
		int j=0;
		for (String string : a) {
			b[j]=Integer.parseInt(string);
			j++;
		}
		Calendar calendar= new GregorianCalendar();
		calendar.set(b[0], b[1], b[2]); //seteo año mes dia
		return calendar;
	}

	public int compararFecha(String fecha1, String fecha2){
		Calendar c1=obtenerFecha(fecha1);
		Calendar c2=obtenerFecha(fecha2);
		return c1.compareTo(c2);
	}
	
	public boolean mayorFechaActual (String fecha)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Fecha f=new Fecha();
		int comparacion=f.compararFecha(dateFormat.format(date), fecha);
		if(comparacion>0)
			return true;
		
		return false;
		
	}
	
}
