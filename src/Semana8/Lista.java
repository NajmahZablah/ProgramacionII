/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Semana8;

/**
 *
 * @author najma
 */
public class Lista {
    
    private Nodo inicio = null;
    private int size = 0;
    
    // función de validación
    public boolean isEmpty() {
        return inicio == null;
    }
    
    // Dentro de los parametros se ubica: lo que quiero guardar y lo que está apuntando
    public void add(Nodo obj) {
        if(obj == null)
            return;
        if(isEmpty()) {
            inicio = obj;
        } else {
            Nodo tmp = inicio;
            while(tmp.sigte != null) {
                tmp = tmp.sigte;
            }
            tmp.sigte = obj;
        }
        size++;
    }
    
    // Función para imprimir
    public void print() {
        Nodo tmp = inicio;
        while(tmp != null) {
            System.out.println(tmp);
            tmp = tmp.sigte;
        }
    }
    
    // Getter
    public Nodo get(int code) {
        Nodo tmp = inicio;
        while(tmp != null) {
            if(tmp.codigo == code)
                return tmp;
            tmp = tmp.sigte;
        }
        return null;
    }
    
    // Función eliminar
    public boolean eliminar(int code) {
        if(isEmpty())
            return false;
        
        // eliminar el primero
        if(inicio.codigo == code) {
            inicio = inicio.sigte;
            size--;
            return true;
        }
        
        // buscar el nodo anterior al que queremos eliminar
        Nodo tmp = inicio;
        while(tmp.sigte != null && tmp.sigte.codigo != code) {
            tmp = tmp.sigte;
        }
        
        if(tmp.sigte != null) {
            tmp.sigte = tmp.sigte.sigte; // saltamos el nodo a eliminar
            size--;
            return true;
        }
        return false;
    }
}