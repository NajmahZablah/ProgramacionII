/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Semana4;

/**
 *
 * @author najma
 */
public enum TipoPeaje {
    LIVIANO(22.5, "A"), EJES2(42.2, "B"), EJES3(70.86, "C"), EJES4(256.5, "D");
    
    double precio;
    String name;
    
    TipoPeaje(double precio, String n) {
        this.precio = precio;
        this.name = n;
    }
}
