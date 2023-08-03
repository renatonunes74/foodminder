package com.foodminder.FoodMinder.controllers;

import com.foodminder.FoodMinder.domain.Planejamento.Planejamento;
import com.foodminder.FoodMinder.domain.Planejamento.PlanejamentoRepository;
import com.foodminder.FoodMinder.domain.Planejamento.RequestPlanejamento;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Planejamento")
public class PlanejamentoController {
	@Autowired
	private PlanejamentoRepository repository;

	@GetMapping
	public ResponseEntity getAllPlanejamentos() {
		var allPlanejamentos = repository.findAll();
		return ResponseEntity.ok(allPlanejamentos);
	}

	@PostMapping
	public ResponseEntity registerPlanejamento(@RequestBody @Valid RequestPlanejamento data) {
		Planejamento newPlanejamento = new Planejamento(data);
		repository.save(newPlanejamento);
		return ResponseEntity.ok().build();
	}

	@PutMapping
	@Transactional
	public ResponseEntity updatePlanejamento(@RequestBody @Valid RequestPlanejamento data){
		Optional<Planejamento> optionalPlanejamento = repository.findById(data.id());
		if (optionalPlanejamento.isPresent()) {
			Planejamento Planejamento = optionalPlanejamento.get();
			Planejamento.setName(data.name());
			Planejamento.setPrice_in_cents(data.price_in_cents());
			return ResponseEntity.ok(Planejamento);
		} else {
			throw new EntityNotFoundException();
		}
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deletePlanejamento(@PathVariable String id){
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			throw new EntityNotFoundException();
		}
	}
}
