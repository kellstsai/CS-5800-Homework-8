import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.example.Snack;
import com.example.SnackDispenseHandler;

public class SnackDispenseHandlerTest {
    public SnackDispenseHandler handler;
    public Map<String, Snack> snacks; 

    @Before
    public void setUp() {
        handler = new SnackDispenseHandler();
        snacks = new HashMap<>();
        snacks.put("Coke", new Snack("Coke", 1.5,5));
        snacks.put("Pepsi", new Snack("Pepsi", 1.5, 3));
    }

    @Test
    public void setNextHandlerTest() {
        SnackDispenseHandler nextHandler = new SnackDispenseHandler();
        handler.setNextHandler(nextHandler);
        assertEquals(nextHandler, handler.nextHandler);
    }

    @Test
    public void handleRequestTest() {
        handler.handleRequest("Coke", 2.0, snacks);
        assertTrue(snacks.get("Coke").getQuantity() == 4);
    }

    @Test
    public void nextHandlerTest() {
        SnackDispenseHandler nextHandler = new SnackDispenseHandler();
        handler.setNextHandler(nextHandler);
        handler.handleRequest("Fanta", 2.0, snacks);
        assertNull(snacks.get("Fanta"));
    }

}
