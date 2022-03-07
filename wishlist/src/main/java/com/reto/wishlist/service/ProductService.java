package com.reto.wishlist.service;

import java.util.Optional;

import com.reto.wishlist.entity.Product;

public interface ProductService {
	
	public Iterable<Product> findAll();
	public Optional<Product> findById(Long id);
	public Product save(Product product);
	public void deleteById(Long id);
	
	

}
