/**
 * @author Krishna Shah 30114067<a
 * href="mailto:krishna.shah@ucalgary.ca">krishna.shah@ucalgary.ca</a>
 * @version 0.6 
 * @since 0.0
 */

package FPCode;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Order is a class that will store multiple hampers in an arraylist, so that
 * an item list can be created for orders that require multiple hampers.
 */

public class Order {

    public List<Hamper> hamperList = new ArrayList<>();


    /**
     * Constructor for Order, this will create a new arraylist that will store multiple hampers
    */

    public Order(){
        this.hamperList = new ArrayList<>();
    }

    /**
     * Method in Order, to add hampers to the existing arraylist
    */

    public void addHamper(Hamper hamper){
        this.hamperList.add(hamper);
    }

    /**
     * Getter in Order, recieves an index that will then return the hamper
     * that is at the given index in the arraylist
    */

    public Hamper getHampterAtIndex(int index){
        return this.hamperList.get(index);
    }

    /**
     * Getter in Order, that will return the entire arraylist with all the hampers
    */

    public List<Hamper> getHamper(){
        return this.hamperList;
    }
    
    
    
}
