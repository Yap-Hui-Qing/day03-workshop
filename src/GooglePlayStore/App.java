package GooglePlayStore;

public class App {
    // store the app information
    String name;
    public String getName() {
        return name;
    }

    String category;
    public String getCategory() {
        return category;
    }

    double rating;

    public double getRating() {
        return rating;
    }

    // constructor
    public App(String name, String category, double rating){
        this.name = name;
        this.category = category;
        this.rating = rating;
    }
}    

