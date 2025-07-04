package exceptions;

public class EmptyCartException extends RuntimeException{
    public EmptyCartException()
    {
        super("Can't Proceed the Shopping cart is empty");
    }
}
