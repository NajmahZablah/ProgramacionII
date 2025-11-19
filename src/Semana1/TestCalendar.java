/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Semana1;

/**
 *
 * @author najma
 */

import java.util.Calendar;
// Investigar...
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TestCalendar {
    public static void main(String[] args) {
        
        // Ejemplo Calendar
        /*
        Calendar hoy = Calendar.getInstance();
        System.out.println("Fecha: "+hoy.getTime());
        
        Calendar Esteban = Calendar.getInstance();
        Esteban.set(2006, Calendar.JUNE, 10);
        System.out.println("Esteban nace en: "+Esteban.getTime());
        */
        
        // Ejemplo Date: Representa un instante específico en el tiempo (fecha y hora)
        /*
        Date ahora = new Date();
        System.out.println("Fecha actual: "+ahora);
        */
        
        // Ejemplo Locale: Sirve para definir la región o idioma que se usará en formatos de fecha, números o texto
        /*
        Date hoy = new Date();
        
        DateFormat formatoES = DateFormat.getDateInstance(DateFormat.LONG, new Locale("es", "ES"));
        DateFormat formatEN = DateFormat.getDateInstance(DateFormat.LONG, new Locale("en", "EN"));
        
        System.out.println("En espaniol: "+formatoES.format(hoy));
        System.out.println("In english: "+formatEN.format(hoy));
        */
        
        // Ejemplo TimeZone: Define la zona horaria usada para interpretar o mostrar una fecha
        /*
        Calendar calendario = Calendar.getInstance(TimeZone.getTimeZone("America/Tegucigalpa"));
        System.out.println("Zona horaria: "+calendario.getTimeZone().getID());
        System.out.println("Hora local: "+calendario.getTime());
        */
        
        // Ejemplo DateFormat: Clase abastracta para formatear o analizar fechas (convertir de Date a texto o viceversa)
        /*
        Date hoy = new Date();
        
        DateFormat formatoCorto = DateFormat.getDateInstance(DateFormat.SHORT);
        DateFormat formatoLargo = DateFormat.getDateInstance(DateFormat.LONG);
        
        System.out.println("Corto: "+formatoCorto.format(hoy));
        System.out.println("Largo: "+formatoLargo.format(hoy));
        */
        
        // Ejemplo SimpleDateFormat: Permite personalizar el formato de salida de una fecha
        /*
        Date hoy = new Date();
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("Fecha formateada: "+formato.format(hoy));
        */
        
        // Ejemplo de como crear un calendario con una zona horaria diferente
        Date ahora = new Date();

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        
        formato.setTimeZone(TimeZone.getTimeZone("GMT"));
        
        System.out.println("Hora forzada en GMT: " + formato.format(ahora));
    }
}
