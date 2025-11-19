/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Semana3;

/**
 *
 * @author najma
 */
public abstract class Plan {
    
    // atributos
    protected int numeroTelefono;
    protected String nombreCliente;
    
    // constructor
    public Plan(int numeroTelefono, String nombreCliente) {
        this.numeroTelefono = numeroTelefono;
        this.nombreCliente = nombreCliente;
    }
    
    public int getNumeroTelefono() {
        return numeroTelefono;
    }
    
    public String getNombreCliente() {
        return nombreCliente;
    }
    
    public abstract double pagoMensual(int mins, int msgs);
    
    public void Imprimir() {
        System.out.println("Numero de Telefono: "+numeroTelefono);
        System.out.println("Nombre de Cliente: "+nombreCliente);
    }
}
