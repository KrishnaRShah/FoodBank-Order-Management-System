/**
* @author Ryan Mailhiot 30080009<a
* href="mailto:ryan.mailhiot@ucalgary.ca ">ryan.mailhiot@ucalgary.ca</a>
* @version 1.0 
* @since 0.0 (1.0 is first working version)

THIS IS A TEST CLASS TO SEE IF THE THINGS IN THIS CLASS ACTUALLY WORKED. IT IS OTHERWISE
UNUSED. ONLY THE FUNCTIONALITY IS USED.
*/

package FPCode;

import java.sql.*;
public class SQLTest{
    private final String DBURL = "jdbc:mysql://localhost/food_inventory";
    private final String USERNAME = "student";
    private final String PASSWORD = "ensf"; 
    private Connection dbConnect;

    public static void main(String[] args) {
        SQLTest test = new SQLTest();
        test.refreshDatabase();
    }

    public void initializeConnection(){
        try {
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
    public void refreshDatabase(){
        try {
            initializeConnection();
            Statement myStmt = dbConnect.createStatement();
    
            @SuppressWarnings("unused")
            boolean temp = myStmt.execute("DROP TABLE IF EXISTS available_food;");
    
            Statement stmt2 = dbConnect.createStatement();
    
            @SuppressWarnings("unused")
            boolean temp2 = stmt2.execute("CREATE TABLE available_food(" + 
            "ItemID int not null AUTO_INCREMENT, " + 
            "Name varchar(50), " + 
            "GrainContent int, " + 
            "FVContent int, " + 
            "ProContent int, " + 
            "Other int, " + 
            "Calories int, " + 
            "primary key (ItemID));");
            myStmt.close();
            stmt2.close();
    
            String query = "INSERT INTO available_food (Name, GrainContent, FVContent, ProContent, Other, Calories) VALUES (?,?,?,?,?,?)";
            PreparedStatement stmt3 = dbConnect.prepareStatement(query);
    
            Items[] stock = getDatabaseTestStock();
    
            for (int i = 0; i < stock.length; i++) {
                stmt3.setString(1, stock[i].getItemName());
                stmt3.setInt(2, (int)stock[i].getNutrientData().getGrains());
                stmt3.setInt(3, (int)stock[i].getNutrientData().getFruits());
                stmt3.setInt(4, (int)stock[i].getNutrientData().getProtein());
                stmt3.setInt(5, (int)stock[i].getNutrientData().getOther());
                stmt3.setInt(6, (int)stock[i].getNutrientData().getTotalCalories());
                @SuppressWarnings("unused")
                int rowCount = stmt3.executeUpdate();
                stmt3.clearParameters();
            }
            stmt3.close();
    
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private Items[] getDatabaseTestStock(){
        double[] tomato = {0, 80, 10, 10, 120};
        double[] apple = {0, 100, 0, 0, 624};
        double[] cheese = {0, 0, 23, 77, 1851};
        double[] bananas = {0, 100, 0, 0, 1740};
        double[] bread = {89, 0, 11, 0, 1904};
        double[] beef = {0, 0, 74, 26, 1179};
        double[] pork = {0, 0, 83, 17, 1193};
        double[] granola = {71, 0, 6, 23, 936};
        double[] carrots = {0, 100, 0, 0, 159};
        double[] broccoli = {0, 92, 8, 0, 621};
        double[] whiteBread = {96, 0, 4, 0, 2192};
        double[] orange = {0, 100, 0, 0, 864};
        double[] eggs = {0, 0, 9, 91, 864};
        double[] chicken = {0, 0, 70, 30, 730};
        double[] pastaPound = {75, 0, 13, 12, 1683};
        double[] cornTort = {94, 0, 6, 0, 989};
        double[] pastaTwoPound = {75, 0, 13, 12, 2366};
        double[] cauli = {0, 100, 0, 0, 420};
        double[] strawberries = {0, 100, 0, 0, 640};
        double[] trailMix = {21, 0, 20, 59, 6000};
        double[] soy = {0, 0, 88, 12, 3350};
    
        Items[] testA = new Items[21];
    
        testA[0] = new Items(1, "Tomato Sauce, jar", tomato);
        testA[1] = new Items(2, "Apple, dozen", apple);
        testA[2] = new Items(3, "Granola Bar, box", granola);
        testA[3] = new Items(4, "Baby carrots, pound", carrots);
        testA[4] = new Items(5, "Broccoli, 3 bunches", broccoli);
        testA[5] = new Items(6, "Wheat bread, loaf", whiteBread);
        testA[6] = new Items(7, "Orange, dozen", orange);
        testA[7] = new Items(8, "Eggs, dozen", eggs);
        testA[8] = new Items(9, "Chicken breast, pound", chicken);
        testA[9] = new Items(10, "Pasta, dry, pound", pastaPound);
        testA[10] = new Items(11, "Cheddar cheese, pound", cheese);
        testA[11] = new Items(12, "Bananas, bunch", bananas);
        testA[12] = new Items(13, "Whole grain bread, loaf", bread);
        testA[13] = new Items(14, "Ground beef, pound", beef);
        testA[14] = new Items(15, "Corn tortillas, pound", cornTort);
        testA[15] = new Items(16, "Pasta, dry, two pounds", pastaTwoPound);
        testA[16] = new Items(17, "Ground pork, pound", pork);
        testA[17] = new Items(18, "Cauliflower, 2 heads", cauli);
        testA[18] = new Items(19, "Strawberries, 2 kg", strawberries);
        testA[19] = new Items(20, "Trail mix, 1 kg", trailMix);
        testA[20] = new Items(21, "Soy protein, 1 kg", soy);
    
        return testA;
    }
    
    
}



