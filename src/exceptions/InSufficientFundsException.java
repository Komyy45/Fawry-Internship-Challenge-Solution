package exceptions;

public class InSufficientFundsException extends RuntimeException{
    public InSufficientFundsException(){
        super("Not Enough Balance");
    }
}
