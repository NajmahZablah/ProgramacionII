/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Semana6;

import java.io.*;
import java.util.Date;
/**
 *
 * @author najma
 */
public class myFile {

    private File miFile = null;

    void setFile(String direccion) {
        miFile = new File(direccion);
    }

    void info() {
        if (miFile.exists()) {
            System.out.println("Nombre: " + miFile.getName());
            System.out.println("Path: " + miFile.getPath());
            System.out.println("Absoluta: " + miFile.getAbsolutePath());
            System.out.println("Bytes: " + miFile.length());
            System.out.println("Modificado: " + new Date(miFile.lastModified()));
            System.out.println("Padre: " + miFile.getAbsoluteFile().getParentFile().getName());

            if (miFile.isFile()) {
                System.out.println("ES FILE");
            } else {
                System.out.println("ES FOLDER");
            }

        } else {
            System.out.println("Error: No existe");
        }
    }
    
    //el utilitario solo puede crear, mas no guardar
    boolean crearFile() throws IOException {
       return miFile.createNewFile();    
    }
    
    boolean crearFolder(){
        return miFile.mkdirs();
    }
    
    private boolean borrarAux(File mf) {
        if (mf.isDirectory()) {
            for (File child : mf.listFiles()) 
                borrarAux(child);
        }
        return miFile.delete();
    }
    
    boolean borrar() {
        if (miFile == null ) {
            return false;
        } else {
            return borrarAux(miFile);
        }
    }
    
    void DIR() {
        if (miFile.isDirectory()) {
            System.out.println("Folder: "+miFile.getName());
            int dirs = 0, files = 0, bytes = 0;
            for (File child : miFile.listFiles()) {
                System.out.print(new Date(child.lastModified()));
                if (child.isDirectory()) {
                    System.out.print("\t<DIR>\t ");
                    dirs++;
                }
                if (child.isFile()) {
                    System.out.print("\t    \t");
                    System.out.print(child.length());
                    files++;
                    bytes += child.length();
                }
                System.out.print("\t"+child.getName());
            }
            System.out.println("("+files+") files y ("+dirs+") dirs");
            System.out.println(bytes+" bytes.");
        } else {
            System.out.println("Accion no permitida!");
        }
    }
}