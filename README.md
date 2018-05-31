# Checkout Kata

This repository contains code implementing a representation of a supermarket checkout. There is no UI implemented, just some tests around the API.

## API
Here is a small demo of the API:
```
// Create some items for the store:
Item cod = new Item(
   "123",                   // <-- SKU
   BigDecimal.valueOf(0.5)  // <-- Price
);

Item chips = new Item("456", BigDecimal.valueOf(0.3));

Item peas = new Item("789", BigDecimal.valueOf(0.2));

// Register the offers for those items using the MultiBuyOffers created:
MultibuyOffer buyOneGetSecondHalfPrice = new MultibuyOffer(
   2,  // <-- Buy N items, to enable a reduction on the Nth item
   50  // <-- Percent reduction applied on the Nth item
);

MultibuyOffer buyTwoGet40PercentOffThirdItem = new MultibuyOffer(3, 40);

Offers offers = new Offers();
offers.registerOffer(chips, buyOneGetSecondHalfPrice);
offers.registerOffer(cod, buyTwoGet40PercentOffThirdItem);

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
