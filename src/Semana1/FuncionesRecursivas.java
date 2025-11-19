/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Semana1;

/**
 *
 * @author najma
 */
public class FuncionesRecursivas {
   
    // Método Up
    public static int sumaUp(int num) {
       if(num >= 1)
           return sumaUp(num-1) + num;
       return 0;
   } 
    
    // Método Down
    public static int sumaDown(int num) {
      return sumaDown(num,0);  
    }
    
    private static int sumaDown(int num, int sumar) {
        if (num >= 1)
            return sumaDown(num-1, sumar+num);
        return sumar;
    }
    
    // Referencia en potencia Up
    public static int potUp(int base, int exp) {
        if (exp > 0)
            return potUp(base, exp-1) * base;
        return 1;
    }
    
    // Referencia en potencia Down
    public static int potDown(int base, int exp) {
        return potDown(base, exp, 1);
    }
    
    private static int potDown(int base, int exp, int pot) {
        if(exp > 0)
            return potDown(base, exp-1, pot*base);
        return pot;
    }
    
    // Reto!
    public static boolean isPalindromo(String palabra) {
        return isPalindromo(palabra, 0, palabra.length()-1);
    }
    
    private static boolean isPalindromo(String palabra, int inicio, int fin ) {
        if(inicio < fin) {
            if (palabra.charAt(inicio) == palabra.charAt(fin)) 
                return isPalindromo(palabra, inicio+1, fin-1);
            return false;
        }
        return true;  
    }
}
