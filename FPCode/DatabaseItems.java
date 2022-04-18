/**
* @author Ryan Mailhiot 30080009<a
* href="mailto:ryan.mailhiot@ucalgary.ca ">ryan.mailhiot@ucalgary.ca</a>
* @version 1.6 
* @since 0.0 (1.0 is first working version)
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
    private Vector<Items> databaseItems;
    private Connection dbConnect;

    /**
     * Constructor which creates the databaseItems. Uses a base size of 20 and an increment value of 10. Will auto-refresh the database so
     * there will be items in the vector after the constructor is called. 
     * @since 0.1
     */
    public DatabaseItems() throws FailedToConnectException{
        databaseItems = new Vector<Items>(SIZE_VECTOR, INCREMENT_VECTOR);
        refreshDatabaseItems();
    }

    /**
     * Creates a connection to the SQL database using the URL, USERNAME and PASSWORD. 
     * @since 0.1 updated 1.6
     */
    public void initializeConnection() throws FailedToConnectException{
        try {
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new FailedToConnectException("Failed to connect to the Database. Check DBURL, USERNAME, PASSWORD.");
        }
        if (dbConnect == null) {
            throw new FailedToConnectException("Failed to connect to the Database. Check DBURL, USERNAME, PASSWORD.");
        }
    }

    /**
     * This will grab all items from the database and store it in databaseItems. databaseItems is a vector of Items that get pulled from the database.
     * It will initialize connection using the initializeConnection method and then select everything from the database called "available_food," then
     * store all each row of the database as a new item and add it to the databaseItems vector.
     * @return null
     * @since 0.3
     */
    public void refreshDatabaseItems() throws FailedToConnectException{
        initializeConnection();
        databaseItems.clear(); // clear the vector so it can be rewritten. 
        Items itemAdd;
        double[] nutrientsInfo = new double[5];
        try {
            // Query information to the SQL database
            String query = "SELECT * FROM available_food";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            ResultSet results = myStmt.executeQuery();

            // This will add the items with their specific nutrients information to the databaseItems vector.
            while (results.next()) {
                nutrientsInfo[0] = (double)results.getInt("GrainContent");
                nutrientsInfo[1] = (double)results.getInt("FVContent");
                nutrientsInfo[2] = (double)results.getInt("ProContent");
                nutrientsInfo[3] = (double)results.getInt("Other");
                nutrientsInfo[4] = (double)results.getInt("Calories");
                try {
                    itemAdd = new Items(results.getInt("ItemID"), results.getString("Name"), nutrientsInfo);
                } catch (IllegalArgumentException e) {
                    itemAdd = null;
                }
                
                if (itemAdd != null) {
                    databaseItems.add(itemAdd);
                }
                
            }
            // Closes
            myStmt.close();
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            System.err.println("Unexcpected SQL exception thrown in refreshDatabaseItems in DatabaseItems. Read the SQL error code for more details");
            e.printStackTrace();
        }
        databaseItems.trimToSize();

    }
    /**
     * This updates the SQL Database by deleting the items in the items array from the database. This will auto-refresh the "on hand" database items
     * @param items is an array of items that will be removed from the database.
     * @throws IllegalArgumentException if the ID of the item does not exist in the database.
     * @since 0.3
     * Updated 1.4
     */
    public void updateDatabase(Items[] items) throws IllegalArgumentException, FailedToConnectException{
        initializeConnection();
        try {
            // Query information for deleting
            String query = "DELETE FROM available_food WHERE ItemID = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);

            // loops through the items array to remove each item using the ID. (ID is used because it is unique from the SQL database.)
            for (int i = 0; i < items.length; i++) {
                if (items[i] == null) {
                    System.err.println("Null entry in items. Skipping"); // Incase there are null elements.
                    continue;
                }
                // If the ID doesn't exist locally, then it doesnt exist in the database. SQL can deal with this no problem, but its more of an issue
                // where this will be called after a hamper is created. If we just made a hamper with a non existent food item... THATS A PROBLEM.
                if (!checkForItem(items[i].getItemID())) {
                    throw new IllegalArgumentException("Item is not in database"); 
                }

                myStmt.setInt(1, items[i].getItemID());

                int updateCheck = myStmt.executeUpdate();
                // Even if there is no delete, update check is 1. How ever if it is ever not 1, that means that either 2+ items had the same ID or some other
                // issue occured. Throwing an exception allows this to be caught and then it can be worked around.
                if (updateCheck != 1) {
                    throw new IllegalArgumentException("SQL Update was not 1. Input must have been faulty"); 
                } else {
                    updateCheck = 0;
                }
                myStmt.clearParameters();
            }
            myStmt.close();
            dbConnect.close();
        }   
        catch (SQLException e) {
            System.err.println("Unexcpected SQL exception thrown in updateDatabase in DatabaseItems. Read the SQL error code for more details");
            e.printStackTrace();
        }
        
        refreshDatabaseItems();
    }

    // Commenting out the logic parts because there was a different approach to calculating the algorithm. This was typed out and didn't want it to go to waste.
    // This is to show that the testing and methodology was there for a different type of algorithm, it was just scrapped late in the process. 

    // /**
    //  * Returns an object of type "Items" in which the item contains the most calories from a given "type" that gets input.
    //  * @param type Is either grains, fruits, protein or other. Selects the category in which you are trying to find the item with the most
    //  * calories from.
    //  * @return Items object
    //  * @throws IllegalArgumentException if the string type is not one of the 4 listed options. 
    //  * @throws NoItemExistsException if there is no item in databaseItems that is under calories for a given type.
    //  * @since 0.5
    //  */
    // public Items getLargestItem(String type) throws IllegalArgumentException, NoItemExistsException { 
    //     String selection = type.trim().toLowerCase();
    //     double[] zeroed = {25.0, 25.0, 25.0, 25.0, 0.0};
    //     Items returnItem = new Items(-1, "Remove Item", zeroed);
    //     Items testItem;

    //     switch (selection) {
    //         case "grains":
    //             for (int i = 0; i < databaseItems.size(); i++) {
    //                 try {
    //                     testItem = databaseItems.get(i);
    //                 } catch (ArrayIndexOutOfBoundsException e) {
    //                     return returnItem;
    //                 }
    //                 if (testItem.getNutrientData().getGrainCals() > returnItem.getNutrientData().getGrainCals()) {
    //                     returnItem = testItem;
    //                 }
    //             }
    //             if (returnItem.getItemID() == -1) {
    //                 throw new NoItemExistsException();
    //             }
    //             return returnItem;

    //         case "fruits":
    //             for (int i = 0; i < databaseItems.size(); i++) {
    //                 try {
    //                     testItem = databaseItems.get(i);
    //                 } catch (ArrayIndexOutOfBoundsException e) {
    //                     return returnItem;
    //                 }
    //                 if (testItem.getNutrientData().getFruitCals() > returnItem.getNutrientData().getFruitCals()) {
    //                     returnItem = testItem;
    //                 }
    //             }
    //             if (returnItem.getItemID() == -1) {
    //                 throw new NoItemExistsException();
    //             }
    //             return returnItem;
        
    //         case "protein":
    //             for (int i = 0; i < databaseItems.size(); i++) {
    //                 try {
    //                     testItem = databaseItems.get(i);
    //                 } catch (ArrayIndexOutOfBoundsException e) {
    //                     return returnItem;
    //                 }
    //                 if (testItem.getNutrientData().getProteinCals() > returnItem.getNutrientData().getProteinCals()) {
    //                     returnItem = testItem;
    //                 }
    //             }
    //             if (returnItem.getItemID() == -1) {
    //                 throw new NoItemExistsException();
    //             }
    //             return returnItem;
        
    //         case "other":
    //             for (int i = 0; i < databaseItems.size(); i++) {
    //                 try {
    //                     testItem = databaseItems.get(i);
    //                 } catch (ArrayIndexOutOfBoundsException e) {
    //                     return returnItem;
    //                 }
    //                 if (testItem.getNutrientData().getOtherCals() > returnItem.getNutrientData().getOtherCals()) {
    //                     returnItem = testItem;
    //                 }
    //             }
    //             if (returnItem.getItemID() == -1) {
    //                 throw new NoItemExistsException();
    //             }
    //             return returnItem;
        
    //         default:
    //             throw new IllegalArgumentException("In getLargestItem, all case values failed to identify");
    //     }
    // }
    
    // /**
    //  * Returns an object of type "Items" which contains the most calories under a certain threshold "calories" from a given 
    //  * "type" of calories. 
    //  * @param type String of either grains, fruits, protein or other.
    //  * @param calories Int in which the number of calories from a given type will be under. Can be 0 but will not function in that case.
    //  * @return Items object
    //  * @throws IllegalArgumentException if the input calories is a negative number, or the string type is not one of the 4 listed options. 
    //  * @throws NoItemExistsException if there is no item in databaseItems that is under calories for a given type.
    //  * @since 0.6
    //  */
    // public Items getLargestItemUnder(String type, int calories) throws IllegalArgumentException, NoItemExistsException{
    //     if (calories < 0) {
    //         throw new IllegalArgumentException("in getLargestItemUnder, calories was a negative number");
    //     }
    //     String selection = type.trim().toLowerCase();
    //     double[] zeroed = {25.0, 25.0, 25.0, 25.0, 0.0};
    //     Items returnItem = new Items(-1, "Remove Item", zeroed);
    //     Items testItem;
    //     switch (selection) {
    //         case "grains":
    //             for (int i = 0; i < databaseItems.size(); i++) {
    //                 try {
    //                     testItem = databaseItems.get(i);
    //                 } catch (ArrayIndexOutOfBoundsException e) {
    //                     return returnItem;
    //                 }
    //                 if (testItem.getNutrientData().getGrainCals() < calories) {
    //                     if (testItem.getNutrientData().getGrainCals() > returnItem.getNutrientData().getGrainCals()) {
    //                         returnItem = testItem;
    //                     }
    //                 }
    //             }
    //             if (returnItem.getItemID() == -1) {
    //                 throw new NoItemExistsException();
    //             }

    //             return returnItem;

    //         case "fruits":
    //             for (int i = 0; i < databaseItems.size(); i++) {
    //                 try {
    //                     testItem = databaseItems.get(i);
    //                 } catch (ArrayIndexOutOfBoundsException e) {
    //                     return returnItem;
    //                 }
    //                 if (testItem.getNutrientData().getFruitCals() < calories) {
    //                     if (testItem.getNutrientData().getFruitCals() > returnItem.getNutrientData().getFruitCals()) {
    //                         returnItem = testItem;
    //                     }
    //                 }
    //             }
    //             if (returnItem.getItemID() == -1) {
    //                 throw new NoItemExistsException();
    //             }
    //             return returnItem;
        
    //         case "protein":
    //             for (int i = 0; i < databaseItems.size(); i++) {
    //                 try {
    //                     testItem = databaseItems.get(i);
    //                 } catch (ArrayIndexOutOfBoundsException e) {
    //                     return returnItem;
    //                 }
    //                 if (testItem.getNutrientData().getProteinCals() < calories) {
    //                     if (testItem.getNutrientData().getProteinCals() > returnItem.getNutrientData().getProteinCals()) {
    //                         returnItem = testItem;
    //                     }
    //                 }
    //             }
    //             if (returnItem.getItemID() == -1) {
    //                 throw new NoItemExistsException();
    //             }
    //             return returnItem;
        
    //         case "other":
    //             for (int i = 0; i < databaseItems.size(); i++) {
    //                 try {
    //                     testItem = databaseItems.get(i);
    //                 } catch (ArrayIndexOutOfBoundsException e) {
    //                     return returnItem;
    //                 }
    //                 if (testItem.getNutrientData().getOtherCals() < calories) {
    //                     if (testItem.getNutrientData().getOtherCals() > returnItem.getNutrientData().getOtherCals()) {
    //                         returnItem = testItem;
    //                     }
    //                 }
    //             }
    //             if (returnItem.getItemID() == -1) {
    //                 throw new NoItemExistsException();
    //             }
    //             return returnItem;
        
    //         default:
    //             throw new IllegalArgumentException("In getLargestItemUnder, all case values failed to identify");
    //     }
    // }

    // /**
    //  * Returns an object of type "Items" which contains the least amount of calories over a certain threshold "calories" from a given 
    //  * "type" of calories.
    //  * @param type String of either grains, fruits, protein or other.
    //  * @param calories Int in which the number of calories from a given type will be under. Can be 0 but will not function in that case.
    //  * @return Items object
    //  * @throws IllegalArgumentException if the input calories is a negative number, or the string type is not one of the 4 listed options. 
    //  * @throws NoItemExistsException if there is no item in databaseItems that is under calories for a given type.
    //  * @since 0.7
    //  */
    // public Items getSmallestItemOver(String type, int calories) throws IllegalArgumentException, NoItemExistsException{
    //     if (calories < 0) {
    //         throw new IllegalArgumentException("in getLargestItemUnder, calories was a negative number");
    //     }
    //     String selection = type.trim().toLowerCase();
    //     double[] zeroed = {25.0, 25.0, 25.0, 25.0, 50000000.0};
    //     Items returnItem = new Items(-1, "Remove Item", zeroed);
    //     Items testItem;
    //     switch (selection) {
    //         case "grains":
    //             for (int i = 0; i < databaseItems.size(); i++) {
    //                 try {
    //                     testItem = databaseItems.get(i);
    //                 } catch (ArrayIndexOutOfBoundsException e) {
    //                     return returnItem;
    //                 }
    //                 if (testItem.getNutrientData().getGrainCals() > calories) {
    //                     if (testItem.getNutrientData().getGrainCals() < returnItem.getNutrientData().getGrainCals()) {
    //                         returnItem = testItem;
    //                     }
    //                 }
    //             }
    //             if (returnItem.getItemID() == -1) {
    //                 throw new NoItemExistsException();
    //             }

    //             return returnItem;

    //         case "fruits":
    //             for (int i = 0; i < databaseItems.size(); i++) {
    //                 try {
    //                     testItem = databaseItems.get(i);
    //                 } catch (ArrayIndexOutOfBoundsException e) {
    //                     return returnItem;
    //                 }
                    
    //                 if (testItem.getNutrientData().getFruitCals() > calories) {
                        
    //                     if (testItem.getNutrientData().getFruitCals() < returnItem.getNutrientData().getFruitCals()) {
    //                         returnItem = testItem;
    //                     }
    //                 }
    //             }
    //             if (returnItem.getItemID() == -1) {
    //                 throw new NoItemExistsException();
    //             }
    //             return returnItem;
        
    //         case "protein":
    //             for (int i = 0; i < databaseItems.size(); i++) {
    //                 try {
    //                     testItem = databaseItems.get(i);
    //                 } catch (ArrayIndexOutOfBoundsException e) {
    //                     return returnItem;
    //                 }
    //                 if (testItem.getNutrientData().getProteinCals() > calories) {
    //                     if (testItem.getNutrientData().getProteinCals() < returnItem.getNutrientData().getProteinCals()) {
    //                         returnItem = testItem;
    //                     }
    //                 }
    //             }
    //             if (returnItem.getItemID() == -1) {
    //                 throw new NoItemExistsException();
    //             }
    //             return returnItem;
        
    //         case "other":
    //             for (int i = 0; i < databaseItems.size(); i++) {
    //                 try {
    //                     testItem = databaseItems.get(i);
    //                 } catch (ArrayIndexOutOfBoundsException e) {
    //                     return returnItem;
    //                 }
    //                 if (testItem.getNutrientData().getOtherCals() > calories) {
    //                     if (testItem.getNutrientData().getOtherCals() < returnItem.getNutrientData().getOtherCals()) {
    //                         returnItem = testItem;
    //                     }
    //                 }
    //             }
    //             if (returnItem.getItemID() == -1) {
    //                 throw new NoItemExistsException();
    //             }
    //             return returnItem;
        
    //         default:
    //             throw new IllegalArgumentException("In getLargestItemUnder, all case values failed to identify");
    //     }
    // }

    /**
     * Getter function to return the Database Items too an array. 
     * @return An array of the Items object
     * @since 0.4 Updated 1.1
     */
    public Items[] getDatabaseItems (){
        /*
        This method is extremely jank, but it works to return what we are looking for. Just using the .toArray function broke everything. 
        */
        databaseItems.trimToSize();
        Items[] dbItems = new Items[databaseItems.size()];
        for (int i = 0; i < dbItems.length; i++) {
            dbItems[i] = databaseItems.get(i);
        }
        return dbItems;
    }

    /**
     * This is a helper function that checks through databaseItems to see if the ID is in the vector list. 
     * @param id Int
     * @return Boolean. true if the ID is contained in databaseItems, else false.
     * @since 1.4
     */
    private boolean checkForItem(int id){
        // Because it is a vector (and id's can be deleted), this needs to be a for loop search.
        // Using the .contains() function could work, but its much easier to use ID's
        for (int i = 0; i < this.databaseItems.size(); i++) {
            if (id == databaseItems.get(i).getItemID()) {
                return true;
            }
        }
        return false;
    }

}


