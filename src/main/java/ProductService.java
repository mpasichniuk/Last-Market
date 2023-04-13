import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

        private final ProductRepository productRepository;

        @Autowired
        public ProductService(ProductRepository productRepository) {
            this.productRepository = productRepository;
        }

        public List<Product> getAllProducts() {
            return productRepository.findAll();
        }

        public List<Product> getProductsByCategory(String category) {
            return productRepository.findByCategory(category);
        }

        public Optional<Product> getProductById(Long id) {
            return productRepository.findById(id);
        }

        public void saveProduct(Product product) {
            productRepository.save(product);
        }

        public void deleteProduct(Long id) {
            productRepository.deleteById(id);
        }
    }


