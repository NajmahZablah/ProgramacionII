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
public class PlanBlackBerry extends Plan {
    
    // atributos
    private int pin;
    private ArrayList<String> bbm;
    
    // contructor 
    public PlanBlackBerry(int numeroTelefono, String nombreCliente, int pin) {
        super(numeroTelefono, nombreCliente);
        this.bbm = new ArrayList<>();
        this.pin = pin;
    }
    
    @Override
    public double pagoMensual(int mins, int msgs) {
        
        double planInternet = 40.0;
        
        if (mins > 200) {
            planInternet += (mins - 200) * 0.8;
        } else {
            
        }
        
        if (msgs > 300) {
            planInternet += (msgs - 300) * 0.2;
        } else {
            
        }
        
        return planInternet;
    }
    
    @Override
    public void Imprimir() {
        super.Imprimir();
        System.out.println("Dato del pin: "+pin);
        System.out.println("Lista de amigos BBM: "+bbm.size());
    }
    
    public void addPinAmigo(String pinAmigo) {
        if (!bbm.contains(pinAmigo)) 
            bbm.add(pinAmigo);
    }
    
    public int getPin() {
        return pin;
    }
}
