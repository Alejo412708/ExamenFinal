import java.util.ArrayList;
import java.util.Scanner;

// Clase que representa la estructura de un Producto (Objeto)
class Product {
    private int id;
    private String name;
    private int stock;
    private double price;

    public Product(int id, String name, int stock, double price) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getStock() { return stock; }
    public double getPrice() { return price; }
    
    public void updateStock(int newStock) { this.stock = newStock; }
}

public class InventarioGestion {
    public static void main(String[] args) {
        ArrayList<Product> listaInventario = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        
        // Productos iniciales simulando el estado de la aplicación
        listaInventario.add(new Product(1, "Arroz Integral", 3, 4500));
        listaInventario.add(new Product(2, "Leche Entera", 12, 3800));
        
        int nextId = 3;
        boolean cerrar = false; // Control de ciclo mediante booleano

        System.out.println("--- SISTEMA DE GESTIÓN DE INVENTARIO Y DESPERDICIOS ---");
        System.out.println("Problemática: Monitoreo de pérdidas y quiebres de stock.");

        while (!cerrar) {
            System.out.println("\n--- MENÚ DE CONTROL ---");
            System.out.println("1. Add Product (Registrar)");
            System.out.println("2. Search Product (Buscar)");
            System.out.println("3. Check Critical Stock (Validar Alertas de la Problemática)");
            System.out.println("4. Exit (Salir)");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del producto: ");
                    String name = scanner.nextLine();
                    System.out.print("Cantidad en Stock: ");
                    int stock = scanner.nextInt();
                    System.out.print("Precio unitario: ");
                    double price = scanner.nextDouble();
                    
                    listaInventario.add(new Product(nextId++, name, stock, price));
                    System.out.println("¡Producto registrado exitosamente en el sistema!");
                    break;
                    
                case 2:
                    System.out.print("Ingrese el nombre del producto a buscar: ");
                    String buscarNombre = scanner.nextLine().toLowerCase();
                    boolean encontrado = false;
                    
                    for (Product p : listaInventario) {
                        if (p.getName().toLowerCase().contains(buscarNombre)) {
                            System.out.println("ID: " + p.getId() + " | " + p.getName() + " | Stock: " + p.getStock() + " | Precio: $" + p.getPrice());
                            encontrado = true;
                        }
                    }
                    if (!encontrado) System.out.println("No se encontraron coincidencias.");
                    break;
                    
                case 3:
                    System.out.println("\n⚠️ EVALUACIÓN DE ALERTAS DE DESPERDICIO Y STOCK CRÍTICO:");
                    int alertasActivas = 0;
                    for (Product p : listaInventario) {
                        if (p.getStock() == 0) {
                            System.out.println("❌ [CRITICAL STOCK] - " + p.getName() + " está agotado. Riesgo inminente de pérdida financiera.");
                            alertasActivas++;
                        } else if (p.getStock() <= 5) {
                            System.out.println("⚠️ [LOW STOCK] - " + p.getName() + " tiene solo " + p.getStock() + " unidades. Requiere reabastecimiento.");
                            alertasActivas++;
                        }
                    }
                    if (alertasActivas == 0) {
                        System.out.println("✅ Todos los productos cuentan con niveles estables de inventario.");
                    }
                    break;
                    
                case 4:
                    cerrar = true;
                    System.out.println("Saliendo del sistema de gestión. Cambios respaldados.");
                    break;
                    
                default:
                    System.out.println("Opción inválida.");
            }
        }
        scanner.close();
    }
}
