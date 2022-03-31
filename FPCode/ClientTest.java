import org.junit.*;
import static org.junit.Assert.*;


public class ClientTest {
    @Test
    public void testClientConstructor(){
        boolean exceptionThrown = false;

        try {
            @SuppressWarnings("unused")
            Client client = new Client(1, "AM");
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }

        assertFalse("An exception was thrown when attempting to create a Client.", exceptionThrown);
    }

    @Test
    public void testClientGetsNSets(){
        boolean exceptionThrown = false;

        Client client = new Client(123, "AM");
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

        assertFalse("An unexpected exception was thrown.", exceptionThrown);
        assertFalse("The Client member variables were not initialized.", npt);
        assertEquals("Client ID did not return the correct value.", id);
        assertEquals("Client type did not return the correct value.", type);
        assertEquals("Client nutrient data did not return the correct value.", nut);
    }

    @Test
    public void testAllClientTypes(){
        boolean exceptionThrown = false;
        @SuppressWarnings("unused")
        Client am;
        @SuppressWarnings("unused")
        Client af;
        @SuppressWarnings("unused")
        Client co8;
        @SuppressWarnings("unused")
        Client cu8;

        try {
            am = new Client(1, "am");
        } catch (Exception e) {
            exceptionThrown = true;
        }
        try {
            af = new Client(2, "AF");
        } catch (Exception e) {
            exceptionThrown = true;
        }
        try {
            co8 = new Client(3, "CO8");
        } catch (Exception e) {
            exceptionThrown = true;
        }
        try {
            cu8 = new Client(4, "Cu8");
        } catch (Exception e) {
            exceptionThrown = true;
        }

        assertFalse("An unexpected exception was thrown.", exceptionThrown);
        assertEquals("AM Client type did not return the correct type.", am.getClientType(), "Adult Male");
        assertEquals("AM Client type did not return the correct type.", af.getClientType(), "Adult Female");
        assertEquals("AM Client type did not return the correct type.", co8.getClientType(), "Child Over 8");
        assertEquals("AM Client type did not return the correct type.", cu8.getClientType(), "Child Under 8");

    }

    @Test
    public void testInvalidClientType(){
        boolean exceptionThrown1 = false;
        boolean exceptionThrown2 = false;
        boolean exceptionThrown3 = false;
        @SuppressWarnings("unused")
        Client client;

        try {
            client = new Client(1, "SWAG");
        } catch (IllegalArgumentException e) {
            exceptionThrown1 = true;
        }
        try {
            client = new Client(1, "a m");
        } catch (IllegalArgumentException e) {
            exceptionThrown2 = true;
        }
        try {
            client = new Client(1, "");
        } catch (IllegalArgumentException e) {
            exceptionThrown3 = true;
        }
        
        assertTrue("An invalid Client type did not throw an IllegalArgumentException.", exceptionThrown1);
        assertTrue("An invalid Client type did not throw an IllegalArgumentException.", exceptionThrown2);
        assertTrue("An invalid Client type did not throw an IllegalArgumentException.", exceptionThrown3);
    }

    @Test
    public void testInvalidClientID(){
        boolean exceptionThrown = false;

        try {
            @SuppressWarnings("unused")
            Client client1 = new Client(5, "AM");
            Client client2 = new Client(0, "FM");
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }

        assertTrue("An invalid Client ID did not throw an IllegalArgumentException.", exceptionThrown);
    }

    @Test
    public void testIDTypeMismatch(){
        boolean exceptionThrown = false;

        try {
            @SuppressWarnings("unused")
            Client client1 = new Client(4, "AM");
            Client client2 = new Client(2, "CU8");
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }

        assertTrue("An unmatching ID and type did not throw an IllegalArgumentException.", exceptionThrown);
    }

}
