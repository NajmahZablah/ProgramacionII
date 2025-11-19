/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Semana3;

/**
 *
 * @author najma
 */
public class PlanIPhone extends Plan {
    
    // atributos
    private String email;
    
    public PlanIPhone(int numeroTelefono, String nombreCliente, String email) {
        super(numeroTelefono, nombreCliente);
        this.email = email;
    }
    
    @Override
    public double pagoMensual(int mins, int msgs) {
        
        double planInternet = 22.0;
        
        if (mins > 0)
            planInternet += mins * 0.4;
        if (msgs > 0)
            planInternet += msgs * 0.1;
        
        return planInternet;
    }
    
    
    @Override
    public void Imprimir() {
        super.Imprimir();
        System.out.println("Email: "+email);
        
    }
    
    public String getEmail() {
        return email;
    }
}
