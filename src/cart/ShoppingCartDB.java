package cart;

import java.io.*;
import java.util.*;

// manage the database
// should contain methods like load a shopping cart, save a shopping cart, etc
public class ShoppingCartDB {

    private String cartDirectory;
    private String currentUser;
    private List<String> cartItems = new LinkedList<>();

    // constructor to initialise the database directory
    public ShoppingCartDB(String cartDirectory) {
        this.cartDirectory = cartDirectory;
    }

    // login method to load user data
    public void login(String username, List<String> cartItems) throws FileNotFoundException, IOException {
        this.currentUser = username;
        File userFile = new File(cartDirectory + File.separator + username + ".db");

        // check if file exists
        if (!userFile.exists()) {
            // create database file if it does not exist
            userFile.createNewFile();
        }

        // if the file is empty
        if (userFile.length() == 0) {
            System.out.printf("%s, your cart is empty\n", username);
        } else {
            System.out.println(currentUser + ", your cart contains the following items");

            // load existing cart items from file
            FileReader reader = new FileReader(userFile);
            BufferedReader br = new BufferedReader(reader);

            // read line
            int index = 1;
            String line;
            while ((line = br.readLine()) != null) {
                cartItems.add(line);
                System.out.printf("%d. %s\n", index, line);
                index += 1;
            }
            reader.close();
        }
    }

    // save shopping cart to user's file
    public void saveFile(List<String> cartItems) throws IOException {

        // no particular user
        if (currentUser == null) {
            System.out.println("Please login before saving.");
            return;
        }

        // define the file path for the user's cart
        String file = cartDirectory + File.separator + currentUser + ".db";
        File userFile = new File(file);

        // open file for writing
        FileWriter writer = new FileWriter(userFile);
        BufferedWriter bw = new BufferedWriter(writer);

        // write the cart items into the file, one per line
        for (String s : cartItems) {
            bw.write(s);
            bw.newLine();
        }

        System.out.println("Your cart has been saved");
        bw.flush();
        bw.close();
        writer.close();
    }

    // list all users
    public void userList(String cartDirectory) {
        String currDirectory = System.getProperty("user.dir");
        String directoryPath = currDirectory + File.separator + cartDirectory;

        // create directory
        File directory = new File(directoryPath);
        File[] list = directory.listFiles();

        // no files in directory
        if (list == null || list.length == 0) {
            System.out.println("No users found.");
        } else{
            System.out.println("The following users are registered");
            int index = 1;
            // loop through each file
            for (File file : list) {
                // list the users
                String user = file.getName().replace(".db", "");
                System.out.printf("%d. %s\n", index, user);
                index += 1;
            }
        }
    }

}
