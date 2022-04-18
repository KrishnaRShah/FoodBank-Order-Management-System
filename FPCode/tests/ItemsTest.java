/** 
@author Ryan Mailhiot 30080009<a
href="mailto:ryan.mailhiot@ucalgary.ca">ryan.mailhiot@ucalgary.ca</a>
@version 1.1 
@since 0.0 (1.0 is first working version)
*/
package FPCode.tests;
import FPCode.*;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * This is a test class for the file Items.java used in FPCode.
 */
public class ItemsTest {
    public static double[] dblArray = {25.0, 25.0, 25.0, 25.0, 1000.0};
    public static double[] badDblArray = {25.0, 25.0, 30.0, 25.0, 1000.0};
    
    /**
     * Tests the constructor creates a valid item
     */
    @Test
    public void testItemsConstructorGoodData(){
        boolean testException = false;
        try {
            Items first = new Items(1, "first", dblArray);
            assertNotNull("Items constructor did not create a valid object", first);
        } catch (IllegalArgumentException e) {
            testException = true;
        }
        
        assertNotEquals("Items threw illegalArgumentException for good data from nutrients.", true, testException);
    }

    /**
     * Tests that it throws an IllegalArgumentException if the nutrients are not valid. 
     */
    @Test
    public void testItemsConstructorBadData(){
        boolean testException = false;
        try {
            @SuppressWarnings("unused")
            Items second = new Items(2, "second", badDblArray);
        } catch (IllegalArgumentException e) {
            testException = true;
        } catch (Exception e){

        }

        assertEquals("Constructor did not throw an IllegalArgumentException with bad data.", true, testException);
    }

    
    public Items testItem = new Items(25, "Twenty Fifth", dblArray); // This is funky without the correct test file structure.

    /**
     * Tests the getter function for the nutrient data
     */
    @Test
    public void testGetNutrientData(){
        
        Nutrients expectedData = new Nutrients(dblArray[0], dblArray[1], dblArray[2], dblArray[3], dblArray[4]);
        Nutrients actualData = testItem.getNutrientData();

        assertEquals("getNutrientData returns different data compared to what is expected", true, hasSameProperties(expectedData, actualData));
    }

    /**
     * Tests that it gets the correct item id.
     */
    @Test
    public void testGetItemID(){
        int expectedID = 25;
        int actualID = testItem.getItemID();

        assertEquals("getITEM_ID does not return the correct ID", expectedID, actualID);
    }

    /**
     * Tests that it gets the correct item name
     */
    @Test
    public void testGetItem_Name(){
        String expectedName = "Twenty Fifth";
        String actualName = testItem.getItemName();

        assertEquals("getITEM_NAME does not return the correct name", expectedName, actualName);
    }

    // HELPER FUNCTION

    /**
     * Used to determine that all elements of the nutrients are valid.
     * @param first Nutrients object
     * @param second Nutrients object
     * @return True if all elements match, false otherwise.
     */
    public static boolean hasSameProperties(Nutrients first, Nutrients second){
        if (first.getGrainCals() != second.getGrainCals()) {
            return false;
        }
        
        if (first.getFruitCals() != second.getFruitCals()) {
            return false;
        }

        if (first.getProteinCals() != second.getProteinCals()) {
            return false;
        }

        if (first.getOtherCals() != second.getOtherCals()) {
            return false;
        }

        if (first.getTotalCalories() != second.getTotalCalories()) {
            return false;
        }

        return true;
    }
}