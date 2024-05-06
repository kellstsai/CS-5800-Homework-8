package com.example;

// DispensingSnackState.java
public class DispensingSnackState implements StateOfVendingMachine {
    public VendingMachine vendingMachine;
    public String selectedSnack;

    public DispensingSnackState(VendingMachine vendingMachine, String selectedSnack) {
        this.vendingMachine = vendingMachine;
        this.selectedSnack = selectedSnack;
    }

    @Override
    public void selectSnack(String snackName) {
        System.out.println("A snack is being dispensed.");
    }

    @Override
    public void insertMoney(double amount) {
        System.out.println("Money has been inserted.");
    }

    @Override
    public void dispenseSnack() {
        System.out.println("Snack is being dispensed.");
        SnackDispenseHandler snackDispenser = vendingMachine.getSnackDispenser();
        snackDispenser.handleRequest(selectedSnack, vendingMachine.getCurrentMoney(), vendingMachine.getSnacks());
        vendingMachine.setCurrentMoney(0);
        vendingMachine.setCurrentState(new IdleState(vendingMachine));
    }
}



