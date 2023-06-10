import com.market.Application;
import com.market.controllers.CartController;
import com.market.controllers.PaymentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class PaymentAndCartTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CartController cartController;

    @Autowired
    private PaymentController paymentController;

    @Test
    public void testCartPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cart"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("cart"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("cart"));
    }

    @Test
    public void testPaymentPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/payment"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("payment"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("payment"));
    }
}
