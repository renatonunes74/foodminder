package com.foodminder.FoodMinder.controllers;

import com.foodminder.FoodMinder.domain.planejamento.PlanejamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planejamento")
public class PlanejamentoController {
	@Autowired
	private PlanejamentoRepository repository;

	@GetMapping
	public ResponseEntity getAllPlanejamento() {
		return ResponseEntity.ok(repository.findAll());
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
