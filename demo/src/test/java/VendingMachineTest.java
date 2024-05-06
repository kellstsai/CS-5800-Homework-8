import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.Snack;
import com.example.VendingMachine;
import com.example.WaitingForMoneyState;

public class VendingMachineTest {

    public VendingMachine vendingMachine;
    public Map<String, Snack> snacks; 
    public final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    public final PrintStream originalOut = System.out;

     @Before
    public void setUp() {
        snacks = new HashMap<>();
        snacks.put("Coke", new Snack("Coke", 1.5, 5));
        snacks.put("Pepsi", new Snack("Pepsi", 1.5, 3));
        vendingMachine = new VendingMachine(snacks);
    }

    @Test
    public void selectSnackTest() {
        vendingMachine.selectSnack("Coke");
        assertEquals("Coke", vendingMachine.selectedSnack);
        assertEquals(WaitingForMoneyState.class, vendingMachine.currentState.getClass());
    }

    @Test
    public void insertMoneyTest() {
        vendingMachine.setCurrentState(new WaitingForMoneyState(vendingMachine, "Coke"));
        vendingMachine.insertMoney(2.0);
        assertEquals(2.0, vendingMachine.getCurrentMoney(), 0.001);
    }

    @Test
    public void dispenseSnackSuccessTest() {
        vendingMachine.setCurrentState(new WaitingForMoneyState(vendingMachine, "Coke"));
        vendingMachine.insertMoney(2.0);
        vendingMachine.dispenseSnack();
        assertEquals(5, snacks.get("Coke").getQuantity());
        assertNotEquals(5, vendingMachine.getCurrentMoney(), 0.001);
    }

    @Test
    public void notEnoughMoneyTest() {
        vendingMachine.setCurrentState(new WaitingForMoneyState(vendingMachine, "Coke"));
        vendingMachine.insertMoney(1.0);
        vendingMachine.dispenseSnack();
        assertEquals(5, snacks.get("Coke").getQuantity());
        assertNotEquals(1.0, vendingMachine.getCurrentMoney(), 0.001);
    }

    @Test
    public void outOfStockTest() {
        snacks.get("Coke").decreaseQuantity(5); // Set quantity to 0
        vendingMachine.setCurrentState(new WaitingForMoneyState(vendingMachine, "Coke"));
        vendingMachine.insertMoney(2.0);
        vendingMachine.dispenseSnack();
        assertNotEquals(2.0, vendingMachine.getCurrentMoney(), 0.001);
    }


    
}
