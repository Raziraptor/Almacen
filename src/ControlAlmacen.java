import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
public class ControlAlmacen {

    private static final String NOMBRE_ARCHIVO = "salidas.xlsx";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Registrar Salida");
            System.out.println("2. Registrar Regreso");
            System.out.println("3. Salir");
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
                    System.out.println("Saliendo de la aplicación. ¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private static void registrarSalida() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = obtenerHoja(workbook, "salidas");

            System.out.println("Registros actuales de salidas:");
            imprimirRegistros(sheet);

            int lastRowNum = sheet.getLastRowNum();

            Row newRow = sheet.createRow(lastRowNum + 1);
            newRow.createCell(0).setCellValue(lastRowNum + 1);

            Scanner scanner = new Scanner(System.in);

            System.out.print("Ingrese quién: ");
            newRow.createCell(1).setCellValue(scanner.nextLine());

            System.out.print("Ingrese herramienta/material: ");
            newRow.createCell(2).setCellValue(scanner.nextLine());

            System.out.print("Ingrese cantidad (si es material): ");
            newRow.createCell(3).setCellValue(scanner.nextInt());

            try (FileOutputStream fileOut = new FileOutputStream(NOMBRE_ARCHIVO)) {
                workbook.write(fileOut);
                System.out.println("Salida registrada correctamente.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void registrarRegreso() {
        try (FileInputStream fileIn = new FileInputStream(NOMBRE_ARCHIVO);
             Workbook workbook = new XSSFWorkbook(fileIn)) {

            Sheet sheet = obtenerHoja(workbook, "salidas");

            System.out.println("Registros actuales de salidas:");
            imprimirRegistros(sheet);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el ID de la salida a la que desea registrar el regreso: ");
            int idRegreso = scanner.nextInt();

            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                if (row.getCell(0).getNumericCellValue() == idRegreso) {
                    sheet.removeRow(row);
                    System.out.println("Regreso registrado correctamente.");
                    break;
                }
            }

            // Reorganizar los ID después de eliminar una fila
            reorganizarIDs(sheet);

            try (FileOutputStream fileOut = new FileOutputStream(NOMBRE_ARCHIVO)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void imprimirRegistros(Sheet sheet) {
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            System.out.println("ID: " + (int) row.getCell(0).getNumericCellValue() +
                    ", Quién: " + row.getCell(1).getStringCellValue() +
                    ", Herramienta/Material: " + row.getCell(2).getStringCellValue() +
                    ", Cantidad: " + (int) row.getCell(3).getNumericCellValue());
        }
    }

    private static void reorganizarIDs(Sheet sheet) {
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            row.getCell(0).setCellValue(i);
        }
    }

    private static Sheet obtenerHoja(Workbook workbook, String nombreHoja) {
        Sheet sheet = workbook.getSheet(nombreHoja);
        if (sheet == null) {
            sheet = workbook.createSheet(nombreHoja);
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Quién");
            headerRow.createCell(2).setCellValue("Herramienta/Material");
            headerRow.createCell(3).setCellValue("Cantidad");
        }
        return sheet;
    }
}
