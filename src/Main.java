import entities.*;
import services.PaymentService;

import java.time.LocalDate;
import java.time.temporal.TemporalUnit;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        var cheese = new Product(1, "Cheese", 100.75, 5,new PerishablePolicy(LocalDate.of(2026, 1, 1)), Optional.of(new ShippingComponent(400)));
        var tv = new Product(2, "TV", 600.50, 5, new NonPerishablePolicy(), Optional.of(new ShippingComponent(5000)));
        var scratchCard = new Product(3, "Scratch Card", 10.66, 3, new NonPerishablePolicy(), Optional.empty());

        var cart = new Cart();
        var paymentService = new PaymentService();
        var customer = new Customer("Mohamed", 10_000);


        cart.add(cheese, 2);
        cart.add(tv, 3);
        cart.add(scratchCard,1);


        paymentService.checkout(customer, cart);


        cart.add(cheese, 3);
        cart.add(tv, 2);
        cart.add(scratchCard, 2);

        paymentService.checkout(customer, cart);




        /// one product is out of stock error ///

        // cart.add(cheese, 1);


        /// Empty Cart Error ///

        // paymentService.checkout(customer, cart);

        /// one product is expired ///

        // var expiredProduct = new Product(4,
        //        "Biscuits",
        //       15.0,
        //        5,
        //        new PerishablePolicy(LocalDate.of(2016, 1, 1)),
        //        Optional.of(new ShippingComponent(5000)));

        // cart.add(expiredProduct, 2);

        /// Customer's balance is insufficient Error ///

        // var poorCustomer = new Customer("Eyad", 1000);
        // tv.setQuantity(2);
        // cart.add(tv, 2);
        // paymentService.checkout(poorCustomer, cart);
    }
}