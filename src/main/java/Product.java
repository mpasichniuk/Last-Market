import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Product {
@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        private String description;

        private BigDecimal price;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;

        @ManyToOne(fetch = FetchType.LAZY)
        private String category;

        @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
        private List<Review> reviews;

    public Product() {
    }

    public Product(Long id, String name, String description, BigDecimal price, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice(){
        return price;
    }
    public void setPrice( BigDecimal price){
        this.price = price;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
 public LocalDateTime getUpdatedAt(){
        return updatedAt;
 }

 public void setUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt = updatedAt;
 }


    public void setCategory(String category) {
        this.category = category;
    }
}
