package FPCode;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Vector;


public class HamperTest {

    @Test
    public void testHamperConstructor(){

        boolean exceptionThrown = false;
        int[] testArray = {1,2,3,4};

        try{
            Hamper testHamper = new Hamper(testArray);
        }
        catch(IllegalArgumentException e){
            exceptionThrown = true;
        }

        assertFalse("An exception was thrown when attempting to create a Client.", exceptionThrown);

    }

    @Test
    public void testcalcHamperNutrients(){
        int[] testArray = {1,2};
        Hamper testHamper = new Hamper(testArray);

        Nutrients correctNutrients = new Nutrients(16, 28, 26, 30, 5000);
        testHamper.calcHamperNutrients();

        Nutrients testNutrients = testHamper.getHamperNutrients();

        assertEquals("calcHamperNutrients returns different data compared to what is expected", correctNutrients, testNutrients);

    }

    @Test
    public void  testbuildItemList(){

    }
 
    @Test
    public void testgetClientArray(){
        int[] testArray = {1};
        Hamper testHamper = new Hamper(testArray);
        Vector<Client> testClientArray = testHamper.getClientArray();

        Vector<Client> expectedClientArray = new Vector<Client>();
        Client expectedClient = new Client(1, "AM");
        expectedClientArray.add(expectedClient);

        assertEquals("getClientArray returns different data compared to what is expected", expectedClientArray, testClientArray);

    }
    
    @Test
    public void testgetItemsList(){

        

    }

    @Test
    public void testgetHamperNutrients(){

    }
    @Test

    public void testgetDaysNeeded(){

    }


    
}
