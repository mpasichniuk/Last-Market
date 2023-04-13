import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(ProductController.class)
public class ProductTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetProductById() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("This is a test product");
        product.setPrice(BigDecimal.valueOf(9.99));
        product.setCategory("Test Category");

        when(productService.getProductById(1L)).thenReturn(java.util.Optional.of(product));


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
        product1.setName("Test Product 1");
        product1.setDescription("This is a test product 1");
        product1.setPrice(BigDecimal.valueOf(9.99));
        product1.setCategory("Test Category");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Test Product 2");
        product2.setDescription("This is a test product 2");
        product2.setPrice(BigDecimal.valueOf(19.99));
        product2.setCategory("Test Category");

        products.add(product1);
        products.add(product2);

        when(productService.getProductsByCategory("Test Category")).thenReturn(products);


        mockMvc.perform(MockMvcRequestBuilders.get("/product/category/Test Category"))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("products", products))
                .andExpect(MockMvcResultMatchers.view().name("category"));
    }
}
