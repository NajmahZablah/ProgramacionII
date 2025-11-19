/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Semana3;

import java.util.ArrayList;
/**
 *
 * @author najma
 */
public class Tigo {
    
    private ArrayList<Plan> planes = new ArrayList<>();
    
    public void addPlan(int numeroTelefono, String nombreCliente, String extra, String tipo) {
       
        if ("IPHONE".equalsIgnoreCase(tipo)) {
            planes.add(new PlanIPhone(numeroTelefono, nombreCliente, extra));
            
        } else 
            if ("BLACKBERRY".equalsIgnoreCase(tipo)) {
            int pin = Integer.parseInt(extra.trim());
            planes.add(new PlanBlackBerry(numeroTelefono, nombreCliente, pin));
            
        } else {
            System.out.println("Tipo de plan no valido: " + tipo);
            
        }
    }
    
    public boolean busqueda(int numeroTelefono, String datoExtra, String tipo) {
        for (Plan p : planes) {
            if (p.getNumeroTelefono() == numeroTelefono) {
                if (tipo.equalsIgnoreCase("IPHONE")) {
                    PlanIPhone iphone = (PlanIPhone) p;
                    if (iphone.getEmail().equalsIgnoreCase(datoExtra)) {
                       return true; 
                    }
                } else 
                    if (tipo.equalsIgnoreCase("BLACKBERRY")) {
                        PlanBlackBerry bb = (PlanBlackBerry) p;
                        int pin = Integer.parseInt(datoExtra);
                        if (bb.getPin() == pin) {
                            return true;
                        }
                    }
            }
        }
        return false;
    }
    
    public double cobro(int numeroTelefono, int mins, int msgs) {
        for (Plan p : planes) {
            if (p.getNumeroTelefono() == numeroTelefono) {
                return p.pagoMensual(mins, msgs);
            }
        }
        return 0;
    }
    
    public void addAmigo(int numeroTelefono, String pinAmigo) {
        for (Plan p : planes) {
            if (p.getNumeroTelefono() == numeroTelefono) {
                PlanBlackBerry bb = (PlanBlackBerry) p;
                bb.addPinAmigo(pinAmigo);
                System.out.println("Amigo agregado al BB con numero: "+numeroTelefono);
            }
        }
    }
        
    public void totalInfo() {
        int totalIPhone = 0;
        int totalBB = 0;

        System.out.println("===LISTA DE PLANES===");
        for (Plan p : planes) {

            p.Imprimir();

            if (p instanceof PlanIPhone) {
                totalIPhone++;
            } else if (p instanceof PlanBlackBerry) {
                totalBB++;
            }

            System.out.println();
        }

        System.out.println("Total de iPhone: " + totalIPhone);
        System.out.println("Total de BlackBerry: " + totalBB);
    }
}
