package com.example;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Snack> snacks = new HashMap<>();
        snacks.put("Coke", new Snack("Coke", 1.5, 1));
        snacks.put("Pepsi", new Snack("Pepsi", 1.5, 3));
        snacks.put("Cheetos", new Snack("Cheetos", 2.5, 4));
        snacks.put("Doritos", new Snack("Doritos", 2.0, 6));
        snacks.put("KitKat", new Snack("KitKat", 1.75, 2));
        snacks.put("Snickers", new Snack("Snickers", 1.5, 2));

        VendingMachine vendingMachine = new VendingMachine(snacks);
        SnackDispenseHandler cokeHandler = new SnackDispenseHandler();
        SnackDispenseHandler pepsiHandler = new SnackDispenseHandler();
        SnackDispenseHandler cheetosHandler = new SnackDispenseHandler();
        SnackDispenseHandler doritosHandler = new SnackDispenseHandler();
        SnackDispenseHandler kitkatHandler = new SnackDispenseHandler();
        SnackDispenseHandler snickersHandler = new SnackDispenseHandler();

        cokeHandler.setNextHandler(pepsiHandler);
        pepsiHandler.setNextHandler(cheetosHandler);
        cheetosHandler.setNextHandler(doritosHandler);
        doritosHandler.setNextHandler(kitkatHandler);
        kitkatHandler.setNextHandler(snickersHandler);

        vendingMachine.getSnackDispenser().setNextHandler(cokeHandler);

        System.out.println("********COKE********");
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(2.0);
        vendingMachine.dispenseSnack();

        System.out.println("Case when there is no more Coke."); 
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(2.0);
        vendingMachine.dispenseSnack();

        vendingMachine.getSnackDispenser().setNextHandler(pepsiHandler);

        System.out.println("********PEPSI********");
        vendingMachine.selectSnack("Pepsi");
        vendingMachine.insertMoney(2.5);
        vendingMachine.dispenseSnack();

        System.out.println("********CHEETOS********");
        vendingMachine.selectSnack("Cheetos");
        vendingMachine.insertMoney(2.5);
        vendingMachine.dispenseSnack();

        System.out.println("********DORITOS********");
        vendingMachine.selectSnack("Doritos");
        vendingMachine.insertMoney(3.0);
        vendingMachine.dispenseSnack();

        System.out.println("********KITKAT********");
        vendingMachine.selectSnack("KitKat");
        vendingMachine.insertMoney(1.75);
        vendingMachine.dispenseSnack();

        vendingMachine.getSnackDispenser().setNextHandler(snickersHandler);

        System.out.println("********SNICKERS********");
        vendingMachine.selectSnack("Snickers");
        vendingMachine.insertMoney(5.5);
        vendingMachine.dispenseSnack();

        System.out.println("==========");
        vendingMachine.selectSnack("Snickers");
        vendingMachine.insertMoney(5.5);
        vendingMachine.dispenseSnack();

        System.out.println("==========");
        vendingMachine.selectSnack("Snickers");
        vendingMachine.insertMoney(5.5);
        vendingMachine.dispenseSnack();

        System.out.println("********DORITOS incorrect amount********");
        vendingMachine.selectSnack("Doritos");
        vendingMachine.insertMoney(1.0);
        vendingMachine.dispenseSnack();
       

    }
    
}