/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Semana6;

import java.io.IOException; // se lanza por errores de entrada/salida al crear archivos
import java.util.InputMismatchException; // se lanza cuando el usuario ingresa texto en lugar de un número
import java.util.Scanner; 
/**
 *
 * @author najma
 */
// Esta clase implementa el menú interactivo con manejo de excepciones
public class fileTest {

    static Scanner lea = new Scanner(System.in);

    public static void main(String[] args) {
        myFile mf = new myFile();
        int opcion = 0;

        do {
            System.out.println("\n------- MENU -------");
            System.out.println("1. Set File/Folder"); //set de archivos
            System.out.println("2. Ver informacion");
            System.out.println("3. Crear archivo"); //file
            System.out.println("4. Crear directorio"); //folder
            System.out.println("5. Borrar");
            System.out.println("6. Renombrar");
            System.out.println("7. DIR");
            System.out.println("8. Tree");
            System.out.println("9. Escribir un texto");
            System.out.println("10. Leer un texto");
            System.out.print("Escoja una opcion: ");

            try {
                opcion = lea.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Direccion: ");
                        mf.setFile(lea.next());
                        break;
                    case 2:
                        mf.info();
                        break;
                    case 3:
                        if (mf.crearFile()) {
                            System.out.println("Se creo correctamente el archivo");
                        } else {
                            System.out.println("Error: No se creo el archivo");
                        }
                        break;
                    case 4:
                        if (mf.crearFolder()) {
                            System.out.println("Se creo correctamente el folder");
                        } else {
                            mf.setFile(lea.next());
                            System.out.println("Error: No se creo el folder");
                        }
                        break;
                    case 5:
                        if (mf.borrar()) {
                            System.out.println("Se borro");
                        } else {
                            mf.setFile(lea.next());
                            System.out.println("Error: No se borro");
                        }
                        break;
                    case 6:
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = lea.next();
                        if (mf.renombrar(nuevoNombre)) {
                            System.out.println("Se renombro correctamente");
                        } else {
                            System.out.println("Error: No se pudo renombrar");
                        }
                        break;
                    case 7:
                        mf.DIR();
                        break;
                    case 8: 
                        mf.tree();
                        break;
                    case 9: 
                        System.out.println("Ingrese el texto");
                        lea.nextLine();
                        String texto = lea.nextLine();
                        mf.escribirTexto(texto);
                        System.out.println("Texto guardado correctamente");
                        break;
                    case 10:
                        mf.leerTexto();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese una opcion valida");
                lea.next();
            } catch(NullPointerException e){ // se lanza cuando intentas operar sin haber establecido primero el archivo
                System.out.println("Error: Tiene que ingresar primero la opcion 1");
                lea.next();
            } catch (IOException e) {
                System.out.println("error: " + e.getMessage());
            }

        } while (opcion != 12);

    }
}