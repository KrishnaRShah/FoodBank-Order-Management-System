/**
 * @author Danny Picazo 301271082<a
 * href="mailto:daniel.picazo@ucalgary">daniel.picazo@ucalgary.ca</a>
 * @author Ryan Mailhiot 30080009<a
 * href="mailto:ryan.mailhiot@ucalgary.ca ">ryan.mailhiot@ucalgary.ca</a>
 * @version 0.7 
 * @since 0.0
 */



package FPCode;

/**
 * The Items class stores data about an "Item" which can be classified as an article of food. It will contain
 * the ITEM_ID from the database it was gotten, the NUTRIENT_DATA of object type Nutrient, as well as the ITEM_NAME
 * which is a brief description of the item. 
 * @param NUTRIENT_DATA is a Nutrients object that stores the nutrient data of the item 
 * @param ITEM_ID is a unique id from the Database for the given item
 * @param ITEM_NAME is a brief description of the item
 */
public class Items {
    private final Nutrients NUTRIENT_DATA;
    private final int ITEM_ID;
    private final String ITEM_NAME;
    /**
     * Items constructor takes in an id, name, and the nutrientInfo to fill out the relevant data and store it for future use. 
     * @param id int 
     * @param name String
     * @param nutrientInfo double Array of length 5 in order Grains, Fruits, Protein, Other, Total Calories.
     */
    Items(int id, String name, double[] nutrientInfo){
        this.ITEM_ID = id;
        this.ITEM_NAME = name;
        if(nutrientInfo.length != 5){
            throw new IllegalArgumentException();
        }
        this.NUTRIENT_DATA = new Nutrients(nutrientInfo[0], nutrientInfo[1],
                            nutrientInfo[2], nutrientInfo[3], nutrientInfo[4]);
    }

    /**
     * Getter for the nutrient data
     * @return Nutrients object
     */
    public Nutrients getNutrientData() {
        return this.NUTRIENT_DATA;
    }

    /**
     * Getter for the item ID
     * @return Int
     */
    public int getItemID() {
        return this.ITEM_ID;
    }

    /**
     * Getter for the item name
     * @return String
     */
    public String getItemName() {
        return this.ITEM_NAME;
    }

}
