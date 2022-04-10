/**
* @author Ryan Mailhiot 30080009<a
* href="mailto:ryan.mailhiot@ucalgary.ca ">ryan.mailhiot@ucalgary.ca</a>
* @version 0.9 
* @since 0.0
*/

package FPCode;

import java.sql.*;
import java.util.Vector;

/**
 * @author Ryan Mailhiot 30080009 <a
 * href="mailto:ryan.mailhiot@ucalgary.ca">ryan.mailhiot@ucalgary.ca</a>
 * This class is designed to interact with the database and create a "Local" vector of databaseItems to access at once. Contains methods to
 * create a connection to the database, refresh the databaseItems locally, update the database with items to remove, as well as several
 * search functions for items in the database. 
 */
public class DatabaseItems {
    private final int SIZE_VECTOR = 20;
    private final int INCREMENT_VECTOR = 10;
    private final String DBURL = "jdbc:mysql://localhost/food_inventory";
    private final String USERNAME = "student";
    private final String PASSWORD = "ensf"; 
    private static Vector<Items> databaseItems;
    private Connection dbConnect;

    /**
     * Constructor which creates the databaseItems. Uses a base size of 20 and an increment value of 10. Will auto-refresh the database so
     * there will be items in the vector after the constructor is called. 
     * @since 0.1
     */
    protected DatabaseItems(){
        databaseItems = new Vector<Items>(SIZE_VECTOR, INCREMENT_VECTOR);
        refreshDatabaseItems();
    }

    /**
     * Creates a connection to the SQL database using the URL, USERNAME and PASSWORD. 
     * @since 0.1
     */
    public void initializeConnection(){
        try {
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace(); // THIS NEEDS TO BE CHANGED BECAUSE WE NEED TO BE ABLE TO INTERNALLY HANDLE ERRORS
        }
    }

