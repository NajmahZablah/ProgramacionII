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
public class myFile { // el núcleo de las operaciones
    
    // atributo principal
    private File miFile = null; // inicialmente es null hasta que el usuario establezca una ruta

    void setFile(String direccion) {
        miFile = new File(direccion); // crea un objeto File a partir de una ruta
    }
    
    // método que demuestra los métodos más importantes de la clase File
    void info() {
        if (miFile.exists()) { // verifica si el archivo/directorio existe
            System.out.println("Nombre: " + miFile.getName()); // retorna el nombre del archivo
            System.out.println("Path: " + miFile.getPath()); // retorna la ruta tal como se ingresó
            System.out.println("Absoluta: " + miFile.getAbsolutePath()); // retorna la ruta completa desde la raiz
            System.out.println("Bytes: " + miFile.length()); // retorna el tamaño en bytes
            System.out.println("Modificado: " + new Date(miFile.lastModified())); // retorna la fecha de modificacion
            System.out.println("Padre: " + miFile.getAbsoluteFile().getParentFile().getName());

            if (miFile.isFile()) { // determina el tipo
                System.out.println("ES FILE");
            } else {
                System.out.println("ES FOLDER");
            }

        } else {
            System.out.println("Error: No existe");
        }
    }
    
    // el utilitario solo puede crear, mas no guardar
    // crea físicamente un archivo vacío. Retorna true si se creó exitosamente, false si ya existía.
    // lanza IOException si hay problemas de permisos o ruta inválida
    boolean crearFile() throws IOException {
       return miFile.createNewFile();    
    }
    
    boolean crearFolder(){
        return miFile.mkdirs(); // crea el directorio y todos los directorios padres necesarios
    }
    
    // En Java, no puedes borrar un directorio que contenga archvios. 
    // Por eso se usa recursión para primero borrar todo el contenido y luego el directorio
    private boolean borrarAux(File mf) {
        if (mf.isDirectory()) {
            for (File child : mf.listFiles()) 
                borrarAux(child);
        }
        return mf.delete();
    }
    
    boolean borrar() {
        if (miFile == null ) {
            return false;
        } else {
            return borrarAux(miFile);
        }
    }
    
    // Método para renombrar o mover un archivo
    boolean renombrar(String nuevoNombre) {
        File padre = miFile.getAbsoluteFile().getParentFile(); // obtenemos la carpeta padre donde está el archivo
        File nuevoFile = new File(padre, nuevoNombre); // creamos el nuevo archivo con el numero nombre en la misma ubicación
        return miFile.renameTo(nuevoFile);
    }
    
    // Este método limita el comando dir de Windows, listado el contenido de un directorio
    // Usa listFiles() que retorna un array de objetos File representando cada elemeno dentro del directorio
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
   
    // Método Tree
    void tree() {
        if (miFile.isDirectory()) {
            treeAux(miFile, 0);
        } else {
            System.out.println("Acción no permitida!");
        }
    }
    
    private void treeAux(File archivo, int nivel) {
        // creamos la indentación según el nivel de profundidad
        String indent = "";
        for (int i = 0; i < nivel; i++) {
            indent += "    ";
        }
        
        System.out.println(indent+"|-- "+archivo.getName());
        
        // si es directorio, listamos su contenido recursivamente
        if (archivo.isDirectory()) {
            File[] hijos = archivo.listFiles();
            if (hijos != null) {
                for (File hijo : hijos) {
                    treeAux(hijo, nivel + 1);
                }
            }
        }
    }
    
    // método para escribir texto en un archivo
    void escribirTexto(String texto) throws IOException {
        FileWriter fw = new FileWriter(miFile);
        fw.write(texto);
        fw.close(); // asegura que los datos se guarden
    }
    
    void leerTexto() throws IOException {
        if (miFile.exists() && miFile.isFile()) {
            BufferedReader br = new BufferedReader(new FileReader(miFile)); // lee el archivo línea por línea
            String linea;
            System.out.println("--- Contenido del archivo ---");
            while ((linea = br.readLine()) != null) { // continúa hasta que readLine() retorna null, fin del archivo
                System.out.println(linea);
            }
            System.out.println("--- Fin del archivo ---");
            br.close();
        } else {
            System.out.println("Error: el archivo no existe o no es un archivo");
        }
    }
}