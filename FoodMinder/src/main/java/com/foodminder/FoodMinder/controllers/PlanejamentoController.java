package com.foodminder.FoodMinder.controllers;

import com.foodminder.FoodMinder.domain.planejamento.Planejamento;
import com.foodminder.FoodMinder.domain.planejamento.PlanejamentoRepository;
import com.foodminder.FoodMinder.domain.planejamento.RequestPlanejamento;
import com.foodminder.FoodMinder.domain.refeicao.Refeicao;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicao;
import com.foodminder.FoodMinder.domain.tipoRefeicao.TipoRefeicaoRepository;
import com.foodminder.FoodMinder.domain.refeicao.RefeicaoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/planejamento")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlanejamentoController {
	@Autowired
	private PlanejamentoRepository repository;

	@Autowired
	private RefeicaoRepository refeicaoRepository;

	@Autowired
	private TipoRefeicaoRepository tipoRefeicaoRepository;


	@GetMapping
	public ResponseEntity getAllPlanejamento() {
		return ResponseEntity.ok(repository.findAll());
	}
	@PostMapping
	public ResponseEntity registerPlanejamento(@Valid @RequestBody RequestPlanejamento requestData) {
		Refeicao refeicao = refeicaoRepository.findById(requestData.refeicao().getId()).orElseThrow(EntityNotFoundException::new);
		TipoRefeicao tipoRefeicao = tipoRefeicaoRepository.findById(requestData.tipoRefeicao().getId()).orElseThrow(EntityNotFoundException::new);
		Planejamento newPlanejamento = new Planejamento();
		newPlanejamento.setData(requestData.data());
		newPlanejamento.setRefeicao(refeicao);
		newPlanejamento.setTipoRefeicao(tipoRefeicao);

		Planejamento savedPlanejamento = repository.save(newPlanejamento);
		return ResponseEntity.ok(savedPlanejamento);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getPlanejamentoById(@PathVariable Integer id) {
		Optional<Planejamento> planejamentoOptional = repository.findById(id);
		if (planejamentoOptional.isPresent()) {
			Planejamento planejamento = planejamentoOptional.get();
			return ResponseEntity.ok(planejamento);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	 @DeleteMapping("/{id}")
	 @Transactional
	 public ResponseEntity deletePlanejamento(@PathVariable Integer id){
	 	if (repository.existsById(id)) {
	 		repository.deleteById(id);
	 		return ResponseEntity.noContent().build();
	 	} else {
	 		throw new EntityNotFoundException();
	 	}
	 }
}
