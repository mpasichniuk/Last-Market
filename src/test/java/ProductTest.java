import com.market.Product;
import com.market.controllers.ProductController;
import com.market.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class})
@SpringBootTest(classes=ProductController.class)
public class ProductTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetProductById() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test com.market.Product");
        product.setDescription("This is a test product");
        product.setPrice(BigDecimal.valueOf(9.99));

        when(productService.getProductById(1L)).thenReturn(product);


        mockMvc.perform(MockMvcRequestBuilders.get("/product/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("product", product))
                .andExpect(MockMvcResultMatchers.view().name("product"));
    }

    @Test
    public void testGetProductsByCategory() throws Exception {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Test com.market.Product 1");
        product1.setDescription("This is a test product 1");
        product1.setPrice(BigDecimal.valueOf(9.99));

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Test com.market.Product 2");
        product2.setDescription("This is a test product 2");
        product2.setPrice(BigDecimal.valueOf(19.99));

        products.add(product1);
        products.add(product2);

        when(productService.getProductsByCategory("Test com.market.Category")).thenReturn(products);


        mockMvc.perform(MockMvcRequestBuilders.get("/product/category/Test com.market.Category"))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("products", products))
                .andExpect(MockMvcResultMatchers.view().name("category"));
    }
}
