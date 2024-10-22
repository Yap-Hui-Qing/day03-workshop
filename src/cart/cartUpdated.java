package cart;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import javax.swing.border.LineBorder;

// implement a simple text file database

public class cartUpdated {

    public static void menu(){
        System.out.println("Welcome to your shopping cart");
        
        System.out.println("Login to your account: type 'login y/n'");
        System.out.println("Save the contents of your cart: type 'save'");
        System.out.println("List all users: type 'users'");

    }

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
            System.out.println("Directory already exists.\n");
    }

    // default directory
    private static String cartDirectory = "db";
    private static ShoppingCartDB shoppingCartDB;
    private static String currentUser;
    private static List<String> cartItems = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        // user can specify a directory
        // otherwise default directory is 'db'
        if (args.length > 0) {
            cartDirectory = args[0];
        }

        cartUpdated cart = new cartUpdated();

        // create a new directory
        cart.createDirectory(cartDirectory);
        // initialise the shopping cart DB
        shoppingCartDB = new ShoppingCartDB(cartDirectory);

        boolean exit = false;

        Console console = System.console();

        while (!exit) {

            menu();

            String cmd = console.readLine("> ");

            cmd = cmd.replaceAll("\\p{Punct}", "").toLowerCase();

            String[] command = cmd.trim().split(" ");

            switch (command[0]) {

                case "login":
                    if (command.length > 1) {
                        cartItems = new LinkedList<>();
                        String username = command[1];
                        currentUser = username;
                        shoppingCartDB.login(currentUser, cartItems);
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

            }
        }

    }
}
