# TBQ: Canteen

## Part B

In this part we're going to start creating food items (classes that implement `MenuItem`) that can consume ingredients from the inventory.

## The MenuItem interface

Any class implementing this represents a food item that can be prepared and ordered off the menu.

In the body of the `make` method defined in the interface, the food class will check if there are enough ingredients to make the item, and take them out of the inventory as necessary. If there is not enough, the make method should throw `Inventory.NoMoreIngredientException`. **The MenuItems should either take no ingredients or take just enough ingredients to make one serving.**

By letting each food class implement the `MenuItem` interface and defining their own logic of how much ingredients they need, we are shifting responsibility away from the `Inventory` to the `MenuItem`s themselves. This is an example of the command design pattern.

This interface is found in the `menu` package. We will create a package for each different canteen stall.

## Objectives

### `chicken_rice` package

Create two food classes, each implementing `MenuItem`:

- `WantonNoodle`
    - x5 [`WANTON`](psi_element://simulator.Ingredient#WANTON)
    - x1 [`NOODLE`](psi_element://simulator.Ingredient#NOODLE)

- `SteamedChickenRice`
    - x1 [`CHICKEN_BREAST`](psi_element://simulator.Ingredient#CHICKEN_BREAST)
    - x1 [`WHITE_RICE`](psi_element://simulator.Ingredient#WHITE_RICE)
    - Special behaviours:
        - If there is not enough `CHICKEN_BREAST`, one [`WHOLE_CHICKEN`](psi_element://simulator.Ingredient#WHOLE_CHICKEN) can be broken into five `CHICKEN_BREAST`s and two `CHICKEN_WING`s.
        
### `mixed_rice` package

Just one class that has special behaviour:

- `EconomicRice`
    - x1 [`WHITE_RICE`](psi_element://simulator.Ingredient#WHITE_RICE)
    - x1 [`WHOLE_EGG`](psi_element://simulator.Ingredient#WHOLE_EGG)
    - x1 [`CHICKEN_HAM`](psi_element://simulator.Ingredient#CHICKEN_HAM)
    - Special behaviours:
        - If there is a [`CATERPILLAR`](psi_element://simulator.Ingredient#CATERPILLAR) in the inventory, there is a 10% chance this Caterpillar will be consumed and make its way into the `EconomicRice`. 