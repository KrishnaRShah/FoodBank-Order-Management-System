/**
 * @author German Fonseca 30061209
 * href="mailto:german.fonseca@ucalgary.ca">german.fonseca@ucalgary.ca</a>
 * @version 1.8 
 * @since 0.0
 */

package FPCode;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.EventQueue;
import java.util.regex.*;
import java.util.ArrayList;


public class Inter extends JFrame implements ActionListener, MouseListener {
//static public int[] clients=new int[4];
    
    static private String adultFemale;
    static private String adultMale;
    static private String childOver8; //Fields where information about how many people are in each hamper will be stored
    static private String childUnder8;
    static private String daysNeeded;
    
    static private JLabel inst;
    static private JLabel aFL;
    static private JLabel aML;//Jlabels to inform user on how to use GUI
    static private JLabel cOL;
    static private JLabel cUL;
    static private JLabel dN;

    
    static private JTextField femaleInput;
    static private JTextField maleInput;
    static private JTextField childUnder;//Contain user input
    static private JTextField childOver;
    static private JTextField daysInput;


    static private JButton submit;//button to add hamoer to order
    static private JButton newOrder;//button to specify end of order

    static public ArrayList<int[]> numberOfHampers=new ArrayList<int[]>();
    static public ArrayList<Integer> daysNeededArray = new ArrayList<Integer>();

