# Fawry Rise Journey E-commerce System 

An uncomplicated e-commerce platform constructed with Java. The system manages diverse product expiration dates along with shipping processes and checkout operations. 

# Project Structure

    src/
    ├── contracts/
    │   ├── ExpirationPolicy.java
    │   └── Shippable.java
    |
    ├── entities/
    │   ├── Cart.java
    │   ├── CartItem.java
    │   ├── Customer.java
    │   ├── NonPerishablePolicy.java
    │   ├── PerishablePolicy.java
    │   ├── Product.java
    │   ├── ShippableCartItem.java
    │   └── ShippingComponent.java
    |
    ├── exceptions/
    │   ├── EmptyCartException.java
    │   ├── ExpiredProductException.java
    │   └── InSufficientFundsException.java
    |
    ├── services/
    │   ├── PaymentService.java
    │   └── ShippingService.java
    |
    ├── utils/
    │   ├── MathHelpers.java
    │   └── StringBuilderHelpers.java
    |
    └── Main.java

# Features 

* Expiration date driven product management 
* Shipping support for valid products 
* Management mechanisms for error conditions related to typical problems 

# How to Run 

1. Execute the process to transfer this repository onto your personal computer system. 
2. Initiate the project examination process using your selected integrated development environment. 
3. Execute the program by initiating the `Main` class. 


# Running the Tests

![buy](https://github.com/user-attachments/assets/c00fdedb-be0e-46bb-8517-aa67b318ccd7)

![quantity](https://github.com/user-attachments/assets/9c4156d4-fbb3-4f99-8f5b-0504ed896f66)

![empty cart](https://github.com/user-attachments/assets/2d66e5f6-85dc-4002-80f3-536fd68f1294)

![expired](https://github.com/user-attachments/assets/16b5dccf-4bae-4084-9a84-7b39964805c2)

![balance](https://github.com/user-attachments/assets/ebc33da3-5f8d-4446-9531-fe58fce6fbf1)
