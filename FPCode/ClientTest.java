/**
* @author Krishna Shah 30114067<a
* href="mailto:krishna.shah@ucalgary.ca">krishna.shah@ucalgary.ca</a>
* @author Danny Picazo 301271082<a
* href="mailto:daniel.picazo@ucalgary">daniel.picazo@ucalgary.ca</a>
* @version 0.8 
* @since 0.0
*/

package FPCode;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Class to test Client
 */
public class ClientTest {


    /**
    * Tests Client Constructor
    */

    @Test
    public void testClientConstructor(){
        boolean exceptionThrown = false;

        try {
            @SuppressWarnings("unused")
            Client client = new Client(1);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }

        assertFalse("An exception was thrown when attempting to create a Client.", exceptionThrown);
    }

    /**
    * Tests Client getters and setters
    */
    @Test
    public void testClientGetsNSets(){
        boolean exceptionThrown = false;

        Client client = new Client(1);
        int id = -1337;
        String type = "you definitely messed something up";
        Nutrients nut = new Nutrients(100, 0, 0, 0, 420);

        boolean npt = false;
        try {
            id = client.getClientID();
            type = client.getClientType();
            nut = client.getNutrientData();
        } catch (Exception e) {
            npt = true;
        }
        // Nutrients actualNut = new Nutrients(16, 28, 26, 30, 2500);
        assertFalse("An unexpected exception was thrown.", exceptionThrown);
        assertFalse("The Client member variables were not initialized.", npt);
        assertEquals("Client ID did not return the correct value.", id, 1);
        assertEquals("Client type did not return the correct value.", type, "Adult Male");
        assertEquals("Client nutrient data did not return the correct value.", nut.getTotalCalories(),
                                                                                (double)2500, 2500);
    }

    /**
    * Tests all Client types
    */

    @Test
    public void testAllClientTypes(){
        // boolean exceptionThrown = false;

        Client am = new Client(1);
        Client af = new Client(2);
        Client co8 = new Client(3);
        Client cu8 = new Client(4);


        // assertFalse("An unexpected exception was thrown.", exceptionThrown);
        assertEquals("AM Client type did not return the correct type.", am.getClientType(), "Adult Male");
        assertEquals("AM Client type did not return the correct type.", af.getClientType(), "Adult Female");
        assertEquals("AM Client type did not return the correct type.", co8.getClientType(), "Child Over 8 years old");
        assertEquals("AM Client type did not return the correct type.", cu8.getClientType(), "Child Under 8 years old");

    }

    // @Test
    // public void testInvalidClientType(){
    //     boolean exceptionThrown1 = false;
    //     boolean exceptionThrown2 = false;
    //     boolean exceptionThrown3 = false;
    //     @SuppressWarnings("unused")
    //     Client client;

    //     try {
    //         client = new Client(-2);
    //     } catch (IllegalArgumentException e) {
    //         exceptionThrown1 = true;
    //     }
    //     try {
    //         client = new Client(13);
    //     } catch (IllegalArgumentException e) {
    //         exceptionThrown2 = true;
    //     }
    //     try {
    //         client = new Client(22);
    //     } catch (IllegalArgumentException e) {
    //         exceptionThrown3 = true;
    //     }
        
    //     assertTrue("An invalid Client type did not throw an IllegalArgumentException.", exceptionThrown1);
    //     assertTrue("An invalid Client type did not throw an IllegalArgumentException.", exceptionThrown2);
    //     assertTrue("An invalid Client type did not throw an IllegalArgumentException.", exceptionThrown3);
    // }

    /**
    * Tests invalid client id's
    */


    @Test
    public void testInvalidClientID(){
        boolean exceptionThrown = false;

        try {
            @SuppressWarnings("unused")
            Client client1 = new Client(5);
            Client client2 = new Client(0);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }

        assertTrue("An invalid Client ID did not throw an IllegalArgumentException.", exceptionThrown);
    }

    // @Test
    // public void testIDTypeMismatch(){
    //     boolean exceptionThrown = false;

    //     try {
    //         @SuppressWarnings("unused")
    //         Client client1 = new Client(2);
    //         Client client2 = new Client(2);
    //     } catch (IllegalArgumentException e) {
    //         exceptionThrown = true;
    //     }

    //     assertTrue("An unmatching ID and type did not throw an IllegalArgumentException.", exceptionThrown);
    // }

}
