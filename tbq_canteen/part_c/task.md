# TBQ: Canteen

## Part C

In this part we're going to be utilizing a variant of the `Observer` design pattern through an _Event Bus_ to pass various data out of the `MenuItem.make` method even though it returns void.

---

## Event Bus: The observable singleton

In class, you've seen the Observer pattern being applied on individual objects (each of them being `Observable`). In our case, we want to observe each instance of a `MenuItem`. However, since it might be possible that we are processing thousands of food orders, if they all share the same observers, it is not efficient to have every single `MenuItem` be an `Observable` (because then each order will maintain a copy of the same list of observers). The complexity would scale to `O(n * m)` (n observables, m observers).

To simplify things, we're going to delegate observation events to a single instance called the `EventBus`. You can imagine this as a "fan in, fan out" scenario: many `MenuItems` will notify the **same** event bus of events, and this event bus will broadcast the events to many observers. The complexity is now `O(n + m)`.

To further simplify things, we assume we simulate one canteen at a time, so all `MenuItem`s can share the same bus. Instead of passing the EventBus to each MenuItem object, we can use the **singleton pattern** to ensure that the same EventBus object can be accessed from anywhere.

## Objectives

### Apply the Singleton pattern to the [`EventBus`](psi_element://simulator.EventBus) class.

We want the singleton instance to be accessed via a public static method called `getInstance`.

### Make EventBus extend Observable

This gives the EventBus instance the `notifyObservers()` and `addObserver()` methods, the ones we care about.

> Note: Because of how the `Observer` class works we need to call `this.setChanged()` once before we can use `notifyObservers()`. You can do this inside the `getInstance()` method.

### Make the MenuItems pass out data via notify

Now that the EventBus is accessible everywhere, let's look at what kind of data we're interested to make our MenuItems share. The `events` package defines two events that our EventBus will help to broadcast.

#### Ingredient Usage

As you've seen with the `EconomicRice`, the amount and types of ingredients used can change every time the order is made. Inside the make method of `EconomicRice`, `SteamedChickenRice` and `WantonNoodle`, construct a [`IngredientUsedEvent`](psi_element://events.IngredientUsedEvent) object and send it via EventBus's `notifyObservers()`. The [`IngredientUsedEvent`](psi_element://events.IngredientUsedEvent) class has been created for you. You can send different instances of [`IngredientUsedEvent`](psi_element://events.IngredientUsedEvent) within the same invocation of `MenuItem.make()`.


#### Price calculations

In the same way of firing events within the `make` method, we can also notify observers of the final price of this `MenuItem`. The [`ItemSaleEvent`](psi_element://events.ItemSaleEvent) class has been created for you.

Match the prices with the details below:

- `SteamedChickenRice`: 2.8
- `WantonNoodle`: 2.0
- `EconomicRice`:
  - 1.8 if no caterpillar
  - 0 if got caterpillar 
   
### Writing the Observers

Now we write the observer classes that will be added to the EventBus via `addObserver`. As per the normal observer pattern, these classes must implement [`Observer`](psi_element://java.util.Observer). Implement the following three observers in the `observers` package:

#### [MostUsedIngredientTracker](psi_element://observers.MostUsedIngredientTracker)

Only interested in listening to [`IngredientUsedEvent`](psi_element://events.IngredientUsedEvent). Keeps an internal tally of which Ingredient was used the most, which can be retrieved via `getMostUsedIngredient()`.

#### [NEAWatchdog](psi_element://observers.NEAWatchdog)

Only interested in listening to [`IngredientUsedEvent`](psi_element://events.IngredientUsedEvent). The moment a `Caterpillar` has been used, permanently trip an internal boolean flag that can be checked via `willIssueSummon()`.

#### [Cashier](psi_element://observers.Cashier)

Only interested in listening to [`ItemSaleEvent`](psi_element://events.ItemSaleEvent). Keeps track of the total amount earned so far, which can be retrieved by `getTotalSales()`.

As with normal `Observer` classes, they will receive the events (or whatever was passed into `notifyObserver`) as a plain `Object`. You must first check if it is the type of event it is interested in listening to and typecast appropiately.  

## Testing your code

You can refer to the code in the tests folder to see how it works, and write your own separate test program if needed. Essentially, you do the following:

- Initialize the observers
- Clear the event bus of existing observers, then add the new observers
- Initialize my inventory
- Create some `MenuItem`s and [`make`](psi_element://menu.MenuItem#make) them
- Check what the observers report (e.g. [`Cashier.getTotalSales`](psi_element://observers.Cashier#getTotalSales))