/**
 * @author Krishna Shah 30114067<a
 * href="mailto:krishna.shah@ucalgary.ca">krishna.shah@ucalgary.ca</a>
 * @version 1.5 
 * @since 0.0
 */

package FPCode;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Krishna Shah 30114067 <a
 * href="mailto:krishna.shah@ucalgary.ca">krishna.shah@ucalgary.ca</a>
 * This class is designed to test the Order Class, it will test the Order Constructor
 * as well it will test all the getters and setters required for the Order class
 */
public class OrderTest {

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
        

        try{
            Order testOrder = new Order(testArrayList);
        }
        catch(IllegalArgumentException e){
            exceptionThrown = true;
        }

        assertFalse("An exception was thrown when attempting to create a Client.", exceptionThrown);

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
        Order testOrder = new Order(testArrayList);

    

        int[] correctHamperValues = {1,2};                  //Creates correctHamper
        Hamper correctHamper = new Hamper(correctHamperValues);    //Creates hamper

        int[] testHamperValues = {1,2,3};
        Hamper testHamper = new Hamper(testHamperValues);       //creates test hampper
        
        
        testHamper = testOrder.getHampterAtIndex(0);
        

        assertEquals("Hamper did not return the correct value.", testHamper, correctHamper);

        
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
        Order testOrder = new Order(testArrayList);

        List<Hamper> correctHamperList = new ArrayList<Hamper>();    //Creates hamperList
        int[] correctClientTypes = {1,2};                  //Creates clientTypes
        Hamper correctHamper = new Hamper(correctClientTypes);    //Creates hamper
        correctHamperList.add(correctHamper);                     //Adds hamper to hamperList

        List<Hamper> testHamperList = new ArrayList<>();    //Creates testhamperList

        testHamperList = testOrder.getHamper();

        assertEquals("Hamper did not return the correct value.", testHamperList, correctHamperList);
        
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
        int[] testArray = {};
        testArrayList.add(testArray);
        Order testOrder = new Order(testArrayList);

        int[] correctClientTypes = {1,2};                 
        Hamper correctHamper = new Hamper(correctClientTypes);   

        
        testOrder.addHamper(correctHamper);



        assertEquals("Hamper did not return the correct value.", testOrder.getHampterAtIndex(1), correctHamper);




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
}
