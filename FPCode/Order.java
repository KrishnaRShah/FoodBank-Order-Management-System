/**
 * @author Krishna Shah 30114067<a & German Fonseca 30061209
 * href="mailto:krishna.shah@ucalgary.ca">krishna.shah@ucalgary.ca</a>
 * @version 2.0
 * @since 0.0
 */

package FPCode;
import java.util.ArrayList;
import java.util.List;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

/**
 * Class Order is a class that will store multiple hampers in an arraylist, so that
 * an item list can be created for orders that require multiple hampers.
 */

public class Order implements Output{
    public ArrayList<int[]>clientsPerHamper=new ArrayList<int[]>();
    public ArrayList<Integer> daysNeeded = new ArrayList<Integer>();
    public List<Hamper> hamperList = new ArrayList<>();//local variables


    /**
     * Constructor for Order, this will create a new arraylist, then create a hamper for each index
     * in the arrayList, and then add the Hamper to the arrayList of Hampers
    */
    public Order(){}
    public Order(ArrayList<int[]> clientArray, ArrayList<Integer> daysNeeded) throws NotEnoughFoodException, FailedToConnectException{
        this.clientsPerHamper=clientArray;
        this.daysNeeded = daysNeeded;
        this.hamperList = new ArrayList<>();
        for(int i = 0; i < clientArray.size(); i++)
        {
            Hamper addHamper = new Hamper(clientArray.get(i), daysNeeded.get(i));
            this.hamperList.add(addHamper);
        }
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

    /**
     * Prints an error file
    */
    
    public void printError(){ 
        try{
            File file1=new File("Order.txt");    
            FileWriter fw=new FileWriter(file1);
            PrintWriter pw=new PrintWriter(fw);
            pw.println("Not enough food");
            pw.close();
        }
        catch(IOException e){
        System.err.println("couldn't print to file");
        }
        
    }

    /**
     * Method to format a String
    */

    public String formatString(){ return"";}

    /**
     * Creates a new Order.txt file that will hold the order
     * Prints original Hamper Request
     * Goes through the HamperList and adds the number of clients per Hamper
     * Adds Item's list to the output file
    */

    public void printToTXT(){
        try{
            File file1=new File("Order.txt");
        
            FileWriter fw=new FileWriter(file1);
        
            PrintWriter pw=new PrintWriter(fw);
        
        pw.println("Original Hamper Request:");
        for (int k=0;k<hamperList.size();k++){
            pw.println();
            pw.print("Hamper ");
            pw.print(k+1);
            pw.print(" (for ");
            pw.print(daysNeeded.get(k));
            pw.print(" days)");
            pw.print(": ");
            
            if(clientsPerHamper.get(k)[0]!=0){
                pw.print("Adult Males: ");
                pw.print(clientsPerHamper.get(k)[0]);
                pw.print("   ");
            }
            if(clientsPerHamper.get(k)[1]!=0){
                pw.print("Adult Females: ");
                pw.print(clientsPerHamper.get(k)[1]);
                pw.print("   ");
            }
            if(clientsPerHamper.get(k)[2]!=0){
                pw.print("Children Over 8: ");
                pw.print(clientsPerHamper.get(k)[2]);
                pw.print("   ");
            }
            if(clientsPerHamper.get(k)[3]!=0){
                pw.print("Children Under 8:");
                pw.print(clientsPerHamper.get(k)[3]);
                pw.print("   ");
            }
        }
            
    
        for (int i=0;i<hamperList.size();i++){
            pw.println();
            pw.println();
            pw.print("Hamper ");
            pw.print(i+1);
            pw.print(" Items:");
            pw.println();
            for(int j=0;j<hamperList.get(i).getItemsList().size();j++){
                pw.print(hamperList.get(i).getItemsList().get(j).getItemID());
                pw.print(",    ");
                pw.print(hamperList.get(i).getItemsList().get(j).getItemName());
                pw.println();
            }
        }
        pw.close();
    }
    catch(IOException e){
        System.err.println("couldn't print to file");
    }

    }

    
    
    
}
