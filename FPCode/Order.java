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

public class Order implements Output{
    public ArrayList<int[]>clientsPerHamper=new ArrayList<int[]>();
    public List<Hamper> hamperList = new ArrayList<>();


    /**
     * Constructor for Order, this will create a new arraylist, then create a hamper for each index
     * in the arrayList, and then add the Hamper to the arrayList of Hampers
    */
    public Order(){}
    public Order(ArrayList<int[]> clientArray){
         this.clientsPerHamper=clientArray;
        this.hamperList = new ArrayList<>();
        for(int i = 0; i < clientArray.size(); i++)
        {
            Hamper addHamper = new Hamper(clientArray.get(i));
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
    
    public void printError(){ 
        try{
        File file1=new File("Order");    
        FileWriter fw=new FileWriter(file1);
    PrintWriter pw=new PrintWriter(fw);
     pw.println("Not enough food");}
     catch(IOException e){
        System.err.println("couldn't print to file");
    }
        
}
    public String formatString(){ return"";}
    public void printToTXT(){
        try{
            File file1=new File("Order");
        
            FileWriter fw=new FileWriter(file1);
        
            PrintWriter pw=new PrintWriter(fw);
        
        pw.println("Original Hamper Request:");
        for (int k=0;k<hamperList.size();k++){
            pw.println("hamper ");
            pw.print(k+1);
            pw.print(": ");
            if(clientsPerHamper.get(k)[0]!=0){
                pw.print("Adult Males: ");
                pw.print(clientsPerHamper.get(k)[0]);
            }
            if(clientsPerHamper.get(k)[1]!=0){
                pw.print("Adult Females: ");
                pw.print(clientsPerHamper.get(k)[1]);
            }
            if(clientsPerHamper.get(k)[2]!=0){
                pw.print("Children Over 8: ");
                pw.print(clientsPerHamper.get(k)[2]);
            }
            if(clientsPerHamper.get(k)[3]!=0){
                pw.print("Children Under 8:");
                pw.print(clientsPerHamper.get(k)[3]);
            }
        }
            
    
        for (int i=0;i<hamperList.size();i++){
            pw.println("hamper ");
            pw.print(i+1);
            pw.print(" Items:");
            pw.println();
            for(int j=0;j<hamperList.get(i).getItemsList().size();j++){
                pw.println(hamperList.get(i).getItemsList().get(j).getItemID());
                pw.print(",    ");
                pw.print(hamperList.get(i).getItemsList().get(j).getItemName());
            }
          
            System.out.println();
            System.out.println();

        }
        pw.close();
    }
    catch(IOException e){
        System.err.println("couldn't print to file");
    }

    }

    
    
    
}
