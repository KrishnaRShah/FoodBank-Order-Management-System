/*
@author Ryan Mailhiot 30080009<a
href="mailto:ryan.mailhiot@ucalgary.ca">ryan.mailhiot@ucalgary.ca</a>
@version 0.5
@since 0.0
*/
package FPCode;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import java.util.*;

// OF NOTE: Due to errors with the install of JUNIT, I cannot properly test this file, this is just written up
// as a skeleton. It is possible these tests do not work, it is just the baseline for the tests.

public class DatabaseItemsTest {
    public DatabaseItems dbItems = new DatabaseItems();
    public static Double[] dblArray = {11.1,12.1,13.1,14.1,15.1};
    Items[] itemsList = getItemsList();
    Items[] badItemsList = getBadItemsList();

    @Test 
    public void testConstructor(){
        public DatabaseItems dbTest = new DatabaseItems();
        assertNotNull("The Database Constructor does not load in Database items.", DatabaseItems.getDatabaseItems());
    }

    // TEST GETLARGESTITEM... 4 TESTS
    @Test 
    public Void testGetLargestItemGrains(){
        int expectedOutput = 10;
        int actualOutput = dbItems.getLargestItem("Grains").getITEM_ID(); 

        assertEquals("getLargestItem returned the wrong item ID for grains. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
    }
    @Test 
    public Void testGetLargestItemFruits(){
        int expectedOutput = 10;
        int actualOutput = dbItems.getLargestItem("Fruits").getITEM_ID(); 

        assertEquals("getLargestItem returned the wrong item ID for fruits. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
    }
    @Test 
    public Void testGetLargestItemProtein(){
        int expectedOutput = 10;
        int actualOutput = dbItems.getLargestItem("Protein").getITEM_ID(); 

        assertEquals("getLargestItem returned the wrong item ID for protein. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
    }
    @Test 
    public Void testGetLargestItemOther(){
        int expectedOutput = 10;
        int actualOutput = dbItems.getLargestItem("other").getITEM_ID(); 

        assertEquals("getLargestItem returned the wrong item ID for other. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
    }

    // TEST GETLARGESTITEMUNDER... 4 TESTS
    @Test 
    public Void testGetLargestItemUnderGrains(){
        int expectedOutput = 54;
        int actualOutput = dbItems.getLargestItemUnder("Grains", 1000).getITEM_ID(); 
        
        assertEquals("getLargestItemUnder returned the wrong item ID for Grains. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
        
    }

    @Test 
    public Void testGetLargestItemUnderFruits(){
        int expectedOutput = 54;
        int actualOutput = dbItems.getLargestItemUnder("Fruits", 1000).getITEM_ID(); 
        
        assertEquals("getLargestItemUnder returned the wrong item ID for fruits. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
        
    }

    @Test 
    public Void testGetLargestItemUnderProtein(){
        int expectedOutput = 54;
        int actualOutput = dbItems.getLargestItemUnder("Protein", 1000).getITEM_ID(); 
        
        assertEquals("getLargestItemUnder returned the wrong item ID for protein. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
        
    }

    @Test 
    public Void testGetLargestItemUnderOther(){
        int expectedOutput = 54;
        int actualOutput = dbItems.getLargestItemUnder("Other", 1000).getITEM_ID(); 
        
        assertEquals("getLargestItemUnder returned the wrong item ID for other. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
        
    }


    // TEST SMALLESTITEMOVER... 4 TESTS
    @Test 
    public Void testSmallestItemOverGrains(){
        int expectedOutput = 33;
        int actualOutput = dbItems.getSmallestItemOver("Grains", 500).getITEM_ID(); 

        assertEquals("getLargestItemUnder returned the wrong item ID for grains. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
        
    }

    @Test 
    public Void testSmallestItemOverFruits(){
        int expectedOutput = 33;
        int actualOutput = dbItems.getSmallestItemOver("Fruits", 500).getITEM_ID(); 

        assertEquals("getLargestItemUnder returned the wrong item ID for fruits. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
        
    }

    @Test 
    public Void testSmallestItemOverProtein(){
        int expectedOutput = 33;
        int actualOutput = dbItems.getSmallestItemOver("Protein", 500).getITEM_ID(); 

        assertEquals("getLargestItemUnder returned the wrong item ID for protein. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
        
    }

    @Test 
    public Void testSmallestItemOverOther(){
        int expectedOutput = 33;
        int actualOutput = dbItems.getSmallestItemOver("Other", 500).getITEM_ID(); 

        assertEquals("getLargestItemUnder returned the wrong item ID for other. Expected: " + expectedOutput + " But got: "+ actualOutput, expectedOutput, actualOutput);
        
    }

    // TEST FOR THE DATABASE UPDATE (Delete) AND REFRESH (auto done)
    @Test 
    public Void testUpdateDatabaseAndRefreshDatabaseItems(){
        boolean correctException = false;
        Items[] testVector = DatabaseItems.getDatabaseItems();
        Items[] testVector2;
        try {
            dbItems.updateDatabase(itemsList); // The refresh happens automatically at the end of updateDatabase
            testVector2 = DatabaseItems.getDatabaseItems();
        } catch (IllegalArgumentException e) {
            correctException = true;
        } catch (Exception e) {

        }
        
        assertNotEquals("Database does not update with the refreshed database items, or the items do not get deleted.", testVector, testVector2);
        assertEquals("Database tried to delete an item that did not exist. ", true, correctException);
    }

    //This test is specifically important because if we are deleting items in the hamper that do not exist, something is seriously wrong
    @Test 
    public void testUpdateDatabaseBadData(){
        boolean correctException = false;
        try {
            dbItems.updateDatabase(badItemsList);
        } catch (IllegalArgumentException e) {
            correctException = true;
        } catch (Exception e){

        }
        assertEquals("Did not catch when IllegalArgumentException when invalid data was put in.", true, correctException);
    }

    // HELPER METHODS
    // These are just arbitrary values because the program is not made yet so testing with real data is impossible.

    public static Items[] getItemsList (){
        Items first = new Items(1, "first", dblArray);
        Items second = new Items(2, "second", dblArray);
        Items third = new Items(3, "third", dblArray);
        Items fourth = new Items(4, "fourth", dblArray);
        Items[] returnItems = {first, second, third, fourth};
        return returnItems;
    }

    public static Items[] getBadItemsList (){
        Items first = new Items(1, "first", dblArray);
        Items second = new Items(1, "second", dblArray);
        Items third = new Items(1, "third", dblArray);
        Items fourth = new Items(1, "fourth", dblArray);
        Items[] returnItems = {first, second, third, fourth};
        return returnItems;
    }

}