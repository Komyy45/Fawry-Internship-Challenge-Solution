package entities;

import contracts.ExpirationPolicy;
import java.util.Optional;

public class Product {
    private final int id;
    private String name;
    private double price;
    private int quantity;
    private final ExpirationPolicy expirationPolicy;
    private final Optional<ShippingComponent> shippingCapability;

    public Product(int id,
                    String name,
                   double price,
                   int quantity,
                   ExpirationPolicy expirationPolicy,
                   Optional<ShippingComponent> shippingCapability)
    {
        this.id = id;
        this.setName(name);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.expirationPolicy = expirationPolicy;
        this.shippingCapability = shippingCapability;
    }


    public int getId() {
        return id;
    }

    public void setName(String name)
    {
        if(name.isEmpty())
            throw new IllegalArgumentException("Product Name can't be set to empty string");

        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }

    public void setPrice(double price)
    {
        if(price < 0)
            throw new IllegalArgumentException("Product Price must be a positive number");

        this.price = price;
    }
    public double getPrice(){
        return this.price;
    }

    public int getQuantity()
    {
        return this.quantity;
    }

    public boolean isExpired()
    {
        return expirationPolicy.isExpired();
    }

    public Optional<ShippingComponent> getShippingCapability()
    {
        return this.shippingCapability;
    }

    public void setQuantity(int quantity)
    {
        if(quantity < 0)
            throw new IllegalArgumentException("Quantity must be a positive number");

        this.quantity = quantity;
    }
}
