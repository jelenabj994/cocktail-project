package com.jelena.jovanovic.cocktails.services;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.jelena.jovanovic.cocktails.dtos.CocktailDetailedResponse;
import com.jelena.jovanovic.cocktails.dtos.CocktailShortResponse;
import com.jelena.jovanovic.cocktails.entity.Cocktail;

public interface CocktailService {

	void saveAll(List<Cocktail> cocktails);

	List<CocktailShortResponse> findAll();

	CocktailDetailedResponse findById(long id) throws NotFoundException;
}
