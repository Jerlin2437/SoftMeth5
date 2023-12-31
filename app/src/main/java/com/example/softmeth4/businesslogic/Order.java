package com.example.softmeth4.businesslogic;

import com.example.softmeth4.pizzas.Pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a customer's pizza order and contains methods to
 * get and set information about an order, calculate total costs for the order,
 * and methods to return information about an order in a string representation.
 *
 * @author Jerlin Yuen, Jason Lei
 */
public final class Order {
    private static final double TAX_RATE = 0.06625;
    private static Order instance;
    private int orderNumber;
    private ArrayList<Pizza> pizzas = new ArrayList<>();
    private double subTotalValue;
    private double salesTaxValue;
    private double orderTotalValue;
    private String finalSubTotal;
    private String finalSalesTax;
    private String finalOrderTotal;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private Order() {
        pizzas = new ArrayList<>();
    }
    /**
     * Copy constructor to create a new order instance with the same values.
     *
     * @param originalOrder The order to copy.
     */
    private Order(Order originalOrder) {
        // Copy values from the original order
        this.orderNumber = originalOrder.orderNumber;
        this.pizzas.addAll(originalOrder.pizzas);
        // Copy other properties as needed
    }
    /**
     * Factory method to create a new order instance with the same values as the current order.
     *
     * @return A new order instance.
     */
    public static Order createNewOrder() {
        Order currentOrder = getInstance();
        return new Order(currentOrder);
    }

    /**
     * Method to get the singleton instance of Order.
     *
     * @return The singleton instance.
     */
    public static synchronized Order getInstance() {
        if (instance == null) {
            instance = new Order();
        }
        return instance;
    }
    /**
     * Adds a pizza to the order.
     *
     * @param pizza - specific pizza
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }
    public void resetOrder() {
        pizzas.clear();
        orderNumber = 0;
        subTotalValue = 0.0;
        salesTaxValue = 0.0;
        orderTotalValue = 0.0;
        finalSubTotal = null;
        finalSalesTax = null;
        finalOrderTotal = null;
    }
    /**
     * Removes a pizza from the order.
     *
     * @param pizza - pizza from the order
     */


    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }

    /**
     * Getter method (accessor)
     *
     * @return pizzas
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Getter method (accessor)
     *
     * @return orderTotalValue
     */
    public double getOrderTotalValue() {
        return orderTotalValue;
    }

    /**
     * Getter method (accessor)
     *
     * @return orderNumber
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Setter method (mutator)
     *
     * @param orderNumber - order number for a specific order
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * Returns a list of pizzas in the order with detailed information for each pizza.
     *
     * @return list of pizzas and detailed information for each, such as toppings, size, and price.
     */
    public List<String> getPizzaDetails() {
        List<String> pizzaDetails = new ArrayList<>();
        for (int i = 0; i < pizzas.size(); i++) {
            Pizza pizza = pizzas.get(i);
            pizzaDetails.add("Pizza " + (i + 1) + ": \n" + pizza.toString() + ("\n"));
        }
        return pizzaDetails;
    }

    /**
     * Returns a textual representation of a customer's order with each pizza and their respective details.
     *
     * @return - string textual representation
     */
    public String toString() {
        StringBuilder orderString = new StringBuilder();
        orderString.append("Order Number: ").append(orderNumber).append("\n");

        for (int i = 0; i < pizzas.size(); i++) {
            Pizza pizza = pizzas.get(i);
            orderString.append("Pizza ").append(i + 1).append(": ").append(pizza.toString()).append("\n");
        }

        return orderString.toString();
    }

    //instead of just subtotal, displays subtotal, sales tax, and order total

    /**
     * Returns a textual representation of a customer's order with each pizza and their respective details,
     * also contains values for subtotal, sales tax, and order total amount.
     *
     * @return - string textual representation
     */
    public String toFinalOrderDetailsString() {
        StringBuilder orderString = new StringBuilder();
        orderString.append("Order Number: ").append(orderNumber).append("\n");

        for (int i = 0; i < pizzas.size(); i++) {
            Pizza pizza = pizzas.get(i);
            orderString.append("Pizza ").append(i + 1).append(": ").append(pizza.toString()).append("\n");
        }
        calculateFinalOrderValues();
        orderString.append("Subtotal: $").append(finalSubTotal).append("\n");
        orderString.append("Sales Tax: $").append(finalSalesTax).append("\n");
        orderString.append("Order Total: $").append(finalOrderTotal).append("\n");

        return orderString.toString();
    }

    /**
     * Calculates values for subtotal, sales tax, and total amount for a pizza order.
     */
    private void calculateFinalOrderValues() {
        subTotalValue = 0.0;
        for (Pizza pizza : pizzas) {
            subTotalValue += pizza.price();
        }
        double taxRate = TAX_RATE;
        salesTaxValue = subTotalValue * taxRate;
        orderTotalValue = subTotalValue + salesTaxValue;

        finalSubTotal = String.format("%.2f", subTotalValue);
        finalSalesTax = String.format("%.2f", salesTaxValue);
        finalOrderTotal = String.format("%.2f", orderTotalValue);
    }

}
