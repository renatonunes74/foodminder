package com.foodminder.FoodMinder.controllers;

import com.foodminder.FoodMinder.domain.planejamento.Planejamento;
import com.foodminder.FoodMinder.domain.planejamento.PlanejamentoRepository;
import com.foodminder.FoodMinder.domain.planejamento.RequestPlanejamento;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicaoRepository;
import com.foodminder.FoodMinder.domain.refeicao.RefeicaoRepository;
import com.foodminder.FoodMinder.services.PlanejamentoService;
import com.foodminder.FoodMinder.exceptions.RecursoNaoEncontrado;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planejamento")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlanejamentoController {
	@Autowired
	private PlanejamentoService planejamentoService;
	@Autowired
	private PlanejamentoRepository repository;

	@Autowired
	private RefeicaoRepository refeicaoRepository;

	@Autowired
	private TipoRefeicaoRepository tipoRefeicaoRepository;

	@GetMapping
	public ResponseEntity getAllPlanejamento() {
		return ResponseEntity.ok(planejamentoService.obterTodosPlanejamentos());
	}
	@PostMapping
	public ResponseEntity registerPlanejamento(@Valid @RequestBody RequestPlanejamento requestData) {
		Planejamento savedPlanejamento = planejamentoService.registrarPlanejamento(requestData);
		return ResponseEntity.ok(savedPlanejamento);
	}
	@GetMapping("/{id}")
	public ResponseEntity getPlanejamentoById(@PathVariable Integer id) {
		try {
			Planejamento planejamento = planejamentoService.obterPlanejamentoPorId(id);
			return ResponseEntity.ok(planejamento);
		} catch (RecursoNaoEncontrado e) {
			return ResponseEntity.notFound().build();
		}
	}
	 @DeleteMapping("/{id}")
	 @Transactional
	 public ResponseEntity deletePlanejamento(@PathVariable Integer id) {
		 try {
			 Planejamento planejamento = planejamentoService.deletarPlanejamentoPorId(id);
			 return ResponseEntity.noContent().build();
		 } catch (RecursoNaoEncontrado e) {
			 return ResponseEntity.notFound().build();
		 }
	 }
}
