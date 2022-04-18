/**
 * @author Krishna Shah 30114067<a
 * href="mailto:krishna.shah@ucalgary.ca">krishna.shah@ucalgary.ca</a>
 * @author Danny Picazo 301271082<a
 * href="mailto:daniel.picazo@ucalgary">daniel.picazo@ucalgary.ca</a>
 * @version 1.5 
 * @since 0.0
 */

package FPCode;

import org.junit.*;
import static org.junit.Assert.*;

import java.sql.*;
// import java.util.Vector;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Krishna Shah 30114067 <a
 * href="mailto:krishna.shah@ucalgary.ca">krishna.shah@ucalgary.ca</a>
 * @author Danny Picazo 301271082<a
 * href="mailto:daniel.picazo@ucalgary">daniel.picazo@ucalgary.ca</a>
 * This class is designed to test the Order Class, it will test the Order Constructor
 * as well it will test all the getters and setters required for the Order class
 */
public class OrderTest {
    private final String DBURL = "jdbc:mysql://localhost/food_inventory";
    private final String USERNAME = "student";
    private final String PASSWORD = "ensf";
    private Connection dbConnect;


    /**
     * Test for constructor
     * First creates an arrayList of int[] and then adds a test int[] into the arrayList
     * In the try-catch, the Order constructor is called with the test arrayList
     * and will catch a IllegalArgumentException
     * @since 0.1
     */
    @Test
    public void testOrderConstructor() throws NotEnoughFoodException{
        boolean exceptionThrown = false;


        ArrayList<int[]> testArrayList = new ArrayList<int[]>();
        int[] testArray = {1,2};
        testArrayList.add(testArray);
        ArrayList<Integer> testDays = new ArrayList<Integer>();
        testDays.add(1);

        try{
            @SuppressWarnings("unused")
            Order testOrder = new Order(testArrayList, testDays);
            refreshDatabase();
        }
        catch(IllegalArgumentException e){
            exceptionThrown = true;
        }

        assertFalse("An exception was thrown when attempting to create a Client.", exceptionThrown);

        refreshDatabase();
    }

    /**
     * Tests getter function to get a certain Hamper in the List of Hampers
     * First creates an arrayList of int[] and then adds a test int[] into the arrayList, and calls the Order constructor
     * Creates the correctHamper(what the getter should grab)
     * Creates a testHamper that will be used to hold the getter return value
     * Calls getHamperAtIndex, and will then compare return values to corectHamper to see if correct Hamper
     * is recieved
     * and will catch a IllegalArgumentException
     * @since 0.1
     */
    
    @Test
    public void testGetHamperAtIndex() throws NotEnoughFoodException{

        //creates an arrayList for order constructor
        ArrayList<int[]> testArrayList = new ArrayList<int[]>(); 
        int[] testArray = {1,2};
        testArrayList.add(testArray);
        ArrayList<Integer> testDays = new ArrayList<Integer>();
        testDays.add(1);
        Order testOrder = new Order(testArrayList, testDays);
        refreshDatabase();
    

        int[] correctHamperValues = {1,2};                  //Creates correctHamper
        Hamper correctHamper = new Hamper(correctHamperValues, 1);    //Creates hamper
        refreshDatabase();
        int[] testHamperValues = {1,2,1};
        Hamper testHamper = new Hamper(testHamperValues, 1);       //creates test hampper
        refreshDatabase();
        
        testHamper = testOrder.getHampterAtIndex(0);
        

        assertEquals("Hamper did not return the correct value.", testHamper.clientArray.get(1).getClientType(), correctHamper.clientArray.get(1).getClientType());

        refreshDatabase();
    }
    /**
     * Tests getter function to get the List of Hampers
     * First creates an arrayList of int[] and then adds a test int[] into the arrayList, and calls the Order constructor
     * Creates the correctHamperList(what the getter should grab)
     * Creates a testHamperList that will be used to hold the getter return value
     * Calls getHamper, and will then compare return values to correctHamperList to see if correct List of Hampers
     * is recieved
     * and will catch a IllegalArgumentException
     * @since 0.1
     */

    @Test
    public void testGetHamperList() throws NotEnoughFoodException{
        
        ArrayList<int[]> testArrayList = new ArrayList<int[]>(); 
        int[] testArray = {1,2};
        testArrayList.add(testArray);
        ArrayList<Integer> testDays = new ArrayList<Integer>();
        testDays.add(1);
        Order testOrder = new Order(testArrayList, testDays);
        refreshDatabase();

        List<Hamper> correctHamperList = new ArrayList<Hamper>();    //Creates hamperList
        int[] correctClientTypes = {1,2};                  //Creates clientTypes
        Hamper correctHamper = new Hamper(correctClientTypes, 1);    //Creates hamper
        refreshDatabase();
        correctHamperList.add(correctHamper);                     //Adds hamper to hamperList

        List<Hamper> testHamperList = new ArrayList<>();    //Creates testhamperList

        testHamperList = testOrder.getHamper();

        assertEquals("Hamper did not return the correct value.", testHamperList.get(0).itemsList.get(0).getItemName(),
                                                                        correctHamperList.get(0).itemsList.get(0).getItemName());
        refreshDatabase();
    }

    /**
     * Tests method to add Hamper to exisiting arrayList
     * First creates an arrayList of int[] and then adds an empty test int[] into the arrayList, 
     * and calls the Order constructor
     * Creates the correctHamper and uses the addHamper method to add to testOrder
     * Calls getHamperAtIndex if the Hamper that was just added matches the correct Hamper
d
     * and will catch a IllegalArgumentException
     * @since 0.1
     */

    @Test
    public void testAddHamper() throws NotEnoughFoodException{

        ArrayList<int[]> testArrayList = new ArrayList<int[]>(); 
        int[] testArray = {1};
        testArrayList.add(testArray);
        ArrayList<Integer> testDays = new ArrayList<Integer>();
        testDays.add(1);
        Order testOrder = new Order(testArrayList, testDays);
        refreshDatabase();

        int[] correctClientTypes = {1,2};                 
        Hamper correctHamper = new Hamper(correctClientTypes, 1);   
        refreshDatabase();
        
        testOrder.addHamper(correctHamper);

        assertEquals("Hamper did not return the correct value.", testOrder.getHampterAtIndex(1), correctHamper);
        refreshDatabase();

    }

    // The following tests will come from the interface Output.
    @Test 
    public void testFormatString(){
        // This will compare the a test formatted string to the result and see if they match. 
    }

    @Test 
    public void testPrintToTXT(){
        // this will test the method and make sure it functions, then it will test to see if a file was created, and if it is empty.
    }

    @Test 
    public void testPrintError(){
        // this will identify a "print error" thrown by hamper into order. This should not throw the error with good data.
    }
    
    @Test 
    public void testPrintErrorBadData(){
        // this will identify a "print error" thrown by hamper into order. This should throw the error. 
    }



    /**
     * Helper methods from HamperTest, used here to refresh the database when an Order is created as to not run out of food during testing. 
     * Initializes a connection the small test inventory database. 
     */
    public void initializeConnection(){
        try {
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
    /**
     * HamperTest helper method.
     * Refreshes the test database so that it will not run out of food before testing is finished. 
     */
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
    /**
     * HamperTest helper method.
     * Returns the small inventory test database stock. 
     */
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
