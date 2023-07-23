package com.jelena.jovanovic.cocktails.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.jelena.jovanovic.cocktails.dtos.CocktailDetailedResponse;
import com.jelena.jovanovic.cocktails.dtos.CocktailShortResponse;
import com.jelena.jovanovic.cocktails.entity.Cocktail;
import com.jelena.jovanovic.cocktails.entity.CocktailIngredient;
import com.jelena.jovanovic.cocktails.repository.CocktailRepository;

@Service
public class CocktailServiceImpl implements CocktailService {

	private final CocktailRepository repo;

	public CocktailServiceImpl(CocktailRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public void saveAll(List<Cocktail> cocktails) {
		this.repo.saveAll(cocktails);

	}

	@Override
	public List<CocktailShortResponse> findAll() {
		return this.repo.findAll().stream().map(this::mapCocktailToCocktailShortResponse).toList();
	}

	@Override
	public CocktailDetailedResponse findById(long id) throws NotFoundException {
		var cocktail = this.repo.findById(id).orElseThrow(() -> new NotFoundException());

		return this.mapCocktailToCocktailDetailedResponse(cocktail);
	}

	private CocktailShortResponse mapCocktailToCocktailShortResponse(Cocktail cocktail) {

		return new CocktailShortResponse(cocktail.getId(), cocktail.getName(), cocktail.getType());
	}

	private CocktailDetailedResponse mapCocktailToCocktailDetailedResponse(Cocktail cocktail) {
		var ingredientsGet = cocktail.getIngredients();
		Map<String, String> ingredients = new LinkedHashMap<>();
		if (ingredientsGet != null) {
			ingredients = mapIngredients(ingredientsGet);
		}

		return new CocktailDetailedResponse(cocktail.getId(), cocktail.getName(), cocktail.getInstructions(),
				cocktail.getType(), cocktail.getImage(), ingredients, cocktail.getModifiedDate());
	}

	public Map<String, String> mapIngredients(List<CocktailIngredient> cocktailIngredients) {
		AtomicInteger counter = new AtomicInteger(0);

		Function<CocktailIngredient, String> getIngredientName = cocktailIngredient -> {
			int num = counter.incrementAndGet();
			return num + ". " + cocktailIngredient.getIngredient().getName();
		};
		Function<CocktailIngredient, String> getMeasure = cocktailIngredient -> cocktailIngredient.getMeasure();
		return cocktailIngredients.stream().collect(Collectors.toMap(getIngredientName, getMeasure,
				(existingValue, newValue) -> newValue, LinkedHashMap::new));
	}

}
