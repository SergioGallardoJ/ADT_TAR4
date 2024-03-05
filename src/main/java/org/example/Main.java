package org.example;

import org.example.model.Item;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ItemService  itemService = new ItemService();

    public static void main(String[] args){

        seed.seed();

        System.out.print("////////////////////\nMenú:\n////////////////////\n");
        System.out.print("1. Registrar un nuevo item. \n2. Buscar por id.\n3. Buscar por nombre.\n4. Actualizar item.\n5. Borrar item.\n6. Asignar categoría.\n7. Salir.\n////////////////////\n");
        boolean salir = false;
        Scanner sc = new Scanner(System.in);
        int opcion;

        do{
            System.out.println("Introduce un número del 1 al 7 en función de que seleción del menú quiere realizar:");
            opcion = sc.nextInt();
            while (opcion < 1 || opcion > 7) {
                System.err.println("Error al seleccionar la opción del menú.");
                System.out.println("Introduce un número del 1 al 7 en función de que seleción del menú quiere realizar:");
                opcion = sc.nextInt();
            }
            switch (opcion){
                case 1: //createItem: Permite crear un nuevo item introduciendo los datos por teclado

                    createItem(sc);
                    break;
                case 2: //readItem: Permite obtener un artículo por id. Devuelve un string con los campos name y description, o la cadena “No se ha encontrado el item”
                    readItem(sc);
                    break;
                case 3: //searchItem: Permite obtener un artículo por nombre. Devuelve un string con los campos name y description, o la cadena “No se ha encontrado el item”
                    searchItem(sc);
                    break;
                case 4: //updateItem: Permite la modificación de un item existente. Recibe como parámetro el id del artículo.
                        // Pregunta al usuario por los nuevos datos del item (no se puede modificar el id, solo el nombre y la descripción).
                        //Si el usuario deja algún campo en blanco, quiere decir que no quiere realizar cambios. Devuelve la cadena con el estado final del item
                    updateItem(sc);
                    break;
                case 5: //deleteItem: Permite eliminar un item existente. Recibe como parámetro el id del artículo.
                        //Devuelve la cadena “Item eliminado correctamente” o “Problema al eliminar el item”.
                    deleteItem(sc);
                    break;
                case 6: //assignCategory: Permite asignar una categoría al item. Devuelve la cadena “Categoría asignada correctamente” o “No se ha podido asignar la categoría”.
                    assignCategory(sc);
                    break;
                case 7:
                    salir = true;
                    break;

            }
        } while (!salir);
    }
    private static void createItem(Scanner scanner) {
        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            itemService.create(name, description);

            System.out.println("Item created successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readItem(Scanner scanner) {
        try {
            System.out.print("Enter id: ");
            int id = scanner.nextInt();

            Item item = itemService.get(id);

            if (item != null) {
                System.out.println(item);
            } else {
                System.out.println("Item not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void searchItem(Scanner scanner) {
        try {
            System.out.print("Enter name to search: ");
            String name = scanner.nextLine();

            List<Item> items = itemService.search(name);

            if (items.isEmpty()) {
                System.out.println("No items found with that name");
            } else {
                System.out.println("Found items:");
                for (Item item : items) {
                    System.out.println(item);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateItem(Scanner scanner) {
        try {
            System.out.print("Enter item id: ");
            int id = scanner.nextInt();

            System.out.print("Enter new name (leave empty to not change): ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter new description (leave empty to not change): ");
            String description = scanner.nextLine().trim();

            itemService.update(id, name, description);

            System.out.println("Item updated successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteItem(Scanner scanner) {
        try {
            System.out.print("Enter item id: ");
            int id = scanner.nextInt();

            itemService.delete(id);

            System.out.println("Item deleted successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void assignCategory(Scanner scanner) {
        try {
            System.out.print("Enter item id: ");
            int itemId = scanner.nextInt();

            System.out.print("Enter categories (comma separated): ");
            String categoriesInput = scanner.nextLine();

            String[] categoryNames = categoriesInput.split(",");

            itemService.setCategories(itemId, categoryNames);

            System.out.println("Categories assigned successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
