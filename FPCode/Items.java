package FPCode;

public class Items {
    private final Nutrients NUTRIENT_DATA;
    private final int ITEM_ID;
    private final String ITEM_NAME;

    Items(int id, String name, double[] nutrientInfo){
        this.ITEM_ID = id;
        this.ITEM_NAME = name;
        if(nutrientInfo.length != 5){
            throw new IllegalArgumentException();
        }
        this.NUTRIENT_DATA = new Nutrients(nutrientInfo[0], nutrientInfo[1],
                            nutrientInfo[2], nutrientInfo[3], nutrientInfo[4]);
    }

    public Nutrients getNutrientData() {
        return this.NUTRIENT_DATA;
    }

    public int getItemID() {
        return this.ITEM_ID;
    }

    public String getItemName() {
        return this.ITEM_NAME;
    }

}
