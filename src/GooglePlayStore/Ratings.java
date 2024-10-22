package GooglePlayStore;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Ratings {

    public static void main(String[] args)
     throws FileNotFoundException, IOException{
        File file = new File(args[0]);

        // reading the contents of the csv file
        BufferedReader reader = new BufferedReader(new FileReader(file));

        // create a list to store the csv
        // and to store each row
        List<App> csv = new ArrayList<>();

        // skip the header
        reader.readLine();

        while (true){
            String line = reader.readLine();
            if (line == null){
                break;
            }
            // split with comma as the delimiter
            // but ensures the commas inside double quotes are not treated as delimiters
            String[] row = line.split(",(?=([^\"]|\"[^\"]*\")*$)");
            String appName = row[0];
            String category = row[1];
            if (!row[2].equals("NaN")){
                double rating = Double.parseDouble(row[2]);
                csv.add(new App(appName, category, rating));
            }

        }

        // process the data: group by category
        // group App objects by category
        Map<String, List<App>> appsByCategory = csv.stream().collect(Collectors.groupingBy(App :: getCategory));

        // {
        // "Games": [App{name, category, ratings}, App{name, category, ratings}],
        // "Education": [App{name, category, ratings}]
        // }

        //display results for each category
        for (Map.Entry<String, List<App>> entry : appsByCategory.entrySet()){
            String category = entry.getKey();
            List<App> categoryApps = entry.getValue();

            // print the result
            // For every category, display the following: 
            // Category name, Highest rated app name and rating, Lowest rated app name and rating, Average rating for the category

            // category name
            System.out.printf("Category: %s\n", category);

            // highest rated app name and rating
            App highest = Collections.max(categoryApps, Comparator.comparingDouble(App :: getRating));
            System.out.printf("The highest rated app is %s with rating %f.\n", highest.getName(), highest.getRating());

            // lowest rated app name and rating
            App lowest = Collections.min(categoryApps, Comparator.comparingDouble(App :: getRating));
            System.out.printf("The lowest rated app is %s with rating %f.\n", lowest.getName(), lowest.getRating());

            // average rating
            double totalRating = 0.0;
            int numOfApps = 0;
            for (App app : categoryApps){
                totalRating += app.getRating();
                numOfApps += 1;
            }
            double averageRating = totalRating/(double)numOfApps;
            System.out.printf("The average rating is %2f.\n", averageRating);
            System.out.printf("\n");

        }


    
        
    }
}
    
