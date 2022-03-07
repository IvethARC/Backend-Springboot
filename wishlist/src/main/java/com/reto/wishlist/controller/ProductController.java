package com.reto.wishlist.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reto.wishlist.entity.Product;
import com.reto.wishlist.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Product product){
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
		
	}
	
	@GetMapping("/{id}")	
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<Product> oProduct = productService.findById(id);
		
		if (!oProduct.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oProduct);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Product productDetails, @PathVariable Long id){
		Optional<Product> product = productService.findById(id);
		
		if (!product.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		product.get().setName(productDetails.getName());
		product.get().setPrice(productDetails.getPrice());
		product.get().setAmount(productDetails.getAmount());
		product.get().setInformation(productDetails.getInformation());
		product.get().setImage_url(productDetails.getImage_url());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product.get()));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		if (!productService.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		productService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public List<Product> readAll(){
		
		List<Product> product = StreamSupport
				.stream(productService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return product;
		
	}
	
	
	
	
	
	
	
}
