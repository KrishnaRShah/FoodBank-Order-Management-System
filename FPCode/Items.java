/*
@author Ryan Mailhiot 30080009<a
href="mailto:ryan.mailhiot@ucalgary.ca">ryan.mailhiot@ucalgary.ca</a>
@version 0.1 
@since 0.0
*/

package FPCode;

public class Items {
    private final Nutrients NUTRIENT_DATA; // Will never be set manually for any reason
    private final int ITEM_ID;
    private final String ITEM_NAME;

    public Items (int id, String name, Double[] nutrientsInfo){
        this.NUTRIENT_DATA = new Nutrients(nutrientsInfo[0], nutrientsInfo[1], nutrientsInfo[2], nutrientsInfo[3], nutrientsInfo[4]);
        this.ITEM_NAME = name;
        this.ITEM_ID = id;
    }


    public Nutrients getNutrientData() {
        return this.NUTRIENT_DATA;
    }


    public int getITEM_ID() {
        return this.ITEM_ID;
    }


    public String getITEM_NAME() {
        return this.ITEM_NAME;
    }



}
