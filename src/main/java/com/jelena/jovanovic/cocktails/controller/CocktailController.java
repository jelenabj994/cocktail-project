package com.jelena.jovanovic.cocktails.controller;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jelena.jovanovic.cocktails.dtos.CocktailDetailedResponse;
import com.jelena.jovanovic.cocktails.dtos.CocktailShortResponse;
import com.jelena.jovanovic.cocktails.services.CocktailService;

@RestController
@RequestMapping("/cocktails")
public class CocktailController {

	private CocktailService cocktailService;

	public CocktailController(CocktailService cocktailService) {
		this.cocktailService = cocktailService;
	}

	@GetMapping
	public List<CocktailShortResponse> findAll() {
		return this.cocktailService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<CocktailDetailedResponse> findById(@PathVariable Long id) throws NotFoundException {
		return ResponseEntity.ok(this.cocktailService.findById(id));
	}

}