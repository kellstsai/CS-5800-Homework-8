package com.example;

public interface StateOfVendingMachine {
    public void selectSnack(String snackName);
    public void insertMoney(double amount);
    public void dispenseSnack();
}