package exceptions;

public class ExpiredProductException extends RuntimeException{
    public ExpiredProductException(){
        super("This Product has expired and can't be purchased");
    }
}
