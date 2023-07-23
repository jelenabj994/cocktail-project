package com.jelena.jovanovic.cocktails.bootstrap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jelena.jovanovic.cocktails.entity.Cocktail;
import com.jelena.jovanovic.cocktails.entity.CocktailIngredient;
import com.jelena.jovanovic.cocktails.entity.Image;
import com.jelena.jovanovic.cocktails.entity.Ingredient;
import com.jelena.jovanovic.cocktails.entity.enums.DrinkType;
import com.jelena.jovanovic.cocktails.entity.enums.ImageType;
import com.jelena.jovanovic.cocktails.repository.CocktailRepository;
import com.jelena.jovanovic.cocktails.repository.IngredientRepository;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CocktailBootstrap implements CommandLineRunner {

	private final RestTemplate restTemplate;
	private final List<String> cocktailNameList;
	private final ObjectMapper objectMapper;
	private final CocktailRepository coktailRepo;
	private final IngredientRepository ingredientRepo;
	private final Map<String, Ingredient> mapIngredients = new HashMap<>();

	public CocktailBootstrap(RestTemplate restTemplate, List<String> cocktailNameList, ObjectMapper objectMapper,
			CocktailRepository coktailRepo, IngredientRepository ingredientRepo) {
		super();
		this.restTemplate = restTemplate;
		this.cocktailNameList = cocktailNameList;
		this.objectMapper = objectMapper;
		this.coktailRepo = coktailRepo;
		this.ingredientRepo = ingredientRepo;
	}

	@Override
	public void run(String... args) throws Exception {
		for (var cocktailName : this.cocktailNameList) {
			String url = String.format("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=%s", cocktailName);

			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

			if (response.getStatusCode().is2xxSuccessful()) {

				var cocktails = objectMapper.readValue(response.getBody(), new TypeReference<Drinks>() {
				});

				saveCocktails(cocktails);
			} else {

				System.out.println("Failed to get cocktails from The Cocktail DB.");
			}
		}
	}

	private void saveCocktails(Drinks drinks) {
		var cocktails = drinks.getDrinks().stream().map(this::mapCoctail3PartyToCocktail).toList();
		this.coktailRepo.saveAll(cocktails);
	}

	private Cocktail mapCoctail3PartyToCocktail(Cocktail3Party cocktail3p) {

		var cocktail = new Cocktail(cocktail3p.getStrDrink(), cocktail3p.getStrInstructions(),
				cocktail3p.getDateModified(),
				cocktail3p.getStrAlcoholic().equals("Alcoholic") ? DrinkType.ALCOHOLIC : DrinkType.NONALCOHOLIC,
				setImage(cocktail3p));

		List<CocktailIngredient> cocktailIngredients = IntStream.range(1, 16).mapToObj(i -> {
			var name = cocktail3p.getIngredientNum(i);
			return Optional.ofNullable(name)
					.map(n -> new IngredientData(new Ingredient(n), cocktail3p.getMeasureNum(i))).orElse(null);
		}).filter(data -> data != null).map(data -> {
			var ingredient = mapIngredients.computeIfAbsent(data.getIngredient().getName(), key ->

			ingredientRepo.save(data.getIngredient()));
			data.setIngredient(ingredient);
			return data;
		}).map(data -> new CocktailIngredient(cocktail, data.getIngredient(), data.getMeasure()))
				.collect(Collectors.toList());
		cocktail.setIngredients(cocktailIngredients);
		return cocktail;
	}

	private Image setImage(Cocktail3Party cocktail3p) {
		var image = new Image();
		image.setType(cocktail3p.getStrDrinkThumb() == null ? ImageType.ORIGINAL : ImageType.THUMBNAIL);
		image.setUrl(
				cocktail3p.getStrDrinkThumb() == null ? cocktail3p.getStrImageSource() : cocktail3p.getStrDrinkThumb());
		return image;
	}

	private static class IngredientData {
		private Ingredient ingredient;
		private String measure;

		public IngredientData(Ingredient ingredient, String measure) {
			this.ingredient = ingredient;
			this.measure = measure != null ? measure : "";
		}

		public Ingredient getIngredient() {
			return ingredient;
		}

		public void setIngredient(Ingredient ingredient) {
			this.ingredient = ingredient;
		}

		public String getMeasure() {
			return measure;
		}
	}
}
