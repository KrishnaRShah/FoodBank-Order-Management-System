package FPCode;
import java.util.*;

public class Hamper{
    public Vector<Client> clientArray;
    public Vector<Items> itemsList;
    public Nutrients hamperNutrients;
    public int daysNeeded;

    public Hamper(int[] numClientTypes){
        
        for(int i = 0; i < numClientTypes.length; i++){
            this.clientArray.addElement(new Client(numClientTypes[i], "AM")); //Hard-Coded an client type for now
        }
    }

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

    public void buildItemList() throws NotEnoughFoodException{
        

    }

    public Vector<Client> getClientArray(){
        return this.clientArray;
    }
    public Vector<Items> getItemsList(){
        return this.itemsList;
    }
    public Nutrients getHamperNutrients(){
        return this.hamperNutrients;
    }
    public int getDaysNeeded(){
        return this.daysNeeded;
    }

    
}