    /**
     * This will grab all items from the database and store it in databaseItems. 
     * @return null
     * @since 0.3
     */
    public void refreshDatabaseItems(){
        // Call to database to return all from the given inventory
        initializeConnection();
        databaseItems.clear();
        Items itemAdd;
        double[] nutrientsInfo = new double[5];
        try {
            String query = "SELECT * FROM available_food";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            ResultSet results = myStmt.executeQuery();

            while (results.next()) {
                nutrientsInfo[0] = (double)results.getInt("GrainContent");
                nutrientsInfo[1] = (double)results.getInt("FVContent");
                nutrientsInfo[2] = (double)results.getInt("ProContent");
                nutrientsInfo[3] = (double)results.getInt("Other");
                nutrientsInfo[4] = (double)results.getInt("Calories");
                itemAdd = new Items(results.getInt("ItemID"), results.getString("Name"), nutrientsInfo);
                databaseItems.add(itemAdd);
            }
            myStmt.close();
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseItems.trimToSize();

    }
    /**
     * This updates the SQL Database by deleting the items in the items array from the database. This will auto-refresh the "on hand" database items
     * @return null
     * @param items is an array of items that will be removed from the database.
     * @since 0.3
     */
    public void updateDatabase(Items[] items){
        // List of items to remove from data base.
        initializeConnection();
        try {
            String query = "DELETE FROM available_food WHERE ItemID = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            for (int i = 0; i < items.length; i++) {
                myStmt.setInt(1, items[i].getItemID());

                int updateCheck = myStmt.executeUpdate();
                if (updateCheck != 1) {
                    throw new IllegalArgumentException();
                } else {
                    updateCheck = 0;
                }
                myStmt.clearParameters();
            }
            myStmt.close();
            dbConnect.close();
        } catch (IllegalArgumentException e) {
            System.out.println("Line does not exist");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        refreshDatabaseItems();
    }

    /**
     * Returns an object of type "Items" in which the item contains the most calories from a given "type" that gets input.
     * @param type Is either grains, fruits, protein or other. Selects the category in which you are trying to find the item with the most
     * calories from.
     * @return Items object
     * @throws IllegalArgumentException if the string type is not one of the 4 listed options. 
     * @throws NoItemExistsException if there is no item in databaseItems that is under calories for a given type.
     * @since 0.5
     */
    public Items getLargestItem(String type) throws IllegalArgumentException, NoItemExistsException { // THIS WILL BE COMPLETED ONCE NUTRIENT CLASS IS MADE
        String selection = type.trim().toLowerCase();
        double[] zeroed = {0.0, 0.0, 0.0, 0.0, 0.0};
        Items returnItem = new Items(-1, "Remove Item", zeroed);
        Items testItem;

        switch (selection) {
            case "grains":
                for (int i = 0; i < databaseItems.size(); i++) {
                    try {
                        testItem = databaseItems.get(i);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return returnItem;
                    }
                    if (testItem.getNutrientData().getGrainCals() > returnItem.getNutrientData().getGrainCals()) {
                        returnItem = testItem;
                    }
                }
                if (returnItem.getItemID() == -1) {
                    throw new NoItemExistsException();
                }
                return returnItem;

            case "fruits":
                for (int i = 0; i < databaseItems.size(); i++) {
                    try {
                        testItem = databaseItems.get(i);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return returnItem;
                    }
                    if (testItem.getNutrientData().getFruitCals() > returnItem.getNutrientData().getFruitCals()) {
                        returnItem = testItem;
                    }
                }
                if (returnItem.getItemID() == -1) {
                    throw new NoItemExistsException();
                }
                return returnItem;
        
            case "protein":
                for (int i = 0; i < databaseItems.size(); i++) {
                    try {
                        testItem = databaseItems.get(i);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return returnItem;
                    }
                    if (testItem.getNutrientData().getProteinCals() > returnItem.getNutrientData().getProteinCals()) {
                        returnItem = testItem;
                    }
                }
                if (returnItem.getItemID() == -1) {
                    throw new NoItemExistsException();
                }
                return returnItem;
        
            case "other":
                for (int i = 0; i < databaseItems.size(); i++) {
                    try {
                        testItem = databaseItems.get(i);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return returnItem;
                    }
                    if (testItem.getNutrientData().getOtherCals() > returnItem.getNutrientData().getOtherCals()) {
                        returnItem = testItem;
                    }
                }
                if (returnItem.getItemID() == -1) {
                    throw new NoItemExistsException();
                }
                return returnItem;
        
            default:
                throw new IllegalArgumentException("In getLargestItem, all case values failed to identify");
        }
    }
    
    /**
     * Returns an object of type "Items" which contains the most calories under a certain threshold "calories" from a given 
     * "type" of calories. 
     * @param type String of either grains, fruits, protein or other.
     * @param calories Int in which the number of calories from a given type will be under. Can be 0 but will not function in that case.
     * @return Items object
     * @throws IllegalArgumentException if the input calories is a negative number, or the string type is not one of the 4 listed options. 
     * @throws NoItemExistsException if there is no item in databaseItems that is under calories for a given type.
     * @since 0.6
     */
    public Items getLargestItemUnder(String type, int calories) throws IllegalArgumentException, NoItemExistsException{
        if (calories < 0) {
            throw new IllegalArgumentException("in getLargestItemUnder, calories was a negative number");
        }
        String selection = type.trim().toLowerCase();
        double[] zeroed = {0.0, 0.0, 0.0, 0.0, 0.0};
        Items returnItem = new Items(-1, "Remove Item", zeroed);
        Items testItem;
        switch (selection) {
            case "grains":
                for (int i = 0; i < databaseItems.size(); i++) {
                    try {
                        testItem = databaseItems.get(i);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return returnItem;
                    }
                    if (testItem.getNutrientData().getGrainCals() < calories) {
                        if (testItem.getNutrientData().getGrainCals() > returnItem.getNutrientData().getGrainCals()) {
                            returnItem = testItem;
                        }
                    }
                }
                if (returnItem.getItemID() == -1) {
                    throw new NoItemExistsException();
                }

                return returnItem;

            case "fruits":
                for (int i = 0; i < databaseItems.size(); i++) {
                    try {
                        testItem = databaseItems.get(i);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return returnItem;
                    }
                    if (testItem.getNutrientData().getFruitCals() < calories) {
                        if (testItem.getNutrientData().getFruitCals() > returnItem.getNutrientData().getFruitCals()) {
                            returnItem = testItem;
                        }
                    }
                }
                if (returnItem.getItemID() == -1) {
                    throw new NoItemExistsException();
                }
                return returnItem;
        
            case "protein":
                for (int i = 0; i < databaseItems.size(); i++) {
                    try {
                        testItem = databaseItems.get(i);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return returnItem;
                    }
                    if (testItem.getNutrientData().getProteinCals() < calories) {
                        if (testItem.getNutrientData().getProteinCals() > returnItem.getNutrientData().getProteinCals()) {
                            returnItem = testItem;
                        }
                    }
                }
                if (returnItem.getItemID() == -1) {
                    throw new NoItemExistsException();
                }
                return returnItem;
        
            case "other":
                for (int i = 0; i < databaseItems.size(); i++) {
                    try {
                        testItem = databaseItems.get(i);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return returnItem;
                    }
                    if (testItem.getNutrientData().getOtherCals() < calories) {
                        if (testItem.getNutrientData().getOtherCals() > returnItem.getNutrientData().getOtherCals()) {
                            returnItem = testItem;
                        }
                    }
                }
                if (returnItem.getItemID() == -1) {
                    throw new NoItemExistsException();
                }
                return returnItem;
        
            default:
                throw new IllegalArgumentException("In getLargestItemUnder, all case values failed to identify");
        }
    }

    /**
     * Returns an object of type "Items" which contains the least amount of calories over a certain threshold "calories" from a given 
     * "type" of calories.
     * @param type String of either grains, fruits, protein or other.
     * @param calories Int in which the number of calories from a given type will be under. Can be 0 but will not function in that case.
     * @return Items object
     * @throws IllegalArgumentException if the input calories is a negative number, or the string type is not one of the 4 listed options. 
     * @throws NoItemExistsException if there is no item in databaseItems that is under calories for a given type.
     * @since 0.7
     */
    public Items getSmallestItemOver(String type, int calories) throws IllegalArgumentException, NoItemExistsException{
        if (calories < 0) {
            throw new IllegalArgumentException("in getLargestItemUnder, calories was a negative number");
        }
        String selection = type.trim().toLowerCase();
        double[] zeroed = {0.0, 0.0, 0.0, 0.0, 0.0};
        Items returnItem = new Items(-1, "Remove Item", zeroed);
        Items testItem;
        switch (selection) {
            case "grains":
                for (int i = 0; i < databaseItems.size(); i++) {
                    try {
                        testItem = databaseItems.get(i);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return returnItem;
                    }
                    if (testItem.getNutrientData().getGrainCals() > calories) {
                        if (testItem.getNutrientData().getGrainCals() < returnItem.getNutrientData().getGrainCals()) {
                            returnItem = testItem;
                        }
                    }
                }
                if (returnItem.getItemID() == -1) {
                    throw new NoItemExistsException();
                }

                return returnItem;

            case "fruits":
                for (int i = 0; i < databaseItems.size(); i++) {
                    try {
                        testItem = databaseItems.get(i);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return returnItem;
                    }
                    if (testItem.getNutrientData().getFruitCals() > calories) {
                        if (testItem.getNutrientData().getFruitCals() < returnItem.getNutrientData().getFruitCals()) {
                            returnItem = testItem;
                        }
                    }
                }
                if (returnItem.getItemID() == -1) {
                    throw new NoItemExistsException();
                }
                return returnItem;
        
            case "protein":
                for (int i = 0; i < databaseItems.size(); i++) {
                    try {
                        testItem = databaseItems.get(i);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return returnItem;
                    }
                    if (testItem.getNutrientData().getProteinCals() > calories) {
                        if (testItem.getNutrientData().getProteinCals() < returnItem.getNutrientData().getProteinCals()) {
                            returnItem = testItem;
                        }
                    }
                }
                if (returnItem.getItemID() == -1) {
                    throw new NoItemExistsException();
                }
                return returnItem;
        
            case "other":
                for (int i = 0; i < databaseItems.size(); i++) {
                    try {
                        testItem = databaseItems.get(i);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return returnItem;
                    }
                    if (testItem.getNutrientData().getOtherCals() > calories) {
                        if (testItem.getNutrientData().getOtherCals() < returnItem.getNutrientData().getOtherCals()) {
                            returnItem = testItem;
                        }
                    }
                }
                if (returnItem.getItemID() == -1) {
                    throw new NoItemExistsException();
                }
                return returnItem;
        
            default:
                throw new IllegalArgumentException("In getLargestItemUnder, all case values failed to identify");
        }
    }

    /**
     * Getter function to return the Database Items too an array. 
     * @return An array of the Items object
     * @since 0.4
     */
    public Items[] getDatabaseItems (){
        /*
        This method is extremely jank, but it works to return what we are looking for. Just using the .toArray function broke everything. 
        */
        databaseItems.trimToSize();
        Items[] temp = (Items[])databaseItems.toArray();
        Items[] dbItems = new Items[databaseItems.size()];
        for (int i = 0; i < dbItems.length; i++) {
            dbItems[i] = temp[i];
        }
        return dbItems;
    }

}


