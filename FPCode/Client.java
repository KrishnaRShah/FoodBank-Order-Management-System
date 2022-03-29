package FPCode;

public class Client{
    private Nutrients nutrientData;
    private final int CLIENT_ID;
    private final String CLIENT_TYPE;


    public Client(int id){
        this.CLIENT_ID = id;
        this.CLIENT_TYPE = " ";//FIX
    }

    public void setNutrients(Nutrients nutrientData){
        this.nutrientData = nutrientData;
    }

    public Nutrients getNutrientData() {
        return this.nutrientData;
    }

    public int getCLIENT_ID() {
        return this.CLIENT_ID;
    }


    public String getCLIENT_TYPE() {
        return this.CLIENT_TYPE;
    }



}