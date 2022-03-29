/*
@author Ryan Mailhiot 30080009<a
href="mailto:ryan.mailhiot@ucalgary.ca">ryan.mailhiot@ucalgary.ca</a>
@version 0.1 
@since 0.0
*/
package FPCode;

import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;
import java.util.*;

// OF NOTE: Due to errors with the install of JUNIT, I cannot properly test this file, this is just written up
// as a skeleton

public class ItemsTest {
    public static Double[] dblArray = {25.0, 25.0, 25.0, 25.0, 1000.0};
    public static Double[] badDblArray = {25.0, 25.0, 30.0, 25.0, 1000.0};
    

    @test 
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

    @test 
    public void testItemsConstructorBadData(){
        boolean testException = false;
        try {
            Items second = new Items(2, "second", badDblArray);
        } catch (IllegalArgumentException e) {
            testException = true;
        } catch (Exception e){

        }

        assertEquals("Constructor did not throw an IllegalArgumentException with bad data.", true, testException);
    }

    
    public Items testItem = new Items(25, "Twenty Fifth", dblArray); // This is funky without the correct test file structure.

    @test
    public void testGetNutrientData(){
        
        Nutrients expectedData = new Nutrients(dblArray[0], dblArray[1], dblArray[2], dblArray[3], dblArray[4]);
        Nutrients actualData = testItem.getNutrientData();

        assertEquals("getNutrientData returns different data compared to what is expected", expectedData, actualData);
    }

    @test 
    public void testGetItemID(){
        int expectedID = 25;
        int actualID = testItem.getITEM_ID();

        assertEquals("getITEM_ID does not return the correct ID", expectedID, actualID);
    }

    @test 
    public void testGetItem_Name(){
        String expectedName = "Twenty Fifth";
        String actualName = testItem.getITEM_NAME();

        assertEquals("getITEM_NAME does not return the correct name", expectedName, actualName);
    }
}