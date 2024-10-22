package cart;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

// implement a simple text file database

public class cartCopy {

    // method to create a new directory
    public void createDirectory(String directoryName) {

        // address of current directory
        String currDirectory = System.getProperty("user.dir");

        // specify the path of the directory to be created
        String directoryPath = currDirectory + File.separator + directoryName;

        // create a file object representing the directory
        File directory = new File(directoryPath);

        // check if directory exists
        if (!directory.exists()) {
            // attempt to create the directory if it does not exist
            directory.mkdir();
        } else
            System.out.println("Directory already exists.");
    }

    // default directory
    private static String cartDirectory = "db";
    private static ShoppingCartDB shoppingCartDB;
    private static List<String> cartItems = new LinkedList<>();
    private static String currentUser;

    public static void main(String[] args) throws IOException {
        // user can specify a directory to store their shopping cart
        // default directory is db
        if (args.length > 0) {
            cartDirectory = args[0];
        }

        cartCopy cart = new cartCopy();

        // create a new directory
        cart.createDirectory(cartDirectory);
        // initialise the shopping cart DB
        shoppingCartDB = new ShoppingCartDB(cartDirectory);

        System.out.println("Welcome to your shopping cart");

        boolean exit = false;

        Console console = System.console();

        while (!exit) {

            String cmd = console.readLine("> ");

            cmd = cmd.replaceAll("\\p{Punct}", "").toLowerCase();

            String[] command = cmd.trim().split(" ");

            switch (command[0]) {

                case "login":
                    if (command.length > 1) {
                        cartItems = new LinkedList<>();
                        String username = command[1];
                        shoppingCartDB.login(username, cartItems);
                        currentUser = username;

                    } else {
                        System.out.println("Please provide a username.");
                    }
                    break;

                case "save":
                    shoppingCartDB.saveFile(cartItems);
                    break;

                case "users":
                    shoppingCartDB.userList(cartDirectory);
                    break;

                case "list": // cmd.equals("list")
                    if (currentUser == null){
                        System.out.println("Please login first.");
                    } else{
                        
                        if (cartItems.size() != 0) {
                            for (int i = 0; i < cartItems.size(); i += 1) {
                                int index = i + 1;
                                System.out.printf("%d. %s\n", index, cartItems.get(i));
                            }
                        } else {
                            System.out.println("Your cart is empty\n");
                        }
                    }
                    break;

                case "add": // cmd.equals("add")
                    if (currentUser == null){
                        System.out.println("Please login first.");
                    } else{

                        for (int i = 1; i < command.length; i += 1) {
                            if (cartItems.contains(command[i])) {
                                System.out.printf("You have %s in your cart\n", command[i]);
                            } else {
                                cartItems.add(command[i]);
                                System.out.printf("%s added to cart\n", command[i]);
                            }
                        }
                    }
                    break;

                case "delete": // cmd.equals("delete")
                    int deleteIndex = Integer.parseInt(command[1]) - 1;
                    if (deleteIndex < cartItems.size() && deleteIndex >= 0) {
                        System.out.printf("%s removed from cart\n", cartItems.get(deleteIndex));
                        cartItems.remove(deleteIndex);
                    } else {
                        System.out.println("Incorrect item index\n");
                    }
                    break;

                default:
                    System.out.println("Invalid command. Please enter again.\n");
                    break;

                case "exit":
                    // exit your shopping cart
                    System.out.println("You have exited your shopping cart\n");
                    exit = true;
                    break;
            }
        }

    }
}
