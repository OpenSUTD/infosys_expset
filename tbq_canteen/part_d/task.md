# TBQ: Canteen

## Part D

In this final part we'll be using the Builder design pattern to build very complicated orders that still can be used with the existing systems

---

Recall that the Builder pattern can be used when there are many ways to construct an object.

In our canteen simulator, we will be using the Builder pattern to build `SetMeal` objects (from the Western Stall). A `SetMeal` will contain exactly 1 main dish and at least 2 side dishes.

> There are several variants of the Builder pattern. We will use the one that makes use of a private/protected constructor instead of accessing the instance attributes directly.

## Prelude

Review the classes in the `menu.western` package. There are two subpackages, `mains` and `sides`. [`MainDish`](psi_element://menu.western.mains.MainDish) and [`SideDish`](psi_element://menu.western.sides.SideDish) are extensions to `MenuItem`. They introduce another method, `canMake`, that checks against a provided inventory if it has the required ingredients without actually taking things from the inventory.

I have defined 5 side dishes and 3 main dishes, and implemented everything for you so you can focus on making the `SetMealBuilder`.

## Objective

Instead of letting the client create `SetMeal`s directly, we're going to use the [`SetMealBuilder`](psi_element://menu.western.SetMeal.SetMealBuilder).

### Builder: setting mains

The main dish can either be [`AglioOlio`](psi_element://menu.western.mains.AglioOlio), [`Burger`](psi_element://menu.western.mains.Burger) and [`ChickenCutlet`](psi_element://menu.western.mains.ChickenCutlet). All of them implement the [`MainDish`](psi_element://menu.western.mains.MainDish) interface, which is an extension of [`MenuItem`](psi_element://menu.MenuItem) (so they can be sold separately).

Implement [`setMainAsAglioOlio`](psi_element://menu.western.SetMeal.SetMealBuilder#setMainAsAglioOlio), [`setMainAsBurger`](psi_element://menu.western.SetMeal.SetMealBuilder#setMainAsBurger) and [`setMainAsChickenCutlet`](menu.western.SetMeal.SetMealBuilder#setMainAsChickenCutlet) respectively. Since there can only be one main, if the user calls more than one of this method, replace the main dish with the more recent one.

### Builder: adding sides

The side dishes available are

- [`CheeseFries`](psi_element://menu.western.sides.CheeseFries)
- [`FriedEgg`](psi_element://menu.western.sides.FriedEgg)
- [`Fries`](psi_element://menu.western.side.Fries)
- [`MashedPotato`](psi_element://menu.western.side.MashedPotato)
- [`Rice`](psi_element://menu.western.side.Rice)

Each side dish implements the [`SideDish`](psi_element://menu.western.mains.MainDish), so they can also be sold separately.

Implement the `addSideDish` method that accepts any side dish. A side dish is any class that implements the `SideDish` interface. You can have multiple side dishes (e.g. x2 Rice) and there is no upper bound on how many side dishes you can add to the set meal. How you want to keep track of the side dishes added is up to you.


### Builder: the `build` method

The `build` method should use [the protected constructor](psi_element://menu.western.SetMeal#SetMeal) to create a new instance of a `SetMeal`. There must be at least two side dishes to complete a set meal. If there are not enough side dishes or a main dish has not been set, throw a `IllegalArgumentException`.

> Note: the `build` method does not actually take the ingredients, it is just creating a `SetMeal` object. Think of it as you trying to think up of your order as you're still in the queue before you tell the uncle to make your food.


### Completing [`SetMeal.make`](psi_element://menu.western.SeatMeal#make)

The set meal is essentially just a main dish + at least two side dishes but with a discount. Since it can also be ordered off the menu, it should also implement `MenuItem.make`. These are the steps you need to take:

#### Checking if each component can be made

The `MainDish` and `SideDish` interfaces adds a `canMake` method that checks if there are enough ingredients to make the dish, without actually modifying the inventory. Use these to check if there are enough ingredients to make every component in the SetMeal. If there is not enough, throw a `Inventory.NoMoreIngredientException` as usual.

#### Make each component

Call `.make` on each component to consume ingredients and send out the events as we did in Part C.

#### Apply the discount

Apply a flat `1.50` discount to all set meals. This can be done by sending a `ItemSaleEvent` with a negative price.

This sounds ridiculous, but it is not so far-fetched from real cashier systems. Some systems first charge you ala carte prices for all the individual items, then adds a "Set Meal" item with a negative price to apply the discount. Take a look at your receipt next time you eat out :)