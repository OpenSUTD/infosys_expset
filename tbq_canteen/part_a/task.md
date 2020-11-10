# TBQ: Canteen

## Part A

Welcome to the Canteen Theme Based Question! In this section you'll be creating **Canteen Simulator 2020™** to help food stall owners simulate how much costs and profits will be incurred when they complete a certain set of orders.

## Prelude

Look at the [`Ingredient.java`](psi_element://.imulator.Ingredient) file. This is an **enum**. An enum is a special "class" that represents a group of constants or elements. This is very useful for things you want to define at compile time, for example, the days of the week (e.g. Monday, Tuesday, Wednesday). It is safer and faster than using a String because you can't make typo mistakes with them.

In this example, our `Ingredient` enum will represent the universal set of ingredients that our canteen stalls will ever need to use. Of course, this is a simulation, so don't expect everything.

## Objective

Before we can simulate how much profits canteen stalls can earn, we first need to simulate an **Inventory**. An inventory helps the stall owner keep track of how much of each ingredient he has remaining.

In the class there are three public methods you need to finish implementing:


### [getIngredientCount](psi_element://simulator.Inventory#getIngredientCount)

This method should tell me how much of an ingredient is left in the inventory. We assume everything to be stored as integers of an arbitary unit.

### [putInIngredients](psi_element://simulator.Inventory#putInIngredients)

This method should "top up" the inventory given a certain ingredient by a given amount. If someone tries to top up with a negative value, throw an `IllegalArgumentException`.

### [takeOutIngredients](psi_element://simulator.Inventory#takeOutIngredients)

Retrieve ingredients from the inventory. If there are not enough of that ingredient to satisfy the request, throw an `Inventory.NoMoreIngredientException`.

---

### The `EnumMap`

To help you implement these methods, I have created a private instance variable called an `EnumMap`. An `EnumMap` is a special Map (dictionary) that maps Enum entries to any type, in this case an Integer. Using this EnumMap we can easily store and answer the question of "how many of X ingredient do I have left"? You are encouraged to explore the different methods available on the `EnumMap` to see what suits your needs.


## Testing your code

The tests that will be used to validate your code are found in the [`test/InventoryTest.java`](psi_element://InventoryTest) file. You can peek at them to get a sense of how your `Inventory` class will be used, but don't change the assert statements. Press the check button below to automatically run the tests and get a test report of what failed.