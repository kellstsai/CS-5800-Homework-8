package com.example;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine implements StateOfVendingMachine {
    public StateOfVendingMachine currentState;
    public Map<String, Snack> snacks;
    public double currentMoney;
    public SnackDispenseHandler snackDispenser;
    public String selectedSnack; 

    public VendingMachine(Map<String, Snack> snacks) {
        this.snacks = snacks;
        currentState = new IdleState(this);
        snackDispenser = new SnackDispenseHandler();
    }


    @Override
    public void selectSnack(String snackName) {
        if (currentState instanceof DispensingSnackState) {
            //System.out.println("Already dispensing a snack. Returning to idle state.");
            setCurrentState(new IdleState(this));
        }
        //System.out.println("Selected snack: " + snackName);
        this.selectedSnack = snackName; // Update selectedSnack value
        setCurrentState(new WaitingForMoneyState(this, snackName));
    }

    @Override
    public void insertMoney(double amount) {
        currentState.insertMoney(amount);
    }

    @Override
    public void dispenseSnack() {
        System.out.println("Dispensing snack: " + selectedSnack);
        SnackDispenseHandler snackDispenser = getSnackDispenser();
        snackDispenser.handleRequest(selectedSnack, getCurrentMoney(), getSnacks());
        Snack snack = snacks.get(selectedSnack);
        if (snack != null && snack.getQuantity() > -1) {
            double price = snack.getPrice();
            if (getCurrentMoney() >= price) {
                double change = getCurrentMoney() - price;
                setCurrentMoney(0);
                // Print change if there's any
                if (change > 0) {
                    System.out.println("Dispensing change: $" + change);
                }
                // Decrease the quantity of the dispensed snack
                snack.decreaseQuantity(1);
                // Transition to the next snack
                selectNextSnack();
            } else {
                //System.out.println("Not enough money. Please insert $" + price + " or more.");
                System.out.println("Returning money.");
                setCurrentMoney(0);
            }
        } else {
            //System.out.println("Out of stock. Unable to dispense " + selectedSnack + ".");
            System.out.println("Returning money.");
            setCurrentMoney(0);
        }
    }
    
    
    

    public void setCurrentState(StateOfVendingMachine state) {
        currentState = state;
    }

    public double getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(double currentMoney) {
        this.currentMoney = currentMoney;
    }

    public Map<String, Snack> getSnacks() {
        return snacks;
    }

    public SnackDispenseHandler getSnackDispenser() {
        return snackDispenser;
    }

    private void selectNextSnack() {
        for (Map.Entry<String, Snack> entry : getSnacks().entrySet()) {
            if (entry.getValue().getQuantity() > 0) {
                //System.out.println("Selected next snack: " + entry.getKey());
                selectSnack(entry.getKey());
                return;
            }
        }
        System.out.println("No more snacks are available.");
        setCurrentState(new IdleState(this));
    }
    
}



