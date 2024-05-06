import static org.junit.Assert.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.IdleState;
import com.example.Snack;
import com.example.VendingMachine;

public class IdleStateTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private VendingMachine vendingMachine;
    private IdleState idleState;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        Map<String, Snack> snacks = new HashMap<>();
        snacks.put("Coke", new Snack("Coke", 1.5, 5));
        vendingMachine = new VendingMachine(snacks);
        idleState = new IdleState(vendingMachine);
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void selectSnackTest() {
        idleState.selectSnack("Coke");
        assertNotEquals("The selected snack: Coke.", outContent.toString());
    }

    @Test
    public void insertMoneyTest() {
        idleState.insertMoney(1.0);
        assertNotEquals("Please select a snack first.", outContent.toString());
    }

    @Test
    public void dispenseSnackTest() {
        idleState.dispenseSnack();
        assertNotEquals("Please select a snack first.\n", outContent.toString());
    }
}
