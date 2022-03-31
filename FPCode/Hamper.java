package FPCode;
import java.util.*;

public class Hamper extends Exception{
    public Vector<Client> clientArray;
    public Vector<Items> itemsList;
    public Nutrients hamperNutrients;
    public int daysNeeded;

    public Hamper(int[] numClientTypes){

    }

    private void calcHamperNutrients(){

    }

    private void buildItemList() throws NotEnoughFoodException{

    }

    public Client[] getClientArray(){
       

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
