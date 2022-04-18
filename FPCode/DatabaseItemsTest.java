/**
 * @author Ryan Mailhiot 30080009<a
 * href="mailto:ryan.mailhiot@ucalgary.ca">ryan.mailhiot@ucalgary.ca</a>
 * @version 1.1
 * @since 0.0
*/
package FPCode;

import org.junit.*;
import static org.junit.Assert.*;

// THIS MUST BE TESTED WITH THE micro test inventory.sql FILE LOCATED IN THE FPCODE DIRECTORY. OTHERWISE THE TESTS WILL FAIL. 

/**
 * This is a test file for the DatabaseItems.java file which controls the accessing and organizing of database related tasks. 
 * There is a large portion of this section commented out because functionality was removed last minute in favor of a different
 * method for the algorithm. As such search functionality was not needed. 
 */
public class DatabaseItemsTest {
    /**
     * Constructor test to make sure it actually creates something. Because method testing is later, it isn't necessary for dbtest to be valid, just not be null.
     */
    @Test 
    public void testConstructor(){
        DatabaseItems dbTest = new DatabaseItems();
        assertNotNull("The Database Constructor does not load in Database items.", dbTest);
    }

    // These tests are no longer needed because the method in which we are calulating the hamper does not need these methods. So there is no use having them
    // in the tests. However, incase you are curious. If you uncomment the methods out of DatabaseItems and the tests out of here, they (should) work. This was completed
    // first so hamper could be tested. 

    // TEST GETLARGESTITEM... 4 TESTS
    // @Test 
    // public void testGetLargestItemGrains(){
    //     int expectedOutput = 10;
    //     int actualOutput = dbItems.getLargestItem("Grains").getItemID(); 

    //     assertEquals("getLargestItem returned the wrong item ID for grains. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
    // }
    // @Test 
    // public void testGetLargestItemFruits(){
    //     int expectedOutput = 10;
    //     int actualOutput = dbItems.getLargestItem("Fruits").getITEM_ID(); 

    //     assertEquals("getLargestItem returned the wrong item ID for fruits. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
    // }
    // @Test 
    // public void testGetLargestItemProtein(){
    //     int expectedOutput = 10;
    //     int actualOutput = dbItems.getLargestItem("Protein").getITEM_ID(); 

    //     assertEquals("getLargestItem returned the wrong item ID for protein. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
    // }
    // @Test 
    // public void testGetLargestItemOther(){
    //     int expectedOutput = 10;
    //     int actualOutput = dbItems.getLargestItem("other").getITEM_ID(); 

    //     assertEquals("getLargestItem returned the wrong item ID for other. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
    // }

    // // TEST GETLARGESTITEMUNDER... 4 TESTS
    // @Test 
    // public void testGetLargestItemUnderGrains(){
    //     int expectedOutput = 54;
    //     int actualOutput = dbItems.getLargestItemUnder("Grains", 1000).getITEM_ID(); 
        
    //     assertEquals("getLargestItemUnder returned the wrong item ID for Grains. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
        
    // }

    // @Test 
    // public void testGetLargestItemUnderFruits(){
    //     int expectedOutput = 54;
    //     int actualOutput = dbItems.getLargestItemUnder("Fruits", 1000).getITEM_ID(); 
        
    //     assertEquals("getLargestItemUnder returned the wrong item ID for fruits. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
        
    // }

    // @Test 
    // public void testGetLargestItemUnderProtein(){
    //     int expectedOutput = 54;
    //     int actualOutput = dbItems.getLargestItemUnder("Protein", 1000).getITEM_ID(); 
        
    //     assertEquals("getLargestItemUnder returned the wrong item ID for protein. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
        
    // }

    // @Test 
    // public void testGetLargestItemUnderOther(){
    //     int expectedOutput = 54;
    //     int actualOutput = dbItems.getLargestItemUnder("Other", 1000).getITEM_ID(); 
        
    //     assertEquals("getLargestItemUnder returned the wrong item ID for other. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
        
    // }


    // // TEST SMALLESTITEMOVER... 4 TESTS
    // @Test 
    // public void testSmallestItemOverGrains(){
    //     int expectedOutput = 33;
    //     int actualOutput = dbItems.getSmallestItemOver("Grains", 500).getITEM_ID(); 

