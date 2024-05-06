package com.example;

// Idle state of the Vending Machine
public class IdleState implements StateOfVendingMachine {
    public VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectSnack(String snackName) {
        System.out.println("The selected snack: " + snackName);
        vendingMachine.setCurrentState(new WaitingForMoneyState(vendingMachine, snackName));
    }

    @Override
    public void insertMoney(double amount) {
        System.out.println("Please select a snack first.");
    }

    @Override
    public void dispenseSnack() {
        System.out.println("Please select a snack first.");
    }
}


