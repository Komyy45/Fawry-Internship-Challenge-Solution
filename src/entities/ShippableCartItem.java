package entities;

public class ShippableCartItem extends CartItem{
    private final double weight;

    public  ShippableCartItem(String name,
                              int quantity,
                              double unitPrice,
                              double weight)
    {
        super(name, quantity, unitPrice);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}
