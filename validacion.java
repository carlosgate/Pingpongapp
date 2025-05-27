package Pingpongapp;

import java.sql.Types;
import java.util.Scanner;

public class validacion {

    public static String pedirYValidar(Scanner sc, String columnName, int columnType) {
        String input;

        while (true) {
            try {
                System.out.print(columnName + ": ");
                input = sc.nextLine().trim();

                switch (columnType) {
                    case Types.INTEGER:
                        Integer.parseInt(input); // validación
                        return input;

                    case Types.VARCHAR:
                        if (input.isEmpty()) {
                            throw new IllegalArgumentException("El campo no puede estar vacío.");
                        }
                        if(input.matches("\\d+")) {throw new IllegalArgumentException("El campo no puede ser númerico.");}
                        return input;

                    // Si agregás más tipos:
                    // case Types.DOUBLE:
                    //     Double.parseDouble(input);
                    //     return input;

                    default:
                        return input; // Si no se reconoce el tipo, se devuelve igual
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Se esperaba un número entero.");
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }
}
