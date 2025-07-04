package services;

import contracts.Shippable;
import entities.Cart;
import entities.Customer;
import entities.ShippableCartItem;
import exceptions.EmptyCartException;
import exceptions.InSufficientFundsException;
import utils.MathHelpers;
import utils.StringBuilderHelpers;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {
    private final StringBuilder checkoutReceiptBuilder = new StringBuilder();

    public void checkout(Customer customer, Cart cart){
        if(cart.getItems().isEmpty())
            throw new EmptyCartException();

        var shippingService = new ShippingService();

        var shippableItems = getShippableItems(cart);

        var shipmentCost = shippingService.handleShipment(shippableItems);

        var totalCost = cart.getSubTotal() + shipmentCost;
        var currentCustomerBalance = customer.getBalance();

        if(totalCost > currentCustomerBalance)
            throw new InSufficientFundsException();


        customer.setBalance(currentCustomerBalance - totalCost);

        shippingService.printShipmentNotice();
        printCheckoutReceipt(cart, shipmentCost, customer.getBalance());

        cart.getItems().clear();
    }

    private List<Shippable> getShippableItems(Cart cart)
    {
        var items = cart.getItems();
        var shippableItems = new ArrayList<Shippable>();
        StringBuilderHelpers.appendLine(checkoutReceiptBuilder,"** Checkout Receipt **");

        for(var entry : items.entrySet())
        {

            var item = entry.getValue();

            StringBuilderHelpers.appendLine(checkoutReceiptBuilder,String.format("%dx %s \t %s",
                    item.getQuantity(),
                    item.getName(),
                    MathHelpers.round(item.getUnitPrice() * item.getQuantity(), 1)));


            if(item instanceof ShippableCartItem)
                shippableItems.add(new Shippable() {
                    @Override
                    public String getName() {
                        return String.format("%dx %s", item.getQuantity(), item.getName());
                    }

                    @Override
                    public double getWeight() {
                        return ((ShippableCartItem)item).getWeight() * item.getQuantity();
                    }
                });
        }

        return shippableItems;
    }

    private void printCheckoutReceipt(Cart cart, double shippingCost, double customerBalanceAfterPayment)
    {
        var totalAmount = cart.getSubTotal() + shippingCost;
        StringBuilderHelpers.appendLine(checkoutReceiptBuilder,"--------------------------");
        StringBuilderHelpers.appendLine(checkoutReceiptBuilder, String.format("SubTotal \t %s", MathHelpers.round(cart.getSubTotal(), 1)));
        StringBuilderHelpers.appendLine(checkoutReceiptBuilder, String.format("Shipping \t %s", MathHelpers.round(shippingCost, 1)));
        StringBuilderHelpers.appendLine(checkoutReceiptBuilder, String.format("Amount \t %s",  MathHelpers.round(cart.getSubTotal() + shippingCost, 1)));
        StringBuilderHelpers.appendLine(checkoutReceiptBuilder, String.format("Rem. Balance \t %s",  MathHelpers.round(customerBalanceAfterPayment, 1)));
        StringBuilderHelpers.appendLine(checkoutReceiptBuilder,"\n");
        System.out.println(checkoutReceiptBuilder.toString());

        checkoutReceiptBuilder.setLength(0);
    }
}
