package com.example;

import java.util.Map;

public class SnackDispenseHandler {
    public SnackDispenseHandler nextHandler;

    public void setNextHandler(SnackDispenseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handleRequest(String snackName, double money, Map<String, Snack> snacks) {
        if (snacks.containsKey(snackName) && snacks.get(snackName).getQuantity() > 0 && money >= snacks.get(snackName).getPrice()) {
            System.out.println("Currently dispensing " + snackName + ".");
            snacks.get(snackName).decreaseQuantity(1);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(snackName, money, snacks);
        } else {
            System.out.println("This item is out of stock. Unable to dispense " + snackName + ".");
        }
    }
}



/* 
public class SnackDispenseHandler {
    public Map<String, Snack> snacks;
    public  StateOfVendingMachine state;
    public SnackDispenseHandler nextHandler;

    public SnackDispenseHandler(Map<String, Snack> snacks) {
        this.snacks = snacks;
    }

    public void setNextHandler(SnackDispenseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void dispenseSnack(String snackName) {
        if (snacks.containsKey(snackName)) {
            state.selectSnack(snackName);
        } else if (nextHandler != null) {
            nextHandler.dispenseSnack(snackName);
        } else {
            System.out.println("Invalid snack selection.");
        }
    }

    public void setState(StateOfVendingMachine state) {
        this.state = state;
    }

    // Cycle through snacks until the proper one is dispensed
    public void cycleThroughSnacks(String initialSnackName) {
        SnackDispenseHandler currentHandler = this;
        do {
            currentHandler.dispenseSnack(initialSnackName);
            currentHandler = currentHandler.nextHandler;
        } while (currentHandler != null && currentHandler != this);
    }
}
*/