package FPCode;

import org.junit.*;
import static org.junit.Assert.*;


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

    }

    @Test
    public void  testbuildItemList(){

    }

    @Test
    public void testgetClientArray(){

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
