package FPCode;

public class Client {
    private Nutrients nutrientData;
    private final int CLIENT_ID;
    private final String CLIENT_TYPE;

    // temporary hardcoded client type values until we figured out
    // whatever the hell it's supposed to be
    Client(int id, String type){
        // 4 possible IDs
        if(id < 1 || id > 4){
            throw new IllegalArgumentException();
        }
        // set nutrients based on the type
        String temp = type.toUpperCase();
        switch (temp) {
            case "AM":
                nutrientData = new Nutrients(16, 28, 26, 30, 2500);
                type = "Adult Male";
                // If AM is always 1, then why do we even need to send it
                // as an argument? Can't we just send the client ID to the
                // constructor and have this switch-case set types? - Danny
                if(id != 1) throw new IllegalArgumentException();
                break;
            case "AF":
                nutrientData = new Nutrients(16, 28, 26, 30, 2000);
                type = "Adult Female";
                if(id != 2) throw new IllegalArgumentException();
                break;
            case "CO8":
                nutrientData = new Nutrients(21, 33, 31, 15, 2200);
                type = "Child Over 8";
                if(id != 3) throw new IllegalArgumentException();
                break;
            case "CU8":
                nutrientData = new Nutrients(21, 33, 31, 15, 1400);
                type = "Child Under 8";
                if(id != 4) throw new IllegalArgumentException();
                break;
            default:
                type = "";
                throw new IllegalArgumentException();
        }
        // no error, so commence forth
        this.CLIENT_ID = id;
        this.CLIENT_TYPE = type;
    }

    public Nutrients getNutrientData() {
        return this.nutrientData;
    }

    public void setNutrientData(Nutrients nutrientData) {
        this.nutrientData = nutrientData;
    }

    public int getClientID() {
        return this.CLIENT_ID;
    }

    public String getClientType() {
        return this.CLIENT_TYPE;
    }

}
