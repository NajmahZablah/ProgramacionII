/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Semana2;

import java.util.Calendar;

/**
 *
 * @author najma
 */
public class CuentaBancaria {
    
    // atributos
    protected int numero;
    protected double saldo;
    protected String cliente, moneda; 
    protected Calendar apertura;
    
    // contructor
    public CuentaBancaria(int numero, String cliente, String moneda) {
        this.numero = numero;
        this.saldo = 0;
        this.cliente = cliente;
        this.moneda = moneda;
        apertura = Calendar.getInstance();
    }
    
    public int getNumero() {
        return numero;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public String getCliente() {
        return cliente;
    }
   
    public String getMoneda() {
        return moneda;
    }
    
    public Calendar getApertura() {
        return apertura;
    }
    
    public void depositar(double monto) {
        if (monto > 0) 
            saldo += monto;
        else 
            System.out.println("Monto Invalido! (El monto no debe ser negativo)");
       
    }
    
    public boolean retirar(double monto) {
        
        if ( monto > 0 && monto <= saldo) {
            saldo -= monto;
            return true;
            
        } else {
            return false;
        }
        
    }
    
    @Override
    public String toString() {
        return "Cliente: "+cliente+
                "\nNumero de cuenta: "+numero+
                "\nTipo de moneda: "+moneda+
                "\nSaldo actual: "+saldo+
                "\nFecha de apertura: "+ apertura.getTime();
    }
}
