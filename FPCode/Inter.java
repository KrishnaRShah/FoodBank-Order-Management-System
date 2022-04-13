package FPCode;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.EventQueue;
import java.util.regex.*;
import java.util.ArrayList;


public class Inter extends JFrame implements ActionListener, MouseListener {
    public int[] clients=new int[4];
    
    private String adultFemale;
    private String adultMale;
    private String childOver8;
    private String childUnder8;
    
    private JLabel inst;
    private JLabel aFL;
    private JLabel aML;
    private JLabel cOL;
    private JLabel cUL;
    
    private JTextField femaleInput;
    private JTextField maleInput;
    private JTextField childUnder;
    private JTextField childOver;

    private JButton submit;
    private JButton newOrder;

    public ArrayList<int[]> numberOfHampers=new ArrayList<int[]>();

    public Inter(){
        super("generate hamper");
        createWindow();
        setSize(550,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createWindow(){
        inst=new JLabel("enter required information to generate hamper, please enter only numeric characters (0-9)");
        aFL=new JLabel("enter number of adult females");
        aML=new JLabel("                      enter number of adult males");
        cOL=new JLabel("enter number of children over 8");
        cUL=new JLabel("                              enter number of children under 8");

        femaleInput=new JTextField("",15);
        maleInput=new JTextField("",15);
        childOver=new JTextField("",15);
        childUnder=new JTextField("",15);

        femaleInput.addMouseListener(this);
        maleInput.addMouseListener(this);
        childOver.addMouseListener(this);
        childUnder.addMouseListener(this);

        submit=new JButton("submit");
        submit.addActionListener(this);
        newOrder=new JButton("end current order");
        newOrder.addActionListener(this);
        
        JPanel headerPanel=new JPanel();
        headerPanel.setLayout(new FlowLayout());

        JPanel panel2=new JPanel();
        panel2.setLayout(new FlowLayout());

        JPanel submitPanel=new JPanel();
        submitPanel.setLayout(new FlowLayout());
        JPanel newOrderPanel=new JPanel();
        newOrderPanel.setLayout(new FlowLayout());
        
        headerPanel.add(inst);
        panel2.add(aFL);
        panel2.add(femaleInput);
        panel2.add(aML);
        panel2.add(maleInput);
        panel2.add(cOL);
        panel2.add(childOver);
        panel2.add(cUL);
        panel2.add(childUnder);
        submitPanel.add(submit);
        submitPanel.add(newOrder);

        this.add(headerPanel,BorderLayout.NORTH);
        this.add(panel2,BorderLayout.CENTER);
        this.add(submitPanel,BorderLayout.SOUTH);
        
    }

    public void actionPerformed(ActionEvent event){

        

        adultFemale=(femaleInput.getText());
        adultMale=(maleInput.getText());
        childOver8=(childOver.getText());
        childUnder8=(childUnder.getText());

        if(event.getSource()==submit){

        
  
            if(validateIn()){
                clients[0]=Integer.parseInt(adultMale);
                clients[1]=Integer.parseInt(adultFemale);
                clients[2]=Integer.parseInt(childOver8);
                clients[3]=Integer.parseInt(childUnder8);
                numberOfHampers.add(clients);
                System.out.println(clients[0]);
                System.out.println(clients[1]);
                System.out.println(clients[2]);
                System.out.println(clients[3]);
                
            }
  
            else{
                JOptionPane.showMessageDialog(this,"illegal character entered");
            }
        }
        if(event.getSource()==newOrder){
            //new Order(numberOfHampers);
            numberOfHampers.clear();
        }
    }

    private boolean validateIn(){
        String regex="[0-9]+";
        Pattern p=Pattern.compile(regex);
        Matcher f=p.matcher(adultFemale);
        Matcher m=p.matcher(adultMale);
        Matcher o=p.matcher(childOver8);
        Matcher u=p.matcher(childUnder8);
        if(f.matches()&&m.matches()&&o.matches()&&u.matches()){
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
   
    public void mouseClicked(MouseEvent event){
        if (event.getSource().equals(femaleInput)){
            femaleInput.setText("");
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

    public static void main(String args[]){
       
        EventQueue.invokeLater(()->{
            new Inter().setVisible(true);
        });

    
    }



}
