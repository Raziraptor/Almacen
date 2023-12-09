import java.io.*;
import java.util.Scanner;

public class ControlAlmacen {

    private static final String NOMBRE_ARCHIVO = "salidas.csv";

    public static void main(String[] args) {
        try {
            // Crear el archivo CSV si no existe
            crearArchivoCSV();

            // Lógica principal de la aplicación
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. Registrar Salida");
                System.out.println("2. Registrar Regreso");
                System.out.println("3. Mostrar Salidas");
                System.out.println("4. Salir");
                System.out.print("Seleccione una opción: ");

                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        registrarSalida();
                        break;
                    case 2:
                        registrarRegreso();
                        break;
                    case 3:
                        mostrarSalidas();
                        break;
                    case 4:
                        System.out.println("Saliendo de la aplicación. ¡Hasta luego!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtelo de nuevo.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void crearArchivoCSV() throws IOException {
        File archivoCSV = new File(NOMBRE_ARCHIVO);
        if (!archivoCSV.exists()) {
            archivoCSV.createNewFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {
                writer.write("ID,Quien,Herramienta/Material,Cantidad,EsRegreso");
                writer.newLine();
            }
        }
    }

    private static void registrarSalida() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, true))) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Ingrese quién: ");
            String quien = scanner.next();

            System.out.print("Ingrese herramienta/material: ");
            String herramientaMaterial = scanner.next();

            System.out.print("Ingrese cantidad (si es material): ");
            int cantidad = scanner.nextInt();

            // Obtener el siguiente ID basado en el número de líneas en el archivo
            int id = obtenerSiguienteID();

            // Escribir la nueva salida en el archivo CSV
            writer.write(id + "," + quien + "," + herramientaMaterial + "," + cantidad + ",false");
            writer.newLine();

            System.out.println("Salida registrada correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void registrarRegreso() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, true))) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Ingrese ID de la salida a la que desea registrar el regreso: ");
            String idRegresoStr = scanner.next();

            try {
                int idRegreso = Integer.parseInt(idRegresoStr);

                // Marcar la salida como regreso en el archivo CSV
                marcarComoRegreso(idRegreso);

                System.out.println("Regreso registrado correctamente.");
            } catch (NumberFormatException ex) {
                System.out.println("Formato de ID no válido. Inténtelo de nuevo.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void marcarComoRegreso(int idRegreso) throws IOException {
        // Crear una copia temporal del archivo
        File archivoTemporal = new File("temporal.csv");
        archivoTemporal.createNewFile();

        try (BufferedReader reader = new BufferedReader(new FileReader(NOMBRE_ARCHIVO));
             BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTemporal))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");

                // Verificar si la línea tiene el formato esperado
                if (partes.length == 5) {
                    int id = Integer.parseInt(partes[0]);

                    // Marcar como regreso si el ID coincide
                    if (id == idRegreso) {
                        partes[4] = "true";
                    }
                    writer.write(String.join(",", partes));
                    writer.newLine();
                } else {
                    System.out.println("Error: Formato de línea no válido en el archivo CSV.");
                }
            }
        }

        // Reemplazar el archivo original con la copia temporal
        archivoTemporal.renameTo(new File(NOMBRE_ARCHIVO));
    }

    private static void mostrarSalidas() {
        try (BufferedReader reader = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int obtenerSiguienteID() {
        try (BufferedReader reader = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            int lineCount = 0;
            while (reader.readLine() != null) {
                lineCount++;
            }
            return lineCount + 1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
