/**
 * @author Danny Picazo 301271082<a
 * href="mailto:daniel.picazo@ucalgary">daniel.picazo@ucalgary.ca</a>
 * @author German (David) Fonseca 30061209<a
 * href="mailto:german.fonseca@ucalgary.ca">german.fonseca@ucalgary.ca</a>
 * @version 0.6 
 * @since 0.0
 */


package FPCode;
import java.util.*;

/**
 * Class Hamper is a custom object class that contains information about clients, nutritional data for people using the hamper
 * and the days needed for the hamper. This acts as the "connecting" point between all other connections in the program.
 * Hamper interacts with almost all other files in some way and is key to understanding the program as a whole. 
 * 
 * Hamper will build the most efficient "Hamper" of food for the given clients for the days needed. 
 * 
 * UPDATE THE REST OF THIS TO INCLUDE RELEVANT COMMENTS ABOUT HAMPER.
 */
public class Hamper{
    public Vector<Client> clientArray;
    public Vector<Items> itemsList;
    public Nutrients hamperNutrients;
    public int daysNeeded;

    /**
     * Constructor for Hamper. Takes in an integer array of size 4 (or atleast reads the first 4 elements) which will be used
     * to construct clients to be used in the calculation of hamper nutrients. 
     * @param numClientTypes Int array of size 4.
     */
    public Hamper(int[] numClientTypes){
        
        for(int i = 0; i < numClientTypes.length; i++){
            this.clientArray.addElement(new Client(numClientTypes[i])); //Hard-Coded an client type for now
        }
    }

    /**
     * @author David Fonseca
     * Calculates the hampers nutrients using the data stored in the clientArray.
     */
    public void calcHamperNutrients(){

        // Creating variables to hold total macro values, which we will average
        double avgGrains = 0.0;
        double avgFruits = 0.0;
        double avgProtein = 0.0;  
        double avgOther = 0.0;
        double wholeCalories = 0.0;
        //Using a for loop to go through the clientArray and add all macro values, for all clients
        for(int i = 0; i < clientArray.size(); i++){
            avgGrains = avgGrains + this.clientArray.get(i).getNutrientData().getGrains();
            avgFruits = avgFruits + this.clientArray.get(i).getNutrientData().getFruits();
            avgProtein = avgProtein + this.clientArray.get(i).getNutrientData().getProtein();
            avgOther = avgOther + this.clientArray.get(i).getNutrientData().getOther();
            wholeCalories = wholeCalories + this.clientArray.get(i).getNutrientData().getTotalCalories();
        }
        //creates a Nutrient object that is the average macro's needed for the clientArray and their total calories
        this.hamperNutrients = new Nutrients(avgGrains/(clientArray.size()), avgFruits/(clientArray.size()), 
            avgProtein/(clientArray.size()), avgOther/(clientArray.size()), wholeCalories);

    }

    /**
     * INCOMPLETE
     * @throws NotEnoughFoodException
     */
    public void buildItemList() throws NotEnoughFoodException{
        

    }

    /**
     * Getter for the clientArray
     * @return Vector<client>
     */
    public Vector<Client> getClientArray(){
        return this.clientArray;
    }

    /**
     * Getter for the getItemsList
     * @return Vector<Items> (Items object)
     */
    public Vector<Items> getItemsList(){
        return this.itemsList;
    }

    /**
     * Getter for the getHamperNutrients
     * @return Nutrients object
     */
    public Nutrients getHamperNutrients(){
        return this.hamperNutrients;
    }

    /**
     * Getter for the daysNeeded
     * @return Int
     */
    public int getDaysNeeded(){
        return this.daysNeeded;
    }

    
}