         /**
    *constructor specifiying GUI window details
    */
    public Inter(){
        super("Generate Hamper");
        createWindow();
        setSize(550,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
     /**
    *this method instantiates all fields and adds them into the GUI window to be visible by the user
    */
    public void createWindow(){
        inst=new JLabel("Enter required information to generate hamper, please enter only numeric characters (0-9)");
        aFL=new JLabel("Enter number of adult females");
        aML=new JLabel("                      Enter number of adult males");
        cOL=new JLabel("Enter number of children over 8");
        cUL=new JLabel("                              Enter number of children under 8");
        dN=new JLabel("                        Enter number of days needed for this hamper");


        femaleInput=new JTextField("",15);
        maleInput=new JTextField("",15);
        childOver=new JTextField("",15);
        childUnder=new JTextField("",15);
        daysInput=new JTextField("",15);


        femaleInput.addMouseListener(this);
        maleInput.addMouseListener(this);
        childOver.addMouseListener(this);//add mouse listeners to to text fields used for user input
        childUnder.addMouseListener(this);

        submit=new JButton("SUBMIT");
        submit.addActionListener(this);
        newOrder=new JButton("END CURRENT ORDER");
        newOrder.addActionListener(this);
        
        JPanel headerPanel=new JPanel();//panel that will contain text instructions on use of the GUI
        headerPanel.setLayout(new FlowLayout());

        JPanel panel2=new JPanel();//this pannel will allow for input to be received from user 
        panel2.setLayout(new FlowLayout());

        JPanel submitPanel=new JPanel();//this panel will confirm the input of user and begin forming hampers in an order
        submitPanel.setLayout(new FlowLayout());
        JPanel newOrderPanel=new JPanel();
        newOrderPanel.setLayout(new FlowLayout());
        
        headerPanel.add(inst);//adding all instantiations to GUI window
        panel2.add(aFL);
        panel2.add(femaleInput);
        panel2.add(aML);
        panel2.add(maleInput);
        panel2.add(cOL);
        panel2.add(childOver);
        panel2.add(cUL);
        panel2.add(childUnder);
        panel2.add(dN);
        panel2.add(daysInput);
        submitPanel.add(submit);
        submitPanel.add(newOrder);

        this.add(headerPanel,BorderLayout.NORTH);//specifying placement of the panels
        this.add(panel2,BorderLayout.CENTER);
        this.add(submitPanel,BorderLayout.SOUTH);
        
    }
      /**
    *this function specifies procedure of the GUI when an action is performed
    */
    public void actionPerformed(ActionEvent event){

        

        adultFemale=(femaleInput.getText());//takes input from text fields and stores input in these fields
        adultMale=(maleInput.getText());
        childOver8=(childOver.getText());
        childUnder8=(childUnder.getText());
        daysNeeded=(daysInput.getText());


        if(event.getSource()==submit){//if hamper is submitted, do the foollowing

            if(validateIn()){//check that input is made up of numerical characters
                numberOfHampers.add(new int[]{Integer.parseInt(adultMale),Integer.parseInt(adultFemale),Integer.parseInt(childOver8),Integer.parseInt(childUnder8)});
                daysNeededArray.add(Integer.parseInt(daysNeeded));
                JOptionPane.showMessageDialog(this,"Successfully added hamper to order");

                //if input is valid, add input to the list of hampers
            }
  
            else{//if input is invalid, tell user input is invalid and allow them to try again
                JOptionPane.showMessageDialog(this,"Illegal character entered, please use only numerical characters (0-9).");
            }
        }
        if(event.getSource()==newOrder){//if the end order button is pressed do the following:
            JOptionPane.showMessageDialog(this,"Please wait while order is processed.");//tell user to be patient to allow the order to be processed 
            Order a;
            try{//try to make a new order with the client number input 

                
            a=new Order(numberOfHampers, daysNeededArray);
                
                
            a.printToTXT();//once order is complete, create output on a txt file
            JOptionPane.showMessageDialog(this,"Your order is complete.");//tell user order is complete
        }
            catch (NotEnoughFoodException e){//if not enough food is available to fill out order, do the following:
                JOptionPane.showMessageDialog(this,"Not enough food to fill your order.");//tell user not enough food is available
                System.out.println("SUCCESSFUL ERROR: NotEnoughFoodException was thrown.");
                Order b=new Order();//instantiate new order to generate output file
                b.printError();//generate error output file
            }
            catch (FailedToConnectException z){//if there is an error connecting to database, inform the user
                JOptionPane.showMessageDialog(this,"Failed to connect to database. Check url, username and/or password and try again.");//tell user not enough food is available

            }
            numberOfHampers.clear();//once order is done, clear out contents of order to restart process
        }
    }
    
      /**
    *method to check for input being only numerical characters
    */
    private boolean validateIn(){
        String regex="[0-9]+";
        Pattern p=Pattern.compile(regex);
        Matcher f=p.matcher(adultFemale);
        Matcher m=p.matcher(adultMale);
        Matcher o=p.matcher(childOver8);
        Matcher u=p.matcher(childUnder8);
        Matcher d=p.matcher(daysNeeded);
        if(adultFemale.equals("0")&&adultMale.equals("0")&&childOver8.equals("0")&&childUnder8.equals("0")){
            JOptionPane.showMessageDialog(this,"Must enter at least 1 client to generate a hamper");
            return false; 
        }
        if(f.matches()&&m.matches()&&o.matches()&&u.matches()&&d.matches()){
            return true;
        }
        else{
            return false;
        }
    }

    public void mouseEntered(MouseEvent event){}
    public void mouseExited(MouseEvent event){}
    public void mousePressed(MouseEvent event){

    }
    public void mouseReleased(MouseEvent event){}
    
    
    /**
    *specifies function of GUI when mouse is clicked on the GUI
    */
    public void mouseClicked(MouseEvent event){
        if (event.getSource().equals(femaleInput)){
            femaleInput.setText("");//clears out old input to ease the process of making a new order
        }
        if (event.getSource().equals(maleInput)){
            maleInput.setText("");
        }
        if (event.getSource().equals(childOver)){
            childOver.setText("");
        }
        if (event.getSource().equals(childUnder)){
            childUnder.setText("");
        }

    }


/**
*Starting point
*calls interface to begin GUI process
*/
    public static void main(String args[]){
       
        EventQueue.invokeLater(()->{
            new Inter().setVisible(true);
        });

    
    }



}
