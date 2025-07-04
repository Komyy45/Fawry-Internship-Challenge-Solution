package entities;

public class CartItem {
    private final String name;
    private int quantity;
    private final double unitPrice;

    public CartItem(String name,
                    int quantity,
                    double unitPrice)
    {
        this.name =  name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        if(quantity >= 0)
            this.quantity = quantity;
        else
            throw new IllegalArgumentException("The quantity must be a positive value");
    }
}
