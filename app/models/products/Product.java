package models.products;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity


public class Product extends Model {
    
        // Properties
        @Id
        private Long id;
        @Constraints.Required
        private String name;
        @ManyToMany(cascade = CascadeType.ALL,mappedBy="products")
        public List<Category> categories;
        @Constraints.Required
        private String description; 
        @Constraints.Required
        private int stock; 
        @Constraints.Required
        private double price;
        @Constraints.Required
        private String pegi;
       

        public static final Finder<Long, Product> find = new Finder<>(Product.class);
        // List of category ids - this will be bound to checkboxes in the view form
        private List<Long> catSelect = new ArrayList<Long>();

        public static final List<Product> findAll() { 
            
            return Product.find.all();
}
    // Find all Products in the database
    // Filter product name 
    public static List<Product> findAll(String filter) {
        return Product.find.query().where()
                        // name like filter value (surrounded by wildcards)
                        .ilike("name", "%" + filter + "%")
                        .orderBy("id asc")
                        .findList();
    }
    
    // Find all Products for a category
    // Filter product name 
    public static List<Product> findFilter(Long catID, String filter) {
        return Product.find.query().where()
                        // Only include products from the matching cat ID
                        // In this case search the ManyToMany relation
                        .eq("categories.id", catID)
                        // name like filter value (surrounded by wildcards)
                        .ilike("name", "%" + filter + "%")
                        .orderBy("id asc")
                        .findList();
    }
        // Default Constructor
        public Product() {
        }
    
        // Constructor to initialise object
        public Product(Long id, String name, String description, int stock, double price, String pegi) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.stock = stock;
            this.price = price;
            this.pegi=pegi;
        }
    
        // Accessor methods
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getPegi() {
            return this.pegi;
        }
        public void setPegi(String pegi) {
            this.pegi=pegi;
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
        public int getStock() { 
            return stock; 
        } 
        public void setStock(int stock) { 
            this.stock = stock; 
        } 
        public double getPrice() { 
            return price; 
        } 
        public void setPrice(double price) { 
            this.price = price; 
        } 
        public List<Long> getCatSelect(){
            return catSelect;
        }
        public void setCatSelect(List<Long> catSelect){
            this.catSelect = catSelect;
        }

        public static Map<String, String> options() {
            LinkedHashMap<String, String> options = new LinkedHashMap();
    
            for (Product p: Product.findAll()) {
                options.put(p.getId().toString(), p.getName());
            }
            
            return options;
        }
    }
    