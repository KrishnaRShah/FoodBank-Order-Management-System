package FPCode;

public class Client {
    private Nutrients nutrientData;
    private final int CLIENT_ID;
    private final String CLIENT_TYPE;

    // temporary bullshit
    Client(int id, String type){
        this.CLIENT_ID = id;
        this.CLIENT_TYPE = type;
        if(type.toUpperCase().equals("AM")){
            nutrientData = new Nutrients(16, 28, 26, 30, 2500);
        }
        if(type.toUpperCase().equals("AF")){
            nutrientData = new Nutrients(16, 28, 26, 30, 2000);
        }
        if(type.toUpperCase().equals("CO8")){
            nutrientData = new Nutrients(21, 33, 31, 15, 2200);
        }
        if(type.toUpperCase().equals("CU8")){
            nutrientData = new Nutrients(21, 33, 31, 15, 1400);
        }
        else{
            throw new IllegalArgumentException();
        }
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
