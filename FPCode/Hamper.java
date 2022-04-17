/**
 * @author Krishna Shah 30114067<a
 * href="mailto:krishna.shah@ucalgary.ca">krishna.shah@ucalgary.ca</a>
 * @author Danny Picazo 301271082<a
 * href="mailto:daniel.picazo@ucalgary">daniel.picazo@ucalgary.ca</a>
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
     * GUI's constructor for Hamper. Takes in an integer array of size 4 (or atleast reads the first 4 elements) which will be used
     * to construct clients to be used in the calculation of hamper nutrients. 
     * @param numClientTypes Int array of size 4.
     */
    public Hamper(int[] numClientTypes) throws NotEnoughFoodException{
        this.clientArray = new Vector<Client>();

        //iterates through the entire array, which contains the # of each client at a given index
        //numClientTypes[0] = # of adult males
        //numClientTypes[1] = # of adult females
        //numClientTypes[2] = # of child over 8
        //numClientTypes[3] = # of child under 8
        
        for(int i = 0; i < numClientTypes.length; i++){ 

            //Add the number of clients needed for that type to the client array
            //by iterating through the size of the int in the index

            for(int j = 0; j < numClientTypes[i]; j++){ 

                //Creates a new client element with the current i value
                //i = 0 = AM
                //i = 1 = AF
                //i = 2 = CO8
                //i = 3 = CU8

                Client testC = new Client(i+1);
                this.clientArray.addElement(testC); 

            }
        }

        calcHamperNutrients();
        // test logging
        // System.out.println(this.hamperNutrients.getGrainCals());
        // System.out.println(this.hamperNutrients.getFruitCals());
        // System.out.println(this.hamperNutrients.getProteinCals());
        // System.out.println(this.hamperNutrients.getOtherCals());
        // System.out.println(this.hamperNutrients.getTotalCalories());

        buildItemList();

    }

    /**
     * Alternative constructor for use as objects. Will use the argument to fill Nutrients.
     * @author Danny Picazo
     * @param listOfItems Vector of Items in the Hamper.
     */
    public Hamper(Vector<Items> listOfItems){
        // copy the list of items
        this.itemsList = new Vector<Items>();
        for(Items i : listOfItems){
            this.itemsList.add(i);
        }
        // get all the nutrient calories
        double grains = 0;
        double fruits = 0;
        double protein = 0;
        double other = 0;
        double total = 0;
        for(Items item : listOfItems){
            grains += item.getNutrientData().getGrainCals();
            fruits += item.getNutrientData().getFruitCals();
            protein += item.getNutrientData().getProteinCals();
            other += item.getNutrientData().getOtherCals();
            total += item.getNutrientData().getTotalCalories();
        }
        // convert nutrient calories to percentages of the total calories
        // to send as arguments to the Nutrients constructor
        grains = grains * 100 / total;
        fruits = fruits * 100 / total;
        protein = protein * 100 / total;
        other = other * 100 / total;
        // voila
        this.hamperNutrients = new Nutrients(grains, fruits, protein, other, total);
    }


    /**
     * @author Krishna Shah
     * Calculates the hampers nutrients using the data stored in the clientArray.
     */
    public void calcHamperNutrients(){
        // System.out.println("Calculates Nutrients");
        // Creating variables to hold total macro values, which we will average
        double avgGrains = 0.0;
        double avgFruits = 0.0;
        double avgProtein = 0.0;  
        double avgOther = 0.0;
        double wholeCalories = 0.0;
        //Using a for loop to go through the clientArray and add all macro values, for all clients
        for(int i = 0; i < clientArray.size(); i++){
            avgGrains = avgGrains + this.clientArray.get(i).getNutrientData().getGrainCals();
            avgFruits = avgFruits + this.clientArray.get(i).getNutrientData().getFruitCals();
            avgProtein = avgProtein + this.clientArray.get(i).getNutrientData().getProteinCals();
            avgOther = avgOther + this.clientArray.get(i).getNutrientData().getOtherCals();
            wholeCalories = wholeCalories + this.clientArray.get(i).getNutrientData().getTotalCalories();
        }
        //creates a Nutrient object that is the average macro's needed for the clientArray and their total calories
        // this.hamperNutrients = new Nutrients(avgGrains/(clientArray.size()), avgFruits/(clientArray.size()), 
        //     avgProtein/(clientArray.size()), avgOther/(clientArray.size()), wholeCalories);

        // convert the temp variables into percentages of the total cals
        avgGrains = avgGrains * 100 / wholeCalories;
        avgFruits = avgFruits * 100 / wholeCalories;
        avgProtein = avgProtein * 100 / wholeCalories;
        avgOther = avgOther * 100 / wholeCalories;

        // test logging
        // System.out.println(avgGrains);
        // System.out.println(avgFruits);
        // System.out.println(avgProtein);
        // System.out.println(avgOther);
        // System.out.println("---------------------------------------------------");
        
        this.hamperNutrients = new Nutrients(avgGrains, avgFruits, avgProtein, avgOther, wholeCalories);
    }

    /**
     * @author Danny Picazo
     * @throws NotEnoughFoodException
     */
    public void buildItemList() throws NotEnoughFoodException{
        // System.out.println("Starts");
        // all items currently in the database
        DatabaseItems db = new DatabaseItems();
        Items[] stock = db.getDatabaseItems();

        // set default hamper to something absurd
        double[] overkill = {25, 25, 25, 25, 999999};
        Items heartattack = new Items(-1, "pure grease", overkill);
        Vector<Items> death = new Vector<Items>();
        death.add(heartattack);
        Hamper cardiacarrest = new Hamper(death);
        Hamper bestHamper = cardiacarrest;
        Hamper[] bestHampers = new Hamper[stock.length];

        // make all possible hampers
        for (int i = 0; i < stock.length; i++) {
            Vector<Items> combination = new Vector<Items>();
            // System.out.println("swag " + i);
            bestHamper = buildListHelper(combination, i, stock, bestHamper);
            // save all the best hampers
            bestHampers[i] = bestHamper;
        }

        try {
            // from best hampers, choose the best
            for(Hamper h : bestHampers){
                if(h.getHamperNutrients().getTotalCalories() < bestHamper.getHamperNutrients().getTotalCalories()){
                    bestHamper = h;
                }
            }
        } catch (NullPointerException e) {
            // bestHampers is empty?
            throw new NotEnoughFoodException();
        }

        // did not find a better hamper than the default absurd hamper
        // that met the requirements, thus it did not find a suitable hamper
        if(bestHamper.equals(cardiacarrest)){
            System.out.println("ERROR: Throwing NotEnoughFoodException...");
            throw new NotEnoughFoodException();
        }

        // logging the hamper nutrients
        System.out.println("BEST HAMPER:  "+bestHamper.getHamperNutrients().getTotalCalories() + " calories");
        System.out.print("Grains: " + bestHamper.getHamperNutrients().getGrains() + "%, ");
        System.out.println(bestHamper.getHamperNutrients().getGrainCals() + " Cals");
        System.out.print("Fruits: " + bestHamper.getHamperNutrients().getFruits() + "%, ");
        System.out.println(bestHamper.getHamperNutrients().getFruitCals() + " Cals");
        System.out.print("Protein: " + bestHamper.getHamperNutrients().getProtein() + "%, ");
        System.out.println(bestHamper.getHamperNutrients().getProteinCals() + " Cals");
        System.out.print("Other: " + bestHamper.getHamperNutrients().getOther() + "%, ");
        System.out.println(bestHamper.getHamperNutrients().getOtherCals() + " Cals");

        // save best hamper item combo to this.itemsList
        this.itemsList = bestHamper.getItemsList();
        // if(bestHamper.getItemsList().isEmpty()){
        //     System.out.println("THE HAMPER ITEM LIST IS EMPTY!!!");
        // }

        // logging the hamper items
        System.out.println("Hamper consists of: ");
        for(Items a : itemsList){
            System.out.println(a.getItemName());
        }

        // update database
        Items[] items = new Items[this.itemsList.size()];
        int i = 0;
        for(Items it : this.itemsList){
            items[i] = it;
            i++;
        }
        db.updateDatabase(items);
    }
    /**
     * @author Danny Picazo
     * @throws NotEnoughFoodException
     * @param current The current Vector of Items in the recursion process.
     * @param index The current index of the stock array.
     * @param stock The list of Items in the database.
     * @param best The currently best hamper combination. 
     * @return The best combination from that recursion. 
     */
    private Hamper buildListHelper(Vector<Items> current, int index, Items[] stock, Hamper best) throws NotEnoughFoodException{
        // add item from stock
        current.add(stock[index]);
        
        // check if hamper meets requirements
        Hamper temp = new Hamper(current);
        if(temp.getHamperNutrients().getGrainCals() >= this.hamperNutrients.getGrainCals()
            && temp.getHamperNutrients().getFruitCals() >= this.hamperNutrients.getFruitCals()
            && temp.getHamperNutrients().getProteinCals() >= this.hamperNutrients.getProteinCals()
            && temp.getHamperNutrients().getOtherCals() >= this.hamperNutrients.getOtherCals()){
                // if it does, then check if its better than current best
                if(temp.getHamperNutrients().getTotalCalories() < best.getHamperNutrients().getTotalCalories()){
                    best = temp;
                    // System.out.println("found a new best!");
                }
                else{
                    current.remove(stock[index]);
                    return best;
                }
        }

        // recurse through the indices
        for(int i = index+1; i < stock.length; i++){
            best = buildListHelper(current, i, stock, best);
        }

        // remove item from the hamper
        current.remove(stock[index]);

        // return what the best was
        return best;
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
