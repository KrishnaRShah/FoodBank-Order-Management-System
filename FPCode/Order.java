package FPCode;

import java.util.ArrayList;
import java.util.List;

public class Order {

    public List<Hamper> hamperList = new ArrayList<>();
    public int[] numClientTypes;


    public Order(int numberOfClients){

        this.hamperList = new ArrayList<>();

        this.numClientTypes = new int[numberOfClients];

        for(int i = 0; i < numberOfClients; i++){       //Give ID values
            numClientTypes[i] = i+1;
        }
    }

    public void addHamper(Hamper hamper){
        this.hamperList.add(hamper);
    }

    public Hamper getHampterAtIndex(int index){
        return this.hamperList.get(index);
    }

    public List<Hamper> getHamper(){
        return this.hamperList;
    }
    
    
    
}
