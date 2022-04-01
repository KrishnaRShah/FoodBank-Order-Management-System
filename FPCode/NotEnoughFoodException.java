package FPCode;

public class NotEnoughFoodException extends Exception{
    // default
    public NotEnoughFoodException(){
        super();
    }

    // with error message
    public NotEnoughFoodException(String errorMessage){
        super(errorMessage);
    }
}

