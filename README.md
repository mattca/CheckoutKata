# CheckoutKata

This repository contains code implementing a representation of a supermarket checkout. There is no UI implemented, just some tests around the API.

## API
Here is a small demo of the API:
```
// Create some items for the store:
Item cod = new Item(/* SKU: */ "123", /* Price: */ BigDecimal.valueOf(0.5));

Item chips = new Item("456", BigDecimal.valueOf(0.3));

Item peas = new Item("789", BigDecimal.valueOf(0.2));

// Register some offers for those items:
Offers offers = new Offers();
offers.registerOffer(cod, new MultibuyOffer(3, 40)); // Buy two get 40% off third item
offers.registerOffer(chips, new MultibuyOffer(2, 50)); // Buy one get second half price

// Create a checkout and pass the offers in
Checkout checkout = new Checkout(offers);

// Scan items through the checkout
checkout.scanItem(cod);
checkout.scanItem(chips);
checkout.scanItem(cod);
checkout.scanItem(peas);
checkout.scanItem(cod);
checkout.scanItem(chips);
checkout.scanItem(peas);

// Obtain the total of the items
Totals totals = checkout.calculateTotals();

System.out.println(totals);

// This outputs: Totals{subTotal=2.5, reductions=0.35, total=2.15}
```

## Run tests

To run tests, maven is required:
```
mvn test
```
