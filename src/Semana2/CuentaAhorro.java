/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Semana2;

/**
 *
 * @author najma
 */

import java.util.Calendar;

public class CuentaAhorro extends CuentaBancaria {
    
    private Calendar ultimoUso;
    
    public CuentaAhorro(int numero, String cliente, String moneda) {
        super(numero, cliente, moneda);
        ultimoUso = Calendar.getInstance();
    }
    
    public boolean isActiva() {
        Calendar hoy = Calendar.getInstance();
        hoy.add(Calendar.MONTH, -6);
        return ultimoUso.after(hoy);
    }
    
    @Override // estética
    public void depositar(double monto) {
        
        if (isActiva()) {
            super.depositar(monto);
            ultimoUso = Calendar.getInstance();
        } else {
            System.out.println("Cuenta Inactiva! El deposito no se ha podido realizar");
        }
    }
    
    @Override
    public boolean retirar(double monto) {
        if (isActiva()) {
            ultimoUso = Calendar.getInstance();
            return super.retirar(monto);
        } else {
            System.out.println("Cuenta Inactiva!");
        }
        return false;   
    }
    
    
    
}
