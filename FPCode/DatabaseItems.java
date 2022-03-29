/*
@author Ryan Mailhiot 30080009<a
href="mailto:ryan.mailhiot@ucalgary.ca">ryan.mailhiot@ucalgary.ca</a>
@version 0.3 
@since 0.0
*/

package FPCode;

import java.sql.*;
import java.util.Vector;

public class DatabaseItems {
    private final int SIZE_VECTOR = 20;
    private final int INCREMENT_VECTOR = 10;
    private final String DBURL = "jdbc:mysql://localhost/food_inventory";
    private final String USERNAME = "student";
    private final String PASSWORD = "ensf"; 
    private static Vector<Items> databaseItems;
    private Connection dbConnect;


    protected DatabaseItems(){
        databaseItems = new Vector<Items>(SIZE_VECTOR, INCREMENT_VECTOR);
        refreshDatabaseItems();
    }
    public void initializeConnection(){
        try {
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace(); // THIS NEEDS TO BE CHANGED BECAUSE WE NEED TO BE ABLE TO INTERNALLY HANDLE ERRORS
        }
    }

    public void refreshDatabaseItems(){
        // Call to database to return all from the given inventory
        databaseItems.clear();
        Items itemAdd;
        Double[] nutrientsInfo = new Double[5];
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateDatabase(Items[] items){
        // List of items to remove from data base.
        try {
            String query = "DELETE FROM available_food WHERE ItemID = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            for (int i = 0; i < items.length; i++) {
                myStmt.setInt(1, items[i].getITEM_ID());

                int updateCheck = myStmt.executeUpdate();
                if (updateCheck != 1) {
                    throw new IllegalArgumentException();
                } else {
                    updateCheck = 0;
                }
                myStmt.clearParameters();
            }
            myStmt.close();
        } catch (IllegalArgumentException e) {
            System.out.println("Line does not exist");
        } catch (Exception e) {
            e.printStackTrace();
        }
        refreshDatabaseItems();
    }

    public Items getLargestItem(String type) { // THIS WILL BE COMPLETED ONCE NUTRIENT CLASS IS MADE
        return null;
    }
    // Update

    public Items getLargestItemUnder(String type, int calories){
        return null;
    }

    public Items getSmallestItemOver(String type, int calories){
        return null;
    }

    public static Items[] getDatabaseItems (){
        return (Items[])databaseItems.toArray();
    }

}


