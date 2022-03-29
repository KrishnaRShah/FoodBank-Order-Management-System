package FPCode;

public class Items {
    private Nutrients nutrientData;
    private final int ITEM_ID;
    private final String ITEM_NAME;

    public Items (int id, String name, Double[] nutrientsInfo){
        this.nutrientData = new Nutrients(nutrientsInfo[0], nutrientsInfo[1], nutrientsInfo[2], nutrientsInfo[3], nutrientsInfo[4]);
        this.ITEM_NAME = name;
        this.ITEM_ID = id;
    }


    public Nutrients getNutrientData() {
        return this.nutrientData;
    }

    public void setNutrientData(Nutrients nutrientData) {
        this.nutrientData = nutrientData;
    }

    public int getITEM_ID() {
        return this.ITEM_ID;
    }


    public String getITEM_NAME() {
        return this.ITEM_NAME;
    }



}
