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

// Create some offer types
MultibuyOffer buyTwoGetSecondHalfPrice = new MultibuyOffer(
   2,  // <-- Buy N items, to enable a reduction on the Nth item
   50  // <-- Percent reduction applied on the Nth item
);

MultibuyOffer buyThreeGet40PercentOffThirdItem = new MultibuyOffer(3, 40);

// Register the offers for certain items using the MultibuyOffers created:
Offers offers = new Offers();
offers.registerOffer(chips, buyTwoGetSecondHalfPrice);
offers.registerOffer(cod, buyThreeGet40PercentOffThirdItem);

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

## Tests

To run tests, maven is required:
```
mvn test
```

## Implementation notes
I tried to keep the implementation as simple as possible for this exercise using a domain driven design approach, avoiding use of 'service' classes and placing related behaviour and state directly into appropriately named classes which should, hopefully, be self explanatory. Due to this simplicity I didn't feel the need to group classes into any specific packages so all are in a single package.

## Possible Improvements
The current code serves the purpose and meets the current spec. However, regarding the MultibuyOffer, what if we wanted to implement other types of offers which trigger different logic? In this case there could be an offer interface which all types of offer could implement. With this in mind I think it actually makes more sense to have the logic which calculates the price reduction of the offer within the offer implementation itself. This means that a specific type of offer can implement it's own logic to calculate the price reduction, as that should be it's concern, rather than the reduction logic being in the checkout as it currently is.
