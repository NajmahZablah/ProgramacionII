/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author najma
 */
public class EmpleadoMain {
    public static void mian(String[] args) {
        Scanner lea = new Scanner(System.in);
        EmpleadoManager em = new EmpleadoManager();
        int opcion = 0;
        
        do {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Agregar un empleado");
            System.out.println("2. Listar empleados (No despedidos)");
            System.out.println("3. Agregar venta empleado");
            System.out.println("4. Pagar empleado");
            System.out.println("5. Despedir empleado");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = lea.nextInt();
                lea.nextLine(); // Limpiar buffer
                
                switch(opcion) {
                    case 1:
                        agregarEmpleado(lea, em);
                        break;
                    case 2:
                        listarEmpleados(em);
                        break;
                    case 3:
                        agregarVenta(lea, em);
                        break;
                    case 4:
                        pagarEmpleado(lea, em);
                        break;
                    case 5:
                        despedirEmpleado(lea, em);
                        break;
                    case 6:
                        System.out.println("Saliendo del sistema...");
                        em.cerrarArchivos();
                        break;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                lea.nextLine(); // Limpiar buffer en caso de error
                opcion = 0; // Para que no salga del menú
            }
            
        } while(opcion != 6);
        
        lea.close();
        System.out.println("¡Hasta luego!");
    }
    
    private static void agregarEmpleado(Scanner lea, EmpleadoManager em) throws IOException {
        System.out.println("\n--- AGREGAR EMPLEADO ---");
        System.out.print("Nombre del empleado: ");
        String nombre = lea.nextLine();
        
        System.out.print("Salario base: ");
        double salario = lea.nextDouble();
        lea.nextLine(); // Limpiar buffer
        
        if (salario <= 0) {
            System.out.println("Error: El salario debe ser mayor a 0");
            return;
        }
        
        em.AddEmployee(nombre, salario);
        System.out.println("✓ Empleado agregado exitosamente!");
    }
    
    private static void listarEmpleados(EmpleadoManager em) throws IOException {
        em.employeeList();
    }
    
    private static void agregarVenta(Scanner lea, EmpleadoManager em) throws IOException {
        System.out.println("\n--- AGREGAR VENTA ---");
        System.out.print("Código del empleado: ");
        int codigo = lea.nextInt();
        
        System.out.print("Mes (1-12): ");
        int mes = lea.nextInt();
        
        System.out.print("Monto de la venta: ");
        double monto = lea.nextDouble();
        lea.nextLine(); // Limpiar buffer
        
        if (monto <= 0) {
            System.out.println("Error: El monto debe ser mayor a 0");
            return;
        }
        
        em.addSale(codigo, mes, monto);
    }
    
    private static void pagarEmpleado(Scanner lea, EmpleadoManager em) throws IOException {
        System.out.println("\n--- PAGAR EMPLEADO ---");
        System.out.print("Código del empleado: ");
        int codigo = lea.nextInt();
        lea.nextLine(); // Limpiar buffer
        
        em.pagarEmpleado(codigo);
    }
    
    private static void despedirEmpleado(Scanner lea, EmpleadoManager em) throws IOException {
        System.out.println("\n--- DESPEDIR EMPLEADO ---");
        System.out.print("Código del empleado a despedir: ");
        int codigo = lea.nextInt();
        lea.nextLine(); // Limpiar buffer
        
        System.out.print("¿Está seguro que desea despedir al empleado " + codigo + "? (S/N): ");
        String confirmacion = lea.nextLine().toUpperCase();
        
        if (confirmacion.equals("S") || confirmacion.equals("SI")) {
            em.despedirEmpleado(codigo);
        } else {
            System.out.println("Operación cancelada");
        }
    }
}

// Probar aumentar salario
// Probar agregar el segundo nombre