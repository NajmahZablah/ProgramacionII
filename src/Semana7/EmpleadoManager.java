/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.*;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author najma
 */
public class EmpleadoManager {
    
    private RandomAccessFile rcods, remps;

    public EmpleadoManager() {
        // Manipular los exception
        try {
            // 1 - Asegurar que el folder company exista
            File fl = new File("Company");
            fl.mkdir();
            // 2 - Instanciar los RAFs dentro de company
            rcods = new RandomAccessFile("company/codigos.emp", "rw");
            remps = new RandomAccessFile("company/empleados.emp", "rw");
            // 3 - Inicializar el archivo de codigo si es nuevo
            initCode();
        } catch(IOException e) {

        }
    }
    /* Formato coidigos.emp 
    int codigo
    */
    private void initCode() throws IOException { 
        if (rcods.length() == 0) {
            rcods.writeInt(1);
        }
    }

    private int getCode() throws IOException {
        rcods.seek(0);
        int codigoActual = rcods.readInt();
        rcods.seek(0);
        rcods.writeInt(codigoActual +1);

        return codigoActual;
    }

    /*
    Formato para empleados.emp
    int coDE
    String name
    double salary
    long fechaC
    long fechaD
    */
    public void AddEmployee(String name, double salary) throws IOException {
        remps.seek(remps.length());
        int code = getCode();
        remps.writeInt(code);
        remps.writeUTF(name);
        remps.writeDouble(salary);
        remps.writeLong(Calendar.getInstance().getTimeInMillis());
        remps.writeLong(0);
        createYearSalesFilesFor(code);
        // Folder del empleado
        
    }
    
    private String employeeFolder(int code) {
        return "company/empleado"+code;
    }
    
    private RandomAccessFile salesFileFor(int code) throws IOException {
        String dirPadre = employeeFolder(code);
        int yearActual = Calendar.getInstance().get(Calendar.YEAR);
        String path = dirPadre+"/ventas"+yearActual+".emp";
        return new RandomAccessFile(path, "rw");
    }
    
    /*
    Formato ventasYear.emp
    double acumularMonto
    boolean siPago
    */
    
    private void createYearSalesFilesFor(int code) throws IOException {
        RandomAccessFile ryear = salesFileFor(code);
        if (ryear.length() == 0) {
            for (int mes = 0; mes < 12; mes++) {
                ryear.writeDouble(0);
                ryear.writeBoolean(false);
            }
        }
    }
    
    private void createEmpleadoFolders(int code) throws IOException {
        File dir = new File(employeeFolder(code));
        dir.mkdir();
        createYearSalesFilesFor(code); 
    }
    
    /*
    Imprime
    Codigo - Nombre - Salario - Fecha de contratacion
    */
    public void employeeList() throws IOException {
        remps.seek(0);
        System.out.println("\n=== EMPLEADOS ACTIVOS ===");
        System.out.println("Código | Nombre | Salario | Fecha Contratación");
        System.out.println("------------------------------------------------");
        
        while(remps.getFilePointer() < remps.length()) {
            int code = remps.readInt();
            String name = remps.readUTF();
            double salary = remps.readDouble();
            Date fecha = new Date(remps.readLong());
            long fechaDespido = remps.readLong();
            
            if (fechaDespido == 0) {
                System.out.println(code + " - " + name + " - $" + salary + " - " + fecha);
            }
        }
    }
    
    private boolean isEmployeeActive(int code) throws IOException {
        remps.seek(0);
        while (remps.getFilePointer() < remps.length()) {
            int codigo = remps.readInt();
            long posicion = remps.getFilePointer();
            remps.readUTF();
            remps.skipBytes(16); // Salario (8) + Fecha contratación (8)
            
            if (remps.readLong() == 0 && codigo == code) {
                remps.seek(posicion);
                return true;
            }
        }
        return false;
    }
    
