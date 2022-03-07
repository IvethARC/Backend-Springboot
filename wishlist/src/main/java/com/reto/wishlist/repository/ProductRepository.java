package com.reto.wishlist.repository;

import org.springframework.stereotype.Repository;
import com.reto.wishlist.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository <Product,Long> {


}
