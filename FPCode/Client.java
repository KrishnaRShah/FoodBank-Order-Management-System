/**
* @author German (David) Fonseca 30061209<a
* href="mailto:german.fonseca@ucalgary.ca">german.fonseca@ucalgary.ca</a>
* @author Ryan Mailhiot 30080009<a
* href="mailto:ryan.mailhiot@ucalgary.ca">ryan.mailhiot@ucalgary.ca</a>
* @author Danny Picazo 301271082<a
* href="mailto:daniel.picazo@ucalgary">daniel.picazo@ucalgary.ca</a>
* @version 0.8 
* @since 0.0
*/

package FPCode;

import java.sql.*;

public class Client {

    // enum ClientType {
    //     ADULTMALE{
    //         public String typeAsString(){
    //             return "Adult Male";
    //         }
    //     },
    //     ADULTFEMALE{
    //         public String typeAsString(){
    //             return "Adult Female";
    //         }
    //     },
    //     CHILDOVER8{
    //         public String typeAsString(){
    //             return "Child Over 8 years old";
    //         }
    //     },
    //     CHILDUNDER8{
    //         public String typeAsString(){
    //             return "Child Under 8 years old";
    //         }
    //     };
    //     public abstract String typeAsString();
    // }

    private Nutrients nutrientData;
    private final int CLIENT_ID;
    private final String CLIENT_TYPE;
    private final String DBURL = "jdbc:mysql://localhost/food_inventory";
    private final String USERNAME = "student";
    private final String PASSWORD = "ensf"; 
    private Connection dbConnect;

    /**
     * Constructor for the object Client. Uses the enum "ClientType" for the client type registration. Takes in an id value for
     * the type of client and sets nutrientData, CLIENT_ID and CLIENT_TYPE;
     * @author Ryan Mailhiot
     * @param id takes a value from 1-4 (inclusive) for the type of client. 1 is Adult Male, 2 is Adult Female, 3 is Child
     * Over 8 years old, 4 is Child under 8 years old.
     */
    public Client(int id){

        if(id < 1 || id > 4){
            throw new IllegalArgumentException("Client ID is invalid.");
        }
        // Initializes the connection with the database and closes later.
        initializeConnection();
        // This switch identifies the client.
        switch (id) {
            case 1:
                this.CLIENT_ID = id;
                this.CLIENT_TYPE = ClientType.ADULTMALE.typeAsString();

                break;

            case 2:
                this.CLIENT_ID = id;
                this.CLIENT_TYPE = ClientType.ADULTFEMALE.typeAsString();
                break;

            case 3:
                this.CLIENT_ID = id;
                this.CLIENT_TYPE = ClientType.CHILDOVER8.typeAsString();
                break;

            case 4:
                this.CLIENT_ID = id;
                this.CLIENT_TYPE = ClientType.CHILDUNDER8.typeAsString();
                break;
        
            default:
                this.CLIENT_ID = 0;
                this.CLIENT_TYPE = "Default Case";
                break;
        }

        // Runs the query to grab the client information we are looking for from the database. 
        try {
            
            String query = "SELECT * FROM daily_client_needs WHERE ClientID = ?";

            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            
            myStmt.setInt(1, id);
            ResultSet results = myStmt.executeQuery();
            
            if (results.next()) {
                this.nutrientData = new Nutrients((double)results.getInt("WholeGrains"),
                (double)results.getInt("FruitVeggies"), (double)results.getInt("Protein"),
                (double)results.getInt("Other"), (double)results.getInt("Calories"));
            }
            
            myStmt.close();
            results.close();
            close();
        } catch (Exception e) {
            System.out.println("Error in Client Constructor");
        }

    }


    // Commented out because new system was implemented
    // Client(int id, String type){
    //     // 4 possible IDs
    //     if(id < 1 || id > 4){
    //         throw new IllegalArgumentException();
    //     }
    //     // set nutrients based on the type
    //     String temp = type.toUpperCase();
    //     switch (temp) {
    //         case "AM":
    //             nutrientData = new Nutrients(16, 28, 26, 30, 2500);
    //             type = "Adult Male";
    //             // If AM is always 1, then why do we even need to send it
    //             // as an argument? Can't we just send the client ID to the
    //             // constructor and have this switch-case set types? - Danny

                
    //             if(id != 1) throw new IllegalArgumentException();
    //             break;
    //         case "AF":
    //             nutrientData = new Nutrients(16, 28, 26, 30, 2000);
    //             type = "Adult Female";
    //             if(id != 2) throw new IllegalArgumentException();
    //             break;
    //         case "CO8":
    //             nutrientData = new Nutrients(21, 33, 31, 15, 2200);
    //             type = "Child Over 8";
    //             if(id != 3) throw new IllegalArgumentException();
    //             break;
    //         case "CU8":
    //             nutrientData = new Nutrients(21, 33, 31, 15, 1400);
    //             type = "Child Under 8";
    //             if(id != 4) throw new IllegalArgumentException();
    //             break;
    //         default:
    //             type = "";
    //             throw new IllegalArgumentException();
    //     }
    //     // no error, so commence forth
    //     this.CLIENT_ID = id;
    //     this.CLIENT_TYPE = type;
    // }
    
    /**
     * Creates an instance called dbConnect to connect to the database and grab the Daily Client Needs. 
     */
    public void initializeConnection(){
        try {
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Unexpected SQL Exception was thrown in initializeConnection in Client." + 
                " This is most likely because the URL, USERNAME or PASSWORD is incorrect.");
            e.printStackTrace(); 
        }
    }

    /**
     * Closes the dbConnect variable to stop unnecessary leak.
     */
    public void close() {
        try {
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }          

    }

    /**
     * Getter for the nutrientData of the client.
     * @return Nutrients Object
     */
    public Nutrients getNutrientData() {
        return this.nutrientData;
    }

    // COMMENTED OUT BECAUSE IT SEEMS EXTREMELY UNNECESSARY - Ryan
    // public void setNutrientData(Nutrients nutrientData) {
    //     this.nutrientData = nutrientData;
    // }
    
    /**
     * Getter for the Client ID
     * @return Int
     */
    public int getClientID() {
        return this.CLIENT_ID;
    }

    /**
     * Getter for the ClientType
     * @return String
     */
    public String getClientType() {
        return this.CLIENT_TYPE;
    }

}
