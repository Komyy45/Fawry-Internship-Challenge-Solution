package entities;

import exceptions.ExpiredProductException;

import java.util.*;

public class Cart {
    private final Map<Integer, CartItem> products = new HashMap<>();;


    public void add(Product product, int requestedQuantity)
    {
        if(product.isExpired())
            throw new ExpiredProductException();

        if(requestedQuantity <= 0)
            throw new IllegalArgumentException("Requested quantity must be a positive number");

        int availableQuantity = product.getQuantity();

        if(availableQuantity < requestedQuantity)
            throw new IllegalArgumentException("Requested quantity exceeds the available stock");


        product.setQuantity(availableQuantity -  requestedQuantity);
        var itemShippingCapability = product.getShippingCapability();

        if(itemShippingCapability.isPresent())
        {
            var cartItem = this.products.getOrDefault(product.getId(),
                    new ShippableCartItem(product.getName(),
                            0,
                            product.getPrice(),
                            itemShippingCapability.get().weight()));

            this.addCartItemWithDesiredQuantity(product.getId(), cartItem, requestedQuantity);
        }
        else
        {

            var cartItem = this.products.getOrDefault(product.getId(), new CartItem(product.getName(),
                                                                               0,
                                                                                      product.getPrice()));

            this.addCartItemWithDesiredQuantity(product.getId(), cartItem, requestedQuantity);
        }


    }

    private void addCartItemWithDesiredQuantity(int id, CartItem cartItem, int quantity){
        cartItem.setQuantity(quantity + cartItem.getQuantity());
        this.products.put(id, cartItem);
    }

    public void remove(Product product, int quantity)
    {
        if(quantity <= 0)
            throw new IllegalArgumentException("Quantity to be removed must be positive number");

        Optional<CartItem> cartItem = Optional.ofNullable(products.get(product.getId()));

        if(cartItem.isEmpty()) return;

        var itemValue = cartItem.get();

        var currentQuantity = itemValue.getQuantity();


        if(quantity >= currentQuantity)
        {
            product.setQuantity(currentQuantity + product.getQuantity());
            this.products.remove(product.getId());
        }
        else
        {
            product.setQuantity(product.getQuantity() + quantity);
            itemValue.setQuantity(currentQuantity - quantity);
            this.products.put(product.getId(), itemValue);
        }
    }

    public Map<Integer,CartItem> getItems(){
        return this.products;
    }

    public double getSubTotal() {
        return this.products.values().stream().mapToDouble(ci -> ci.getQuantity() * ci.getUnitPrice()).sum();
    }
}
