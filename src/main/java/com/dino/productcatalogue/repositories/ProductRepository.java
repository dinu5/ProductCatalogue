package com.dino.productcatalogue.repositories;


import com.dino.productcatalogue.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    public Product findProductById(Long id);

    public Product save(Product product);

    public List<Product> findAllByOrderByIdDesc();

    @Query("SELECT p.title from Product p where p.id=?1") // Here ?1 means it will take first param of the method signature
    public String findProductNameByProductId(Long id);

    @Query("SELECT c.name from Category c JOIN Product p on p.category.id=c.id where p.id=:productId")
    public String findCategoryNameByProductId(Long productId);

}