    //     assertEquals("getLargestItemUnder returned the wrong item ID for grains. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
        
    // }

    // @Test 
    // public void testSmallestItemOverFruits(){
    //     int expectedOutput = 33;
    //     int actualOutput = dbItems.getSmallestItemOver("Fruits", 500).getITEM_ID(); 

    //     assertEquals("getLargestItemUnder returned the wrong item ID for fruits. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
        
    // }

    // @Test 
    // public void testSmallestItemOverProtein(){
    //     int expectedOutput = 33;
    //     int actualOutput = dbItems.getSmallestItemOver("Protein", 500).getITEM_ID(); 

    //     assertEquals("getLargestItemUnder returned the wrong item ID for protein. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
        
    // }

    // @Test 
    // public void testSmallestItemOverOther(){
    //     int expectedOutput = 33;
    //     int actualOutput = dbItems.getSmallestItemOver("Other", 500).getITEM_ID(); 

    //     assertEquals("getLargestItemUnder returned the wrong item ID for other. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
        
    // }


    /**
     * This tests that the getter returns the correct format, and that all of the elements are in the same order and have the same properties as the database.
     * This uses the helper functions getTestItemsList and hasSameProperties; Functionality is at the bottom of this file.
     */
    @Test 
    public void testGetDatabaseItems() {
        DatabaseItems dbItems = new DatabaseItems();
        Items[] expectedValue = getTestItemsList(); // This is a helper method seen below
        Items[] actual = dbItems.getDatabaseItems();

        assertEquals("Items are not the same properties.",true, hasSameProperties(expectedValue, actual));

    }

    /**
     * This is a combo test for the update (delete) and refresh methods. The reason this is a combo test is because in the updateDatabase method, it will
     * automatically refresh the "local" databaseItems after it completes. Because of this, either the database didn't delete an item when it should have 
     * or the refresh didn't occur. How ever because the refresh needs to occur in order for there to be shown a difference, they need to be combined into one.
     * With that being said, if the getter test above passed, then refresh is fine because refresh is also in the constructor. If it refreshes properly to 
     * set the constructor, then it is safe to say that the delete is the issue. If the getter test above failed, then it can be assumed that refresh maybe the
     * issue here as well.
     */
    @Test 
    public void testUpdateDatabaseAndRefreshDatabaseItems(){
        DatabaseItems dbtestItems = new DatabaseItems();
        boolean exceptionCatch = false;
        boolean completeFailure = false;
        Items[] testVector = dbtestItems.getDatabaseItems(); // will be erased, its just something
        try {
            dbtestItems.updateDatabase(getItemsList()); // The refresh happens automatically at the end of updateDatabase
            testVector = dbtestItems.getDatabaseItems(); // This should return the 3 correct items using the test set.
        } catch (IllegalArgumentException e) {
            exceptionCatch = true;
        } catch (Exception e) {
            completeFailure = true;
        }
        
        assertEquals("Does not have same items after deletion",true, hasSameProperties(getAfterDeleteItemsList(), testVector));
        assertNotEquals("Database Threw exception when it shouldn't have. (testUpdateDatabaseAndRefreshDatabaseItems)", true, exceptionCatch);
        assertFalse("An unexpected error occured in testUpdateDatabaseAndRefreshDatabaseItems. This is a big issue.", completeFailure);
    }

    //This test is specifically important because if we are deleting items in the hamper that do not exist, something is seriously wrong
    /**
     * Tests if an IllegalArgumentException is thrown with bad data to delete. The delete uses unique item ID, so if the ID doesn't exist it throws an
     * IllegalArgumentException. This also checks the private method checkForItem. This uses the helper function getBadItemsList which contains an ID
     * much greater than any possible test set (590 was chosen at random). 
     */
    @Test 
    public void testUpdateDatabaseBadData(){
        DatabaseItems dbItems = new DatabaseItems();
        boolean correctException = false;
        try {
            dbItems.updateDatabase(getBadItemsList()); // Tries to delete the the bad item.
        } catch (IllegalArgumentException e) {
            correctException = true;
        } catch (Exception e){

        }
        assertEquals("Did not catch IllegalArgumentException when invalid data was put in.", true, correctException);
    }

