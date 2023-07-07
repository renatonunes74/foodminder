package com.foodminder.FoodMinder.controllers;

import com.foodminder.FoodMinder.domain.product.Product;
import com.foodminder.FoodMinder.domain.product.ProductRepository;
import com.foodminder.FoodMinder.domain.product.RequestProduct;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductRepository repository;

	@GetMapping
	public ResponseEntity getAllProducts() {
		var allProducts = repository.findAll();
		return ResponseEntity.ok(allProducts);
	}

	@PostMapping
	public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data) {
		Product newProduct = new Product(data);
		repository.save(newProduct);
		return ResponseEntity.ok().build();
	}

	@PutMapping
	@Transactional
	public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct data){
		Optional<Product> optionalProduct = repository.findById(data.id());
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			product.setName(data.name());
			product.setPrice_in_cents(data.price_in_cents());
			return ResponseEntity.ok(product);
		} else {
			throw new EntityNotFoundException();
		}
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deleteProduct(@PathVariable String id){
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			throw new EntityNotFoundException();
		}
	}
}
