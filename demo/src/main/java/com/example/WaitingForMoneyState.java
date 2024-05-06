package com.example;

public class WaitingForMoneyState implements StateOfVendingMachine {
    public VendingMachine vendingMachine;
    public String selectedSnack;

    public WaitingForMoneyState(VendingMachine vendingMachine, String selectedSnack) {
        this.vendingMachine = vendingMachine;
        this.selectedSnack = selectedSnack;
    }

    @Override
    public void selectSnack(String snackName) {
        System.out.println("A snack has already been selected.");
    }

    @Override
    public void insertMoney(double amount) {
        Snack selectedSnackObj = vendingMachine.getSnacks().get(selectedSnack);
        if (selectedSnackObj == null) {
            System.out.println("Invalid selection.");
            vendingMachine.setCurrentState(new IdleState(vendingMachine));
            return;
        }

        if (amount >= selectedSnackObj.getPrice()) {
            System.out.println("Amount inserted: $" + amount);
            vendingMachine.setCurrentMoney(amount);
            vendingMachine.setCurrentState(new DispensingSnackState(vendingMachine, selectedSnack));
        } else {
            System.out.println("Amount is not enough. Please insert $" + selectedSnackObj.getPrice() + " or more.");
        }
    }

    @Override
    public void dispenseSnack() {
        System.out.println("Please insert the correct amount.");
    }
}
