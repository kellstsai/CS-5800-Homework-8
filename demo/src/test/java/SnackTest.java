import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.example.Snack; 

public class SnackTest {
    private Snack snack; 

    @Before
    public void setUp() {
        snack = new Snack("snackTest", 1.5, 3); 
    }

    @Test
    public void getNameTest() {
        assertEquals("snackTest", snack.getName()); 
    }

    @Test
    public void getPriceTest() {
        assertEquals(1.5, snack.getPrice(), 0.001); 
    }

    @Test
    public void getQuantityTest() {
        assertEquals(3, snack.getQuantity()); 
    }

    @Test
    public void decreaseQuantityTest() {
        snack.decreaseQuantity(2);
        assertEquals(1, snack.getQuantity());
    }
}