    // MÉTODO PARA AGREGAR VENTA
    public void addSale(int code, int month, double amount) throws IOException {
        if (!isEmployeeActive(code)) {
            System.out.println("Empleado no existe o está despedido");
            return;
        }
        
        if (month < 1 || month > 12) {
            System.out.println("Mes inválido. Debe ser entre 1 y 12");
            return;
        }
        
        RandomAccessFile ryear = salesFileFor(code);
        
        // Cada registro: 8 bytes (double) + 1 byte (boolean) = 9 bytes
        long posicion = (month - 1) * 9;
        ryear.seek(posicion);
        
        double montoActual = ryear.readDouble();
        ryear.seek(posicion);
        ryear.writeDouble(montoActual + amount);
        
        ryear.close();
        System.out.println("Venta agregada exitosamente!");
    }
    
    // MÉTODO PARA PAGAR EMPLEADO
    public void pagarEmpleado(int code) throws IOException {
        if (!isEmployeeActive(code)) {
            System.out.println("Empleado no existe o está despedido");
            return;
        }
        
        // Obtener el salario base del empleado
        remps.seek(0);
        double salarioBase = 0;
        boolean encontrado = false;
        
        while (remps.getFilePointer() < remps.length()) {
            int codigo = remps.readInt();
            remps.readUTF(); // nombre
            double sal = remps.readDouble();
            remps.readLong(); // fecha contratación
            long fechaDespido = remps.readLong();
            
            if (codigo == code && fechaDespido == 0) {
                salarioBase = sal;
                encontrado = true;
                break;
            }
        }
        
        if (!encontrado) {
            System.out.println("Empleado no encontrado");
            return;
        }
        
        // Calcular comisión del mes actual
        int mesActual = Calendar.getInstance().get(Calendar.MONTH); // 0-11
        RandomAccessFile ryear = salesFileFor(code);
        
        long posicion = mesActual * 9;
        ryear.seek(posicion);
        
        double ventas = ryear.readDouble();
        boolean pagado = ryear.readBoolean();
        
        if (pagado) {
            System.out.println("Este empleado ya fue pagado este mes");
            ryear.close();
            return;
        }
        
        double comision = ventas * 0.10; // 10% de comisión
        double totalPago = salarioBase + comision;
        
        // Marcar como pagado
        ryear.seek(posicion + 8); // Saltar el double
        ryear.writeBoolean(true);
        ryear.close();
        
        System.out.println("\n=== COMPROBANTE DE PAGO ===");
        System.out.println("Código: " + code);
        System.out.println("Salario Base: $" + salarioBase);
        System.out.println("Ventas del mes: $" + ventas);
        System.out.println("Comisión (10%): $" + comision);
        System.out.println("TOTAL A PAGAR: $" + totalPago);
        System.out.println("==========================");
    }
    
    // MÉTODO PARA DESPEDIR EMPLEADO
    public void despedirEmpleado(int code) throws IOException {
        if (!isEmployeeActive(code)) {
            System.out.println("Empleado no existe o ya está despedido");
            return;
        }
        
        remps.seek(0);
        while (remps.getFilePointer() < remps.length()) {
            int codigo = remps.readInt();
            remps.readUTF(); // nombre
            remps.skipBytes(8); // salario
            remps.skipBytes(8); // fecha contratación
            long posicionFechaDespido = remps.getFilePointer();
            long fechaDespido = remps.readLong();
            
            if (codigo == code && fechaDespido == 0) {
                remps.seek(posicionFechaDespido);
                remps.writeLong(Calendar.getInstance().getTimeInMillis());
                System.out.println("Empleado despedido exitosamente");
                return;
            }
        }
    }
    
    // MÉTODO PARA CERRAR ARCHIVOS
    public void cerrarArchivos() {
        try {
            if (rcods != null) rcods.close();
            if (remps != null) remps.close();
        } catch (IOException e) {
            System.out.println("Error cerrando archivos: " + e.getMessage());
        }
    }
}