import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ProductController {


        private final ProductRepository productRepository;
        private final CategoryRepository categoryRepository;
        private final ReviewRepository reviewRepository;

        public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository,
                                 ReviewRepository reviewRepository) {
            this.productRepository = productRepository;
            this.categoryRepository = categoryRepository;
            this.reviewRepository = reviewRepository;
        }

        @GetMapping("/product/{id}")
        public String product(@PathVariable Long id, Model model) {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            model.addAttribute("product", product);
            model.addAttribute("reviews", reviewRepository.findByProductIdOrderByCreatedAtDesc(id));
            return "product";
        }
    }


