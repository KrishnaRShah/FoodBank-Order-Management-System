/**
 * @author Danny Picazo 30127082<a
 * href="mailto:daniel.picazo@ucalgary.ca">daniel.picazo@ucalgary.ca</a>
 * @version 2.0
 * @since 0.0
*/

package FPCode;

import org.junit.*;
import static org.junit.Assert.*;
import java.sql.*;
import java.util.Vector;


public class HamperTest {
    private final String DBURL = "jdbc:mysql://localhost/food_inventory";
    private final String USERNAME = "student";
    private final String PASSWORD = "ensf";
    private Connection dbConnect;

    /**
     * This test checks that the main Hamper constructor works as intended.
     */
    @Test
    public void testHamperConstructor(){

        boolean exceptionThrown = false;
        boolean unexpectedException = false;
        int[] testArray = {1,2,3,4};

        try{
            @SuppressWarnings("unused")
            Hamper testHamper = new Hamper(testArray, 1);
        }
        catch(IllegalArgumentException e){
            exceptionThrown = true;
        }
        catch(Exception f){
            unexpectedException = true;
        }

        assertFalse("An IllegalArgumentException was thrown when attempting to create a Hamper.", exceptionThrown);
        assertFalse("An unexpected exception was thrown when attempting to create a Hamper", unexpectedException);
    }

    /**
     * This test checks that the Hamper correctly sets the desired minimum Hamper nutrients from an array of Client types.
     * This test also uses and tests .getHamperNutrients() to make sure there are no errors.
     */
    @Test
    public void testCalcHamperNutrients(){
        int[] testArray = {1,2};
        boolean unexpectedException = false;
        try {
            Hamper testHamper = new Hamper(testArray, 1);

            Nutrients correctNutrients = new Nutrients(16, 28, 26, 30, 5000);
            testHamper.calcHamperNutrients();

            Nutrients testNutrients = testHamper.getHamperNutrients();

            assertEquals("calcHamperNutrients returns different data compared to what is expected", correctNutrients, testNutrients);
        } catch (Exception e) {
            unexpectedException = true;
        }
        assertFalse("An unexpected exception was thrown when attempting to calculate Hamper nutrients.", unexpectedException);
    }

    /**
     * This test checks the getter for the Client array variable in Hamper.
     */
    @Test
    public void testGetClientArray(){
        int[] testArray = {1};
        boolean unexpectedException = false;
        Vector<Client> testClientArray = new Vector<Client>();
        try {
            Hamper testHamper = new Hamper(testArray, 1);
        testClientArray = testHamper.getClientArray();
        } catch (Exception e) {
            unexpectedException = true;
        }

        Vector<Client> expectedClientArray = new Vector<Client>();
        Client expectedClient = new Client(1);
        expectedClientArray.add(expectedClient);

        assertEquals("getClientArray returns different data compared to what is expected", expectedClientArray, testClientArray);
        assertFalse("An unexpected exception was thrown when attempting to calculate Hamper nutrients.", unexpectedException);
    }
    
    /**
     * This test checks that the itemsList getter correctly returns the desired value.
     * This test also uses the secondary Hamper constructor, thus testing it as well.
     */
    @Test
    public void testGetItemsList(){
        
    }

    /**
     * This test checks ?????
     */
    @Test
    public void testGetDaysNeeded(){

    }

    /**
     * This test checks that Hamper correctly builds a good Hamper from a given Client array. 
     * Also tests that the database correctly updates.
     */
    @Test
    public void  testBuildItemList(){
        // literally what
    }

    /**
     * SQL HELPER FUNCTION: 
     */
    public void initializeConnection(){
        try {
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
}
