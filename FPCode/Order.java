package FPCode;

import java.util.ArrayList;
import java.util.List;

public class Order {

    public List<Hamper> hamperList = new ArrayList<>();


    public Order(){
        Hamper newHamper = new Hamper(numClientTypes);
        this.hamperList.add(newHamper);
        
    }

    public Hamper getHampterAtIndex(int index){
        return this.hamperList.get(index);
    }

    public List<Hamper> getHamper(){
        return this.hamperList;
    }
    
    
    
}
