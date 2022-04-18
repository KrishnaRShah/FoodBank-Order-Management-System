/**
* @author Ryan Mailhiot 30080009<a
* href="mailto:ryan.mailhiot@ucalgary.ca">ryan.mailhiot@ucalgary.ca</a>
* @version 1.1 
* @since 0.0
*/
package FPCode;

public class FailedToConnectException extends Exception{
    /**
     * This is an exception that gets thrown when there is a failure to connect to a database.
     * 
     */
    public FailedToConnectException(){
        super();
    }

    // with error message
    /**
     * This is an exception that is thrown with an error message to define when a SQL exception is thrown that mentions it failed to connect to the database.
     */
    public FailedToConnectException(String errorMessage){
        super(errorMessage);
    }
}