    // HELPER METHODS
    // These are just arbitrary values because the program is not made yet so testing with real data is impossible.

    /**
     * Returns an items array of one item for delete testing
     * @return Items[] for deleting
     */
    public static Items[] getItemsList (){
        double[] oneN = {0, 80, 10, 10, 120};
        Items first = new Items(1, "Tomato Sauce, jar", oneN);
        Items[] returnItems = {first};
        return returnItems;
    }

    /**
     * Returns an items array of one item where the ID does not exist in the test set.
     * @return Items[] for testing if the correct exception is thrown.
     */
    public static Items[] getBadItemsList(){
        double[] oneN = {0, 80, 10, 10, 120};
        Items first = new Items(590, "Tomato Sauce, jar", oneN); // Item ID is wayyy too large
        Items[] returnItems = {first};
        return returnItems;
    }

    /**
     * Returns an items array of 4 items which is identical to that in the micro inventory test file.
     * @return Items[] of 4 items in the micro inventory file. 
     */
    public static Items[] getTestItemsList(){
        double[] oneN = {0, 80, 10, 10, 120};
        Items one = new Items(1, "Tomato Sauce, jar", oneN);
        double[] twoN = {0, 100, 0, 0, 624};
        Items two = new Items(2, "Apple, dozen", twoN);
        double[] threeN = {71, 0, 6, 23, 936};
        Items three = new Items(3, "Granola Bar, box", threeN);
        double[] fourN = {0, 0, 14, 86, 95};
        Items four = new Items(4, "Chicken broth, can", fourN);
        Items[] returnItems = {one,two,three,four};
        return returnItems;
    }
    /**
     * Returns an items array of 3 items which is identical to that in the micro inventory test file after the deletion test.
     * @return Items[] of 3 items in the micro inventory file after the delete. 
     */
    public static Items[] getAfterDeleteItemsList(){
        double[] twoN = {0, 100, 0, 0, 624};
        Items two = new Items(2, "Apple, dozen", twoN);
        double[] threeN = {71, 0, 6, 23, 936};
        Items three = new Items(3, "Granola Bar, box", threeN);
        double[] fourN = {0, 0, 14, 86, 95};
        Items four = new Items(4, "Chicken broth, can", fourN);
        Items[] returnItems = {two,three,four};
        return returnItems;
    }

    /**
     * Checks if the Items parameters for firstItems and secondItems arrays are the same. 
     * @param firstItems Items[]
     * @param secondItems Items[]
     * @return True if they are all the same, false if even one parameter is different.
     */
    public static boolean hasSameProperties(Items[] firstItems, Items[] secondItems){
        if (firstItems.length != secondItems.length) {
            return false;
        }
        for (int i = 0; i < firstItems.length; i++) {
            if (firstItems[i].getItemID() != secondItems[i].getItemID()) {
                System.out.println("ID is not the same at location: "+i);
                return false;
            }
            if (!firstItems[i].getItemName().equals(secondItems[i].getItemName())) {
                System.out.println("Name is not the same at location: "+i);
                System.out.println(firstItems[i].getItemName());
                System.out.println(secondItems[i].getItemName());
                return false;
            }
            if (firstItems[i].getNutrientData().getGrains() != secondItems[i].getNutrientData().getGrains()) {
                System.out.println("Grains is not the same at location: "+i);
                return false;
            }
            if (firstItems[i].getNutrientData().getFruits() != secondItems[i].getNutrientData().getFruits()) {
                System.out.println("Fruits is not the same at location: "+i);
                return false;
            }
            if (firstItems[i].getNutrientData().getProtein() != secondItems[i].getNutrientData().getProtein()) {
                System.out.println("Protein is not the same at location: "+i);
                return false;
            }
            if (firstItems[i].getNutrientData().getOther() != secondItems[i].getNutrientData().getOther()) {
                System.out.println("Other is not the same at location: "+i);
                return false;
            }
            if (firstItems[i].getNutrientData().getTotalCalories() != secondItems[i].getNutrientData().getTotalCalories()) {
                System.out.println("Calories is not the same at location: "+i);
                return false;
            }
            
        }
        return true;
    }

    

}
