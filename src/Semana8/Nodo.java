/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Semana8;

/**
 *
 * @author najma
 */
public class Nodo {
    
    // atributos
    public int codigo;
    public String nombre;
    public Nodo sigte; // se conectará con otro nodo, 
                       // por lo que debe su tipo de dato deberá ser compatible

    // constructor
    public Nodo(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        sigte = null;
    }
    
    // función práctica
    public String toString() {
        return "Nodo ("+"Codigo: "+codigo+", Nombre: "+nombre+")";
    }
}