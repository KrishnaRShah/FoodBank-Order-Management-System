/**
* @author Ryan Mailhiot 30080009<a
* href="mailto:ryan.mailhiot@ucalgary.ca">ryan.mailhiot@ucalgary.ca</a>
* @version 0.2 
* @since 0.0
*/
package FPCode;

public class NoItemExistsException extends Exception{
    /**
     * Throws an exception handle that needs to be caught. 
     * Defines an instance where there is no item found in the list which meets the criteria
     */
    public NoItemExistsException(){
        super();
    }

    // with error message
    /**
     * Throws an exception handle that needs to be caught. 
     * Defines an instance where there is no item found in the list which meets the criteria.
     * Contains an error message output
     */
    public NoItemExistsException(String errorMessage){
        super(errorMessage);
    }
}