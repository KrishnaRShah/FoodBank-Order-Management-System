package FPCode;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class OrderTest {

    @Test
    public void testOrderConstructor(){
        boolean exceptionThrown = false;

        try{
            Order testOrder = new Order(4);
        }
        catch(IllegalArgumentException e){
            exceptionThrown = true;
        }

        assertFalse("An exception was thrown when attempting to create a Client.", exceptionThrown);

    }
    
    @Test
    public void testGetHamperAtIndex(){
        boolean exceptionThrown = false;

        Order testOrder = new Order(4);

        int[] correctHamperValues = {1,2,3,4};                  //Creates correctHamper
        Hamper correctHamper = new Hamper(correctHamperValues);    //Creates hamper

        int[] testHamperValues = {1,2,3};
        Hamper testHamper = new Hamper(testHamperValues);       //creates test hampper
        
        try{
            testHamper = testOrder.getHampterAtIndex(0);
        }
        catch (Exception e){

        }
        assertEquals("Hamper did not return the correct value.", testHamper, correctHamper);

        
    }

    @Test
    public void testGetHamper(){
        
    }
    
}
