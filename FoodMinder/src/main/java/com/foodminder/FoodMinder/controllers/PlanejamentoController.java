package com.foodminder.FoodMinder.controllers;

import com.foodminder.FoodMinder.domain.planejamento.RequestPlanejamento;;
import com.foodminder.FoodMinder.domain.rabbitmq.constantes.RabbitMQConstantes;
import com.foodminder.FoodMinder.services.PlanejamentoService;
import com.foodminder.FoodMinder.services.RabbitMQService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planejamento")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlanejamentoController	{
	@Autowired
	private PlanejamentoService planejamentoService;
	@GetMapping
	public ResponseEntity getAllPlanejamento() {
		return planejamentoService.obterTodosPlanejamento();
	}
	@GetMapping("/{id}")
	public ResponseEntity getPlanejamentoById(@PathVariable Integer id) {
		return planejamentoService.obterPlanejamentoPorId(id);
	}
	@PostMapping
	public ResponseEntity registerPlanejamento(@Valid @RequestBody RequestPlanejamento requestPlanejamento) {
		return planejamentoService.registrarPlanejamento(requestPlanejamento);
	}
	 @DeleteMapping("/{id}")
	 @Transactional
	 public ResponseEntity deletePlanejamento(@PathVariable Integer id) {
		return planejamentoService.deletarPlanejamentoPorId(id);
	 }
}
