/**
* @author Krishna Shah 30114067<a
* href="mailto:krishna.shah@ucalgary.ca">krishna.shah@ucalgary.ca</a>
* @author Danny Picazo 301271082<a
* href="mailto:daniel.picazo@ucalgary">daniel.picazo@ucalgary.ca</a>
* @version 1.2 
* @since 0.0
*/

// Commenting and revisions done by Ryan Mailhiot
package FPCode;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * This class will test the client.java class for the final project. Its aim is to test all methods to insure correct functionality of each class.
 */
public class ClientTest {


    /**
    * This tests the client constructor by creating a new client. If the constructor fails it throws an IllegalArgumentException, which would then be
    * caught by the test to presume a fail. Testing all 4 valid inputs to see if any of them throw an exception.
    */

    @Test
    public void testClientConstructor(){
        boolean exceptionThrown = false;

        try {
            @SuppressWarnings("unused")
            Client client1 = new Client(1);
            @SuppressWarnings("unused")
            Client client2 = new Client(2);
            @SuppressWarnings("unused")
            Client client3 = new Client(3);
            @SuppressWarnings("unused")
            Client client4 = new Client(4);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }

        assertFalse("An exception was thrown when attempting to create a Client.", exceptionThrown);
    }

    /**
    * This will test all of the clients getters and setters at the same time. Each will have an individual message for pass or failure, so it will still be
    * possible to tell which ones are successful and which ones are failures. 
    */
    @Test
    public void testClientGetsNSets(){
        boolean exceptionThrown = false;

        // Danny's idea, not mine. -Ryan
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
    * This tests all the client types to make sure they return the correct type from the enum and store the correct information.
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

    // more simple versions below.
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
     * This tests the constructor for the lower end boundary case in which it should throw an IllegalArgumentException for an ID being less than 1.
     */
    @Test
    public void testClientConstructorBadInputLow(){
        boolean exceptionThrown = false;

        try {
            @SuppressWarnings("unused")
            Client client1 = new Client(0);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }

        assertTrue("There was no exception thrown with an invalid ID. (Bad input low)", exceptionThrown);
    }

    /**
     * This tests the constructor for the higher end boundary case in which it should throw an IllegalArgumentException for an ID being greater than 4.
     */
    @Test
    public void testClientConstructorBadInputHigh(){
        boolean exceptionThrown = false;

        try {
            @SuppressWarnings("unused")
            Client client1 = new Client(5);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }

        assertTrue("There was no exception thrown with an invalid ID. (Bad input high)", exceptionThrown);
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
